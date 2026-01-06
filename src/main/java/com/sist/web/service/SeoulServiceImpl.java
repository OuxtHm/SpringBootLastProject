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
	@Override
	public SeoulVO seoulAttractionDetailData(int contentid) {
		mapper.seoulAttractionHitIncrement(contentid);
		return mapper.seoulAttractionDetailData(contentid);
	}
	@Override
	public List<SeoulVO> seoulFindData(Map map) {
		return mapper.seoulFindData(map);
	}
	@Override
	public int seoulFindTotalPage(String address) {
		return mapper.seoulFindTotalPage(address);
	}
	@Override
	public List<SeoulVO> seoulTop5Data() {
		return mapper.seoulTop5Data();
	}
	
	// 구현 => mapper
	// 통합 => service
	// 브라우저로 전송 : request(model) => Controller
	// 					JSON => RestController => ResponseBody
}
