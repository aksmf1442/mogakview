package com.mogakview.domain.user;

import com.mogakview.domain.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "users")
public class User extends BaseEntity {

    @Column(nullable = false)
    private String userName;

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
    public User(String userName, String email, String socialId, SocialType socialType,
        Role role, String profileUrl) {
        this.userName = userName;
        this.socialId = socialId;
        this.socialType = socialType;
        this.role = role;
        this.profileUrl = profileUrl;
    }
}
