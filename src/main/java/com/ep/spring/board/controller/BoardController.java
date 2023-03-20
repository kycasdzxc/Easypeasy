package com.ep.spring.board.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ep.spring.board.model.service.BoardService;
import com.ep.spring.board.model.vo.Board;
import com.ep.spring.board.model.vo.BoardCate;
import com.ep.spring.board.model.vo.BoardReply;
import com.ep.spring.common.model.vo.Attachment;
import com.ep.spring.common.model.vo.PageInfo;
import com.ep.spring.common.template.Pagination;
import com.google.gson.Gson;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService bService;
	
	@RequestMapping("list.bo")
	public ModelAndView selectList(@RequestParam(value="cpage", defaultValue="1") int currentPage, ModelAndView mv) {
		
		int listCount = bService.selectListCount();
		
		PageInfo pi = Pagination.getPageInfo(listCount, currentPage, 5, 10);
		ArrayList<Board> list = bService.selectList(pi);
		
		mv.addObject("pi", pi).addObject("list", list).setViewName("board/boardListView"); 
		
		return mv;
	}
	
	@RequestMapping("enrollForm.bo")
	public String enrollForm() {
		return "board/boardEnrollForm";
	}
	
	@RequestMapping("insert.bo")
	public String insertBoard(Board b, Attachment a, MultipartFile upfile, HttpSession session, Model model) {
		
		ArrayList<Attachment> list = bService.selectAtList(a);
		
		int result = bService.insertBoard(b);
		
		if(result > 0) {
			session.setAttribute("alertMsg", "게시글 등록 성공");
			return "redirect:list.bo";	
		}else {
			model.addAttribute("errorMsg", "게시글 등록 실패");
			return "common/errorPage";
		}
	}
	
	@RequestMapping("detailForm.bo")
	public ModelAndView selectBoard(int no, ModelAndView mv) {
		
		int result = bService.increaseCount(no);
		
		if(result > 0) {
			Board b = bService.selectBoard(no);
			
			mv.addObject("b", b).setViewName("board/boardDetailForm");
		}else {
			mv.addObject("errorMsg", "조회수증가실패").setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping("delete.bo")
	public String deleteBoard(int no, HttpSession session, Model model) {
		
		int result = bService.deleteBoard(no);
		
		if(result > 0) {
			
			session.setAttribute("alertMsg", "성공적으로 게시글이 삭제되었습니다."); 
			return "redirect:list.bo";
		}else {
			model.addAttribute("errorMsg", "게시글 삭제 실패");
			return "common/errorPage";
		}
		
	}
	
	@RequestMapping("updateForm.bo")
	public String updateForm(int no, Model model) {
		
		model.addAttribute("b", bService.selectBoard(no));
		
		return "board/boardUpdateForm";
	}
	
	@RequestMapping("update.bo")
	public String updateBoard(Board b, HttpSession session, Model model) {
		
		int result = bService.updateBoard(b);
		
		if(result > 0) {
			session.setAttribute("alertMsg", "성공적으로 게시글이 수정되었습니다."); 
			return "redirect:detail.bo?no=" + b.getBoardNo();
		}else {
			model.addAttribute("errorMsg", "게시글 수정 실패");
			return "common/errorPage";
		}
		
	}
	
	// 댓글 ajax
	@ResponseBody
	@RequestMapping(value="rlist.bo", produces="application/json; charset=UTF-8")
	public String ajaxSelectReplyList(int no) { // 게시글번호 전달, 똑같은 이름의 매개변수 작성
			
		ArrayList<BoardReply> list = bService.selectReplyList(no);
		
		return new Gson().toJson(list);
	}
		
	@ResponseBody
	@RequestMapping("rinsert.bo")
	public String ajaxInsertReply(BoardReply r) {
			
		int result = bService.insertReply(r);
			
		return result > 0 ? "success" : "fail";
			
	}
	
	
	@RequestMapping("adminSettings.bo")
	public ModelAndView selectSettings(BoardCate bc, ModelAndView mv) {
		
		ArrayList<BoardCate> bcList = bService.selectSettings(bc);
		
		mv.addObject("bcList", bcList).setViewName("board/boardAdminSettings");
		
		return mv;
	}
	
	
	@RequestMapping("insertSettings.bo")
	public String insertCate(BoardCate bc,HttpSession session, Model model) {
		
		int result = bService.insertCate(bc);
		
		if(result > 0) {
			session.setAttribute("alertMsg", "성공적으로 등록되었습니다.");
			return "redirect:adminSettings.bo";
		}else {
			model.addAttribute("errorMsg", "게시판등록실패");
			return "common/errorPage";
		}
		
	}
	
	
}
