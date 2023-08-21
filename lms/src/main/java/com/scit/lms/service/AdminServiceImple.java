package com.scit.lms.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.scit.lms.dao.AdminDAO;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Transactional
@Slf4j
@Service
public class AdminServiceImple implements AdminService {

	@Autowired
	AdminDAO dao;

	
}
