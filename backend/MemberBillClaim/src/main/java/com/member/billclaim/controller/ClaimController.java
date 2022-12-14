package com.member.billclaim.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
@CrossOrigin
public class ClaimController {

	@Autowired
	RestTemplate template;

	@Autowired
	FeignService feign;

	@Autowired
	BillClaimService claimService;

	Logger logger = org.slf4j.LoggerFactory.getLogger(ClaimController.class);

	// for retrieving member details by memberName
	@GetMapping("retrive/name/{name}")
	public ResponseEntity<Member> getMemberByName(@PathVariable @Valid String name) throws MemberNotFoundException {
		logger.info("getMemberByName method Acessed");
		Member member = claimService.getMemberByName(name);
		logger.info("Exited from getMemberByName method");
		return new ResponseEntity<>(member, HttpStatus.OK);
	}

	// for retrieving member details by memberId
	@GetMapping("retrive/memberId/{memberId}")
	public ResponseEntity<Member> getMemberByMemberId(@PathVariable @Valid String memberId)
			throws MemberNotFoundException {
		logger.info("getMemberByMemberID method Acessed");
		Member member = claimService.getMemberByMemberId(memberId);
		logger.info("Exited from getMemberByName method");
		return new ResponseEntity<>(member, HttpStatus.OK);
	}

	// for bill claiming
	@PostMapping("/claim")
	@ResponseStatus(HttpStatus.OK)
	public Map<String, String> billClaim(@RequestBody @Valid BillClaimDto claim)
			throws MemberNotFoundException, BillAlreadyClaimmedException {
		logger.info("billClaim method Acessed");
		BillClaim claimResponse = claimService.submitClaim(claim);
		Map<String, String> response = new HashMap<>();
		String value = "Member Details for bill claim added Sucessfully:" + claimResponse.getId();
		response.put("message", value);
		logger.info("Exited from billClaim method");
		return response;

	}

}
