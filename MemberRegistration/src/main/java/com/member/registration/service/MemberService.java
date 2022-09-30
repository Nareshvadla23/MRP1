package com.member.registration.service;

import java.util.Random;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.member.registration.advice.MemberAlreadyExistException;
import com.member.registration.advice.MemberNotFoundException;
import com.member.registration.dao.MemberRepo;
import com.member.registration.entity.Member;

@Service
public class MemberService {

	@Autowired
	private MemberRepo memberRepo;
	Logger logger = org.slf4j.LoggerFactory.getLogger(MemberService.class);

	public String addMember(Member member) throws MemberAlreadyExistException {
		logger.info("AddMember method Acessed");
		if (memberRepo.findByEmailAdress(member.getEmailAdress()) == null) {
			Random random = new Random();
			String memberId = "R-" + Math.abs((random.nextInt((999 - 111) + 1) + 111));
			member.setMemberId(memberId);
			Member savedMember = memberRepo.save(member);
			return savedMember.getMemberId();
		} else {
			throw new MemberAlreadyExistException("Member MailId Already Exist");
		}
	}

	public Member getMemberByName(String name) throws MemberNotFoundException {
		logger.info("GetMemberByName method Acessed");
		Member member = memberRepo.findByName(name);
		if (member != null) {
			return member;
		} else {
			throw new MemberNotFoundException("Member Details Not found with given Name :" + name);
		}
	}

	public Member updateMemberDetails(Member member) throws MemberNotFoundException {
		logger.info("UpdateMemberDetails method Acessed");
		Member responseMember = memberRepo.findByMemberId(member.getMemberId());
		if (responseMember != null) {
			Member updateMember = responseMember;
			updateMember.setEmailAdress(member.getEmailAdress());
			updateMember.setContactNo(member.getContactNo());
			updateMember.setAddress(member.getAddress());
			updateMember.setPan(member.getPan());
			updateMember.setState(member.getState());
			Member updatedMember = memberRepo.save(updateMember);
			return updatedMember;
		} else {
			throw new MemberNotFoundException("Member Details Not found with given MemberId");
		}
	}
}
