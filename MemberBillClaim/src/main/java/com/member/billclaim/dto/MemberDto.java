package com.member.billclaim.dto;

import com.member.billclaim.entity.Member;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberDto {
	private Member member;
	private String response;
}