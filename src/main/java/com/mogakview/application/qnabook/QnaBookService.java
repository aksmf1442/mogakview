package com.mogakview.application.qnabook;

import com.mogakview.config.auth.AppUser;
import com.mogakview.domain.qnabook.QnaBook;
import com.mogakview.domain.qnabook.QnaBookRepository;
import com.mogakview.domain.qnabooktag.QnaBookTag;
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

    public QnaBookResponse createQnaBook(QnaBookRequest qnaBookRequest, AppUser loginUser) {
        Optional<User> findUser = userRepository.findById(loginUser.getId());
        User user = findUser.orElseThrow(RuntimeException::new);
        QnaBook savedQnaBook = qnaBookRepository.save(qnaBookRequest.toQnaBook(user));
        List<QnaBookTag> qnaBookTags = savedQnaBook.getQnaBookTags();
        return QnaBookResponse.of(savedQnaBook, qnaBookTags);
    }
}
