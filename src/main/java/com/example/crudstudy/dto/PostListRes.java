package com.example.crudstudy.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class PostListRes {

  private List<PostGetRes> posts = new ArrayList<>();

}
