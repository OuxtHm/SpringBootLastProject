package com.sist.web.service;

import java.util.*;
import com.sist.web.vo.*;

public interface CommonsReplyService {
	/*
	@Select("SELECT no, cno, id, name, msg, sex, TO_CHAR(regdate, 'yyyy-mm-dd hh24:mi:ss') as dbday, group_tab "
			+ "FROM commonsReply_1 "
			+ "WHERE cno=#{cno}"
			+ "ORDER BY group_id DESC, group_step ASC "
			+ "OFFSET #{start} ROWS FETCH NEXT 10 ROWS ONLY")
	*/
	public List<CommonsReplyVO> commonsReplyListData(int cno, int start);
	
	/*
	@Select("SELECT CEIL(COUNT(*) / 10.0) FROM commonsReply_1 "
			+ "WHERE cno=#{cno}")
	*/
	
	public int commonsReplyTotalPage(int cno);
	
	/*
	@Insert("INSERT INTO commonsReply(no, cno, id, name, sex, msg, group_id) "
			+ "VALUES(cs2_no_seq.nextval, #{cno}, #{id}, #{name}, #{sex}, #{msg}, (SELECT NVL(MAX(group_id)+1,1) FROM commonsReply_2")
	*/
	public void commonsReplyInsert(CommonsReplyVO vo);
	public void commonsDelete(int no);
}
