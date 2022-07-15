package com.mogakview.application.qnabook;

import com.mogakview.config.auth.AppUser;
import com.mogakview.domain.heart.Heart;
import com.mogakview.domain.heart.HeartRepository;
import com.mogakview.domain.qna.Qna;
import com.mogakview.domain.qna.QnaRepository;
import com.mogakview.domain.qnabook.QnaBook;
import com.mogakview.domain.qnabook.QnaBookRepository;
import com.mogakview.domain.qnabooktag.QnaBookTag;
import com.mogakview.domain.qnabooktag.QnaBookTagRepository;
import com.mogakview.domain.user.User;
import com.mogakview.domain.user.UserRepository;
import com.mogakview.dto.heart.HeartRequestType;
import com.mogakview.dto.qna.QnasResponse;
import com.mogakview.dto.qnabook.DeleteQnaBookResponse;
import com.mogakview.dto.qnabook.LimitQnaBooksRequest;
import com.mogakview.dto.qnabook.LimitQnaBooksResponse;
import com.mogakview.dto.qnabook.QnaBookRequest;
import com.mogakview.dto.qnabook.QnaBookResponse;
import com.mogakview.dto.heart.HeartResponse;
import com.mogakview.exception.qnabook.QnaBookNotFoundException;
import com.mogakview.exception.user.UserNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class QnaBookService {

    private final UserRepository userRepository;
    private final QnaBookRepository qnaBookRepository;
    private final QnaBookTagRepository qnaBookTagRepository;
    private final QnaRepository qnaRepository;
    private final HeartRepository heartRepository;

    public QnaBookResponse createQnaBook(QnaBookRequest qnaBookRequest, AppUser appUser) {
        User user = userRepository.findById(appUser.getId())
            .orElseThrow(UserNotFoundException::new);
        QnaBook savedQnaBook = qnaBookRepository.save(qnaBookRequest.toQnaBook(user));
        updateQnaBookTagsToQnaBook(qnaBookRequest, savedQnaBook);
        return QnaBookResponse.of(savedQnaBook);
    }

    private void updateQnaBookTagsToQnaBook(QnaBookRequest qnaBookRequest, QnaBook savedQnaBook) {
        List<QnaBookTag> qnaBookTags = createQnaBookTags(qnaBookRequest, savedQnaBook);
        savedQnaBook.updateQnaBookTags(qnaBookTags);
        qnaBookTagRepository.saveAll(qnaBookTags);
    }

    @Transactional(readOnly = true)
    public QnaBookResponse findQnaBookById(Long id) {
        QnaBook qnaBook = qnaBookRepository.findById(id).orElseThrow(QnaBookNotFoundException::new);
        return QnaBookResponse.of(qnaBook);
    }

    public QnaBookResponse updateQnaBook(Long id, QnaBookRequest qnaBookRequest, AppUser appUser) {
        QnaBook qnaBook = qnaBookRepository.findById(id).orElseThrow(QnaBookNotFoundException::new);
        appUser.checkSameUser(qnaBook.getUser());
        updateQnaBookWithQnaBookTags(qnaBookRequest, qnaBook);
        return QnaBookResponse.of(qnaBook);
    }

    private void updateQnaBookWithQnaBookTags(QnaBookRequest qnaBookRequest,
        QnaBook qnaBook) {
        List<QnaBookTag> qnaBookTags = createQnaBookTags(qnaBookRequest, qnaBook);
        qnaBook.updateWithQnaBookTags(qnaBookRequest.getTitle(), qnaBookRequest.isOpened(),
            qnaBookTags);
        qnaBookTagRepository.flush();
    }

    private List<QnaBookTag> createQnaBookTags(QnaBookRequest qnaBookRequest,
        QnaBook savedQnaBook) {
        return qnaBookRequest.getTags().stream()
            .map(tag -> QnaBookTag.of(savedQnaBook, tag.getName()))
            .collect(Collectors.toList());
    }

    public DeleteQnaBookResponse deleteQnaBook(Long id, AppUser appUser) {
        QnaBook qnaBook = qnaBookRepository.findById(id).orElseThrow(QnaBookNotFoundException::new);
        User user = userRepository.findById(qnaBook.getUser().getId())
            .orElseThrow(UserNotFoundException::new);
        appUser.checkSameUser(user);
        qnaBook.delete();
        return DeleteQnaBookResponse.of(id);
    }

    @Transactional(readOnly = true)
    public LimitQnaBooksResponse findLimitQnaBooks(LimitQnaBooksRequest qnaBooksRequest) {
        if (qnaBooksRequest.isFirst()) {
            return extractFirstQnaBooksByLimit(qnaBooksRequest.getLimit());
        }

        QnaBook qnaBook = qnaBookRepository.findById(qnaBooksRequest.getId())
            .orElseThrow(QnaBookNotFoundException::new);
        List<QnaBook> qnaBooks = qnaBookRepository.findLimitQnaBooks(qnaBook,
            qnaBooksRequest.getLimit());

        return LimitQnaBooksResponse.of(qnaBooks);
    }

    private LimitQnaBooksResponse extractFirstQnaBooksByLimit(int limit) {
        List<QnaBook> firstQnaBooks = qnaBookRepository.findFirstLimitQnaBooks(limit);
        return LimitQnaBooksResponse.of(firstQnaBooks);
    }

    public QnasResponse findQnasInQnaBook(Long qnaBookId) {
        QnaBook qnaBook = qnaBookRepository.findById(qnaBookId)
            .orElseThrow(QnaBookNotFoundException::new);
        List<Qna> qnas = qnaRepository.findQnasByQnaBook(qnaBook);
        return QnasResponse.of(qnas);
    }

    public HeartResponse toggleHeart(Long qnaBookId, AppUser appUser) {
        return HeartResponse.of(checkHeartStatus(qnaBookId, appUser, HeartRequestType.TOGGLE));
    }

    public HeartResponse checkClickedHeart(Long qnaBookId, AppUser appUser) {
        return HeartResponse.of(checkHeartStatus(qnaBookId, appUser, HeartRequestType.BASIC));
    }

    private boolean checkHeartStatus(Long qnaBookId, AppUser appUser, HeartRequestType type) {
        QnaBook qnaBook = qnaBookRepository.findById(qnaBookId)
            .orElseThrow(QnaBookNotFoundException::new);
        User user = userRepository.findById(appUser.getId())
            .orElseThrow(UserNotFoundException::new);
        Optional<Heart> findHeart = heartRepository.findByQnaBookAndUser(qnaBook, user);
        if (type == HeartRequestType.TOGGLE) {
            return saveOrDeleteHeart(user, qnaBook, findHeart);
        }
        return findHeart.isPresent();
    }

    private boolean saveOrDeleteHeart(User user, QnaBook qnaBook, Optional<Heart> heart) {
        if (heart.isPresent()) {
            heartRepository.delete(heart.get());
        } else {
            heartRepository.save(Heart.of(user, qnaBook));
        }
        return heart.isEmpty();
    }
}
