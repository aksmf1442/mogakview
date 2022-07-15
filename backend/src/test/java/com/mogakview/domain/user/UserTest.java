package com.mogakview.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.mogakview.config.auth.AppUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

    @DisplayName("[성공] 같은 유저인지 확인")
    @Test
    public void checkSameUser() throws Exception {
        // given
        User user = User.builder()
            .id(1L)
            .socialType(SocialType.GOOGLE)
            .role(Role.USER)
            .profileUrl("123")
            .userName("haneul")
            .socialId("haneul")
            .build();

        AppUser appUser = AppUser.of(1L);

        //when
        boolean result = user.checkSameUser(appUser.getId());

        //then
        assertThat(result).isTrue();
    }

    @DisplayName("[실패] 같은 유저인지 확인")
    @Test
    public void failedCheckSameUser() throws Exception {
        // given
        User user = User.builder()
            .id(1L)
            .socialType(SocialType.GOOGLE)
            .role(Role.USER)
            .profileUrl("123")
            .userName("haneul")
            .socialId("haneul")
            .build();

        AppUser appUser = AppUser.of(2L);

        //when
        boolean result = user.checkSameUser(appUser.getId());

        //then
        assertThat(result).isFalse();
    }
}
