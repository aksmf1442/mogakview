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

    private void createQnaBookTags(QnaBookRequest qnaBookRequest, QnaBook savedQnaBook) {
        List<QnaBookTag> qnaBookTags = qnaBookRequest.getTags().stream()
            .map(tag -> QnaBookTag.of(savedQnaBook, tag.getName()))
            .collect(Collectors.toList());
        savedQnaBook.setQnaBookTags(qnaBookTags);
        qnaBookTagRepository.saveAll(qnaBookTags);
    }

    @Transactional(readOnly = true)
    public QnaBookResponse findQnaBookById(Long qnaBookId) {
        Optional<QnaBook> findQnaBook = qnaBookRepository.findById(qnaBookId);
        QnaBook qnaBook = findQnaBook.orElseThrow(RuntimeException::new);
        return createQnaBookResponse(qnaBook);
    }

    private QnaBookResponse createQnaBookResponse(QnaBook qnaBook) {
        List<QnaBookTag> qnaBookTags = qnaBook.getQnaBookTags();
        return QnaBookResponse.of(qnaBook, qnaBookTags);
    }
}
