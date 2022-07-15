package com.mogakview.domain.qnabook;

import java.util.List;

public interface QnaBookQueryRepository {

    List<QnaBook> findLimitQnaBooks(QnaBook book, int limit);

    List<QnaBook> findFirstLimitQnaBooks(int limit);
}

