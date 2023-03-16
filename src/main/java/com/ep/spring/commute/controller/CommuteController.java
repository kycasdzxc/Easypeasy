package com.ep.spring.commute.controller;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ep.spring.approval.model.vo.VacationForm;
import com.ep.spring.common.model.vo.AlertMsg;
import com.ep.spring.commute.model.service.CommuteService;
import com.ep.spring.commute.model.vo.Commute;
import com.ep.spring.commute.model.vo.VacationRecode;
import com.ep.spring.login.controller.EmployeeController;
import com.ep.spring.login.model.service.EmployeeService;
import com.ep.spring.login.model.service.EmployeeServiceImpl;
import com.ep.spring.login.model.vo.Employee;


@Controller
public class CommuteController {
	
	@Autowired
	private CommuteService cService;
	
	@Autowired
	private EmployeeService eService;
	
		//근태관리 메인페이지(일반사용자)
		@RequestMapping("commute.ep")
		public ModelAndView commuteMainForm(HttpSession session, ModelAndView mv) {
			int empNo = ((Employee)session.getAttribute("loginUser")).getEmpNo();
			//System.out.println(empNo);
			
			Commute c = cService.commuteMainPage(empNo);
			//System.out.println(c);
			 
			if(c != null) {
				mv.addObject("c", c)
				  .setViewName("commute/commuteMain");
				return mv;
			}else {
				AlertMsg msg = new AlertMsg("근태관리","나의 근무정보 조회에 실패했습니다. 다시시도 해주세요.");
				mv.addObject("failMsg", msg)
				  .setViewName("common/main");
				return mv;
			}
			
			  
			 
			
			
		
		}
		
		//출근시간 등록
		@ResponseBody
		@RequestMapping("inTime.co")
		public String inTime(Commute c) {			
			
			int result1 = cService.inTime(c);
			
			//int result2 = cService.updateStatus(c);
			
			return result1 > 0 ? "success" : "fail";
			
		}
		
		//퇴근시간 등록
		@ResponseBody
		@RequestMapping("outTime.co")
		
		public String outTime(Commute c) {			
					
			int result = cService.outTime(c);
					
			return result > 0 ? "success" : "fail";
					
		}
		
		//휴가내역 메인페이지
		@RequestMapping("vac.ep")
		public String selectVacMain(HttpSession session) {
			int empNo = ((Employee)session.getAttribute("loginUser")).getEmpNo();
			
			ArrayList<VacationRecode> list1 = cService.selectVacMain(empNo);
			ArrayList<VacationForm> list2 = cService.selectVacForm(empNo);
			
			session.setAttribute("list1", list1);
			session.setAttribute("list2", list2);
			
			//System.out.println(list1);
			//System.out.println(list2);
			
			return "vacation/vacationMain";
		}
		
		//근무상태 변경
		@ResponseBody
		@RequestMapping("updateStatus")
		public String updateStatus(Employee e, HttpSession session) {
			int result = cService.updateStatus(e);
			e.setEmpId(((Employee)session.getAttribute("loginUser")).getEmpId());
			Employee loginUser = eService.loginEmployee(e);
			
			session.setAttribute("loginUser", loginUser);
			return result > 0 ? "success" : "fail";
		}
		
	
		@RequestMapping("working.ep")
		public String monthlyWorkingStatus() {
			return "commute/monthlyWorkingStatus";
		}

}
