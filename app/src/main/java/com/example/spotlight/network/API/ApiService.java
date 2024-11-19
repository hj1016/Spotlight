package com.example.spotlight.network.API;

import com.example.spotlight.Post;
import com.example.spotlight.network.DTO.*;
import com.example.spotlight.network.Request.*;
import com.example.spotlight.network.Response.*;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    // 회원가입 0
    @POST("/api/v1/user/register")
    Call<TokenResponse> registerUser(@Body UserRegistrationDto registrationDto);

    // 아이디 중복 체크 0
    @POST("/api/v1/user/existid")
    Call<ExistIdResponse> existId(@Body ExistIdRequest existIdRequest);

    // 비밀번호 확인 0
    @POST("/api/v1/user/validate-password")
    Call<PasswordValidationResponseDTO> validatePassword(@Body PasswordValidationDTO passwordDTO);

    // 이메일 전송 0
    @POST("/api/v1/user/emailsending")
    Call<EmailSendingResponse> emailSending(@Body EmailSendingRequest emailSendingRequest);

    // 인증 코드 확인 0
    @POST("/api/v1/user/verificationcode")
    Call<TokenResponse> verificationCode(@Body EmailSendingRequest emailSendingRequest);

    // 로그인 0
    @POST("/api/v1/user/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
    // 토큰 갱신 0
    @POST("/api/v1/user/refresh")
    Call<Map<String, String>> refreshAccessToken(@Body RefreshRequest tokenRequest);

    // 프로필 조회 0
    @GET("/api/v1/user/profile")
    Call<UserProfileResponse> getProfile();

    // 프로필 수정 0
    @POST("/api/v1/user/profile")
    @Multipart
    Call<UserProfileResponse> updateProfile(@PartMap Map<String, RequestBody> userProfileUpdateRequest, @Part MultipartBody.Part profileImage);

    // 알림 목록 확인 0
    @GET("/api/v1/notification")
    Call<List<NotificationResponse>> getNotifications();

    // 알림 읽음 상태 업데이트 0
    @PUT("/api/v1/notification/{notification_id}/status")
    Call<String> markAsRead(@Path("notification_id") int notificationId);

    // 팀원 수락/거절 -> 수락 시 수정 권한 및 팀원 추가 0

    // 스크랩 피드 목록 확인 0
    @GET("/api/v1/user/scrap/feed")
    Call<List<Post>> getScrapFeeds();

    // 스크랩 피드 세부 확인

    // 스크랩 인재 확인 0
    @GET("/api/v1/user/scrap/students")
    Call<List<ScrapDTO>> getScrapStudents();

    // 공고제안서 목록 조회 (학생) 0
    @GET("/api/v1/proposal/student")
    Call<List<ProposalResponse>> getProposalsByStudent();

    // 공고제안서 내용 조회 (학생) 0
    @GET("/api/v1/proposal/student/{proposalId}")
    Call<ProposalResponse> getProposalDetailsForStudent(@Path("proposalId") int proposalId);

    // 공고제안서 목록 조회 (리크루터) 0
    @GET("/api/v1/proposal/recruiter")
    Call<List<ProposalResponse>> getProposalsByRecruiter();

    // 공고제안서 내용 조회 (리크루터) 0
    @GET("/api/v1/proposal/recruiter/{proposalId}")
    Call<ProposalResponse> getProposalDetailsForRecruiter(@Path("proposalId") int proposalId);

    // 내가 올린 피드 목록 조회 0
    @GET("/api/v1/user/feed")
    Call<List<Post>> getMyFeeds();

    // 내 피드 내용 조회
    // 재직증명서 업로드 0
    @POST("/api/v1/user/certificate")
    @Multipart
    Call<CertificateResponse> uploadCertificate(@Part MultipartBody.Part certificate);

    // 포트폴리오 사진 업로드 (학생) 0
    @POST("/api/v1/user/portfolio")
    @Multipart
    Call<UploadPortfolioResponse> uploadPortfolio(@Part List<MultipartBody.Part> images);

    // 포트폴리오 조회 (리크루터) 0
    @GET("/api/v1/user/{userid}/portfolio")
    Call<PortfolioResponse> getPortfolio(@Path("userid") Integer userId);


    /*-------------------------- 피드 관련 API --------------------------*/

    // 게시물 등록
    @POST("/api/feeds")
    Call<FeedResponse> createFeed(@Body FeedRequest feedRequest);

    // 게시물 조회
    @GET("/api/feeds/{feedId}")
    Call<FeedResponse> getFeed(@Path("feedId") Long feedId);

    // 게시물 수정
    @PUT("/api/feeds/{feedId}")
    Call<FeedResponse> updateFeed(@Path("feedId") Long feedId, @Body FeedRequest feedRequest);

    // 게시물 삭제
    @DELETE("/api/feeds/{feedId}")
    Call<DeleteResponse> deleteFeed(@Path("feedId") Long feedId);

    // 해시태그를 통해 피드 검색
    @GET("/api/feeds/search")
    Call<List<FeedResponse>> searchFeedsByHashtag(@Query("hashtag") String hashtag);

    // 해시태그 검색 이력 조회
    @GET("/api/feeds/search/history")
    Call<HashtagHistoryResponse> getFeedSearchHistory();

    // 검색 기록 기반 해시태그로 피드 검색
    @GET("/api/feeds/search/history/{hashtag}")
    Call<List<FeedResponse>> searchFeedsByHistoryHashtag(@Path("hashtag") String hashtag);

    // 게시물 조회수 증가
    @POST("/api/feeds/{feedId}/hits")
    Call<FeedResponse> incrementFeedHits(@Path("feedId") Long feedId);

    // 피드 스크랩
    @POST("/api/feeds/{feedId}/scrap")
    Call<ScrapResponse> scrapFeed(
            @Path("feedId") Long feedId,
            @Query("stageId") Long stageId,
            @Query("scrappedUserId") Long scrappedUserId
    );

    // 피드 스크랩 취소
    @DELETE("/api/feeds/{feedId}/scrap")
    Call<UnscrapResponse> unscrapFeed(
            @Path("feedId") Long feedId,
            @Query("stageId") Long stageId,
            @Query("scrappedUserId") Long scrappedUserId
    );

    // 프로젝트 팀원 조회
    @GET("/api/feeds/{feedId}/team-members/{userId}")
    Call<MemberDTO> getProjectTeamMemberInfo(@Path("feedId") Long feedId, @Path("userId") Long userId);

    // 학생 스크랩
    @POST("/api/feeds/{feedId}/students/{studentId}/scrap")
    Call<ScrapResponse> scrapStudent(@Path("feedId") Long feedId, @Path("studentId") Long studentId);

    // 학생 스크랩 취소
    @DELETE("/api/feeds/{feedId}/students/{studentId}/scrap")
    Call<UnscrapResponse> unscrapStudent(@Path("feedId") Long feedId, @Path("studentId") Long studentId);

    /*-------------------------- 전시 관련 API --------------------------*/

    // 전시 등록
    @POST("api/exhibitions")
    Call<ExhibitionResponse> createExhibition(@Body ExhibitionDTO exhibitionDTO);

    /*-------------------------- 프로젝트 관련 API --------------------------*/

    // 팀원 초대
    @POST("api/projects/{projectId}/invite")
    Call<InvitationResponse> inviteTeamMember(
            @Path("projectId") Long projectId,
            @Query("username") String username,
            @Query("role") String role
    );

    // 초대 수락
    @POST("api/projects/accept/{projectRoleId}")
    Call<Void> acceptInvitation(@Path("projectRoleId") Long projectRoleId, @Query("userId") Long userId);

    /*-------------------------- 제안서 관련 API --------------------------*/

    // 새 제안서 생성
    @POST("api/proposals")
    Call<ProposalResponse> createProposal(@Query("userId") Long userId, @Body ProposalDTO proposalDTO);

    // 기존 제안서 수정
    @PUT("api/proposals/{proposalId}")
    Call<ProposalResponse> updateProposal(@Path("proposalId") Long proposalId, @Body ProposalDTO proposalDTO);

    /*-------------------------- 해시태그 검색 관련 API --------------------------*/

    // 해시태그를 통한 피드 검색
    @GET("api/search/feeds")
    Call<List<FeedResponse>> searchFeedsWithHashtag(@Query("hashtag") String hashtag);

    // 검색 기록 조회
    @GET("api/search/history")
    Call<HashtagHistoryResponse> getHashtagSearchHistory();

    // 검색 이력 기반 피드 검색
    @GET("api/search/feeds/history")
    Call<List<FeedResponse>> searchFeedsBySearchHistory(@Query("searchTerm") String searchTerm);

    /*-------------------------- 학교/학과 검색 API --------------------------*/

    // 학교 또는 학과 검색
    @GET("api/search/schoolormajor")
    Call<SearchResponse> searchFeedsBySchoolOrMajor(
            @Query("school") String school,
            @Query("major") String major
    );

    /*-------------------------- 챗봇 API --------------------------*/

    // 챗봇 요청
    @POST("api/chat/ask")
    Call<List<Object>> askChatbot(@Body ChatbotRequest chatbotRequest);

}