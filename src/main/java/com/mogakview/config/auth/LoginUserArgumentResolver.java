package com.mogakview.config.auth;

import com.mogakview.application.auth.AuthService;
import com.mogakview.infrasturcture.auth.JwtAccessToken;
import com.nimbusds.oauth2.sdk.token.AccessToken;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer";

    private final AuthService authService;
    private final JwtAccessToken jwtAccessToken;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = Objects.requireNonNull(
            webRequest.getNativeRequest(HttpServletRequest.class));
        String findAuthorization = request.getHeader(AUTHORIZATION);
        if (checkBearer(findAuthorization)) {
            String extractAuthorization = findAuthorization.substring(BEARER.length()).trim();
            Long userId = authService.extractUserIdByToken(extractAuthorization, jwtAccessToken);
            AppUser loginUser = authService.createLoginUser(userId);
            return loginUser;
        }

        // 커스텀 에러 추가 예정
        throw new RuntimeException();
    }

    private boolean checkBearer(String authorization) {
        return authorization.startsWith(BEARER);
    }
}
