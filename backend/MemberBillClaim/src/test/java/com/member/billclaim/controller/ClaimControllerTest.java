package com.member.billclaim.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.member.billclaim.advice.BillAlreadyClaimmedException;
import com.member.billclaim.advice.MemberNotFoundException;
import com.member.billclaim.dto.BillClaimDto;
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
	
	public static BillClaimDto billClaimDto() {
		BillClaimDto billClaimDto = new BillClaimDto();
		billClaimDto.setBillAmount(2000L);
		billClaimDto.setDateofAdmission(LocalDate.now());
		billClaimDto.setDateofDischarge(LocalDate.now());
		billClaimDto.setDob(LocalDate.now());
		billClaimDto.setName("naresh");
		billClaimDto.setProviderName("sai");
		return billClaimDto;
	}
 
	@Test 
	void testGetMemberByName() throws MemberNotFoundException {
		ResponseEntity<Member> responseMember = controller.getMemberByName("naresh");
		assertEquals(HttpStatus.OK, responseMember.getStatusCode());
	} 
	
	@Test 
	void testGetMemberByMemberId() throws MemberNotFoundException {
		ResponseEntity<Member> responseMember = controller.getMemberByMemberId("R-233");
		assertEquals(HttpStatus.OK, responseMember.getStatusCode());
	} 

	@Test
	void testBillClaim() throws MemberNotFoundException, BillAlreadyClaimmedException {
		BillClaim billClaim = billClaim();
		BillClaimDto billClaimDto = billClaimDto();
		when(claimService.submitClaim(billClaimDto)).thenReturn(billClaim);
		Map<String, String> response = controller.billClaim(billClaimDto);
		assertEquals("Member Details for bill claim added Sucessfully:" + billClaim.getId(), response.get("message"));

	}

}
