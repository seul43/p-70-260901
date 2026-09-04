package com.back.p67260811.domain.member.dto;

import com.back.p67260811.domain.member.entity.Member;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.time.LocalDateTime;
@SecurityRequirement(name = "bearerAuth")
public record MemberDto(
    int id,
    LocalDateTime createDate,
    LocalDateTime modifyDate,
    String name
) {
    public MemberDto(Member member) {
        this(
            member.getId(),
            member.getCreateDate(),
            member.getModifyDate(),
            member.getNickname()
        );
    }
}
