package com.mogakview.application.qnabook;

import com.mogakview.config.auth.AppUser;
import com.mogakview.domain.qnabook.QnaBook;
import com.mogakview.domain.qnabook.QnaBookRepository;
import com.mogakview.domain.qnabooktag.QnaBookTag;
import com.mogakview.domain.qnabooktag.QnaBookTagRepository;
import com.mogakview.domain.user.User;
import com.mogakview.domain.user.UserRepository;
import com.mogakview.dto.qnabook.DeleteQnaBookResponse;
import com.mogakview.dto.qnabook.QnaBookRequest;
import com.mogakview.dto.qnabook.QnaBookResponse;
import java.util.List;
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

    public QnaBookResponse createQnaBook(QnaBookRequest qnaBookRequest, AppUser appUser) {
        User user = userRepository.findById(appUser.getId())
            .orElseThrow(RuntimeException::new);
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
        QnaBook qnaBook = qnaBookRepository.findById(id).orElseThrow(RuntimeException::new);
        return createQnaBookResponse(qnaBook);
    }

    private QnaBookResponse createQnaBookResponse(QnaBook qnaBook) {
        List<QnaBookTag> qnaBookTags = qnaBook.getQnaBookTags();
        return QnaBookResponse.of(qnaBook, qnaBookTags);
    }

    public QnaBookResponse updateQnaBook(Long id, QnaBookRequest qnaBookRequest, AppUser appUser) {
        QnaBook qnaBook = qnaBookRepository.findById(id).orElseThrow(RuntimeException::new);
        appUser.checkSameUser(qnaBook.getUser());
        List<QnaBookTag> qnaBookTags = updateQnaBookTagsOfQnaBook(
            qnaBookRequest, qnaBook);
        return QnaBookResponse.of(qnaBook, qnaBookTags);
    }

    private List<QnaBookTag> updateQnaBookTagsOfQnaBook(QnaBookRequest qnaBookRequest, QnaBook qnaBook) {
        List<QnaBookTag> qnaBookTags = createQnaBookTags(qnaBookRequest, qnaBook);
        qnaBook.updateWithQnaBookTags(qnaBookRequest.getTitle(), qnaBookRequest.isOpened(),
            qnaBookTags);
        return qnaBookTags;
    }

    public DeleteQnaBookResponse deleteQnaBook(Long id, AppUser appUser) {
        QnaBook qnaBook = qnaBookRepository.findById(id).orElseThrow(RuntimeException::new);
        User user = userRepository.findById(qnaBook.getUser().getId())
            .orElseThrow(RuntimeException::new);
        appUser.checkSameUser(user);
        qnaBookRepository.deleteById(id);
        return DeleteQnaBookResponse.of(id);
    }
}
