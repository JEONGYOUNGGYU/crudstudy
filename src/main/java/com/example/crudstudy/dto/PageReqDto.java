package com.example.crudstudy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor
public class PageReqDto {

  private Integer p;
  private Integer limit;

  public Pageable toPageable() {
    return PageRequest.of(p-1, limit);
  }
}
