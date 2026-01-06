package com.sist.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.sist.web.vo.JejuVO;
@Mapper
public interface JejuMapper {
	public List<JejuVO> jejuListData(Map map);
	@Select("SELECT CEIL(COUNT(*) / 12.0) FROM jejutravel "
			+ "WHERE contenttype=#{contenttype} ")
	public int jejuTotalPage(int contenttype);
	/*
	<select id="jejuFindData" resultType="com.sist.web.vo.JejuVo" parameterType="hashmap">
 		SELECT no, contentid, title, address, image1, hit  
 		FROM jejutravel 
 		WHERE contenttype=#{selected}
 		AND title LIKE '%'||#{fd}||'%'
		ORDER BY no ASC 
 		OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY  		
 	</select>
 	
 	<select id="jejuFindTotalPage" resultType="int" parameterType="hashmap">
 		SELECT CEIL(COUNT(*) / 12.0)
 		FROM jejutravel 
 		WHERE contenttype=#{selected}
 		AND title LIKE '%'||#{fd}||'%'
 	</select>
	*/
	public List<JejuVO> jejuFindData(Map map);
	public int jejuFindTotalPage(Map map);
	
	/*
	<select id="jejuTop4Data" resultType="com.sist.web.vo.JejuVO">
 		SELECT no, contentid, title, address, image1, hit, rownum  
 		FROM (SELECT no, contentid, title, address, image1, hit 
 		FROM jejutravel ORDER BY hit DESC)
 		OFFSET rownum &lt;=4; 
 	</select>
	*/
	public List<JejuVO> jejuTop4Data();
}
