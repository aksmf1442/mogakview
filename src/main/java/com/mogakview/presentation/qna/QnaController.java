package com.mogakview.presentation.qna;

import com.mogakview.application.qna.QnaService;
import com.mogakview.config.auth.AppUser;
import com.mogakview.config.auth.LoginUser;
import com.mogakview.dto.qna.QnaRequest;
import com.mogakview.dto.qna.QnaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/qnas")
public class QnaController {

    private final QnaService qnaService;

    @PostMapping("")
    public ResponseEntity<QnaResponse> createQna(@RequestBody QnaRequest qnaRequest,
        @LoginUser AppUser appUser) {
        QnaResponse qnaResponse = qnaService.createQna(qnaRequest, appUser);
        return ResponseEntity.ok(qnaResponse);
    }
}
