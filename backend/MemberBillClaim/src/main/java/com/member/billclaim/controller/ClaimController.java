package com.member.billclaim.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.member.billclaim.advice.BillAlreadyClaimmedException;
import com.member.billclaim.advice.MemberNotFoundException;
import com.member.billclaim.dto.BillClaimDto;
import com.member.billclaim.entity.BillClaim;
import com.member.billclaim.entity.Member;
import com.member.billclaim.service.BillClaimService;
import com.member.billclaim.util.FeignService;

@RestController
@RequestMapping("/member")
public class ClaimController {

	@Autowired
	RestTemplate template;

	@Autowired
	FeignService feign;

	@Autowired
	BillClaimService claimService;

	Logger logger = org.slf4j.LoggerFactory.getLogger(ClaimController.class);

	@GetMapping("retrive/name/{name}")
	public ResponseEntity<Member> getMemberByName(@PathVariable @Valid String name) throws MemberNotFoundException {
		logger.info("getMemberByName method Acessed");
		Member member = claimService.getMemberByName(name);
		logger.info("Exited from getMemberByName method"); 
		return new ResponseEntity<>(member, HttpStatus.OK);
	}

	@PostMapping("/claim")
	public ResponseEntity<String> billClaim(@RequestBody @Valid BillClaimDto claim) throws MemberNotFoundException, BillAlreadyClaimmedException {
		logger.info("billClaim method Acessed");
		BillClaim claimResponse = claimService.submitClaim(claim);
		logger.info("Exited from billClaim method");
		return ResponseEntity.ok("Member Details for bill claim added Sucessfully:" + claimResponse.getId());

	}

}