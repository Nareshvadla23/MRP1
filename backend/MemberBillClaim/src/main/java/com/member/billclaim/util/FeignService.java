package com.member.billclaim.util;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.member.billclaim.entity.Member;

@Service
@FeignClient("MEMBERPORTAL")
public interface FeignService {
	
	@GetMapping("member/retrive/name/{name}")
	public Member getMemberByName(@PathVariable String name);
	
	@GetMapping("member/retrive/memberId/{memberId}")
	public Member getMemberByMemberId(@PathVariable String memberId);
	
	

	

}
