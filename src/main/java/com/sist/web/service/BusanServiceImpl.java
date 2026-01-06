package com.sist.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.BusanMapper;
import com.sist.web.vo.BusanVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusanServiceImpl implements BusanService{
	private final BusanMapper mapper;
	
	@Override
	public List<BusanVO> busanListData(Map map) {
		return mapper.busanListData(map);
	}
	
	@Override
	public int busanTotalPage(int contenttype) {
		return mapper.busanTotalPage(contenttype);
	}
	
	@Override
	public List<BusanVO> busanFindData(Map map) {
		return mapper.busanFindData(map);
	}
	
	@Override
	public int busanFindTotalPage(String address) {
		return mapper.busanFindTotalPage(address);
	}
	
	@Override
	public List<BusanVO> busanTop4Data() {
		return mapper.busanTop4Data();
	}
}
