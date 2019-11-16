package com.oneday.sofa.domain.member.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oneday.sofa.domain.member.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
	
	Optional<Member> findByUserName(String userName);
	
	boolean existsByUserName(String userName);
	
	boolean existsByEmail(String email);
}
