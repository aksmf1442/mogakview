package com.mogakview.application.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.mogakview.config.auth.AppUser;
import com.mogakview.domain.user.Role;
import com.mogakview.domain.user.SocialType;
import com.mogakview.domain.user.User;
import com.mogakview.domain.user.UserRepository;
import com.mogakview.dto.auth.AccessTokenResponse;
import com.mogakview.dto.auth.LoginRequest;
import com.mogakview.dto.auth.RefreshTokenResponse;
import com.mogakview.exception.auth.SocialTypeNotFoundException;
import com.mogakview.infrasturcture.auth.GoogleOauthManager;
import com.mogakview.infrasturcture.auth.JwtTokenProvider;
import com.mogakview.infrasturcture.auth.KakaoOauthManager;
import com.mogakview.infrasturcture.auth.OauthManagerFactory;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private UserRepository userRepository;

    @Mock
    private GoogleOauthManager googleOauthManager;

    @Mock
    KakaoOauthManager kakaoOauthManager;

    @Mock
    private OauthManagerFactory oauthManagerFactory;

    @InjectMocks
    private AuthService authService;

    @DisplayName("[성공]구글 로그인(토큰 만들기) - 회원가입되어 있는 유저")
    @Test
    void createTokenByGoogle() {
        //given
        LoginRequest loginRequest = LoginRequest.builder()
            .code("code")
            .build();
        User oauthUser = User.builder()
            .socialType(SocialType.GOOGLE)
            .role(Role.USER)
            .profileUrl("123")
            .userName("haneul")
            .socialId("haneul")
            .build();
        Optional<User> findUser = Optional.of(oauthUser);
        String accessToken = "accessToken";

        given(oauthManagerFactory.findOauthManagerBySocialType(SocialType.GOOGLE)).willReturn(googleOauthManager);
        given(googleOauthManager.findUserDetailByOauthCode(loginRequest.getCode())).willReturn(oauthUser);
        given(userRepository.findBySocialIdAndSocialType(oauthUser.getSocialId(), oauthUser.getSocialType())).willReturn(findUser);
        given(jwtTokenProvider.createAccessToken(oauthUser.getId())).willReturn(accessToken);

        //when
        AccessTokenResponse accessTokenResponse = authService.createToken("Google", loginRequest);

        //then
        assertThat(accessTokenResponse.getToken()).isEqualTo(accessToken);

        then(oauthManagerFactory)
            .should(times(1))
            .findOauthManagerBySocialType(SocialType.GOOGLE);
        then(googleOauthManager)
            .should(times(1))
            .findUserDetailByOauthCode(loginRequest.getCode());
        then(userRepository)
            .should(times(1))
            .findBySocialIdAndSocialType(oauthUser.getSocialId(), oauthUser.getSocialType());
        then(jwtTokenProvider)
            .should(times(1))
            .createAccessToken(oauthUser.getId());
    }

    @DisplayName("[성공]카카오 로그인(토큰 만들기) - 회원가입되어 있는 유저")
    @Test
    void createTokenByKakao() {
        //given
        LoginRequest loginRequest = LoginRequest.builder()
            .code("code")
            .build();
        User oauthUser = User.builder()
            .socialType(SocialType.KAKAO)
            .role(Role.USER)
            .profileUrl("123")
            .userName("haneul")
            .socialId("haneul")
            .build();
        Optional<User> findUser = Optional.of(oauthUser);
        String accessToken = "accessToken";

        given(oauthManagerFactory.findOauthManagerBySocialType(SocialType.KAKAO)).willReturn(kakaoOauthManager);
        given(kakaoOauthManager.findUserDetailByOauthCode(loginRequest.getCode())).willReturn(oauthUser);
        given(userRepository.findBySocialIdAndSocialType(oauthUser.getSocialId(), oauthUser.getSocialType())).willReturn(findUser);
        given(jwtTokenProvider.createAccessToken(oauthUser.getId())).willReturn(accessToken);

        //when
        AccessTokenResponse accessTokenResponse = authService.createToken("Kakao", loginRequest);

        //then
        assertThat(accessTokenResponse.getToken()).isEqualTo(accessToken);

        then(oauthManagerFactory)
            .should(times(1))
            .findOauthManagerBySocialType(SocialType.KAKAO);
        then(kakaoOauthManager)
            .should(times(1))
            .findUserDetailByOauthCode(loginRequest.getCode());
        then(userRepository)
            .should(times(1))
            .findBySocialIdAndSocialType(oauthUser.getSocialId(), oauthUser.getSocialType());
        then(jwtTokenProvider)
            .should(times(1))
            .createAccessToken(oauthUser.getId());
    }

    @DisplayName("[성공]구글 로그인(토큰 만들기) - 처음 로그인하는 유저(회원가입)")
    @Test
    void createTokenByGoogleAndSaveUser() {
        //given
        LoginRequest loginRequest = LoginRequest.builder()
            .code("code")
            .build();
        User oauthUser = User.builder()
            .socialType(SocialType.GOOGLE)
            .role(Role.USER)
            .profileUrl("123")
            .userName("haneul")
            .socialId("haneul")
            .build();
        Optional<User> findUser = Optional.empty();
        String accessToken = "accessToken";

        given(oauthManagerFactory.findOauthManagerBySocialType(SocialType.GOOGLE)).willReturn(googleOauthManager);
        given(googleOauthManager.findUserDetailByOauthCode(loginRequest.getCode())).willReturn(oauthUser);
        given(userRepository.findBySocialIdAndSocialType(oauthUser.getSocialId(), oauthUser.getSocialType())).willReturn(findUser);
        given(jwtTokenProvider.createAccessToken(oauthUser.getId())).willReturn(accessToken);
        given(userRepository.save(oauthUser)).willReturn(oauthUser);

        //when
        AccessTokenResponse accessTokenResponse = authService.createToken("Google", loginRequest);

        //then
        assertThat(accessTokenResponse.getToken()).isEqualTo(accessToken);

        then(oauthManagerFactory)
            .should(times(1))
            .findOauthManagerBySocialType(SocialType.GOOGLE);
        then(googleOauthManager)
            .should(times(1))
            .findUserDetailByOauthCode(loginRequest.getCode());
        then(userRepository)
            .should(times(1))
            .findBySocialIdAndSocialType(oauthUser.getSocialId(), oauthUser.getSocialType());
        then(jwtTokenProvider)
            .should(times(1))
            .createAccessToken(oauthUser.getId());
        then(userRepository)
            .should(times(1))
            .save(oauthUser);
    }

    @DisplayName("[성공]카카오 로그인(토큰 만들기) - 처음 로그인하는 유저(회원가입)")
    @Test
    void createTokenByKakaoAndSaveUser() {
        //given
        LoginRequest loginRequest = LoginRequest.builder()
            .code("code")
            .build();
        User oauthUser = User.builder()
            .socialType(SocialType.KAKAO)
            .role(Role.USER)
            .profileUrl("123")
            .userName("haneul")
            .socialId("haneul")
            .build();
        Optional<User> findUser = Optional.empty();
        String accessToken = "accessToken";

        given(oauthManagerFactory.findOauthManagerBySocialType(SocialType.KAKAO)).willReturn(googleOauthManager);
        given(googleOauthManager.findUserDetailByOauthCode(loginRequest.getCode())).willReturn(oauthUser);
        given(userRepository.findBySocialIdAndSocialType(oauthUser.getSocialId(), oauthUser.getSocialType())).willReturn(findUser);
        given(jwtTokenProvider.createAccessToken(oauthUser.getId())).willReturn(accessToken);
        given(userRepository.save(oauthUser)).willReturn(oauthUser);

        //when
        AccessTokenResponse accessTokenResponse = authService.createToken("Kakao", loginRequest);

        //then
        assertThat(accessTokenResponse.getToken()).isEqualTo(accessToken);

        then(oauthManagerFactory)
            .should(times(1))
            .findOauthManagerBySocialType(SocialType.KAKAO);
        then(googleOauthManager)
            .should(times(1))
            .findUserDetailByOauthCode(loginRequest.getCode());
        then(userRepository)
            .should(times(1))
            .findBySocialIdAndSocialType(oauthUser.getSocialId(), oauthUser.getSocialType());
        then(jwtTokenProvider)
            .should(times(1))
            .createAccessToken(oauthUser.getId());
        then(userRepository)
            .should(times(1))
            .save(oauthUser);
    }

    @DisplayName("[실패] 로그인 - 허용해둔 소셜 타입이 아니면 SocialTypeNotFoundException 발생")
    @Test
    void createTokenWithSocialTypeNotFoundException() throws Exception {
        //given
        LoginRequest loginRequest = LoginRequest.builder()
            .code("code")
            .build();

        //when

        //then
        assertThatThrownBy(() -> authService.createToken("Googl", loginRequest))
            .isInstanceOf(SocialTypeNotFoundException.class);
    }

    @DisplayName("[성공] refresh 토큰 발급")
    @Test
    void createRefreshToken() throws Exception {
        //given
        Long id = 1L;
        String refreshToken = "refreshToken";
        given(jwtTokenProvider.createRefreshToken(id)).willReturn(refreshToken);

        //when
        RefreshTokenResponse refreshTokenResponse = authService.createRefreshToken(id);

        //then
        assertThat(refreshTokenResponse.getToken()).isEqualTo(refreshToken);

        then(jwtTokenProvider)
            .should(times(1))
            .createRefreshToken(id);
    }

    @DisplayName("[성공] accessToken 토큰 발급")
    @Test
    void createAccessToken() throws Exception {
        //given
        Long id = 1L;
        String accessToken = "accessToken";
        given(jwtTokenProvider.createAccessToken(id)).willReturn(accessToken);

        //when
        AccessTokenResponse accessTokenResponse = authService.createNewAccessToken(id);

        //then
        assertThat(accessTokenResponse.getToken()).isEqualTo(accessToken);

        then(jwtTokenProvider)
            .should(times(1))
            .createAccessToken(id);
    }

    @DisplayName("[성공] create AppUser")
    @Test
    void createAppUser() throws Exception {
        //given
        Long id = 1L;

        //when
        AppUser loginUser = authService.createLoginUser(id);

        //then
        assertThat(loginUser.getId()).isEqualTo(id);
    }


}
