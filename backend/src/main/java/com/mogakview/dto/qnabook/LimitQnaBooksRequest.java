package com.mogakview.dto.qnabook;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LimitQnaBooksRequest {

    private Long id;
    private int limit;
    private boolean first;
}
