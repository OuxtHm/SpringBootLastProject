package com.sist.web.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.*;
@Mapper
@Repository
public interface CommonsReplyMapper {
  @Select("SELECT no,cno,id,name,msg,sex,"
		 +"TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday,"
		 +"group_tab,group_id "
		 +"FROM commonsReply_2 "
		 +"WHERE cno=#{cno} "
		 +"ORDER BY group_id DESC,group_step ASC "
		 +"OFFSET #{start} ROWS FETCH NEXT 10 ROWS ONLY")
  public List<CommonsReplyVO> commonsReplyListData(
     @Param("cno") Integer cno,
     @Param("start") Integer start
  );
  @Select("SELECT CEIL(COUNT(*)/10.0) "
		 +"FROM commonsReply_2 "
		 +"WHERE cno=#{cno}")
  public int commonsReplyTotalPage(int cno);
  
  @Insert("INSERT INTO commonsReply_2(no,cno,id,name,sex,msg,group_id) "
		 +"VALUES(cs2_no_seq.nextval,#{cno},#{id},#{name},"
		 +"#{sex},#{msg},(SELECT NVL(MAX(group_id)+1,1) FROM commonsReply_2))")
  public void commonsReplyInsert(CommonsReplyVO vo);
  
  @Select("SELECT root,depth,group_id,group_step FROM commonsReply_2 "
		 +"WHERE no=#{no}")
  public CommonsReplyVO commonsInfoData(int no);
  
  @Update("UPDATE commonsReply_2 SET "
		 +"msg=#{msg} "
		 +"WHERE no=#{no}")
  public void commonsMsgUpdate(CommonsReplyVO vo);
  
  @Delete("DELETE FROM commonsReply_2 WHERE group_id=#{group_id}")
  public void commonsAllDelete(int group_id);
  
  @Delete("DELETE FROM commonsReply_2 WHERE no=#{no}")
  public void commonsMyDelete(int no);
  
  @Update("UPDATE commonsReply_2 SET "
		 +"depth=depth-1 "
		 +"WHERE no=#{no}")
  public void commonsDepthDecrement(int no);
  
  @Select("SELECT id, group_id,group_step,group_tab "
		 +"FROM commonsReply_2 "
		 +"WHERE no=#{no}")
  public CommonsReplyVO commonsReplyParentData(int no);
  
  @Update("UPDATE commonsReply_2 SET "
		 +"group_step=group_step+1 "
		 +"WHERE group_id=#{group_id} AND group_step>#{group_step}")
  public void commonsGroupStepIncrement(CommonsReplyVO vo);
  
  @Insert("INSERT INTO commonsReply_2 VALUES("
		 +"cs2_no_seq.nextval,#{cno},#{id},#{name},"
		 +"#{sex},#{msg},#{group_id},"
		 +"#{group_step},#{group_tab},#{root},0,SYSDATE)"
		 )
  public void commonsReplyReplyInsert(CommonsReplyVO vo);
  
  @Update("UPDATE commonsReply_2 SET "
			 +"depth=depth+1 "
			 +"WHERE no=#{no}")
  public void commonsDepthIncrement(int no);
}