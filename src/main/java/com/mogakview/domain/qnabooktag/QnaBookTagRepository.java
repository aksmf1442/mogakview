package com.mogakview.domain.qnabooktag;

import com.mogakview.domain.qnabook.QnaBook;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaBookTagRepository extends JpaRepository<QnaBookTag, Long> {

    List<QnaBookTag> findAllByQnaBook(QnaBook savedQnaBook);
}
