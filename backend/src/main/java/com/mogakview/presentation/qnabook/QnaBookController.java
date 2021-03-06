package com.mogakview.presentation.qnabook;

import com.mogakview.application.qnabook.QnaBookService;
import com.mogakview.config.auth.AppUser;
import com.mogakview.config.auth.LoginUser;
import com.mogakview.dto.heart.HeartResponse;
import com.mogakview.dto.qna.QnasResponse;
import com.mogakview.dto.qnabook.DeleteQnaBookResponse;
import com.mogakview.dto.qnabook.LimitQnaBooksRequest;
import com.mogakview.dto.qnabook.LimitQnaBooksResponse;
import com.mogakview.dto.qnabook.QnaBookRequest;
import com.mogakview.dto.qnabook.QnaBookResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/qna-books")
public class QnaBookController {

    private final QnaBookService qnaBookService;

    @GetMapping("")
    public ResponseEntity<LimitQnaBooksResponse> findLimitQnaBooks(
        @ModelAttribute LimitQnaBooksRequest qnaBooksRequest) {
        LimitQnaBooksResponse qnaBookResponses = qnaBookService.findLimitQnaBooks(qnaBooksRequest);
        return ResponseEntity.ok(qnaBookResponses);
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteQnaBookResponse> deleteQnaBook(@PathVariable Long id,
        @LoginUser AppUser appUser) {
        DeleteQnaBookResponse qnaBookResponse = qnaBookService.deleteQnaBook(id, appUser);
        return ResponseEntity.ok(qnaBookResponse);
    }

    @GetMapping("/{qnaBookId}/qnas")
    public ResponseEntity<QnasResponse> findQnasInQnaBook(@PathVariable Long qnaBookId) {
        QnasResponse qnasResponse = qnaBookService.findQnasInQnaBook(qnaBookId);
        return ResponseEntity.ok(qnasResponse);
    }

    @PostMapping("/{qnaBookId}/hearts")
    public ResponseEntity<HeartResponse> toggleHeart(@PathVariable Long qnaBookId,
        @LoginUser AppUser appUser) {
        HeartResponse heartResponse = qnaBookService.toggleHeart(qnaBookId, appUser);
        return ResponseEntity.ok(heartResponse);
    }

    @GetMapping("/{qnaBookId}/hearts")
    public ResponseEntity<HeartResponse> checkClickedHeart(@PathVariable Long qnaBookId,
        @LoginUser AppUser appUser) {
        HeartResponse heartResponse = qnaBookService.checkClickedHeart(qnaBookId, appUser);
        return ResponseEntity.ok(heartResponse);
    }
}
