package com.member.registration.service;

import java.util.Random;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.member.registration.advice.MemberAlreadyExistException;
import com.member.registration.advice.MemberNotFoundException;
import com.member.registration.dao.MemberRepo;
import com.member.registration.dto.MemberDto;
import com.member.registration.entity.Member;

@Service
public class MemberService {

	@Autowired
	private MemberRepo memberRepo;
	Logger logger = org.slf4j.LoggerFactory.getLogger(MemberService.class);

	static Random random = new Random();

	public static Member member(MemberDto memberDto) {
		Member member = new Member();
		String memberId = "R-" + Math.abs((random.nextInt((999 - 111) + 1) + 111));
		member.setMemberId(memberId);
		member.setAddress(memberDto.getAddress());
		member.setCountry(memberDto.getCountry());
		member.setContactNo(memberDto.getContactNo());
		member.setDob(memberDto.getDob());
		member.setEmailAddress(memberDto.getEmailAddress());
		member.setName(memberDto.getName());
		member.setPan(memberDto.getPan());
		member.setState(memberDto.getState());
		return member;
	}

	public Member addMember(MemberDto memberDto) throws MemberAlreadyExistException {
		logger.info("AddMember method Acessed");

		if (memberRepo.findByEmailAddress(memberDto.getEmailAddress()) == null) {
			Member member = member(memberDto);
			return memberRepo.save(member);
		} else {
			throw new MemberAlreadyExistException("Member MailId Already Exist");
		}
	}

	public Member getMemberByName(String name) {
		logger.info("GetMemberByName method Acessed");
		return memberRepo.findByName(name);
	}

	public Member getMemberByMemberId(String memberId) {
		logger.info("GetMemberBymemberId method Acessed");
		return memberRepo.findByMemberId(memberId);
	}

	public Member updateMemberDetails(@Valid MemberDto memberDto) throws MemberNotFoundException {
		logger.info("UpdateMemberDetails method Acessed");
		Member responseMember = memberRepo.findByMemberId(memberDto.getMemberId());
		if (responseMember != null) {
			Member updateMember = responseMember;
			updateMember.setEmailAddress(memberDto.getEmailAddress());
			updateMember.setContactNo(memberDto.getContactNo());
			updateMember.setAddress(memberDto.getAddress());
			updateMember.setPan(memberDto.getPan());
			updateMember.setState(memberDto.getState());
			return memberRepo.save(updateMember);
		} else {
			throw new MemberNotFoundException("Member Details Not found with given MemberId");
		}
	}
}
