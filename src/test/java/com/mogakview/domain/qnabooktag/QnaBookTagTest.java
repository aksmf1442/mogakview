package com.mogakview.domain.qnabooktag;

import static org.assertj.core.api.Assertions.assertThat;

import com.mogakview.domain.qnabook.QnaBook;
import com.mogakview.domain.user.Role;
import com.mogakview.domain.user.SocialType;
import com.mogakview.domain.user.User;
import org.junit.jupiter.api.Test;

class QnaBookTagTest {


    @Test
    public void updateQnaBookTag() throws Exception {
        // given
        User user = User.builder()
            .socialType(SocialType.GOOGLE)
            .role(Role.USER)
            .profileUrl("123")
            .userName("haneul")
            .socialId("haneul")
            .build();

        QnaBook qnaBook = QnaBook.builder()
            .title("qnaBook")
            .opened(true)
            .user(user)
            .deleted(false)
            .build();

        QnaBookTag tag = QnaBookTag.builder()
            .qnaBook(qnaBook)
            .name("tag")
            .deleted(false)
            .build();

        //when
        tag.delete();

        //then
        assertThat(tag.isDeleted()).isTrue();
    }
}
