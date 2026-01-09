package com.sist.web.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.*;

@Mapper
@Repository
public interface MemberMapper {
	@Select("SELECT COUNT(*) FROM project_member_2 "
			+ "WHERE userid=#{userid}")
	public int idCheck(String userid);
	
	@Insert("INSERT INTO project_member_2(userid, username, userpwd, sex, birthday, email, post, addr1, addr2, phone, content) "
			+ "VALUES(#{userid}, #{username}, #{userpwd}, #{sex}, #{birthday}, #{email}, #{post}, #{addr1}, #{addr2}, #{phone}, #{content})")
	public void memberInsert(MemberVO vo);
	
	@Insert("INSERT INTO authority_2 VALUES(#{userid}, 'ROLE_USER')")
	public void memberAuthorityInsert(String userid);
	
	// 비밀번호 검사 => 데이터 읽기 => session 저장
	@Select("SELECT * FROM project_member_2 "
			+ "WHERE userid=#{userid}")
	public MemberVO memberInfoData(String userid);
	
	
}
