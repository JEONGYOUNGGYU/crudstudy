package com.example.crudstudy.board;

import com.example.crudstudy.dto.PageReqDto;
import com.example.crudstudy.dto.PostCreateReq;
import com.example.crudstudy.dto.PostCreateRes;
import com.example.crudstudy.dto.PostDetailRes;
import com.example.crudstudy.dto.PostGetRes;
import com.example.crudstudy.dto.PostListRes;
import com.example.crudstudy.dto.PostUpdateReq;
import com.example.crudstudy.dto.PostUpdateRes;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;
  private final CommentRepository commentRepository;

  public PostCreateRes create(PostCreateReq req) {
    Post post = Post.builder()
        .title(req.title())
        .content(req.content())
        .author(req.author())
        .password(req.password())
        .build();
    postRepository.save(post);

    return PostCreateRes.builder()
        .id(post.getId())
        .title(post.getTitle())
        .content(post.getContent())
        .build();
  }

  public PostListRes getList() {
    List<Post> posts = postRepository.findAll();
    PostListRes res = new PostListRes();
    for (Post post : posts) {
      res.getPosts().add(PostGetRes.builder()
              .id(post.getId())
              .title(post.getTitle())
              .author(post.getAuthor())
          .build());
    }
  return res;
  }

  public List<PostGetRes> getListT() {
    List<Post> posts = postRepository.findAll();
    List<PostGetRes> res = new ArrayList<>();
    for (Post post : posts) {
      res.add(PostGetRes.builder()
          .id(post.getId())
          .title(post.getTitle())
          .author(post.getAuthor())
          .build());
    }
    return res;
  }

  public PostDetailRes getPostRes(Long id) {
    Post post = getPost(id);
    return PostDetailRes.builder()
        .id(post.getId())
        .title(post.getTitle())
        .content(post.getContent())
        .author(post.getAuthor())
        .build();
  }

  @Transactional
  public PostUpdateRes update(Long id, PostUpdateReq req) {
    Post post = passwordCheck(req.getPassword(), id);
    post.update(req.getTitle(), req.getContent());

    return PostUpdateRes.builder()
        .title(req.getTitle())
        .content(req.getContent())
        .build();
  }

  public String delete(Long id, String password) {
    Post post = passwordCheck(password, id);
    postRepository.delete(post);
    return "삭제 성공";
  }

  @Transactional
  public String deleteT(Long id, String password) {
    Post post = passwordCheck(password, id);
    commentRepository.deleteAllPostId(id);
    postRepository.delete(post);
    return "삭제 성공";
  }

  public Page<PostGetRes> getPageList(PageReqDto pageReq) {
    Pageable pageable = pageReq.toPageable();
    Page<Post> pageList = postRepository.findAll(pageable);
    return pageList.map(post -> PostGetRes.builder()
        .id(post.getId())
        .title(post.getTitle())
        .author(post.getAuthor())
        .build());
  }

  public Page<Post> getPageListT(PageReqDto pageReq) {
    Pageable pageable = pageReq.toPageable();
    return postRepository.findAll(pageable);
  }

  public Post getPost(Long id) {
    return postRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("존재X"));
  }

  private Post passwordCheck(String password, Long id) {
    Post post = getPost(id);
    if (!post.getPassword().equals(password)) {
      throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");
    }
    return post;
  }
}
