package com.mogakview.application.qna;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.mogakview.config.auth.AppUser;
import com.mogakview.domain.qna.Qna;
import com.mogakview.domain.qna.QnaRepository;
import com.mogakview.domain.qnabook.QnaBook;
import com.mogakview.domain.qnabook.QnaBookRepository;
import com.mogakview.domain.user.Role;
import com.mogakview.domain.user.SocialType;
import com.mogakview.domain.user.User;
import com.mogakview.dto.qna.DeleteQnaResponse;
import com.mogakview.dto.qna.QnaRequest;
import com.mogakview.dto.qna.QnaResponse;
import com.mogakview.dto.qna.UpdateQnaRequest;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class QnaServiceTest {

    @Mock
    private QnaRepository qnaRepository;

    @Mock
    private QnaBookRepository qnaBookRepository;

    @InjectMocks
    private QnaService qnaService;

    @DisplayName("[성공] qna 생성")
    @Test
    public void createQna() throws Exception {
        // given
        QnaRequest qnaRequest = QnaRequest.builder()
            .qnaBookId(1L)
            .question("question")
            .answer("answer")
            .build();

        AppUser appUser = AppUser.of(1L);

        User user = User.builder()
            .id(1L)
            .socialType(SocialType.GOOGLE)
            .role(Role.USER)
            .profileUrl("123")
            .userName("haneul")
            .socialId("haneul")
            .build();

        QnaBook expectedQnaBook = QnaBook.builder()
            .id(1L)
            .title("qnaBook")
            .opened(true)
            .user(user)
            .deleted(false)
            .build();

        Qna qna = qnaRequest.toQna(expectedQnaBook);

        given(qnaBookRepository.findById(qnaRequest.getQnaBookId())).willReturn(Optional.of(expectedQnaBook));
        given(qnaRepository.save(any())).willReturn(qna);

        //when
        QnaResponse qnaResponse = qnaService.createQna(qnaRequest, appUser);

        //then
        assertThat(qnaResponse.getQuestion()).isEqualTo(qnaRequest.getQuestion());
        assertThat(qnaResponse.getAnswer()).isEqualTo(qnaRequest.getAnswer());

        then(qnaBookRepository)
            .should(times(1))
            .findById(qnaRequest.getQnaBookId());
        then(qnaRepository)
            .should(times(1))
            .save(any());
    }

    @DisplayName("[성공] qna 수정")
    @Test
    void updateQna() throws Exception {
        // given
        Long qnaId = 1L;
        UpdateQnaRequest updateQnaRequest = UpdateQnaRequest.builder()
            .question("updateQuestion")
            .answer("updateAnswer")
            .build();

        User user = User.builder()
            .id(1L)
            .socialType(SocialType.GOOGLE)
            .role(Role.USER)
            .profileUrl("123")
            .userName("haneul")
            .socialId("haneul")
            .build();

        QnaBook expectedQnaBook = QnaBook.builder()
            .id(1L)
            .title("qnaBook")
            .opened(true)
            .user(user)
            .deleted(false)
            .build();

        Qna expectedQna = Qna.builder()
            .id(1L)
            .qnaBook(expectedQnaBook)
            .deleted(false)
            .question(updateQnaRequest.getQuestion())
            .answer(updateQnaRequest.getAnswer())
            .build();

        given(qnaRepository.findById(qnaId)).willReturn(Optional.of(expectedQna));

        //when
        QnaResponse qnaResponse = qnaService.updateQna(qnaId, updateQnaRequest);

        //then
        assertThat(qnaResponse.getId()).isEqualTo(qnaId);
        assertThat(qnaResponse.getQuestion()).isEqualTo(updateQnaRequest.getQuestion());
        assertThat(qnaResponse.getAnswer()).isEqualTo(updateQnaRequest.getAnswer());

        then(qnaRepository)
            .should(times(1))
            .findById(qnaId);
    }

    @DisplayName("[성공] qna 삭제")
    @Test
    void deleteQnaById() throws Exception {
        // given
        Long qnaId = 1L;
        UpdateQnaRequest updateQnaRequest = UpdateQnaRequest.builder()
            .question("updateQuestion")
            .answer("updateAnswer")
            .build();

        AppUser appUser = AppUser.of(1L);

        User user = User.builder()
            .id(1L)
            .socialType(SocialType.GOOGLE)
            .role(Role.USER)
            .profileUrl("123")
            .userName("haneul")
            .socialId("haneul")
            .build();

        QnaBook expectedQnaBook = QnaBook.builder()
            .id(1L)
            .title("qnaBook")
            .opened(true)
            .user(user)
            .deleted(false)
            .build();

        Qna expectedQna = Qna.builder()
            .id(1L)
            .qnaBook(expectedQnaBook)
            .deleted(false)
            .question(updateQnaRequest.getQuestion())
            .answer(updateQnaRequest.getAnswer())
            .build();

        given(qnaRepository.findById(qnaId)).willReturn(Optional.of(expectedQna));

        //when
        DeleteQnaResponse qnaResponse = qnaService.deleteQnaById(qnaId, appUser);

        //then
        assertThat(qnaResponse.getId()).isEqualTo(qnaId);
        assertThat(expectedQna.isDeleted()).isTrue();

        then(qnaRepository)
            .should(times(1))
            .findById(qnaId);
    }

}
