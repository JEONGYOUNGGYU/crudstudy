package com.example.crudstudy.dto;

import lombok.Builder;

@Builder
public record PostCreateRes(
  Long id,
  String title,
  String content
) {

}
