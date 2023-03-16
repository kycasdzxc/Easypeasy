package com.ep.spring.commute.model.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ep.spring.approval.model.vo.VacationForm;
import com.ep.spring.commute.model.dao.CommuteDao;
import com.ep.spring.commute.model.vo.Commute;
import com.ep.spring.commute.model.vo.VacationRecode;
import com.ep.spring.login.model.vo.Employee;


@Service
public class CommuteServiceImpl implements CommuteService{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	@Autowired
	private CommuteDao cDao;
	
	//출근시간 등록
	@Override
	public int inTime(Commute c) {
		
		return cDao.inTime(sqlSession, c);
	}

	//근태관리 메인페이지
	@Override
	public Commute commuteMainPage(int empNo) {
		
		return cDao.commuteMainPage(sqlSession, empNo);
	}
	
	//상태변경
	@Override
	public int updateStatus(Employee e) {
		
		return cDao.updateStatus(sqlSession, e);
	}

	//퇴근시간 등록
	@Override
	public int outTime(Commute c) {
		
		return cDao.outTime(sqlSession, c);
	}

	//12시마다 insert 스케쥴링
	@Override
	public int insertCommuteDay() {
		
		return cDao.insertCommuteDay(sqlSession);
	}

	//휴가 기록 조회
	@Override
	public ArrayList<VacationRecode> selectVacMain(int empNo) {
		
		return cDao.selectVacMain(sqlSession, empNo);
	}

	//휴가 신청 기록 조회
	@Override
	public ArrayList<VacationForm> selectVacForm(int empNo) {
		
		return cDao.selectVacForm(sqlSession, empNo);
	}
	
	


}
