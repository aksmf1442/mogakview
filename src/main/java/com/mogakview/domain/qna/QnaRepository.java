package com.mogakview.domain.qna;

import com.mogakview.domain.qnabook.QnaBook;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QnaRepository extends JpaRepository<Qna, Long> {

    List<Qna> findQnasByQnaBook(QnaBook qnaBook);

}
