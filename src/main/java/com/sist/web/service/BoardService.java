package com.sist.web.service;

import java.util.*;

import com.sist.web.vo.*;

public interface BoardService {
	public List<BoardVO> boardListData(int start);
	public int boardTotalPage();
	public void boardInsert(BoardVO vo);
	public BoardVO boardDetailData(int no);
}
