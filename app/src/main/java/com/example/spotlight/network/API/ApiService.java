package com.example.spotlight.network.API;

import com.example.spotlight.network.DTO.FeedDTO;
import com.example.spotlight.network.DTO.PasswordValidationDTO;
import com.example.spotlight.network.DTO.PasswordValidationResponseDTO;
import com.example.spotlight.network.DTO.ProposalDTO;
import com.example.spotlight.network.DTO.ScrapDTO;
import com.example.spotlight.network.DTO.UserRegistrationDto;
import com.example.spotlight.network.Request.EmailSendingRequest;
import com.example.spotlight.network.Request.ExistIdRequest;
import com.example.spotlight.network.Request.FeedRequest;
import com.example.spotlight.network.Request.InvitationRequest;
import com.example.spotlight.network.Request.LoginRequest;
import com.example.spotlight.network.Request.ProposalRequest;
import com.example.spotlight.network.Request.RefreshRequest;
import com.example.spotlight.network.Response.ApiResponse;
import com.example.spotlight.network.Response.CertificateResponse;
import com.example.spotlight.network.Response.DeleteResponse;
import com.example.spotlight.network.Response.EmailSendingResponse;
import com.example.spotlight.network.Response.ExhibitionResponse;
import com.example.spotlight.network.Response.ExistIdResponse;
import com.example.spotlight.network.Response.FeedHitsResponse;
import com.example.spotlight.network.Response.FeedResponse;
import com.example.spotlight.network.Response.HashtagHistoryResponse;
import com.example.spotlight.network.Response.InvitationResponse;
import com.example.spotlight.network.Response.LoginResponse;
import com.example.spotlight.network.Response.MemberResponse;
import com.example.spotlight.network.Response.NotificationResponse;
import com.example.spotlight.network.Response.PortfolioResponse;
import com.example.spotlight.network.Response.ScrapCancelResponse;
import com.example.spotlight.network.Response.ScrapResponse;
import com.example.spotlight.network.Response.SearchResponse;
import com.example.spotlight.network.Response.TokenResponse;
import com.example.spotlight.network.Response.UploadPortfolioResponse;
import com.example.spotlight.network.Response.UserProfileResponse;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
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
    Call<List<NotificationResponse>> getNotifications(@Header("Authorization") String token);

    // 알림 읽음 상태 업데이트 0
    @PUT("/api/v1/notification/{notification_id}/status")
    Call<String> markAsRead(@Path("notification_id") int notificationId);

    // 팀원 수락/거절 -> 수락 시 수정 권한 및 팀원 추가 0

    // 스크랩 피드 목록 확인 0
    @GET("/api/v1/user/scrap/feed")
    Call<List<FeedDTO>> getScrapFeeds();

    // 스크랩 피드 세부 확인

    // 스크랩 인재 확인 0
    @GET("/api/v1/user/scrap/students")
    Call<List<ScrapDTO>> getScrapStudents();

    // 공고제안서 목록 조회 (학생) 0
    @GET("/api/proposal/student")
    Call<List<ProposalDTO>> getProposalsByStudent(@Header("Authorization") String token);

    // 공고제안서 내용 조회 (학생) 0
    @GET("/api/proposal/student/{proposalId}")
    Call<ProposalDTO> getProposalDetailsForStudent(@Path("proposalId") int proposalId, @Header("Authorization") String token);

    // 공고제안서 목록 조회 (리크루터) 0
    @GET("/api/proposal/recruiter")
    Call<List<ProposalDTO>> getProposalsByRecruiter(@Header("Authorization") String token);

    // 공고제안서 내용 조회 (리크루터) 0
    @GET("/api/proposal/recruiter/{proposalId}")
    Call<ProposalDTO> getProposalDetailsForRecruiter(@Path("proposalId") int proposalId, @Header("Authorization") String token);

    // 내가 올린 피드 목록 조회 0
    @GET("/api/v1/user/feed")
    Call<List<FeedDTO>> getMyFeeds();

    // 내 피드 내용 조회
    // 재직증명서 업로드 0
    @POST("/api/v1/user/certificate")
    @Multipart
    Call<CertificateResponse> uploadCertificate(@Part MultipartBody.Part certificate);

    // 포트폴리오 사진 업로드 (학생) 0
    @POST("/api/v1/user/portfolio")
    @Multipart
    Call<UploadPortfolioResponse> uploadPortfolio(@PartMap Map<String, RequestBody> uploadPortfolioRequest, @Part List<MultipartBody.Part> images);

    // 포트폴리오 조회 (리크루터) 0
    @GET("/api/v1/user/{userid}/portfolio")
    Call<PortfolioResponse> getPortfolio(@Path("userid") Integer userId);


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