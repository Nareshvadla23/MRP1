package com.member.billclaim.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.member.billclaim.advice.MemberNotFoundException;
import com.member.billclaim.dao.MemberBillClaimRepo;
import com.member.billclaim.entity.BillClaim;
import com.member.billclaim.entity.Member;

@Service
public class BillClaimService {

	@Autowired
	private MemberBillClaimRepo memberBillClaimRepo;

	@Autowired
	RestTemplate template;

	public Integer submitClaim(BillClaim claim) throws MemberNotFoundException {

		Member member = template.getForObject("http://localhost:8084/member/retrive/name/" + claim.getName(),
				Member.class);
		if (member != null) {
			Random random = new Random(System.currentTimeMillis());
			Integer Value = (int) (random.nextInt(2000000000) + 1000000000 + member.getId());
			claim.setId(Math.abs(Value));
			BillClaim billClaim = memberBillClaimRepo.save(claim);
			return billClaim.getId();
		} else {
			throw new MemberNotFoundException("Member Details Not with Name :" + claim.getName());
		}
	}
}
