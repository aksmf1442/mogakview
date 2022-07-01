package com.mogakview.dto.qnabooktag;

import com.mogakview.domain.qnabooktag.QnaBookTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class QnaBookTagResponse {

    private Long id;

    private String name;

    public static QnaBookTagResponse of(QnaBookTag qnaBookTag) {
        return QnaBookTagResponse.builder()
            .id(qnaBookTag.getId())
            .name(qnaBookTag.getName())
            .build();
    }
}
