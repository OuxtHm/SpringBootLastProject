package com.sist.web.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberRestConstroller {
	private final MemberService mService;
	
	@GetMapping("/member/idcheck_vue/")
	public String member_idcheck(@RequestParam("userid") String userid)
	{
		int count = mService.idCheck(userid);
		return String .valueOf(count);
	}
}
