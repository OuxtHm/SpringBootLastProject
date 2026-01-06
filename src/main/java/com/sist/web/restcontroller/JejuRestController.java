package com.sist.web.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.service.JejuService;
import com.sist.web.vo.*;

import java.util.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jeju/")
public class JejuRestController {
	private final JejuService jService;
	
	@GetMapping("find_vue/")
	public ResponseEntity<Map> find_vue(@RequestParam("page") int page, @RequestParam("selected") int selected, @RequestParam("fd") String fd)
	{
		Map map = new HashMap<>();
		try {
			map.put("start", (page-1) * 12);
			map.put("selected", selected);
			map.put("fd", fd);
			List<JejuVO> list = jService.jejuFindData(map);
			
			for(JejuVO vo : list)
			{
				String[] addrs = vo.getAddress().split("");
				vo.setAddress(addrs[0] + " " + addrs[1]);
			}
			
			int totalpage = jService.jejuFindTotalPage(map);
			
			final int BLOCK = 5;
			int startPage = ((page - 1) / BLOCK * BLOCK) + 1;
			int endPage = ((page - 1) / BLOCK * BLOCK) + BLOCK;
			
			if(endPage > totalpage)
				endPage = totalpage;
			
			// JSON 으로 묶어서 브라우저로 전송
			map = new HashMap();
			map.put("list", list);
			map.put("totalpage", totalpage);
			map.put("curpage", page);
			map.put("startPage", startPage);
			map.put("endPage", endPage);
			map.put("selected", selected);
			map.put("fd", fd);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
