package com.springboot.blog.service;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNumber, int pageSize,String sortBy,String sortDir);

    PostDto getPostById(long id);

    PostDto updatePostById(PostDto postDto,long id);

    public void deletePostById(long id);

}
