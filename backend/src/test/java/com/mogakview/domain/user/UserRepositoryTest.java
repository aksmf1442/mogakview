package com.mogakview.domain.user;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.mogakview.config.TestConfig;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(TestConfig.class)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @DisplayName("[성공] SocialId와 SocialType으로 유저 찾기")
    @Test
    public void findBySocialIdAndSocialType() throws Exception {
        // given
        User user = User.builder()
            .socialType(SocialType.GOOGLE)
            .role(Role.USER)
            .profileUrl("123")
            .userName("haneul")
            .socialId("haneul")
            .build();
        User savedUser = userRepository.save(user);

        //when
        Optional<User> findUser = userRepository.findBySocialIdAndSocialType(
            user.getSocialId(), user.getSocialType());

        //then
        assertThat(findUser.isPresent()).isTrue();
        assertThat(findUser.get()).isEqualTo(savedUser);
    }
}
