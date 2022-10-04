package com.member.registration.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.member.registration.entity.Member;

@Repository
public interface MemberRepo extends JpaRepository<Member, Long> {

	public Member findByName(String name);
	
	public Member findByMemberId(String memberId);
	

}
