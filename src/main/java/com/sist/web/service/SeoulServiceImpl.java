package com.sist.web.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.SeoulMapper;
import com.sist.web.vo.SeoulVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeoulServiceImpl implements SeoulService{
	private final SeoulMapper mapper;
	
	@Override
	public List<SeoulVO> seoulListData(Map map) {
		return mapper.seoulListData(map);
	}
	@Override
	public int seoulTotalPage(int contenttype) {
		return mapper.seoulTotalPage(contenttype);
	}
}
