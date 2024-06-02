package com.example.spotlight.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    // 회원가입 0
    // 아이디 중복 체크 0
    // 비밀번호 확인 0
    // 이메일 전송 0
    // 인증 코드 확인 0
    // 로그인 0
    @POST("/api/v1/user/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
    // 토큰 갱신 0
    // 프로필 조회 0
    // 프로필 수정 0
    // 알림 보내기 (제안서 등록, 수정, 팀원 추가 로직에 포함) 0
    // 알림 목록 확인 0
    // 알림 읽음 상태 업데이트 0
    // 팀원 수락/거절 -> 수락 시 수정 권한 및 팀원 추가 0
    // 스크랩 피드 목록 확인 0
    // 스크랩 피드 세부 확인
    // 스크랩 인재 확인 0
    // 포트폴리오 조회 (리크루터)
    // 공고제안서 목록 조회 (학생) 0
    // 공고제안서 내용 조회 (학생) 0
    // 공고제안서 목록 조회 (리크루터) 0
    // 공고제안서 내용 조회 (리크루터) 0
    // 내가 올린 피드 목록 조회 0
    // 내 피드 내용 조회
    // 재직증명서 업로드 0
    // 프로필 사진 업로드 (프로필 수정) 0
    // 포트폴리오 사진 업로드 (학생) 0
    // 포트폴리오 조회 (리크루터) 0

    //////////////////////////////

    // 주은 님 api 자리






}
