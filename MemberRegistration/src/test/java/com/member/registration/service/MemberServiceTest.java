package com.member.registration.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.member.registration.advice.MemberNotFoundException;
import com.member.registration.dao.MemberRepo;
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
	void testAddMember() {

		Member member = member();
		when(memberRepo.save(member)).thenReturn(member);
		String savedMember = service.addMember(member);
		assertEquals(member.getMemberId(), savedMember);
	}

	@Test
	void testGetMemberByNamePassCondition() throws MemberNotFoundException {
		Member member = member();
		when(memberRepo.findByName(member.getName())).thenReturn(member);
		Member getMember = service.getMemberByName(member.getName());
		assertEquals(member, getMember);
	}

	@Test
	void testGetMemberByNameFailCondition() throws MemberNotFoundException {
		when(memberRepo.findByName("naresh")).thenReturn(null);
		assertEquals(new MemberNotFoundException("Member Details Not with Name :" + "naresh"),
				service.getMemberByName("naresh"));
	}

	@Test
	void testUpdateMemberDetails() {
		Member member = member();
		when(memberRepo.findByMemberId(member.getMemberId())).thenReturn(member);
		when(memberRepo.save(member)).thenReturn(member);
		assertEquals(member, service.updateMemberDetails(member));

	}

}
