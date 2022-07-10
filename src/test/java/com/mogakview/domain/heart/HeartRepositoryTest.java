package com.mogakview.domain.heart;

import static org.assertj.core.api.Assertions.assertThat;

import com.mogakview.config.TestConfig;
import com.mogakview.domain.qnabook.QnaBook;
import com.mogakview.domain.qnabook.QnaBookRepository;
import com.mogakview.domain.user.Role;
import com.mogakview.domain.user.SocialType;
import com.mogakview.domain.user.User;
import com.mogakview.domain.user.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(TestConfig.class)
class HeartRepositoryTest {

    @Autowired
    private HeartRepository heartRepository;

    @Autowired
    private QnaBookRepository qnaBookRepository;

    @Autowired
    private UserRepository userRepository;

    @DisplayName("[성공] qnaBook과 User로 Heart 찾기 - Heart가 눌러져 있지 않았을 경우")
    @Test
    public void findByQnaBookAndUserIfDontClicked() throws Exception {
        // given
        User user = User.builder()
            .socialType(SocialType.GOOGLE)
            .role(Role.USER)
            .profileUrl("123")
            .userName("haneul")
            .socialId("haneul")
            .build();
        User savedUser = userRepository.save(user);

        QnaBook qnaBook = QnaBook.builder()
            .id(1L)
            .title("qnaBook")
            .opened(true)
            .user(user)
            .deleted(false)
            .build();
        QnaBook savedQnaBook = qnaBookRepository.save(qnaBook);

        //when
        Optional<Heart> heart = heartRepository.findByQnaBookAndUser(savedQnaBook, savedUser);

        //then
        assertThat(heart.isPresent()).isFalse();
    }

    @DisplayName("[성공] qnaBook과 User로 Heart 찾기 - Heart가 눌러져 있을 경우")
    @Test
    public void findByQnaBookAndUserIfClicked() throws Exception {
        // given
        User user = User.builder()
            .socialType(SocialType.GOOGLE)
            .role(Role.USER)
            .profileUrl("123")
            .userName("haneul")
            .socialId("haneul")
            .build();
        User savedUser = userRepository.save(user);

        QnaBook qnaBook = QnaBook.builder()
            .id(1L)
            .title("qnaBook")
            .opened(true)
            .user(user)
            .deleted(false)
            .build();
        QnaBook savedQnaBook = qnaBookRepository.save(qnaBook);

        Heart newHeart = Heart.builder()
            .qnaBook(savedQnaBook)
            .user(savedUser)
            .build();

        heartRepository.save(newHeart);

        //when
        Optional<Heart> heart = heartRepository.findByQnaBookAndUser(savedQnaBook, savedUser);

        //then
        assertThat(heart.isPresent()).isTrue();
        assertThat(heart.get().getQnaBook()).isEqualTo(savedQnaBook);
        assertThat(heart.get().getUser()).isEqualTo(savedUser);
    }
}
