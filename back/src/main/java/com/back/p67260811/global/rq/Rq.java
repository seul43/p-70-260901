package com.back.p67260811.global.rq;

import com.back.p67260811.domain.member.entity.Member;
import com.back.p67260811.domain.member.service.MemberService;
import com.back.p67260811.global.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
//@RequestScope
@RequiredArgsConstructor
public class Rq { //인증정보 등 반복되는 작업이 많으니 Rq라는 클래스에 외주작업을 준 개념

    private final MemberService memberService;
    private final HttpServletRequest request; //요청한 정보들이 쌓인다 리퀘스트 헤더&바디 등
    //Requestscope 타입이라 RQ가 싱글톤이어도 RequestScope를 가지고 있으면
    //내부가 프록시처럼 운영되어 객체가 여러개 생성된다

    public Member getActor() {

        String authorization = request.getHeader("Authorization");

        //인증작업
        if (authorization == null || authorization.isEmpty()) {
            throw new ServiceException("401-1", "헤더에 인증 정보가 없습니다.");
        }

        //인증작업
        if (!authorization.startsWith("Bearer ")) {
            throw new ServiceException("401-2", "헤더의 인증 정보 형식이 올바르지 않습니다.");
        }

        //인증작업
        Member actor = memberService.findByApiKey(authorization.replace("Bearer ", ""))
            .orElseThrow(() -> new ServiceException("401-3", "API 키가 올바르지 않습니다."));

        //인증 됐고 정상적인 회원 정보 반환
        return actor;
    }
}
