package com.example.spotlight.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    // 회원가입
    // 아이디 중복 체크
    // 비밀번호 확인
    // 이메일 전송
    // 인증 코드 확인
    // 로그인
    @POST("/api/v1/user/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
    // 토큰 갱신
    // 프로필 조회
    // 프로필 수정
    // 알림 목록 확인
    // 알림 읽음 상태 업데이트
    // 팀원 수락/거절
    // 스크랩 피드 확인
    // 스크랩 인재 확인
    // 포트폴리오 조회 (리크루터)
    // 공고제안서 조회 (학생)
    // 공고제안서 조회 (리크루터)

    // 프론트엔드에서
    /*
    * 재직증명서 업로드
    * 프로필 사진 업로드 (프로필 수정)
    * 포트폴리오 사진 업로드 (학생)
    */

    //////////////////////////////

    // 주은 님 api 자리






}
