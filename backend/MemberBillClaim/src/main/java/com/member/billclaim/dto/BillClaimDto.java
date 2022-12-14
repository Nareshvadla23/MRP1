package com.member.billclaim.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BillClaimDto {
	private String memberId;
	private String name;
	private LocalDate dob;
	private LocalDate dateofAdmission;
	private LocalDate dateofDischarge;
	private String providerName;
	private Long billAmount;
}
