package com.gauth.practice.domain.member.repository;

import com.gauth.practice.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
