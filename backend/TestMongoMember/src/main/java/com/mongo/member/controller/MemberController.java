package com.mongo.member.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mongo.member.MemberRepo;
import com.mongo.member.entity.Member;

@RestController
public class MemberController {

	@Autowired
	private MemberRepo repo;

	@GetMapping("/message")
	public String message() {
		return "Welcome to Member Portal";
	}

	@PostMapping("/addMember")
	public String addMember(@RequestBody Member member) {
		repo.save(member);
		return "Member Added with ID:" + member.getId();
	}

	@GetMapping("/findAll")
	public List<Member> findall() {
		return repo.findAll();
	}

	@DeleteMapping("/find/{id}")
	public Optional<Member> findById(@PathVariable Integer id) {
		return repo.findById(id);
	}

	@PutMapping("/update")
	public String updateMember(@RequestBody Member member) {
		Optional<Member> response = repo.findById(member.getId());
		Member member1 = response.get();
		member1.setAge(member.getAge());
		member1.setMailId(member.getMailId());
		member1.setName(member.getName());
		repo.save(member1);
		return "Member Details updated with Id:" + member1.getId();
	}

	@DeleteMapping("/delete/{id}")
	public String deleteMember(@PathVariable Integer id) {
		repo.deleteById(id);
		return "Member details deleted:" + id;
	}

}
