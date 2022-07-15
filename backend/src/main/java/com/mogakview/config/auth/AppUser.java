package com.mogakview.config.auth;

import com.mogakview.domain.user.Role;
import com.mogakview.domain.user.User;
import com.mogakview.exception.http.UnauthorizedException;
import com.mogakview.exception.user.UserUnauthorizedException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class AppUser {

    private Long id;
    private Role role;

    public static AppUser of(Long id) {
        return AppUser.builder()
            .id(id)
            .role(Role.USER)
            .build();
    }

    public void checkSameUser(User user) {
        if (!user.checkSameUser(this.getId())) {
            throw new UserUnauthorizedException();
        }
    }
}
