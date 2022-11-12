package com.jp.springboot.blog.repository;

import com.jp.springboot.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//NO need to pass @Repository OR @Transectional since its already included along with JpaRepository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(long postId);

}
