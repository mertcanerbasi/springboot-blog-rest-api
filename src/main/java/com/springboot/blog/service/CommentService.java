package com.springboot.blog.service;

import com.springboot.blog.payload.dto.CommentDto;

import java.util.List;


public interface CommentService {
    CommentDto createComment(long postId,CommentDto commentDto);

    List<CommentDto> getAllCommentsByPostId(long postId);

    CommentDto getCommentById(long postId,long commentId);

    CommentDto updateCommentById(CommentDto commentDto,long postId,long commentId);

    public void deleteCommentById(long postId,long CommentId);

}
