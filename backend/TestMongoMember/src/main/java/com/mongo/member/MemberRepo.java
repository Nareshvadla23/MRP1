package com.mongo.member;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongo.member.entity.Member;

public interface MemberRepo extends MongoRepository<Member, Integer>{

}
