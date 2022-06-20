package com.mogakview.domain.user;

import com.mogakview.domain.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class User extends BaseEntity {

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String socialId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = true)
    private String profileUrl;

    @Builder
    public User(String username, String email, String socialId, SocialType socialType,
        Role role, String profileUrl) {
        this.username = username;
        this.email = email;
        this.socialId = socialId;
        this.socialType = socialType;
        this.role = role;
        this.profileUrl = profileUrl;
    }
}
