package com.ep.spring.mail.model.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.ep.spring.mail.model.vo.MailTag;
import com.ep.spring.mail.model.vo.ReceiveMail;

@Repository
public class MailDao {

	public ArrayList<ReceiveMail> selectReceiveMailList(String email, SqlSessionTemplate sqlSession) {
		return (ArrayList)sqlSession.selectList("mailMapper.selectReceiceMailList", email);
	}

	public ArrayList<MailTag> selectTagList(int empNo, SqlSessionTemplate sqlSession) {
		return (ArrayList)sqlSession.selectList("mailMapper.selectTagList", empNo);
	}
	
	public int insertTag(MailTag t, SqlSessionTemplate sqlSession) {
		return sqlSession.insert("mailMapper.insertTag", t);
	}
}
