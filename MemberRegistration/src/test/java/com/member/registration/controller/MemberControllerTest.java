package com.member.registration.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.member.registration.advice.MemberNotFoundException;
import com.member.registration.entity.Member;
import com.member.registration.service.MemberService;

@ExtendWith(MockitoExtension.class)
class MemberControllerTest {

	@Mock
	private MemberService service;

	@InjectMocks
	private MemberController controller;

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

	@Test
	void testSaveMember() {
		Member member = member();
		when(service.addMember(member)).thenReturn(member.getMemberId());
		ResponseEntity<String> response = controller.saveMember(member);
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
		when(service.updateMemberDetails(member)).thenReturn(member);
		ResponseEntity<String> response = controller.updateMember(member);
		assertEquals(ResponseEntity.ok("Member Details Updated Sucessfully:" + member), response);
	}

}
