package com.sist.web.service;

/*
 *   VO / Entity / DTO / Record 
 *   |      |       |      | getter => Kotiln (data) 
 *                  getter/setter
 *        테이블 제어 (컬럼과 일치) 
 *          => JPA
 *  불변 (setter가 없다) 
 *  *** VO / DTO 
 */
import java.util.*;

import org.springframework.stereotype.Service;

import com.sist.web.vo.*;

import lombok.RequiredArgsConstructor;

import com.sist.web.mapper.*;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService, BoardReplyService {
	private final BoardMapper mapper;
	private final BoardReplyMapper bMapper;

	@Override
	public List<BoardVO> boardListData(int start) {
		// TODO Auto-generated method stub
		return mapper.boardListData(start);
	}

	@Override
	public int boardTotalPage() {
		// TODO Auto-generated method stub
		return mapper.boardTotalPage();
	}

	@Override
	public void boardInsert(BoardVO vo) {
		// TODO Auto-generated method stub
		mapper.boardInsert(vo);
		// return 생략
	}

	@Override
	public BoardVO boardDetailData(int no) {
		// TODO Auto-generated method stub
		mapper.boardHitIncrement(no);
		return mapper.boardDetailData(no);
	}

	@Override
	public BoardVO boardUpdateData(int no) {
		// TODO Auto-generated method stub
		return mapper.boardDetailData(no);
	}

	@Override
	public String boardUpdate(BoardVO vo) {
		// TODO Auto-generated method stub
		String result = "no";
		String db_pwd = mapper.boardGetPassword(vo.getNo());
		if (db_pwd.equals(vo.getPwd())) {
			result = "yes";
			mapper.boardUpdate(vo);
		}
		return result;
	}

	@Override
	public boolean boardDelete(int no, String pwd) {
		// TODO Auto-generated method stub
		String db_pwd = mapper.boardGetPassword(no);
		if (db_pwd.equals(pwd)) {
			mapper.boardDelete(no);
			return true; // 종료 => void인 경우에는 마지막줄에 자동 추가
		}
		return false;
	}

	/////////// 댓글
	@Override
	public List<BoardReplyVO> boardReplyListData(int bno) {
		return bMapper.boardReplyListData(bno);
	}

	// BI ==> 기타 (DB외 처리)
	@Override
	public int boardReplyCount(int bno) {
		// TODO Auto-generated method stub
		int count = bMapper.boardReplyCount(bno);
		mapper.boardReplyCountUpdate(count, bno);
		return count;
	}

	@Override
	public void boardReplyInsert(BoardReplyVO vo) {
		// TODO Auto-generated method stub
		bMapper.boardReplyInsert(vo);
	}
	@Override
	public void boardReplyDelete(int no) {
		bMapper.boardReplyDelete(no);
	}
	
	@Override
	public void boardReplyUpdate(BoardReplyVO vo) {
		bMapper.boardReplyUpdate(vo);
		
	}
}
