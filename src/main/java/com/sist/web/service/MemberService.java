package com.sist.web.service;

import com.sist.web.vo.MemberVO;

public interface MemberService {
	public int idCheck(String userid);
	public void memberInsert(MemberVO vo);
	public void memberAuthorityInsert(String userid);
	public MemberVO memberInfoData(String userid);
}
