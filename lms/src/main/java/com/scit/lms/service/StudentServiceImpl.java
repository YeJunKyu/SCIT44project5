package com.scit.lms.service;

import com.scit.lms.dao.StudentDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Slf4j
@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentDAO dao;

}
