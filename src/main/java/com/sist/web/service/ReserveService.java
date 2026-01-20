package com.sist.web.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.sist.web.vo.ReserveVO;
import com.sist.web.vo.SeoulVO;

public interface ReserveService {

	/*@Select("SELECT no, contentid, image1, title, x, y, address, "
			+ "FROM seoultravel "
			+ "WHERE address LIKE '%'||#{address}||'%' AND contenttype = 39 "
			+ "ORDER BY contentid ASC "
			+ "OFFSET #{start} ROWS FETCH NEXT 10 ROWS ONLY")*/
	public List<SeoulVO> seoulReserveData(Map map);
	
	/*@Select("SELECT CEIL(COUNT(*) / 10.0) "
			+ "FROM seoultravel "
			+ "WHERE address LIKE '%'||#{address}||'%' AND contenttype = 39 ")*/
	public int seoulReserveTotalPage(Map map);
	
	public String reserveInsert(ReserveVO vo);
	public List<ReserveVO> reserveMyData(String id);
	public List<ReserveVO> reserveAdminData();
}
