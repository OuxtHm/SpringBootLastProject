package com.sist.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;
import com.sist.web.service.*;
import com.sist.web.vo.*;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class MainController {
	private final BusanService bService;
	private final SeoulService sService;
	private final JejuService jService;
	private final RealFindDataService rService;

	
	@GetMapping("/main")
	public String main_page(Model model)
	{
		List<JejuVO> jList = jService.jejuTop4Data();
		for(JejuVO vo : jList)
		{
			String[] datas = vo.getAddress().split(" ");
			vo.setAddress(datas[0] + " " + datas[1]);
		}
		
		List<SeoulVO> sList = sService.seoulTop5Data();
		for(SeoulVO vo : sList)
		{
			String[] datas = vo.getAddress().split(" ");
			vo.setAddress(datas[0] + " " + datas[1]);
		}
		
		List<BusanVO> bList = bService.busanTop4Data();
		for(BusanVO vo : bList)
		{
			String[] datas = vo.getAddress().split(" ");
			vo.setAddress(datas[0] + " " + datas[1]);
		}
	
		List<RealFindDataVO> rList = rService.realFindDataAllData();
		
		model.addAttribute("rList", rList);
		model.addAttribute("jList", jList);
		model.addAttribute("sList", sList);
		model.addAttribute("bList", bList);

		model.addAttribute("main_jsp", "../main/home.jsp");
		return "main/main";
	}
}
