package com.mogakview.application.qnabook;

import com.mogakview.config.auth.AppUser;
import com.mogakview.domain.qnabook.QnaBook;
import com.mogakview.domain.qnabook.QnaBookRepository;
import com.mogakview.domain.qnabooktag.QnaBookTag;
import com.mogakview.domain.qnabooktag.QnaBookTagRepository;
import com.mogakview.domain.user.User;
import com.mogakview.domain.user.UserRepository;
import com.mogakview.dto.qnabook.QnaBookRequest;
import com.mogakview.dto.qnabook.QnaBookResponse;
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

    public QnaBookResponse createQnaBook(QnaBookRequest qnaBookRequest, AppUser loginUser) {
        Optional<User> findUser = userRepository.findById(loginUser.getId());
        User user = findUser.orElseThrow(RuntimeException::new);
        QnaBook savedQnaBook = qnaBookRepository.save(qnaBookRequest.toQnaBook(user));
        createQnaBookTags(qnaBookRequest, savedQnaBook);
        return createQnaBookResponse(savedQnaBook);
    }

    private List<QnaBookTag> createQnaBookTags(QnaBookRequest qnaBookRequest,
        QnaBook savedQnaBook) {
        List<QnaBookTag> qnaBookTags = qnaBookRequest.getTags().stream()
            .map(tag -> QnaBookTag.of(savedQnaBook, tag.getName()))
            .collect(Collectors.toList());
        savedQnaBook.updateQnaBookTags(qnaBookTags);
        qnaBookTagRepository.saveAll(qnaBookTags);
        return qnaBookTags;
    }

    @Transactional(readOnly = true)
    public QnaBookResponse findQnaBookById(Long id) {
        QnaBook qnaBook = extractQnaBookByOptional(id);
        return createQnaBookResponse(qnaBook);
    }

    private QnaBookResponse createQnaBookResponse(QnaBook qnaBook) {
        List<QnaBookTag> qnaBookTags = qnaBook.getQnaBookTags();
        return QnaBookResponse.of(qnaBook, qnaBookTags);
    }

    public QnaBookResponse updateQnaBook(Long id, QnaBookRequest qnaBookRequest, AppUser appUser) {
        QnaBook qnaBook = extractQnaBookByOptional(id);
        validateUser(qnaBook.getUser(), appUser);
        List<QnaBookTag> qnaBookTags = updateQnaBookTagsOfQnaBook(
            qnaBookRequest, qnaBook);
        return QnaBookResponse.of(qnaBook, qnaBookTags);
    }

    private QnaBook extractQnaBookByOptional(Long id) {
        Optional<QnaBook> findQnaBook = qnaBookRepository.findById(id);
        return findQnaBook.orElseThrow(RuntimeException::new);
    }

    private List<QnaBookTag> updateQnaBookTagsOfQnaBook(QnaBookRequest qnaBookRequest, QnaBook qnaBook) {
        List<QnaBookTag> qnaBookTags = createQnaBookTags(qnaBookRequest, qnaBook);
        qnaBook.updateWithQnaBookTags(qnaBookRequest.getTitle(), qnaBookRequest.isOpened(),
            qnaBookTags);
        return qnaBookTags;
    }

    private void validateUser(User user, AppUser appUser) {
        if (!user.checkSameUser(appUser.getId())) {
            // custom 에러 추가 예정
            throw new RuntimeException();
        }
    }
}
