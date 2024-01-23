package com.example.crudstudy.dto;

public record PostCreateReq(
    String title,
    String content,
    String author,
    String password
) {
}
