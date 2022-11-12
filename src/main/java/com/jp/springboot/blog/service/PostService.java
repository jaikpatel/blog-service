package com.jp.springboot.blog.service;

import com.jp.springboot.blog.dto.PostDto;
import com.jp.springboot.blog.dto.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    //List<PostDto> getAllPosts();

    //List<PostDto> getAllPosts(int pageNo, int pageSize);

    //PostResponse getAllPosts(int pageNo, int pageSize);

    //PostResponse getAllPosts(int pageNo, int pageSize, String sortBy);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(long id);

    PostDto updatePostById(PostDto postDto, long id);

    void deletePostById(long id);

}
