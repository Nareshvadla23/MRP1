package com.member.registration.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

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
		ResponseEntity<String> response = controller.saveMember(memberDto);
		assertEquals(ResponseEntity.ok("Member Details Added Sucessfully:" + member.getMemberId()), response);
	}

	@Test
	void testGetMemberByName() throws MemberNotFoundException {
		Member member = member();
		when(service.getMemberByName(member.getName())).thenReturn(member);
		Member response = controller.getMemberByName(member.getName());
		assertEquals(member, response);
	}

	@Test
	void testUpdateMember() throws MemberNotFoundException {
		Member member = member();
		MemberDto memberDto = memberDto();
		when(service.updateMemberDetails(memberDto)).thenReturn(member);
		ResponseEntity<String> response = controller.updateMember(memberDto);
		assertEquals(ResponseEntity.ok("Member Details Updated Sucessfully:" + member), response);
	}
	
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

	public static MemberDto memberDto() {
		MemberDto membDto = new MemberDto();
		membDto.setAddress("Hyderabad");
		membDto.setCity("Hyderabad");
		membDto.setContactNo(909900909L);
		membDto.setDob(LocalDate.now());
		membDto.setEmailAdress("naresh@gmail.com");
		membDto.setName("naresh");
		membDto.setPan("ASLSLDDK3A");
		membDto.setState("telangana");
		membDto.setMemberId("R-123");
		return membDto;
	}

}