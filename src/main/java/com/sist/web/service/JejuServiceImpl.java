package com.sist.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.JejuMapper;
import com.sist.web.vo.JejuVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JejuServiceImpl implements JejuService{
	private final JejuMapper mapper;
	
	@Override
	public List<JejuVO> jejuListData(Map map) {
		return mapper.jejuListData(map);
	}
	@Override
	public int jejuTotalPage(int contenttype) {
		return mapper.jejuTotalPage(contenttype);
	}
	
	@Override
	public List<JejuVO> jejuFindData(Map map) {
		return mapper.jejuFindData(map);
	}
	
	@Override
	public int jejuFindTotalPage(Map map) {
		return mapper.jejuFindTotalPage(map);
	}
	@Override
	public List<JejuVO> jejuTop4Data() {
		return mapper.jejuTop4Data();
	}
}
