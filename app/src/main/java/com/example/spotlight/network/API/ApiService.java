package com.example.spotlight.network.API;

import com.example.spotlight.network.DTO.ProposalDTO;
import com.example.spotlight.network.Request.FeedRequest;
import com.example.spotlight.network.Request.InvitationRequest;
import com.example.spotlight.network.Request.LoginRequest;
import com.example.spotlight.network.Request.ProposalRequest;
import com.example.spotlight.network.Response.ApiResponse;
import com.example.spotlight.network.Response.DeleteResponse;
import com.example.spotlight.network.Response.ExhibitionResponse;
import com.example.spotlight.network.Response.FeedHitsResponse;
import com.example.spotlight.network.Response.FeedResponse;
import com.example.spotlight.network.Response.HashtagHistoryResponse;
import com.example.spotlight.network.Response.InvitationResponse;
import com.example.spotlight.network.Response.LoginResponse;
import com.example.spotlight.network.Response.MemberResponse;
import com.example.spotlight.network.Response.ScrapCancelResponse;
import com.example.spotlight.network.Response.ScrapResponse;
import com.example.spotlight.network.Response.SearchResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    // 게시물 등록
    @POST("/api/v1/feed")
    Call<FeedResponse> createFeed(@Body FeedRequest feedRequest);

    // 게시물 수정
    @PUT("/api/v1/feed/{feedId}")
    Call<FeedResponse> updateFeed(@Path("feedId") int feedId, @Body FeedRequest feedRequest);

    // 게시물 삭제
    @DELETE("/api/v1/feed/{feedId}")
    Call<DeleteResponse> deleteFeed(@Path("feedId") int feedId);

    // 게시물 조회수 조회
    @GET("/api/v1/feed/{feedId}/hits")
    Call<FeedHitsResponse> getFeedHits(@Path("feedId") int feedId);

    // 팀원 정보 조회
    @GET("/api/v1//feed/members/{studentid}")
    Call<MemberResponse> getMembersByStudentId(@Path("studentid") Integer studentId);

    // 팀원 스크랩
    @POST("/api/v1//feed/members/{studentid}/scrap")
    Call<Map<String, Object>> scrapMember(@Path("studentid") Integer studentId);

    // 팀원 스크랩 취소
    @DELETE("/api/v1/members/{studentid}/scrap")
    Call<Map<String, Object>> unscrapMember(@Path("studentid") Integer studentId);

    // 팀원 프로젝트 초대
    @POST("/api/v1/feed/invite-member")
    Call<InvitationResponse> inviteMemberToProject(@Body InvitationRequest invitationRequest);

    // 전시 정보 조회
    @GET("/api/v1/feed/exhibition")
    Call<ExhibitionResponse> getExhibition();

    // 게시물 스크랩
    @POST("/api/v1/feed/{feedId}/scrap")
    Call<ScrapResponse> scrapFeed(@Path("feedId") Integer feedId);

    // 게시물 스크랩 취소
    @DELETE("/api/v1/feed/{feedId}/scrap")
    Call<ScrapCancelResponse> cancelScrapFeed(@Path("feedId") Integer feedId);

    // 해시태그 검색
    @GET("/api/v1/search")
    Call<SearchResponse> searchByHashtag(
            @Header("Authorization") String accessToken,
            @Query("hashtag") String hashtag
    );

    // 해시태그 클릭 검색
    @GET("/api/v1/feed/search")
    Call<SearchResponse> searchByFeedHashtag(
            @Query("hashtag") String hashtag,
            @Query("accessToken") String accessToken
    );

    // 해시태그 검색이력
    @GET("/api/v1/search/history")
    Call<HashtagHistoryResponse> getHashtagHistory(
            @Header("Authorization") String token
    );

    // 해시태그 검색이력 기반 조회
    @GET("/api/v1/search/history/results")
    Call<SearchResponse> searchFeedsByHistory(
            @Header("Authorization") String token
    );

    // 학교/학과 검색
    @GET("/api/v1/work/search-posts")
    Call<SearchResponse> searchPosts(
            @Query("apiKey") String apiKey,
            @Query("school") String school,
            @Query("major") String major
    );

    // 공고 제안서 저장
    @POST("/api/v1/proposal")
    Call<ApiResponse> createProposal(
            @Header("Authorization") String token,
            @Body ProposalDTO proposalDTO
    );

    // 공고 제안서 수정
    @PUT("/api/v1/proposal/{proposalId}")
    Call<ApiResponse> updateProposal(@Path("proposalId") int proposalId, @Body ProposalRequest updateRequest, @Header("Authorization") String token);
}