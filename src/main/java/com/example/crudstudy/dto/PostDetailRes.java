package com.example.crudstudy.dto;

import lombok.Builder;

@Builder
public record PostDetailRes(
    Long id,
    String title,
    String content,
    String author
) {

}
