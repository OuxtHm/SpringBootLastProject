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
	/*
	<select id="seoulAttractionDetailData" resultMap="attMap" parameterType="int">
 		SELECT s.no, title, image1, address, x, y, infocenter, restdate, usetime, parking, msg 
 		FROM seoultravel s JOIN attraction a
 		ON s.contentid = a.contentid
 		AND s.contentid = #{contentid}
 	</select>
 	
 	<update id="seoulAttractionHitIncrement" parameterType="hashmap">
 		UPDATE seoultravel SET 
 		hit = hit + 1
 		WHERE contentid = #{contentid}
 	</update>
	*/
	
	public SeoulVO seoulAttractionDetailData(int contentid);
	public void seoulAttractionHitIncrement(int contentid);
	
	/*
	<select id="seoulFindData" resultType="com.sist.web.vo.SeoulVO" parameterType="hashmap">
 		SELECT no, contentid, title, address, image1, hit, contenttype 
 		FROM seoultravel 
 		WHERE address LIKE '%'||#{address}||'%'
 		ORDER BY no ASC 
 		OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY 
 	</select>
 	<select id="seoulFindTotalPage" resultType="int" parameterType="string">
 		SELECT CEIL(count(*)/12.0)
 		FROM seoultravel 
 		WHERE address LIKE '%'||#{address}||'%'
 	</select>
	*/
	public List<SeoulVO> seoulFindData(Map map);
	public int seoulFindTotalPage(String address);
	
	/*	
		<select id="seoulTop5Data" resultType="com.sist.web.vo.SeoulVO">
 		SELECT no, contentid, title, address, image1, hit, rownum  
 		FROM (SELECT no, contentid, title, address, image1, hit 
 		FROM seoultravel WHERE contenttype=39 ORDER BY hit DESC)
 		OFFSET rownum &lt;=5; 
 		</select>
 	*/
	public List<SeoulVO> seoulTop5Data();
}
