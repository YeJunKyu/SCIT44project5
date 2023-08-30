package com.scit.lms.service;

import com.scit.lms.dao.BoardDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@Slf4j
public class BoardServiceImple implements BoardService {

    @Autowired
    BoardDAO dao;

    @Override
    public int selectCount() {
        return dao.selectCount();
    }
}
