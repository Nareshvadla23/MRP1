package com.member.registration.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.member.registration.advice.MemberAlreadyExistException;
import com.member.registration.advice.MemberNotFoundException;
import com.member.registration.dao.MemberRepo;
import com.member.registration.dto.MemberDto;
import com.member.registration.entity.Member;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

	@Mock
	private MemberRepo memberRepo;

	@InjectMocks
	private MemberService service;

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

	@Test
	void testAddMemberPassCondition() throws MemberAlreadyExistException {

		Member member = member();
		MemberDto memberDto = memberDto();
		when(memberRepo.findByEmailAddress(member.getEmailAddress())).thenReturn(null);
		when(memberRepo.save(member)).thenReturn(member);
		assertEquals(member, service.addMember(memberDto)); 
	} 

	@Test
	void testAddMemberFailCondition() throws MemberAlreadyExistException {

		Member member = member();
		MemberDto memberDto = memberDto();
		when(memberRepo.findByEmailAddress(member.getEmailAddress())).thenReturn(member);
		assertThrows(MemberAlreadyExistException.class, () -> service.addMember(memberDto),
				"This method throws MemberAlreadyExistException");

	}

	@Test
	void testGetMemberByName(){
		Member member = member(); 
		when(memberRepo.findByName(member.getName())).thenReturn(member);
		Member response = service.getMemberByName(member.getName());
		assertEquals(member, response);
	}
	
	@Test
	void testGetMemberByMemberId(){
		Member member = member(); 
		when(memberRepo.findByMemberId(member.getMemberId())).thenReturn(member);
		Member response = service.getMemberByMemberId(member.getMemberId());
		assertEquals(member, response);
	}


	@Test
	void testUpdateMemberDetailsPassCondition() throws MemberNotFoundException {
		Member member = member();
		MemberDto memberDto = memberDto();
		when(memberRepo.findByMemberId(member.getMemberId())).thenReturn(member);
		when(memberRepo.save(member)).thenReturn(member);
		assertEquals(member, service.updateMemberDetails(memberDto));

	}

	@Test
	void testUpdateMemberDetailsFailCondition() throws MemberNotFoundException {
		Member member = member();
		MemberDto memberDto = memberDto();
		when(memberRepo.findByMemberId(member.getMemberId())).thenReturn(null);
		assertThrows(MemberNotFoundException.class, () -> service.updateMemberDetails(memberDto),
				"This method throws exception");
	}
	
	public static MemberDto memberDto() {
		MemberDto membDto = new MemberDto();
		membDto.setAddress("Hyderabad");
		membDto.setCountry("Hyderabad");
		membDto.setContactNo(909900909L);
		membDto.setDob(LocalDate.now());
		membDto.setEmailAddress("naresh@gmail.com");
		membDto.setName("naresh");
		membDto.setPan("ASLSLDDK3A");
		membDto.setState("telangana");
		membDto.setMemberId("R-123");

		return membDto;
	}


}
