package com.example.crudstudy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class PostUpdateReq {

  private String title;
  private String content;
  private String password;

}
