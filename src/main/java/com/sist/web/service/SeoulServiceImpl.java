package com.sist.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.SeoulMapper;
import com.sist.web.vo.SeoulVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeoulServiceImpl implements SeoulService{
	private final SeoulMapper mapper;
	
	@Override
	public List<SeoulVO> seoulLocationListData(int start) {
		return mapper.seoulLocationListData(start);
	}
	@Override
	public int seoulTotalPage() {
		return mapper.seoulTotalPage();
	}
}
