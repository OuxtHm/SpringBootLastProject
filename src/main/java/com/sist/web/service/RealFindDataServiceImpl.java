package com.sist.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.RealFindDataMapper;
import com.sist.web.vo.RealFindDataVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RealFindDataServiceImpl implements RealFindDataService{
	private final RealFindDataMapper mapper;

	@Override
	public void realFindDataInsert(RealFindDataVO vo) {
		mapper.realFindDataInsert(vo);
	}

	@Override
	public void realFindDataDelete() {
		mapper.realFindDataDelete();
	}

	@Override
	public List<RealFindDataVO> realFindDataAllData() {
		return mapper.realFindDataAllData();
	}
	
	
}	
