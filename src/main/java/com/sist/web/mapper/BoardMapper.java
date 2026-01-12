package com.sist.web.mapper;

import java.util.*;
import com.sist.web.vo.*;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BoardMapper {
	@Select("SELECT no, name, subject, hit, replycount, TO_CHAR(regdate, 'yyyy-mm-dd') as dbday "
			+ "FROM board_2 "
			+ "ORDER BY no DESC "
			+ "OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY")
	public List<BoardVO> boardListData(int start);
	
	@Select("SELECT CEIL(COUNT(*) / 12.0) FROM board_2 ")
	public int boardTotalPage();
	
	@SelectKey(keyProperty = "no", resultType = int.class, before = true, statement = "SELECT NVL(MAX(no) + 1, 1) as no FROM board_2 ")
	@Insert("INSERT INTO board_2 "
			+ "VALUES( "
			+ "#{no}, "
			+ "#{name}, "
			+ "#{subject}, "
			+ "#{content}, "
			+ "#{pwd}, "
			+ "SYSDATE, "
			+ "0, "
			+ "0)")
	public void boardInsert(BoardVO vo);
	
	@Update("UPDATE board_2 SET "
			+ "hit = hit + 1 "
			+ "WHERE no=#{no}")
	public void boardHitIncrement();
	
	@Select("SELECT no, name, subject, content, hit, TO_CHAR(regdate, 'yyyy-mm-dd hh24:mi:ss') as dbday "
			+ "FROM board_2 "
			+ "WHERE no=#{no}")
	public BoardVO boardDetailData(int no);
}
