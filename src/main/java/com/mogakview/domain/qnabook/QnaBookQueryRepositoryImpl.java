package com.mogakview.domain.qnabook;

import static com.mogakview.domain.qnabook.QQnaBook.*;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QnaBookQueryRepositoryImpl implements QnaBookQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<QnaBook> findLimitQnaBooks(QnaBook book, int limit) {
        return jpaQueryFactory.selectFrom(qnaBook)
            .orderBy(qnaBook.createdAt.desc())
            .where(qnaBook.createdAt.before(book.getCreatedAt()))
            .limit(limit)
            .fetch();
    }

    @Override
    public List<QnaBook> findFirstLimitQnaBooks(int limit) {
        return jpaQueryFactory.selectFrom(qnaBook)
            .orderBy(qnaBook.createdAt.desc())
            .limit(limit)
            .fetch();
    }
}
