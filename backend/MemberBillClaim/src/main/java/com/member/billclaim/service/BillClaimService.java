package com.member.billclaim.service;

import java.util.Random;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.member.billclaim.advice.BillAlreadyClaimmedException;
import com.member.billclaim.advice.MemberNotFoundException;
import com.member.billclaim.dao.MemberBillClaimRepo;
import com.member.billclaim.dto.BillClaimDto;
import com.member.billclaim.entity.BillClaim;
import com.member.billclaim.entity.Member;
import com.member.billclaim.util.FeignService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class BillClaimService {

	@Autowired
	private MemberBillClaimRepo memberBillClaimRepo;

	@Autowired
	RestTemplate template;

	@Autowired
	FeignService feign;
	
	@Autowired
	commonservices service;

	Logger logger = org.slf4j.LoggerFactory.getLogger(BillClaimService.class);
	static Random random = new Random(System.currentTimeMillis());

	public Member getMemberByName(String name) throws MemberNotFoundException {
		logger.info("GetMemberByName method Acessed");
		Member member = feign.getMemberByName(name);
		if (member != null) {
			return member;
		} else {
			throw new MemberNotFoundException("Member details not found with Name :" + name);
		}
	}

	
	public Member getMemberByMemberId(String memberId) throws MemberNotFoundException {
		logger.info("GetMemberByMemberId method Acessed");
		Member member = service.getMemberByMemberId(memberId);
		if (member != null) {
			return member;
		} else {
			throw new MemberNotFoundException("Member details not found with MemberId :" + memberId);
		} 
	}

	public BillClaim submitClaim(BillClaimDto claim) throws MemberNotFoundException, BillAlreadyClaimmedException {
		logger.info("SubmitClaim method Acessed");
		Member member = feign.getMemberByMemberId(claim.getMemberId());
		if (member != null) {
			BillClaim billClaimResponse = memberBillClaimRepo.findByName(claim.getName());
			if (billClaimResponse == null) {
				BillClaim billClaim = billClaim(claim);
				return memberBillClaimRepo.save(billClaim);
			} else {
				throw new BillAlreadyClaimmedException(
						"Member Already Calimed There Bills with claimId:" + billClaimResponse.getId());
			}
		} else {
			throw new MemberNotFoundException("Member details not found with Member ID :" + claim.getMemberId());
		}
	}

	public static BillClaim billClaim(BillClaimDto bilClaimDto) {
		BillClaim billClaim = new BillClaim();

		Integer value = (int) (random.nextInt(2000000000) + 1000000000);
		billClaim.setId(Math.abs(value));
		billClaim.setDob(bilClaimDto.getDob());
		billClaim.setName(bilClaimDto.getName());
		billClaim.setDateofAdmission(bilClaimDto.getDateofAdmission());
		billClaim.setDateofDischarge(bilClaimDto.getDateofDischarge());
		billClaim.setBillAmount(bilClaimDto.getBillAmount());
		billClaim.setProviderName(bilClaimDto.getProviderName());
		return billClaim;
	}
}
