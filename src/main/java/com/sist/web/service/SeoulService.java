package com.sist.web.service;

import java.util.*;
import com.sist.web.vo.*;

/*
						/seoul/location
	user 요청 ------------------------------------ DispatcherServlet
														|
													HandlerMapping
														| URI 주소 찾기 => @GetMapping
														|				 @PostMapping
														|				 @PutMapping
														|				 @DeleteMapping
														|	=> 인증 => 권한부여	
														| 밑에 있는 메소드 호출
																|
															 Service
																|
														 	  Mapper => 수정 시에 의존성 약하게
																|
															  오라클
														DispatcherServlet
															|
														ViewResolver
															|
															Jsp
*/
public interface SeoulService {
	public List<SeoulVO> seoulListData(Map map);
	public int seoulTotalPage(int contenttype);
	public SeoulVO seoulAttractionDetailData(int contentid);
	public List<SeoulVO> seoulFindData(Map map);
	public int seoulFindTotalPage(String address);
	public List<SeoulVO> seoulTop5Data();
}
