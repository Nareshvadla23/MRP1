package com.member.billclaim.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.member.billclaim.advice.MemberNotFoundException;
import com.member.billclaim.entity.BillClaim;
import com.member.billclaim.entity.Member;
import com.member.billclaim.service.BillClaimService;
import com.member.billclaim.util.FeignService;

@ExtendWith(MockitoExtension.class)
class ClaimControllerTest {

	@Mock
	FeignService feign;

	@Mock
	BillClaimService claimService;

	@InjectMocks
	ClaimController controller;

	public static Member member() {
		Member member = new Member();
		member.setId(1L);
		member.setAddress("Hyderabad");
		member.setCity("Hyderabad");
		member.setContactNo(909900909L);
		member.setDob(LocalDate.now());
		member.setEmailAdress("naresh@gmail.com");
		member.setName("naresh");
		member.setPan("ASLSLDDK3A");
		member.setState("telangana");
		member.setMemberId("R-123");
		return member;
	}

	public static BillClaim billClaim() {
		BillClaim billClaim = new BillClaim();
		billClaim.setBillAmount(2000L);
		billClaim.setDateofAdmission(LocalDate.now());
		billClaim.setDateofDischarge(LocalDate.now());
		billClaim.setDob(LocalDate.now());
		billClaim.setId(1);
		billClaim.setName("naresh");
		billClaim.setProviderName("sai");
		return billClaim;
	}

	@Test
	void testGetMemberByName() {
		Member member = member();
		when(feign.getMemberByName("naresh")).thenReturn(member);
		ResponseEntity<Member> responseMember = controller.getMemberByName("naresh");
		assertEquals(HttpStatus.OK, responseMember.getStatusCode());
	}

	@Test
	void testBillClaim() throws MemberNotFoundException { 
		BillClaim billClaim = billClaim();
		Member member = member();
		when(claimService.submitClaim(billClaim)).thenReturn(billClaim);
		ResponseEntity<String> response = controller.billClaim(billClaim);
		assertEquals(HttpStatus.OK, response.getStatusCode());

	}

}