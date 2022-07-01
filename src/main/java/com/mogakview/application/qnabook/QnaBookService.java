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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QnaBookService {

    private final UserRepository userRepository;
    private final QnaBookRepository qnaBookRepository;
    private final QnaBookTagRepository qnaBookTagRepository;

    public QnaBookResponse createQnaBook(QnaBookRequest qnaBookRequest, AppUser loginUser) {
        Optional<User> findUser = userRepository.findById(loginUser.getId());
        if (findUser.isEmpty()) {
            // custom error 만들기
            throw new RuntimeException();
        }
        QnaBook savedQnaBook = qnaBookRepository.save(qnaBookRequest.toQnaBook(findUser.get()));
        List<QnaBookTag> qnaBookTags = qnaBookTagRepository.findAllByQnaBook(savedQnaBook);
        return QnaBookResponse.of(savedQnaBook, qnaBookTags);
    }
}
