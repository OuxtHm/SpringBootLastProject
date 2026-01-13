package com.sist.web.service;

import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sist.web.mapper.CommonsReplyMapper;
import com.sist.web.vo.*;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommonsReplyServiceImpl implements CommonsReplyService{
	private final CommonsReplyMapper mapper;
	
	@Override
	public List<CommonsReplyVO> commonsReplyListData(int cno, int start) {
		return mapper.commonsReplyListData(cno, start);
	}

	@Override
	public int commonsReplyTotalPage(int cno) {
		return mapper.commonsReplyTotalPage(cno);
	}

	@Override
	public void commonsReplyInsert(CommonsReplyVO vo) {
		mapper.commonsReplyInsert(vo);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void commonsDelete(int no) {
		CommonsReplyVO vo=mapper.commonsInfoData(no);
		if(vo.getDepth()==0)
		{
			mapper.commonsDelete(no);
		}
		else
		{
			CommonsReplyVO rvo=new CommonsReplyVO();
			rvo.setNo(no);
			rvo.setMsg("관리자에 의해 삭제된 댓글입니다");
			mapper.commonsMsgUpdate(rvo);
		}
		mapper.commonsDepthDecrement(vo.getRoot());
	}
}
