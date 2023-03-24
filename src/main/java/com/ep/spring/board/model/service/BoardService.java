package com.ep.spring.board.model.service;

import java.util.ArrayList;

import com.ep.spring.board.model.vo.Board;
import com.ep.spring.board.model.vo.BoardCate;
import com.ep.spring.board.model.vo.BoardReply;
import com.ep.spring.common.model.vo.Attachment;
import com.ep.spring.common.model.vo.PageInfo;
import com.ep.spring.mail.model.vo.Mail;

public interface BoardService {

	// 1. 게시판 리스트 조회 서비스 (페이징)
	int selectListCount();
	ArrayList<Board> selectList(PageInfo pi);
		
	// 2. 게시글 작성하기 서비스
	int insertBoard(Board b, ArrayList<Attachment> atList);
	
	// 3. 게시글 상세조회용 서비스
	int increaseCount(int no);
	Board selectBoard(Board b);
	ArrayList<Attachment> selectAttachmentList(Board b);
		
	// 4. 게시글 삭제용 서비스
	int deleteBoard(int boardNo);
		
	// 5. 게시글 수정용 서비스
	int updateBoard(Board b);
		
	// 6. 댓글 리스트 조회용 서비스 (ajax)
	ArrayList<BoardReply> selectReplyList(int boardNo);
		
	// 7. 댓글 작성용 서비스 (ajax)
	int insertReply(BoardReply r);
		
	// 8. 게시글 top5 조회용 서비스 (ajax)
	ArrayList<Board> selectTopList();	
	
	// 9. 게시판 설정 서비스
	int selectSettings(BoardCate bc);
	int insertCate(BoardCate bc);
	
	// 10. 게시판 설정 수정 서비스
	int updateSettings(BoardCate bc);
	
	
}
