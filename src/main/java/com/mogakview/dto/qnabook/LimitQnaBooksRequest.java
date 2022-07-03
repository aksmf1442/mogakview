package com.mogakview.dto.qnabook;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LimitQnaBooksRequest {

    private Long id;
    private int limit;
    private boolean first;
}
