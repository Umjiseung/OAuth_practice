package com.gauth.practice.domain.auth.service;

import com.gauth.practice.domain.auth.presentation.dto.request.SignInRequest;
import com.gauth.practice.domain.auth.presentation.dto.response.TokenResponse;
import com.gauth.practice.domain.member.Member;
import com.gauth.practice.domain.member.StudentNum;
import com.gauth.practice.domain.member.repository.MemberRepository;
import gauth.GAuth;
import gauth.GAuthToken;
import gauth.GAuthUserInfo;
import gauth.exception.GAuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final MemberRepository memberRepository;
    private final GAuth gAuth;

    @Value("gauth.clientId")
    private String clientId;
    @Value("gauth.clientSecret")
    private String clientSecret;
    @Value("gauth.redirectUri")
    private String redirectUri;

    public TokenResponse signIn(SignInRequest signInRequest) {
        try {
            GAuthToken gAuthToken = gAuth.generateToken(
                    signInRequest.getCode(),
                    clientId,
                    clientSecret,
                    redirectUri
            );

            GAuthUserInfo userInfo = gAuth.getUserInfo(gAuthToken.getAccessToken());
            memberRepository.findByEmail(userInfo.getEmail())
                    .orElse(memberRepository.save(saveMember(userInfo)));


        } catch (GAuthException e) {
            log.info(String.valueOf(e));
        } catch (IOException e) {
        }
    }

    private Member saveMember(GAuthUserInfo gAuthUserInfo) {
        Member member = Member.builder()
                .email(gAuthUserInfo.getEmail())
                .studentNum(new StudentNum(gAuthUserInfo.getGrade(), gAuthUserInfo.getClassNum(), gAuthUserInfo.getNum()))
                .name(gAuthUserInfo.getName())
                .build();
    }


}
