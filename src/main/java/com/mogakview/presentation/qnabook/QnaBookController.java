package com.mogakview.presentation.qnabook;

import com.mogakview.application.qnabook.QnaBookService;
import com.mogakview.config.auth.AppUser;
import com.mogakview.config.auth.LoginUser;
import com.mogakview.dto.qnabook.QnaBookRequest;
import com.mogakview.dto.qnabook.QnaBookResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/qnaBook")
public class QnaBookController {

    private final QnaBookService qnaBookService;

    @PostMapping("")
    public ResponseEntity<QnaBookResponse> createQnaBook(@RequestBody QnaBookRequest qnaBookRequest,
        @LoginUser
            AppUser loginUser) {
        QnaBookResponse qnaBookResponse = qnaBookService.createQnaBook(qnaBookRequest, loginUser);
        return ResponseEntity.ok(qnaBookResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QnaBookResponse> findQnaBook(@PathVariable Long id) {
        QnaBookResponse qnaBookResponse = qnaBookService.findQnaBookById(id);
        return ResponseEntity.ok(qnaBookResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QnaBookResponse> updateQnaBook(@RequestBody QnaBookRequest qnaBookRequest,
        @PathVariable Long id, @LoginUser AppUser appUser) {
        QnaBookResponse qnaBookResponse = qnaBookService.updateQnaBook(id, qnaBookRequest, appUser);
        return ResponseEntity.ok(qnaBookResponse);
    }
}
