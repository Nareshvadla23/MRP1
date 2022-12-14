package com.member.registration.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberDto {
	private String memberId;
	private String name;
	private String address;
	private String state;
	private String country;
	private String emailAddress;
	private String pan;
	private Long contactNo;
	private LocalDate dob;
}
