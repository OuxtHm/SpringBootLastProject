package com.sist.web.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.ReserveMapper;
import com.sist.web.vo.ReserveVO;
import com.sist.web.vo.SeoulVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReserveServiceImpl implements ReserveService{
	private final ReserveMapper mapper;

	@Override
	public List<SeoulVO> seoulReserveData(Map map) {
		return mapper.seoulReserveData(map);
	}

	@Override
	public int seoulReserveTotalPage(Map map) {
		return mapper.seoulReserveTotalPage(map);
	}
	
	@Override
	public String reserveInsert(ReserveVO vo) {
		String res = "";
		try {
			mapper.reserveInsert(vo);	
			res = "YES";
		} catch (Exception e) {
			e.printStackTrace();
			res = "NO";
		}
		return res;
	}
	
	@Override
	public List<ReserveVO> reserveAdminData() {
		return mapper.reserveAdminData();
	}
		
	@Override
	public List<ReserveVO> reserveMyData(String id) {
		return mapper.reserveMyData(id);
	}
	
	@Override
	public void reserveOk(int no) {
		mapper.reserveOk(no);
	}
	
	@Override
	public void reserveCancel(int no) {
		mapper.reserveCancel(no);
	}
	
	@Override
	public void reserveDelete(int no) {
		mapper.reserveDelete(no);
	}
	
	@Override
	public ReserveVO reserveDetailData(int no) {
		return mapper.reserveDetailData(no);
	}
}
