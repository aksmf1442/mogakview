package com.mogakview.dto.qnabooktag;

import com.mogakview.domain.qnabooktag.QnaBookTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class TagResponse {

    private Long id;

    private String name;

    public static TagResponse of(QnaBookTag qnaBookTag) {
        return TagResponse.builder()
            .id(qnaBookTag.getId())
            .name(qnaBookTag.getTag().getName())
            .build();
    }
}
