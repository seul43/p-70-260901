package com.back.p67260811.domain.repository;

import com.back.p67260811.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
