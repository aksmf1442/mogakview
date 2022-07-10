package com.mogakview.domain.qna;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.mogakview.domain.qnabook.QnaBook;
import com.mogakview.domain.user.Role;
import com.mogakview.domain.user.SocialType;
import com.mogakview.domain.user.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QnaTest {

    @DisplayName("[성공] Qna 수정")
    @Test
    void update() throws Exception {
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

        Qna qna = Qna.builder()
            .qnaBook(qnaBook)
            .question("question")
            .answer("answer")
            .deleted(false)
            .build();

        String updateQuestion = "question 수정";
        String updateAnswer = "answer 수정";

        //when
        qna.update(updateQuestion, updateAnswer);

        //then
        assertThat(qna.getQuestion()).isEqualTo(updateQuestion);
        assertThat(qna.getAnswer()).isEqualTo(updateAnswer);
    }

    @DisplayName("[성공] Qna 삭제")
    @Test
    public void delete() throws Exception {
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

        Qna qna = Qna.builder()
            .qnaBook(qnaBook)
            .question("question")
            .answer("answer")
            .deleted(false)
            .build();

        //when
        qna.delete();

        //then
        assertThat(qna.isDeleted()).isTrue();
    }
}
