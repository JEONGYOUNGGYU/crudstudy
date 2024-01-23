package com.example.crudstudy.dto;


import lombok.Builder;

@Builder
public record PostUpdateRes(
    String title,
    String content
) {
}
