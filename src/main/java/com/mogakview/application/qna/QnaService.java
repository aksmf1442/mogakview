package com.mogakview.application.qna;

import com.mogakview.config.auth.AppUser;
import com.mogakview.domain.qna.Qna;
import com.mogakview.domain.qna.QnaRepository;
import com.mogakview.domain.qnabook.QnaBook;
import com.mogakview.domain.qnabook.QnaBookRepository;
import com.mogakview.dto.qna.DeleteQnaResponse;
import com.mogakview.dto.qna.QnaRequest;
import com.mogakview.dto.qna.QnaResponse;
import com.mogakview.dto.qna.UpdateQnaRequest;
import com.mogakview.exception.qna.QnaNotFoundException;
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
            .orElseThrow(QnaNotFoundException::new);
        appUser.checkSameUser(qnaBook.getUser());
        Qna qna = qnaRequest.toQna(qnaBook);
        Qna savedQna = qnaRepository.save(qna);
        return QnaResponse.of(savedQna);
    }

    public QnaResponse updateQna(Long id, UpdateQnaRequest qnaRequest) {
        Qna qna = qnaRepository.findById(id)
            .orElseThrow(QnaNotFoundException::new);
        qna.update(qnaRequest.getQuestion(), qnaRequest.getAnswer());
        return QnaResponse.of(qna);
    }

    public DeleteQnaResponse deleteQnaById(Long id, AppUser appUser) {
        Qna qna = qnaRepository.findById(id)
            .orElseThrow(QnaNotFoundException::new);
        appUser.checkSameUser(qna.getQnaBook().getUser());
        qna.delete();
        return DeleteQnaResponse.of(id);
    }
}
