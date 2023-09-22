package com.scit.lms.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scit.lms.domain.*;
import com.scit.lms.service.QuestionService;
import com.scit.lms.service.TestService;
import com.scit.lms.util.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Controller
@RequestMapping("test")
@Slf4j
public class TestController {
    @Autowired
    TestService testService;

    @Autowired
    QuestionService questionService;

    // 파일 저장 경로
    @Value("${notice.servlet.multipart.location}")
    String uploadPath;

    // 시험 페이지 이동
    @GetMapping("")
    public String test(Model model) {
        ArrayList<Test> test = testService.testList();
        model.addAttribute("test", test);

        return "boardView/test/test";
    }

    // 시험 문제 생성
    @GetMapping("create")
    public String createTestForm() {
        return "boardView/test/createTest";
    }

    // 시험 문제 등록
    @PostMapping("insertTest")
    public String insertTest(
            @RequestParam("totalpoints") String totalpoints,
            @RequestParam("testname") String testname,
            @RequestParam("testdate") String testdate,
            @RequestParam("testlimit") String testlimit,
            @RequestParam("requestData") String requestDataString,
            @RequestParam Map<String, MultipartFile> fileMap) throws JsonProcessingException {
        // 문제 배열 추출
        ObjectMapper objectMapper = new ObjectMapper();
        TestRequestObject requestObject = objectMapper.readValue(requestDataString, TestRequestObject.class);
        log.debug("파일확인:{}",requestObject);
        // requestDataString 대신에 requestObject에서 데이터 추출

        // 시험 등록

        Test test = new Test();
        test.setTestname(testname);
        test.setTestdate(testdate.replace("T", " "));
        test.setTestlimit(testlimit.replace("T", " "));
        test.setTotalpoints(String.valueOf(Integer.parseInt(totalpoints)));
        log.debug("날짜:{}, 제한시간:{}", testdate, testlimit);


        log.debug("시험:{}", test);

        int testid = testService.insertTest(test);
        log.debug("시험아이디:{}", testid);




        // 문제 배열 등록
        for (Question q: requestObject.getQuestionDataArray()) {


            MultipartFile currentFile = fileMap.get("file[" + q.getPapernum() + "]");
            log.debug("현파일:{}", currentFile);

            Question question = new Question();
            question.setTestid(test.getTestid());
            question.setPapernum(q.getPapernum());
            question.setContents(q.getContents());
            question.setPoints(q.getPoints());
            question.setType(q.getType());
            question.setOptions(q.getOptions());

            // 파일이 있다면 처리합니다.
            if (currentFile != null && !currentFile.isEmpty()) {
                String savedfile = FileService.saveFile(currentFile, uploadPath);
                log.debug("현파일:{}", currentFile.getOriginalFilename());
                question.setOriginalfile(currentFile.getOriginalFilename());
                question.setSavedfile(savedfile);
            } else {
                log.debug("현파일 없음");
            }
            questionService.insertQuestion(question);
            log.debug("문제등록확인:{}", question);

            int opid = questionService.opidUp();
            log.debug("옵션id:{}",opid);

            // 타입이 1, 2, 3인 경우만 option 및 answer 등록
            if (q.getOptions() != null && !q.getOptions().isEmpty() && (q.getType() == 1 || q.getType() == 2 || q.getType() == 3)) {
                List<Option> options = new ArrayList<>();
                for (Option optionData : q.getOptions()) {
                    if (optionData.getValue() != null) {
                        Option option = new Option();
                        opid++;
                        option.setOptionid(opid);

                        option.setQid(question.getQid());
                        option.setValue(optionData.getValue());
                        option.setContent(optionData.getContent());
                        option.setChecked(optionData.getChecked());
                        options.add(option);
                    }
                }
                log.debug("옵션확인:{}",options);
                // 옵션 등록
                questionService.insertOptions(options);
                log.debug("옵션등록확인:{}", options);

                // 답변 갱신
                questionService.updateAnswer();
                log.debug("답변등록확인:{}", "okay");
            }
        }

        return "boardView/test/test";
    }

    // 시험지 문제 전체 조회
    @GetMapping("viewTest")
    public String viewTest(@RequestParam("testid") int testid, Model model) {
        // 시험 정보
        Test test = testService.selectTest(testid);

        // 시험의 문제
        ArrayList<Question> questions = questionService.selectQuestions(testid);

        // 객관식 유형 문제의 보기 가져오기
        ArrayList<Option> allOptions = new ArrayList<>();
        for (Question q : questions) {
            ArrayList<Option> options = questionService.selectOptions(q.getQid());
            allOptions.addAll(options);
        }
//        log.debug("{}", allOptions);

        model.addAttribute("test", test);
        model.addAttribute("questions", questions);
        model.addAttribute("options", allOptions);

        return "boardView/test/viewTest";
    }

    //시험문제 수정폼
    @GetMapping("updateTest")
    public String updateTest(@RequestParam("testid") int testid, Model model){
        // 시험 정보
        Test test = testService.selectTest(testid);

        // 시험의 문제
        ArrayList<Question> questions = questionService.selectQuestions(testid);

        // 객관식 유형 문제의 보기 가져오기
        ArrayList<Option> allOptions = new ArrayList<>();
        for (Question q : questions) {
            ArrayList<Option> options = questionService.selectOptions(q.getQid());
            allOptions.addAll(options);
        }
//        log.debug("{}", allOptions);

        model.addAttribute("test", test);
        model.addAttribute("questions", questions);
        model.addAttribute("options", allOptions);


        return "boardView/test/updateTest";
    }


    //시험문제 수정
    @PostMapping("updateTest")
    public String updateTest(@RequestParam("totalpoints") String totalpoints,
                             @RequestParam("testname") String testname,
                             @RequestParam("testdate") String testdate,
                             @RequestParam("testlimit") String testlimit,
                             @RequestParam("requestData") String requestDataString,
                             @RequestParam int testid,
                             @RequestParam Map<String, MultipartFile> fileMap) throws JsonProcessingException

    {
        // 문제 배열 추출
        ObjectMapper objectMapper = new ObjectMapper();
        TestRequestObject requestObject = objectMapper.readValue(requestDataString, TestRequestObject.class);
        log.debug("파일확인:{}",requestObject);
        // requestDataString 대신에 requestObject에서 데이터 추출

        Test test = new Test();
        test.setTestid(testid);
        test.setTestname(testname);
        test.setTestdate(testdate.replace("T", " "));
        test.setTestlimit(testlimit.replace("T", " "));
        test.setTotalpoints(String.valueOf(Integer.parseInt(totalpoints)));
        log.debug("날짜:{}, 제한시간:{}", testdate, testlimit);


        log.debug("시험:{}", test);
        
        //시험 수정
        int n = testService.updateTest(test);






        // 문제 배열 등록
        for (Question q: requestObject.getQuestionDataArray()) {


            MultipartFile currentFile = fileMap.get("file[" + q.getPapernum() + "]");
            log.debug("현파일:{}", currentFile);
            Question question = new Question();
            question.setTestid(testid);
            question.setPapernum(q.getPapernum());
            question.setContents(q.getContents());
            question.setPoints(q.getPoints());
            question.setType(q.getType());
            question.setOptions(q.getOptions());




            // 파일이 있다면 처리합니다.
            if (currentFile != null && !currentFile.isEmpty()) {
                String savedfile = FileService.saveFile(currentFile, uploadPath);
                log.debug("현파일:{}", currentFile.getOriginalFilename());
                question.setOriginalfile(currentFile.getOriginalFilename());
                question.setSavedfile(savedfile);
            } else {
                log.debug("현파일 없음");
            }

            if (q.isNew()){
                questionService.insertQuestion(question);
                log.debug("문제등록확인:{}", question);
            } else {
                questionService.updateQuestion(question);
                log.debug("문제수정확인:{}", question);
            }

            int opid = questionService.opidUp();

            // 타입이 1, 2, 3인 경우만 option 및 answer 등록
            if (q.getOptions() != null && !q.getOptions().isEmpty() && (q.getType() == 1 || q.getType() == 2 || q.getType() == 3)) {
                List<Option> options = new ArrayList<>();
                for (Option optionData : q.getOptions()) {
                    if (optionData.getValue() != null) {
                        Option option = new Option();
                        opid++;
                        option.setOptionid(opid);
                        option.setQid(question.getQid());
                        option.setValue(optionData.getValue());
                        option.setContent(optionData.getContent());
                        option.setChecked(optionData.getChecked());
                        options.add(option);
                    }
                }
                // 옵션 등록
                if (q.isNew()){


                    questionService.insertOptions(options);
                    log.debug("옵션등록확인:{}", options);
                } else {
                    questionService.updateOptions(options);
                    log.debug("옵션수정확인:{}", options);
                }



                // 답변 갱신
                questionService.updateAnswer();
                log.debug("답변등록확인:{}", "okay");
            }
        }

        return "boardView/test/test";


    }


    //시험문제 삭제
    @GetMapping("deleteTest")
    public String deleteTest(@RequestParam("testid") int testid){
        log.debug("삭제컨트롤러:{}",testid);
        int m = questionService.deleteQuestion(testid);
        log.debug("문제삭제확인:{}",m);
        int n = testService.deleteTest(testid);
        log.debug("시험삭제확인:{}",n);


        return "redirect:/test";
    }


    //시험문제 제출(학생)
    @ResponseBody
    @PostMapping("submitTest")
    public String submitTest(
            @AuthenticationPrincipal UserDetails user,
            @RequestParam("testid") String testid,
            @RequestParam("answerArray") String answerArray,
            @RequestParam Map<String, MultipartFile> fileMap
    ) {

        log.debug("테스트 : {}", testid);
        log.debug("어레이 : {}", answerArray);
        log.debug("파일 : {}", fileMap);

        TestListFromStudent tlfs = new TestListFromStudent();
        tlfs.setMemberid(user.getUsername());
        tlfs.setTestid(Integer.parseInt(testid));
        int asid = testService.submitTest(tlfs);


        ObjectMapper objectMapper = new ObjectMapper();
//        TestAnswerSheet[] answerSheets = objectMapper.readValue(answerArray, TestAnswerSheet[].class);
//        log.debug("파일 확인 : {}", answerSheets);
//        for(TestAnswerSheet answerSheet : answerSheets) {
//
//        }
        try {
            TestAnswerSheet[] answerSheets = objectMapper.readValue(answerArray, TestAnswerSheet[].class);
            for (TestAnswerSheet answerSheet : answerSheets) {
                log.debug("파일 확인: {}", answerSheet);
//                answerSheet.setAsnum();

                MultipartFile currentFile = fileMap.get("file[" + answerSheet.getQid() + "]");

                if (currentFile != null && !currentFile.isEmpty()) {
                    String savedfile = FileService.saveFile(currentFile, uploadPath);
                    log.debug("현파일:{}", currentFile.getOriginalFilename());
                    answerSheet.setOriginalfile(currentFile.getOriginalFilename());
                    answerSheet.setSavedfile(savedfile);
                } else {
                    log.debug("현파일 없음");
                }

                log.debug("앤서 : {}", answerSheet);
                questionService.submitTest(answerSheet);

            }

        } catch (Exception e) {
            log.error("역직렬화 오류 발생: {}", e.getMessage(), e);
        }

        return "";
    }
}