package com.mogakview.domain.heart;

import com.mogakview.domain.qnabook.QnaBook;
import com.mogakview.domain.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeartRepository extends JpaRepository<Heart, Long> {

    Optional<Heart> findByQnaBookAndUser(QnaBook qnaBook, User user);
}
