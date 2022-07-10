package com.mogakview.domain.qnabook;

import static com.mogakview.domain.qnabook.QQnaBook.qnaBook;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.mogakview.config.TestConfig;
import com.mogakview.domain.qna.QnaRepository;
import com.mogakview.domain.user.Role;
import com.mogakview.domain.user.SocialType;
import com.mogakview.domain.user.User;
import com.mogakview.domain.user.UserRepository;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(TestConfig.class)
class QnaBookRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QnaBookRepository qnaBookRepository;

    @DisplayName("[성공] QnaBook 페이지네이션 - 최신순 ")
    @Test
    public void findLimitQnaBooks() throws Exception {
        // given
        User user = User.builder()
            .socialType(SocialType.GOOGLE)
            .role(Role.USER)
            .profileUrl("123")
            .userName("haneul")
            .socialId("haneul")
            .build();
        User savedUser = userRepository.save(user);

        QnaBook qnaBook1 = QnaBook.builder()
            .title("qnaBook")
            .opened(true)
            .user(savedUser)
            .deleted(false)
            .build();
        QnaBook qnaBook2 = QnaBook.builder()
            .title("qnaBook")
            .opened(true)
            .user(savedUser)
            .deleted(false)
            .build();
        QnaBook qnaBook3 = QnaBook.builder()
            .title("qnaBook")
            .opened(true)
            .user(savedUser)
            .deleted(false)
            .build();
        qnaBookRepository.save(qnaBook1);
        qnaBookRepository.save(qnaBook2);
        QnaBook savedQnaBook3 = qnaBookRepository.save(qnaBook3);

        //when
        List<QnaBook> qnaBooks = qnaBookRepository.findLimitQnaBooks(savedQnaBook3, 2);

        //then
        assertThat(qnaBooks.size()).isEqualTo(2);
    }

    @DisplayName("[성공] QnaBook 페이지네이션 첫 요청 - 최신순 ")
    @Test
    public void findFirstLimitQnaBooks() throws Exception {
        // given
        User user = User.builder()
            .socialType(SocialType.GOOGLE)
            .role(Role.USER)
            .profileUrl("123")
            .userName("haneul")
            .socialId("haneul")
            .build();
        User savedUser = userRepository.save(user);

        QnaBook qnaBook1 = QnaBook.builder()
            .title("qnaBook")
            .opened(true)
            .user(savedUser)
            .deleted(false)
            .build();
        QnaBook qnaBook2 = QnaBook.builder()
            .title("qnaBook")
            .opened(true)
            .user(savedUser)
            .deleted(false)
            .build();
        QnaBook qnaBook3 = QnaBook.builder()
            .title("qnaBook")
            .opened(true)
            .user(savedUser)
            .deleted(false)
            .build();
        qnaBookRepository.save(qnaBook1);
        qnaBookRepository.save(qnaBook2);
        qnaBookRepository.save(qnaBook3);

        //when
        List<QnaBook> qnaBooks = qnaBookRepository.findFirstLimitQnaBooks(3);

        //then
        assertThat(qnaBooks.size()).isEqualTo(3);
    }
}
