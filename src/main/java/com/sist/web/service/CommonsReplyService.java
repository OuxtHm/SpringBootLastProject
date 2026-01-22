package com.sist.web.service;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.web.vo.*;
public interface CommonsReplyService {
	 /*@Select("SELECT no,cno,id,name,msg,sex,"
			 +"TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday,"
			 +"group_tab "
			 +"FROM commonsReply "
			 +"WHERE cno=#{cno} "
			 +"ORDER BY group_id DESC,group_step ASC "
			 +"OFFSET #{start} ROWS FETCH NEXT 10 ROWS ONLY")*/
	  public List<CommonsReplyVO> commonsReplyListData(
	    int cno,int start
	  );
	  /*
	  @Select("SELECT CEIL(COUNT(*)/10.0) "
			 +"FROM commonsReply "
			 +"WHERE cno=#{cno}")*/
	  public int commonsReplyTotalPage(int cno);
	  
	  /*@Insert("INSERT INTO commonsReply(no,cno,id,name,sex,msg,group_id) "
			 +"VALUES(cs_no_seq.nextval,#{cno},#{id},#{name},"
			 +"#{sex},#{msg},(SELECT NVL(MAX(group_id)+1,1) FROM commonsReply))")*/
	  public void commonsReplyInsert(CommonsReplyVO vo);
	  
	  public void commonsDelete(int no);
	  
	  /*@Update("UPDATE commonsReply SET "
				 +"msg=#{msg} "
				 +"WHERE no=#{no}")*/
	  public void commonsMsgUpdate(CommonsReplyVO vo);
	  
	  //@Delete("DELETE FROM commonsAllReply WHERE group_id=#{group_id}")
	  //public void commonsAllDelete(int group_id);
	  
	  //@Delete("DELETE FROM commonsMyReply WHERE no=#{no}")
	  //public void commonsMyDelete(int no);
	  //public void commonsDelete(int no);
	  public String commonsReplyReplyInsert(CommonsReplyVO vo);
	  
}