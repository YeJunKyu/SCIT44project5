package com.scit.lms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    int testid; // 시험 id값
    long qid; // 문제 고유번호
    String contents; // 문제
    int points; // 배점
    int type; // 문제 유형 (1. 객관식, 2. 객관식 복수정답, 3. 단답형, 4. 서술형, 5. 파일 업로드)
    String originalfile; // 원본 첨부파일명
    String savedfile; // 저장된 첨부파일명
//    String answer; // 정답
    List<Option> options; // 옵션 및 정답
}
