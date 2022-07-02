package com.mogakview.application.qna;

import com.mogakview.config.auth.AppUser;
import com.mogakview.domain.qna.Qna;
import com.mogakview.domain.qna.QnaRepository;
import com.mogakview.domain.qnabook.QnaBook;
import com.mogakview.domain.qnabook.QnaBookRepository;
import com.mogakview.dto.qna.QnaRequest;
import com.mogakview.dto.qna.QnaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class QnaService {

    private final QnaRepository qnaRepository;
    private final QnaBookRepository qnaBookRepository;

    public QnaResponse createQna(QnaRequest qnaRequest, AppUser appUser) {
        QnaBook qnaBook = qnaBookRepository.findById(qnaRequest.getQnaBookId())
            .orElseThrow(RuntimeException::new);
        appUser.checkSameUser(qnaBook.getUser());
        Qna qna = qnaRequest.toQna(qnaBook);
        Qna savedQna = qnaRepository.save(qna);
        return QnaResponse.of(savedQna);
    }
}
