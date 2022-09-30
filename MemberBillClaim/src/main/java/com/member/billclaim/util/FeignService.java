package com.member.billclaim.util;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.member.billclaim.entity.BillClaim;
import com.member.billclaim.entity.Member;

@Service
@FeignClient("MEMBERPORTAL")
public interface FeignService {
	
	@GetMapping("member/retrive/name/{name}")
	public Member getMemberByName(@PathVariable String name);

	@PostMapping("member/claim")
	public Member billClaim(@RequestBody BillClaim claim);

}