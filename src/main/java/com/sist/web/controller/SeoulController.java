package com.sist.web.controller;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.service.*;
import com.sist.web.vo.*;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SeoulController {
	private final SeoulService sService;
	
	@GetMapping("/seoul/location")
	public String seoul_location(@RequestParam(name="page", required = false) String page, Model model)
	{
		if(page == null)
			page = "1";
		
		int curpage = Integer.parseInt(page);
		List<SeoulVO> list = sService.seoulLocationListData((curpage-1) * 12);
		int totalpage = sService.seoulTotalPage();
		final int BLOCK = 10;
		int startPage = ((curpage - 1) / BLOCK * BLOCK) +1;
		int endPage = ((curpage - 1) / BLOCK * BLOCK) + BLOCK;
		
		if(endPage > totalpage)
			endPage = totalpage;
		
		for(SeoulVO vo: list)
		{
			String[] addrs = vo.getAddress().split(" ");
			vo.setAddress(addrs[0]+ " " + addrs[1]);
			String s = vo.getLvo().getUsetime();
			int i = s.indexOf("(");
			
			if(i >= 0)
			{
				vo.getLvo().setUsetime(s.substring(0, i).trim());
			}
			
		}
		
		model.addAttribute("list", list);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("curpage", curpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		model.addAttribute("main_jsp", "../seoul/location.jsp");
		return "main/main";
	}
}
