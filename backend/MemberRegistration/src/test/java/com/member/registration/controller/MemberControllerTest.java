package com.member.registration.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.member.registration.advice.MemberAlreadyExistException;
import com.member.registration.advice.MemberNotFoundException;
import com.member.registration.dto.MemberDto;
import com.member.registration.entity.Member;
import com.member.registration.service.MemberService;

@ExtendWith(MockitoExtension.class)
class MemberControllerTest {

	@Mock
	private MemberService service;

	@InjectMocks
	private MemberController controller; 

	@Test
	void testSaveMember() throws MemberAlreadyExistException {
		Member member = member();
		MemberDto memberDto = memberDto();
		when(service.addMember(memberDto)).thenReturn(member);
		Map<String, String> response = controller.saveMember(memberDto);
		assertEquals("Member Details Added Sucessfully:" + member.getMemberId(), response.get("message"));
	}

	@Test
	void testGetMemberByName() {
		Member member = member();
		when(service.getMemberByName(member.getName())).thenReturn(member);
		Member response = controller.getMemberByName(member.getName()); 
		assertEquals(member, response);
	}
	
	@Test
	void testGetMemberByMemberId() {
		Member member = member();
		when(service.getMemberByMemberId(member.getMemberId())).thenReturn(member);
		Member response = controller.getMemberByMemberId(member.getMemberId());
		assertEquals(member.getCountry(), response.getCountry());
	}

	@Test
	void testUpdateMember() throws MemberNotFoundException {
		Member member = member();
		MemberDto memberDto = memberDto();
		when(service.updateMemberDetails(memberDto)).thenReturn(member);
		Map<String, String> response = controller.updateMember(memberDto);
		assertEquals("Member Details Updated Sucessfully:" + member.getMemberId(), response.get("message"));
	}
	
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
