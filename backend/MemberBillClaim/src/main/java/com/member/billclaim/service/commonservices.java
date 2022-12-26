package com.member.billclaim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.member.billclaim.advice.MemberNotFoundException;
import com.member.billclaim.entity.Member;
import com.member.billclaim.util.FeignService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class commonservices {

	@Autowired
	FeignService feign;

	@CircuitBreaker(name = "billClaim", fallbackMethod = "fallbackgetMemberByMemberId")
	public Member getMemberByMemberId(String memberId) throws MemberNotFoundException {

		Member member = feign.getMemberByMemberId(memberId);
		System.out.println(member);
		if (member != null) {
			return member;
		} else {
			throw new MemberNotFoundException("Member details not found with MemberId :" + memberId);
		}
	}

	public Member fallbackgetMemberByMemberId(String memberId, Throwable th) {
		return new Member();

	}

}
