package com.sist.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.web.vo.*;

@Mapper
@Repository
public interface SeoulMapper {
	/*
	<select id="seoulListData" resultType="com.sist.web.vo.SeoulVO" parameterType="hashmap">
 		SELECT no, contentid, title, address, image1, hit  
 		FROM seoultravel 
 		WHERE contenttype=#{contenttype}
 		ORDER BY no ASC 
 		OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY 
 	</select>
	*/
	public List<SeoulVO> seoulListData(Map map);
	@Select("SELECT CEIL(COUNT(*) / 12.0) FROM seoultravel "
			+ "WHERE contenttype=#{contenttype} ")
	public int seoulTotalPage(int contenttype);
}
