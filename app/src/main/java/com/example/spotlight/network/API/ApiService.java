package com.example.spotlight.network.API;

import androidx.annotation.Nullable;

import com.example.spotlight.posting.Post;
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

    // 회원가입
    @POST("/api/user/register")
    Call<UserResponse> registerUser(@Body UserRegistrationDTO registrationDto);

    // 아이디 중복 체크
    @POST("/api/user/existusername")
    Call<ExistIdResponse> existId(@Body ExistIdRequest existIdRequest);

    // 비밀번호 유효성 검사
    @POST("/api/user/validate-password")
    Call<PasswordValidationResponseDTO> validatePassword(@Body PasswordValidationDTO passwordDTO);

    // 이메일 인증 코드 발송
    @POST("/api/user/send-email-verification")
    Call<String> emailSending(@Body EmailSendingRequest emailSendingRequest);

    // 이메일 인증 코드 검증
    @POST("/api/user/verify-email-verification")
    Call<String> verificationCode(@Body EmailSendingRequest emailSendingRequest);

//    // 학생 재학증명서 업로드
//    @Multipart
//    @POST("/api/user/upload-student-certificate")
//    Call<CertificateResponse> uploadStudentCertificate(
//            @Header("Authorization") String token,
//            @Part("file") MultipartBody.Part file,
//            @Part("username") RequestBody username);
//
//    // 리크루터 재직증명서 업로드
//    @Multipart
//    @POST("/api/user/upload-recruiter-certificate")
//    Call<CertificateResponse> uploadRecruiterCertificate(
//            @Header("Authorization") String token,
//            @Part("file") MultipartBody.Part file,
//            @Part("username") RequestBody username);

    // 로그인
    @POST("/api/user/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
    // 토큰 갱신
    @POST("/api/v1/user/refresh")
    Call<Map<String, String>> refreshAccessToken(@Body RefreshRequest tokenRequest);

    // 프로필 조회
    @GET("/api/user/profile")
    Call<UserProfileResponse> getProfile();

    // 프로필 수정
    @PUT("/api/user/profile")
    @Multipart
    Call<UserProfileResponse> updateProfile(@PartMap Map<String, RequestBody> userProfileUpdateRequest, @Part MultipartBody.Part profileImage);

    // 알림 목록 확인
    @GET("/api/notification")
    Call<NotificationListResponse> getNotifications();

    // 알림 읽음 상태 업데이트
    @PUT("/api/notification/{notification_id}/status")
    Call<String> markAsRead(@Path("notification_id") int notificationId);

    // 팀원 수락/거절 -> 수락 시 수정 권한 및 팀원 추가

    // 스크랩 피드 목록 확인
    @GET("/api/v1/user/scrap/feed")
    Call<List<Post>> getScrapFeeds();

    // 스크랩 피드 세부 확인

    // 스크랩 인재 확인
    @GET("/api/v1/user/scrap/students")
    Call<List<ScrapDTO>> getScrapStudents();

    // 공고 제안서 목록 조회 (학생)
    @GET("/api/proposals/list/student")
    Call<List<ProposalResponse>> getProposalsByStudent();

    // 공고 제안서 목록 조회 (리크루터)
    @GET("/api/proposals/list/recruiter")
    Call<List<ProposalResponse>> getProposalsByRecruiter();

    // 공고 제안서 세부 내용 조회
    @GET("/api/proposals/details")
    Call<ProposalResponse> getProposalDetails(
            @Query("proposalId") Long proposalId,
            @Query("isStudent") boolean isStudent);

    // 내가 올린 피드 목록 조회
    @GET("/api/feeds/my-feeds")
    Call<List<Post>> getMyFeeds();

    // 내 피드 내용 조회
    // 재직증명서 업로드
    @POST("/api/v1/user/certificate")
    @Multipart
    Call<CertificateResponse> uploadCertificate(@Part MultipartBody.Part certificate);

    // 포트폴리오 사진 업로드 (학생)
    @POST("/api/user/portfolio")
    @Multipart
    Call<UploadPortfolioResponse> uploadPortfolio(@Part List<MultipartBody.Part> images);

    // 포트폴리오 조회 (리크루터)
    @GET("/api/user/{id}/portfolio")
    Call<PortfolioResponse> getPortfolio(@Path("id") Long id);


    /*-------------------------- 피드 관련 API --------------------------*/

    // 게시물 등록
    @POST("/api/feeds")
    Call<FeedDTO> createFeed(@Body FeedRequest feedRequest);

    // 게시물 조회
    @GET("/api/feeds/{feedId}")
    Call<FeedResponse> getFeed(@Path("feedId") Long feedId);

    // 게시물 수정
    @PUT("/api/feeds/{feedId}")
    Call<FeedResponse> updateFeed(@Path("feedId") Long feedId, @Body FeedRequest feedRequest);

    // 게시물 삭제
    @DELETE("/api/feeds/{feedId}")
    Call<DeleteResponse> deleteFeed(@Path("feedId") Long feedId);

    // 게시물 조회수 증가
    @POST("/api/feeds/{feedId}/hits")
    Call<FeedResponse> incrementFeedHits(@Path("feedId") Long feedId);

    // 피드 스크랩
    @POST("/api/feeds/{feedId}/scrap")
    Call<ScrapResponse> scrapFeed(
            @Path("feedId") Long feedId,
            @Query("stageId") @Nullable Long stageId,
            @Query("scrappedUserId") @Nullable Long scrappedUserId
    );

    // 피드 스크랩 취소
    @DELETE("/api/feeds/{feedId}/scrap")
    Call<ScrapResponse> unscrapFeed(
            @Path("feedId") Long feedId,
            @Query("stageId") @Nullable Long stageId,
            @Query("scrappedUserId") @Nullable Long scrappedUserId
    );

    // 프로젝트 팀원 조회
    @GET("/api/feeds/{feedId}/team-members/{userId}")
    Call<MemberDTO> getProjectTeamMemberInfo(@Path("feedId") Long feedId, @Path("userId") Long userId);

    // 학생 스크랩 상태 확인
    @GET("/api/feeds/{feedId}/students/{studentId}/scrap/status")
    Call<Boolean> checkStudentScrapStatus(@Path("feedId") Long feedId, @Path("studentId") Long studentId);

    // 학생 스크랩
    @POST("/api/feeds/{feedId}/students/{studentId}/scrap")
    Call<ScrapResponse> scrapStudent(@Path("feedId") Long feedId, @Path("studentId") Long studentId);

    // 학생 스크랩 취소
    @DELETE("/api/feeds/{feedId}/students/{studentId}/scrap")
    Call<ScrapResponse> unscrapStudent(@Path("feedId") Long feedId, @Path("studentId") Long studentId);

    /*-------------------------- 전시 관련 API --------------------------*/

    // 전시 등록
    @POST("api/exhibitions")
    Call<ExhibitionResponse> createExhibition(@Body ExhibitionRequest exhibitionRequest);

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
    Call<ProposalResponse> createProposal(@Body ProposalRequest proposalRequest);

    // 기존 제안서 수정
    @PUT("api/proposals/{proposalId}")
    Call<ProposalResponse> updateProposal(@Path("proposalId") Long proposalId, @Body ProposalRequest proposalRequest);

    /*-------------------------- 해시태그 검색 관련 API --------------------------*/

    // 피드 속 해시태그를 통해 피드 검색
    @GET("/api/feeds/search")
    Call<List<FeedDTO>> searchFeedsByHashtag(@Query("hashtag") String hashtag);

    // 해시태그 검색창을 통한 해시태그 검색
    @GET("api/search/feeds")
    Call<List<FeedDTO>> searchFeedsWithHashtag(@Query("hashtag") String hashtag);

    // 검색 기록 조회
    @GET("api/search/history")
    Call<List<String>> getHashtagSearchHistory();

    // 검색 이력 기반 피드 검색
    @GET("api/search/feeds/history")
    Call<List<FeedDTO>> searchFeedsBySearchHistory(@Query("searchTerm") String searchTerm);

    /*-------------------------- 학교/학과 검색 API --------------------------*/

    // 학교 또는 학과 검색
    @GET("api/search/schoolormajor")
    Call<List<FeedDTO>> searchFeedsBySchoolOrMajor(
            @Query("school") String school,
            @Query("major") String major
    );

    /*-------------------------- 챗봇 API --------------------------*/

    // 챗봇 요청
    @POST("api/chat/ask")
    Call<List<Object>> askChatbot(@Body ChatbotRequest chatbotRequest);

}