package com.member.billclaim.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.member.billclaim.advice.MemberNotFoundException;
import com.member.billclaim.dao.MemberBillClaimRepo;
import com.member.billclaim.entity.BillClaim;
import com.member.billclaim.entity.Member;
import com.member.billclaim.util.FeignService;

@ExtendWith(MockitoExtension.class)
class BillClaimServiceTest {

	@Mock
	private MemberBillClaimRepo memberBillClaimRepo;

	@Mock
	FeignService feign;

	@InjectMocks
	BillClaimService service;

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
	void testSubmitClaimPassCondition() throws MemberNotFoundException {
		BillClaim billClaim = billClaim();
		Member member = member();
		when(feign.getMemberByName(billClaim.getName())).thenReturn(member);
		when(memberBillClaimRepo.save(billClaim)).thenReturn(billClaim);
		BillClaim response = service.submitClaim(billClaim);
		assertEquals("naresh", response.getName());
	}

	@Test
	void testSubmitClaimFailCondition() {
		BillClaim billClaim = billClaim();
		when(feign.getMemberByName(billClaim.getName())).thenReturn(null);
		assertThrows(MemberNotFoundException.class, () -> service.submitClaim(billClaim),
				"This method throws exception");
	}

}
