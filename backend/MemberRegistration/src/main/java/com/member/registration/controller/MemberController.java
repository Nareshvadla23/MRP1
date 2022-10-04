package com.member.registration.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.member.registration.advice.MemberAlreadyExistException;
import com.member.registration.advice.MemberNotFoundException;
import com.member.registration.dto.MemberDto;
import com.member.registration.entity.Member;
import com.member.registration.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberController {

	Logger logger = org.slf4j.LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberService;

	@PostMapping("/register")
	public ResponseEntity<String> saveMember(@RequestBody @Valid MemberDto memberDto)
			throws MemberAlreadyExistException {
		logger.info("SaveMemberMethod Acessed");
		Member member = memberService.addMember(memberDto);
		logger.info("Exited from SaveMemberMethod");
		return ResponseEntity.ok("Member Details Added Sucessfully:" + member.getMemberId());
	}

	@GetMapping("retrive/name/{name}")
	public Member getMemberByName(@PathVariable String name) throws MemberNotFoundException {
		logger.info("getMemberByName method Acessed");
		Member member = memberService.getMemberByName(name);
		logger.info("Exited from getMemberByName method");
		return member;
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateMember(@RequestBody @Valid MemberDto memberDto) throws MemberNotFoundException {
		logger.info("updateMember method Acessed");
		Member updatedMember = memberService.updateMemberDetails(memberDto);
		logger.info("Exited from updateMember method");
		return ResponseEntity.ok("Member Details Updated Sucessfully:" + updatedMember);
	}

}