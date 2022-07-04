package com.mogakview.presentation.qnabook;

import com.mogakview.domain.heart.Heart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class HeartResponse {

    private boolean clicked;

    public static HeartResponse of(boolean clicked) {
        return HeartResponse.builder()
            .clicked(clicked)
            .build();
    }
}
