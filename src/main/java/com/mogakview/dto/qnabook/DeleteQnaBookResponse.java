package com.mogakview.dto.qnabook;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DeleteQnaBookResponse {

    private Long id;

    public static DeleteQnaBookResponse of(Long id) {
        return DeleteQnaBookResponse.builder()
            .id(id)
            .build();
    }
}
