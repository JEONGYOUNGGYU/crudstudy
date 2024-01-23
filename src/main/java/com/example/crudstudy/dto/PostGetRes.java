package com.example.crudstudy.dto;

import lombok.Builder;

@Builder

public record PostGetRes(
    Long id,
    String title,
    String author
) {

}
