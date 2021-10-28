package com.sbs.exam.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.exam.demo.vo.Member;

@Mapper
public interface MemberRepository {

	public void join(@Param("loginId") String loginId, @Param("loginPw") String loginPw, @Param("name") String name,
			@Param("nickname") String nickname, @Param("cellPhoneNo") String cellPhoneNo, @Param("email") String email);

	public int getLastInsertId();

	public Member getMemberById(@Param("id") int id);

	public Member getMemberByLoginId(@Param("loginId") String loginId);

	public Member getMemberByNameAndEmail(@Param("name") String name, @Param("email") String email);
}
