package com.mogakview.dto.qna;

import com.mogakview.domain.qna.Qna;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class QnasResponse {

    private List<QnaResponse> qnasResponse;

    public static QnasResponse of(List<Qna> qnas) {
        return QnasResponse.builder()
            .qnasResponse(qnas.stream()
                .map(QnaResponse::of)
                .collect(Collectors.toList()))
            .build();
    }
}
