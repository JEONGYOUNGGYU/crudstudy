package com.example.crudstudy.board;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  private String content;

  private String author;

  private String password;

  @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
  private List<Comment> comments = new ArrayList<>();

  @Builder
  public Post(String title, String content, String author, String password) {
    this.title = title;
    this.content = content;
    this.author = author;
    this.password = password;
  }

  public void update(String title, String content) {
    this.title = title;
    this.content = content;
  }
}
