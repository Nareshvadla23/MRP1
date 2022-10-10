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

import com.member.billclaim.advice.BillAlreadyClaimmedException;
import com.member.billclaim.advice.MemberNotFoundException;
import com.member.billclaim.dao.MemberBillClaimRepo;
import com.member.billclaim.dto.BillClaimDto;
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
		member.setCountry("Hyderabad");
		member.setContactNo(909900909L);
		member.setDob(LocalDate.now());
		member.setEmailAddress("naresh@gmail.com");
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
	void testSubmitClaimPassCondition() throws MemberNotFoundException, BillAlreadyClaimmedException {
		BillClaim billClaim = billClaim();
		BillClaimDto billClaimDto = billClaimDto();
		Member memberDto = member();
		when(feign.getMemberByName(billClaim.getName())).thenReturn(memberDto);
		when(memberBillClaimRepo.save(billClaim)).thenReturn(billClaim);
		when(memberBillClaimRepo.findByName("naresh")).thenReturn(null);
		BillClaim response = service.submitClaim(billClaimDto);
		assertEquals("naresh", response.getName());
	}

	@Test
	void testSubmitClaimFailCondition() {
		BillClaim billClaim = billClaim();
		BillClaimDto billClaimDto = billClaimDto();
		when(feign.getMemberByName(billClaim.getName())).thenReturn(null);
		assertThrows(MemberNotFoundException.class, () -> service.submitClaim(billClaimDto),
				"This method throws exception");
	}

	@Test
	void testSubmitClaimFailCondition2() {
		BillClaim billClaim = billClaim();
		BillClaimDto billClaimDto = billClaimDto();
		Member memberDto = member();
		when(feign.getMemberByName(billClaim.getName())).thenReturn(memberDto);
		when(memberBillClaimRepo.findByName("naresh")).thenReturn(billClaim);
		assertThrows(BillAlreadyClaimmedException.class, () -> service.submitClaim(billClaimDto),
				"This method throws exception");
	}

	@Test
	void testGetMemberByNamePassCondition() throws MemberNotFoundException {
		Member member = member();
		when(feign.getMemberByName("naresh")).thenReturn(member);
		assertEquals(member, service.getMemberByName("naresh"));
	}

	@Test
	void testGetMemberByNameFailCondition() {
		when(feign.getMemberByName("naresh")).thenReturn(null);
		assertThrows(MemberNotFoundException.class, () -> service.getMemberByName("naresh"),
				"This method throws exception");
	}

}
