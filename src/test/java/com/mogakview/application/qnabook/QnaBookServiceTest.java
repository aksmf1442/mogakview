package com.mogakview.application.qnabook;

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
import com.mogakview.domain.qnabooktag.QnaBookTag;
import com.mogakview.domain.qnabooktag.QnaBookTagRepository;
import com.mogakview.domain.user.Role;
import com.mogakview.domain.user.SocialType;
import com.mogakview.domain.user.User;
import com.mogakview.domain.user.UserRepository;
import com.mogakview.dto.qna.QnasResponse;
import com.mogakview.dto.qnabook.DeleteQnaBookResponse;
import com.mogakview.dto.qnabook.LimitQnaBooksRequest;
import com.mogakview.dto.qnabook.LimitQnaBooksResponse;
import com.mogakview.dto.qnabook.QnaBookRequest;
import com.mogakview.dto.qnabook.QnaBookResponse;
import com.mogakview.dto.qnabooktag.QnaBookTagRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class QnaBookServiceTest {


    @Mock
    private UserRepository userRepository;

    @Mock
    private QnaBookRepository qnaBookRepository;

    @Mock
    private QnaRepository qnaRepository;

    @Mock
    QnaBookTagRepository qnaBookTagRepository;

    @InjectMocks
    private QnaBookService qnaBookService;


    @DisplayName("[성공] QnaBook과 QnaBookTag 생성")
    @Test
    void createQnaBookWithQnaBookTag() throws Exception {
        //given
        AppUser appUser = AppUser.of(1L);
        User user = User.builder()
            .id(1L)
            .socialType(SocialType.GOOGLE)
            .role(Role.USER)
            .profileUrl("123")
            .userName("haneul")
            .socialId("haneul")
            .build();

        List<QnaBookTagRequest> tagRequests = new ArrayList<>();
        tagRequests.add(QnaBookTagRequest.builder().name("tag1").build());
        tagRequests.add(QnaBookTagRequest.builder().name("tag2").build());

        QnaBook expectedQnaBook = QnaBook.builder()
            .id(1L)
            .title("qnaBook")
            .opened(true)
            .user(user)
            .deleted(false)
            .build();

        QnaBookRequest qnaBookRequest = QnaBookRequest.builder()
            .title(expectedQnaBook.getTitle())
            .opened(expectedQnaBook.isOpened())
            .tags(tagRequests)
            .build();

        List<QnaBookTag> qnaBookTags = tagRequests.stream()
            .map(tagRequest -> QnaBookTag.of(expectedQnaBook, tagRequest.getName()))
            .collect(Collectors.toList());

        given(userRepository.findById(appUser.getId())).willReturn(Optional.of(user));
        given(qnaBookRepository.save(any())).willReturn(expectedQnaBook);
        given(qnaBookTagRepository.saveAll(any())).willReturn(qnaBookTags);

        //when
        QnaBookResponse qnaBookResponse = qnaBookService.createQnaBook(qnaBookRequest, appUser);

        //then
        assertThat(qnaBookResponse.getId()).isEqualTo(expectedQnaBook.getId());
        assertThat(qnaBookResponse.getTitle()).isEqualTo(expectedQnaBook.getTitle());
        assertThat(qnaBookResponse.getAuthor()).isEqualTo(expectedQnaBook.getUser().getUserName());
        for (int i = 0; i < qnaBookResponse.getTags().size(); i++) {
            assertThat(qnaBookResponse.getTags().get(i).getName()).isEqualTo(tagRequests.get(i).getName());
        }

        then(userRepository)
            .should(times(1))
            .findById(appUser.getId());
        then(qnaBookRepository)
            .should(times(1))
            .save(any());
    }

    @DisplayName("[성공] qnaBook 찾기")
    @Test
    void findQnaBookById() throws Exception {
        //given
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

        given(qnaBookRepository.findById(expectedQnaBook.getId())).willReturn(Optional.of(expectedQnaBook));

        //when
        QnaBookResponse qnaBookResponse = qnaBookService.findQnaBookById(expectedQnaBook.getId());

        //then
        assertThat(qnaBookResponse.getId()).isEqualTo(expectedQnaBook.getId());
        assertThat(qnaBookResponse.getTitle()).isEqualTo(expectedQnaBook.getTitle());
        assertThat(qnaBookResponse.getAuthor()).isEqualTo(expectedQnaBook.getUser().getUserName());

        then(qnaBookRepository)
            .should(times(1))
            .findById(expectedQnaBook.getId());
    }

    @DisplayName("[성공] QnaBook과 QnaBook에 포함된 QnaBookTags 수정")
    @Test
    void updateQnaBook() throws Exception {
        //given
        AppUser appUser = AppUser.of(1L);
        User user = User.builder()
            .id(1L)
            .socialType(SocialType.GOOGLE)
            .role(Role.USER)
            .profileUrl("123")
            .userName("haneul")
            .socialId("haneul")
            .build();

        List<QnaBookTagRequest> tagRequests = new ArrayList<>();
        tagRequests.add(QnaBookTagRequest.builder().name("tag1").build());
        tagRequests.add(QnaBookTagRequest.builder().name("tag2").build());

        QnaBook expectedQnaBook = QnaBook.builder()
            .id(1L)
            .title("qnaBook")
            .opened(true)
            .user(user)
            .deleted(false)
            .build();

        QnaBookRequest qnaBookRequest = QnaBookRequest.builder()
            .title(expectedQnaBook.getTitle())
            .opened(expectedQnaBook.isOpened())
            .tags(tagRequests)
            .build();

        given(qnaBookRepository.findById(expectedQnaBook.getId())).willReturn(Optional.of(expectedQnaBook));

        //when
        QnaBookResponse qnaBookResponse = qnaBookService.updateQnaBook(expectedQnaBook.getId(),
            qnaBookRequest, appUser);

        //then
        assertThat(qnaBookResponse.getId()).isEqualTo(expectedQnaBook.getId());
        assertThat(qnaBookResponse.getTitle()).isEqualTo(expectedQnaBook.getTitle());
        assertThat(qnaBookResponse.getAuthor()).isEqualTo(expectedQnaBook.getUser().getUserName());
        for (int i = 0; i < qnaBookResponse.getTags().size(); i++) {
            assertThat(qnaBookResponse.getTags().get(i).getName()).isEqualTo(tagRequests.get(i).getName());
        }

        then(qnaBookRepository)
            .should(times(1))
            .findById(expectedQnaBook.getId());
    }

    @DisplayName("[성공] qnaBook과 이와 연관된 qnaBookTags 삭제")
    @Test
    void deleteQnaBook() throws Exception {
        //given
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

        List<QnaBookTag> tags = new ArrayList<>();
        tags.add(QnaBookTag.of(expectedQnaBook, "tag1"));
        tags.add(QnaBookTag.of(expectedQnaBook, "tag2"));

        expectedQnaBook.updateQnaBookTags(tags);

        given(qnaBookRepository.findById(expectedQnaBook.getId())).willReturn(Optional.of(expectedQnaBook));
        given(userRepository.findById(user.getId())).willReturn(Optional.of(user));

        //when
        DeleteQnaBookResponse deleteQnaBookResponse = qnaBookService.deleteQnaBook(
            expectedQnaBook.getId(), appUser);

        //then
        assertThat(deleteQnaBookResponse.getId()).isEqualTo(expectedQnaBook.getId());
        assertThat(expectedQnaBook.isDeleted()).isTrue();
        expectedQnaBook.getQnaBookTags().forEach(tag -> assertThat(tag.isDeleted()).isTrue());

        then(qnaBookRepository)
            .should(times(1))
            .findById(expectedQnaBook.getId());
        then(userRepository)
            .should(times(1))
            .findById(user.getId());
    }

    @DisplayName("[성공] QnaBooks 페이지네이션 - 첫 요청(first=true)")
    @Test
    void firstFindLimitQnaBooks() throws Exception {
        // given
        LimitQnaBooksRequest limitQnaBooksRequest = LimitQnaBooksRequest.builder()
            .limit(3)
            .first(true)
            .build();

        User user = User.builder()
            .id(1L)
            .socialType(SocialType.GOOGLE)
            .role(Role.USER)
            .profileUrl("123")
            .userName("haneul")
            .socialId("haneul")
            .build();

        List<QnaBook> expectedQnaBooks = new ArrayList<>();
        expectedQnaBooks.add(QnaBook.builder().id(1L).user(user).build());
        expectedQnaBooks.add(QnaBook.builder().id(2L).user(user).build());
        expectedQnaBooks.add(QnaBook.builder().id(3L).user(user).build());

        given(qnaBookRepository.findFirstLimitQnaBooks(limitQnaBooksRequest.getLimit())).willReturn(expectedQnaBooks);

        //when
        LimitQnaBooksResponse limitQnaBooks = qnaBookService.findLimitQnaBooks(limitQnaBooksRequest);

        //then
        assertThat(limitQnaBooks.getQnaBooksResponses().get(0).getId()).isEqualTo(1L);
        assertThat(limitQnaBooks.getQnaBooksResponses().get(1).getId()).isEqualTo(2L);
        assertThat(limitQnaBooks.getQnaBooksResponses().get(2).getId()).isEqualTo(3L);

        then(qnaBookRepository)
            .should(times(1))
            .findFirstLimitQnaBooks(limitQnaBooksRequest.getLimit());
    }

    @DisplayName("[성공] QnaBooks 페이지네이션 - 첫 요청 아님(first=false)")
    @Test
    void findLimitQnaBooks() throws Exception {
        // given
        LimitQnaBooksRequest limitQnaBooksRequest = LimitQnaBooksRequest.builder()
            .id(1L)
            .limit(3)
            .first(false)
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

        List<QnaBook> expectedQnaBooks = new ArrayList<>();
        expectedQnaBooks.add(QnaBook.builder().id(2L).user(user).build());
        expectedQnaBooks.add(QnaBook.builder().id(3L).user(user).build());
        expectedQnaBooks.add(QnaBook.builder().id(4L).user(user).build());

        given(qnaBookRepository.findById(limitQnaBooksRequest.getId())).willReturn(Optional.of(expectedQnaBook));
        given(qnaBookRepository.findLimitQnaBooks(expectedQnaBook, limitQnaBooksRequest.getLimit())).willReturn(expectedQnaBooks);

        //when
        LimitQnaBooksResponse limitQnaBooks = qnaBookService.findLimitQnaBooks(limitQnaBooksRequest);

        //then
        assertThat(limitQnaBooks.getQnaBooksResponses().get(0).getId()).isEqualTo(2L);
        assertThat(limitQnaBooks.getQnaBooksResponses().get(1).getId()).isEqualTo(3L);
        assertThat(limitQnaBooks.getQnaBooksResponses().get(2).getId()).isEqualTo(4L);

        then(qnaBookRepository)
            .should(times(1))
            .findById(limitQnaBooksRequest.getId());
        then(qnaBookRepository)
            .should(times(1))
            .findLimitQnaBooks(expectedQnaBook, limitQnaBooksRequest.getLimit());
    }


    @DisplayName("[성공] QnaBook에 있는 Qnas 모두 찾기")
    @Test
    void findQnasInQnaBook() throws Exception {
        // given
        Long qnaBookId = 1L;

        User user = User.builder()
            .id(1L)
            .socialType(SocialType.GOOGLE)
            .role(Role.USER)
            .profileUrl("123")
            .userName("haneul")
            .socialId("haneul")
            .build();


        QnaBook expectedQnaBook = QnaBook.builder()
            .id(qnaBookId)
            .title("qnaBook")
            .opened(true)
            .user(user)
            .deleted(false)
            .build();

        List<Qna> qnas = new ArrayList<>();
        qnas.add(Qna.builder().id(1L).build());
        qnas.add(Qna.builder().id(2L).build());
        qnas.add(Qna.builder().id(3L).build());

        given(qnaBookRepository.findById(qnaBookId)).willReturn(Optional.of(expectedQnaBook));
        given(qnaRepository.findQnasByQnaBook(expectedQnaBook)).willReturn(qnas);

        //when
        QnasResponse qnasResponse = qnaBookService.findQnasInQnaBook(qnaBookId);

        //then
        assertThat(qnasResponse.getQnasResponse().get(0).getId()).isEqualTo(1L);
        assertThat(qnasResponse.getQnasResponse().get(1).getId()).isEqualTo(2L);
        assertThat(qnasResponse.getQnasResponse().get(2).getId()).isEqualTo(3L);

        then(qnaBookRepository)
            .should(times(1))
            .findById(qnaBookId);
        then(qnaRepository)
            .should(times(1))
            .findQnasByQnaBook(expectedQnaBook);
    }
}
