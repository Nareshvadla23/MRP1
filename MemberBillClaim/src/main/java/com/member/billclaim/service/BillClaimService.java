package com.member.billclaim.service;

import java.util.Random;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.member.billclaim.advice.MemberNotFoundException;
import com.member.billclaim.dao.MemberBillClaimRepo;
import com.member.billclaim.entity.BillClaim;
import com.member.billclaim.entity.Member;
import com.member.billclaim.util.FeignService;

@Service
public class BillClaimService {

	@Autowired
	private MemberBillClaimRepo memberBillClaimRepo;

	@Autowired
	RestTemplate template;

	@Autowired
	FeignService feign;

	Logger logger = org.slf4j.LoggerFactory.getLogger(BillClaimService.class);

	public BillClaim submitClaim(BillClaim claim) throws MemberNotFoundException {

		logger.info("SubmitClaim method Acessed");
		Member member = feign.getMemberByName(claim.getName());

		if (member != null) {
			Random random = new Random(System.currentTimeMillis());
			Integer Value = (int) (random.nextInt(2000000000) + 1000000000 + member.getId());
			claim.setId(Math.abs(Value));
			BillClaim billClaim = memberBillClaimRepo.save(claim);
			return billClaim;
		} else {
			throw new MemberNotFoundException("Member details not found with Name :" + claim.getName());
		}
	}
}
