package com.member.registration.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.member.registration.advice.MemberAlreadyExistException;
import com.member.registration.advice.MemberNotFoundException;
import com.member.registration.dto.MemberDto;
import com.member.registration.entity.Member;
import com.member.registration.service.MemberService;

@RestController
@CrossOrigin
@RequestMapping("/member")
public class MemberController {

	Logger logger = org.slf4j.LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberService;

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.OK)
	public Map<String, String> saveMember(@RequestBody @Valid MemberDto memberDto) throws MemberAlreadyExistException {
		logger.info("SaveMemberMethod Acessed");
		Member member = memberService.addMember(memberDto);
		Map<String, String> response = new HashMap<>();
		String value = "Member Details Added Sucessfully:" + member.getMemberId();
		response.put("message", value);
		logger.info("Exited from SaveMemberMethod");
		return response;
	}

	@GetMapping("retrive/name/{name}")
	public Member getMemberByName(@PathVariable String name) throws MemberNotFoundException {
		logger.info("getMemberByName method Acessed");
		Member member = memberService.getMemberByName(name);
		logger.info("Exited from getMemberByName method");
		return member;
	}

	@GetMapping("retrive/memberId/{memberId}")
	public Member getMemberByMemberId(@PathVariable String memberId) throws MemberNotFoundException {
		logger.info("getMemberByName method Acessed");
		Member member = memberService.getMemberByMemberId(memberId);
		logger.info("Exited from getMemberByName method");
		return member;
	}

	@PutMapping("/update")
	@ResponseStatus(HttpStatus.OK)
	public Map<String, String> updateMember(@RequestBody @Valid MemberDto memberDto) throws MemberNotFoundException {
		logger.info("updateMember method Acessed");
		Map<String, String> response = new HashMap<>();
		Member updatedMember = memberService.updateMemberDetails(memberDto);
		String value = "Member Details Updated Sucessfully:" + updatedMember.getMemberId();
		response.put("message", value);
		logger.info("Exited from updateMember method");
		return response;
	}

}
