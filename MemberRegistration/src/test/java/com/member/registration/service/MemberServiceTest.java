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
	void testAddMemberPassCondition() throws MemberAlreadyExistException {

		Member member = member();
		when(memberRepo.findByEmailAdress(member.getEmailAdress())).thenReturn(null);
		when(memberRepo.save(member)).thenReturn(member);
		String savedMember = service.addMember(member);
		assertEquals(member.getMemberId(), savedMember);
	}

	@Test
	void testAddMemberFailCondition() throws MemberAlreadyExistException {

		Member member = member();
		when(memberRepo.findByEmailAdress(member.getEmailAdress())).thenReturn(member);
		assertThrows(MemberAlreadyExistException.class, () -> service.addMember(member),
				"This method throws MemberAlreadyExistException");

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
		assertThrows(MemberNotFoundException.class, () -> service.getMemberByName("naresh"),
				"This method throws exception");

	}

	@Test
	void testUpdateMemberDetailsPassCondition() throws MemberNotFoundException {
		Member member = member();
		when(memberRepo.findByMemberId(member.getMemberId())).thenReturn(member);
		when(memberRepo.save(member)).thenReturn(member);
		assertEquals(member, service.updateMemberDetails(member));

	}
	@Test
	void testUpdateMemberDetailsFailCondition() throws MemberNotFoundException {
		Member member = member();
		when(memberRepo.findByMemberId(member.getMemberId())).thenReturn(null);
		assertThrows(MemberNotFoundException.class, () -> service.updateMemberDetails(member),
				"This method throws exception");
	}

}
