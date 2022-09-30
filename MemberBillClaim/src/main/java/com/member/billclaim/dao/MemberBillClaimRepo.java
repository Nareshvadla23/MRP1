package com.member.billclaim.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.member.billclaim.entity.BillClaim;

@Repository
public interface MemberBillClaimRepo extends JpaRepository<BillClaim, Long> {

	public BillClaim findByName(String name);

}
