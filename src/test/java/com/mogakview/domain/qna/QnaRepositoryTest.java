package com.mogakview.domain.qna;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.mogakview.config.TestConfig;
import com.mogakview.domain.qnabook.QnaBook;
import com.mogakview.domain.qnabook.QnaBookRepository;
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
class QnaRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QnaBookRepository qnaBookRepository;

    @Autowired QnaRepository qnaRepository;

    @DisplayName("[성공] QnaBook의 Qna들 찾기")
    @Test
    void findQnasByQnaBook() {
        //given
        User user = User.builder()
            .socialType(SocialType.GOOGLE)
            .role(Role.USER)
            .profileUrl("123")
            .userName("haneul")
            .socialId("haneul")
            .build();
        User savedUser = userRepository.save(user);

        QnaBook qnaBook = QnaBook.builder()
            .title("qnaBook")
            .opened(true)
            .user(savedUser)
            .deleted(false)
            .build();
        QnaBook savedQnaBook = qnaBookRepository.save(qnaBook);

        Qna newQna = Qna.builder()
            .qnaBook(savedQnaBook)
            .deleted(false)
            .question("question")
            .answer("answer")
            .build();
        Qna savedQna = qnaRepository.save(newQna);

        //when
        List<Qna> qnas = qnaRepository.findQnasByQnaBook(qnaBook);

        //then
        assertThat(qnas.size()).isEqualTo(1);
    }
}
