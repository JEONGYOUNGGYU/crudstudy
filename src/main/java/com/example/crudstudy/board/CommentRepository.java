package com.example.crudstudy.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Long> {

  @Modifying
  @Query("delete from Comment c where c.post.id = :postId")
  void deleteAllPostId(Long postId);

  // jpa -> jpql -> sql -> db
  // table 기준 jpql 엔티티 기준
  // querydsl -> jpql -> sql ->
  // Comment comment jpql 이클립스 <-
  // sql study -> 프로그래머스 문제 ->
  // jdbc 개발 -> 순수 sql 써서
  // comment.getPost().getId().equals(postId)
  // 좋아요 생성, 삭제, (조회 join 쿼리 최적화) -- 테이블 여러개 엮여있어서?
  // 일단 구현하고 -> 그 다음에 리팩토링
  // 팔로우 생성, 삭제, 조회

  // left Comment -> c post.id = post id
  // PostId1 댓글1 // PostId 제목1 내용1
  // PostId 1 댓글 1 제목 1 내용 1

}
