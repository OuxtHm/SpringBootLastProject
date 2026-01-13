package com.sist.web.service;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.web.vo.BoardReplyVO;
import com.sist.web.vo.BoardVO;
public interface BoardReplyService {
	   public List<BoardReplyVO> boardReplyListData(int bno);
	   public int boardReplyCount(int bno);
	   public void boardReplyInsert(BoardReplyVO vo);
	   public void boardReplyDelete(int no);
	   public void boardReplyUpdate(BoardReplyVO vo);
}