package com.mongo.member.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Document(collection = "member")
public class Member {

	@Id
	private Integer id;
	private String name;
	private Integer age;
	private String mailId;

}
