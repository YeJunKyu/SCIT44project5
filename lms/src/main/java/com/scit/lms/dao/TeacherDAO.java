package com.scit.lms.dao;

import com.scit.lms.domain.Board;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherDAO {
    // 공지 글쓰기
    void writeNotice(Board board);
}
