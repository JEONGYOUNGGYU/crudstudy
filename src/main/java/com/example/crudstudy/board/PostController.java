package com.example.crudstudy.board;

import com.example.crudstudy.dto.PageReqDto;
import com.example.crudstudy.dto.PostCreateReq;
import com.example.crudstudy.dto.PostCreateRes;
import com.example.crudstudy.dto.PostDetailRes;
import com.example.crudstudy.dto.PostGetRes;
import com.example.crudstudy.dto.PostListRes;
import com.example.crudstudy.dto.PostUpdateReq;
import com.example.crudstudy.dto.PostUpdateRes;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.jaxb.SpringDataJaxb.PageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

  // 목록 조회, 상세 조회
  private final PostService postService;
  private final CommentRepository commentRepository;

  @PostMapping
  public ResponseEntity<PostCreateRes> create(@RequestBody PostCreateReq req) {

    return ResponseEntity.ok(postService.create(req));
  }

  @GetMapping
  public ResponseEntity<PostListRes> getList() {
    PostListRes res = postService.getList();
    return ResponseEntity.ok(res);
  }

  @GetMapping("/v2")
  public ResponseEntity<List<PostGetRes>> getListT() {
    return ResponseEntity.ok(postService.getListT());
  }

  // 페이징
  @GetMapping("/v3")
  public ResponseEntity<Page<PostGetRes>> getPageList(PageReqDto pageReq) {
    Page<PostGetRes> res = postService.getPageList(pageReq);
    return ResponseEntity.ok(res);
  }

  @GetMapping("/v4")
  public ResponseEntity<Page<Post>> getPageListT(PageReqDto pageReq) {
    return ResponseEntity.ok(postService.getPageListT(pageReq));
  }

  @GetMapping("/{id}")
  public ResponseEntity<PostDetailRes> getPostRes(@PathVariable Long id){
    return ResponseEntity.ok(postService.getPostRes(id));
  }

  /**
   * 수정, 삭제 Patch -> title, content
   */

  @PatchMapping("/{id}")
  public ResponseEntity<PostUpdateRes> update(@PathVariable Long id,
      @RequestBody PostUpdateReq req){

    return ResponseEntity.ok(postService.update(id, req));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable Long id,
      @RequestBody Map<String, String> body) {

    String password = body.get("password");
    return ResponseEntity.ok(postService.delete(id, password));
  }

  @DeleteMapping("/v2/{id}")
  public ResponseEntity<String> deleteT(@PathVariable Long id,
      @RequestBody Map<String, String> body) {

    String password = body.get("password");
    return ResponseEntity.ok(postService.deleteT(id, password));
  }

  @PostMapping("/comments/{id}")
  public ResponseEntity<?> initComment(@PathVariable Long id) {
    Post post = postService.getPost(id);
    for (int i = 0; i < 10; i++) {
      commentRepository.save(Comment.builder()
          .content("내용" + i)
          .post(post)
          .build());
    }
    return ResponseEntity.ok("생성");
  }


}