package com.mogakview.domain.qnabooktag;

import com.mogakview.config.TestConfig;
import com.mogakview.domain.qnabook.QnaBook;
import com.mogakview.domain.qnabook.QnaBookRepository;
import com.mogakview.domain.user.Role;
import com.mogakview.domain.user.SocialType;
import com.mogakview.domain.user.User;
import com.mogakview.domain.user.UserRepository;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(TestConfig.class)
class QnaBookTagRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QnaBookRepository qnaBookRepository;

    @Autowired
    private QnaBookTagRepository qnaBookTagRepository;

    @Test
    public void findAllByQnaBook() throws Exception {
        // given
        User user = User.builder()
            .socialType(SocialType.GOOGLE)
            .role(Role.USER)
            .profileUrl("123")
            .userName("haneul")
            .socialId("haneul")
            .build();
        userRepository.save(user);

        QnaBook qnaBook = QnaBook.builder()
            .id(1L)
            .title("qnaBook")
            .opened(true)
            .user(user)
            .deleted(false)
            .build();
        QnaBook savedQnaBook = qnaBookRepository.save(qnaBook);

        QnaBookTag tag1 = QnaBookTag.builder()
            .qnaBook(savedQnaBook)
            .deleted(false)
            .name("tag1")
            .build();

        QnaBookTag tag2 = QnaBookTag.builder()
            .qnaBook(savedQnaBook)
            .deleted(false)
            .name("tag2")
            .build();

        qnaBookTagRepository.save(tag1);
        qnaBookTagRepository.save(tag2);

        //when
        List<QnaBookTag> qnaBookTags = qnaBookTagRepository.findAllByQnaBook(savedQnaBook);

        //then
        Assertions.assertThat(qnaBookTags.size()).isEqualTo(2);
    }
}
