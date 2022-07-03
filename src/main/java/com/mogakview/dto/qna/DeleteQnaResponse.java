package com.mogakview.dto.qna;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class DeleteQnaResponse {

    private Long id;

    public static DeleteQnaResponse of(Long id) {
        return DeleteQnaResponse.builder()
            .id(id)
            .build();
    }
}
