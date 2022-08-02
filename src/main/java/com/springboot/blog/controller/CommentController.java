package com.springboot.blog.controller;

import com.springboot.blog.payload.dto.CommentDto;
import com.springboot.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDto> createPost(@Valid @RequestBody CommentDto commentDto, @PathVariable(name = "postId") long postId){
        return new ResponseEntity<>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentById(@PathVariable(name = "postId") long postId){
        return new ResponseEntity<>(commentService.getAllCommentsByPostId(postId),HttpStatus.OK);
    }

    @GetMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(name = "postId") long postId,@PathVariable(name = "commentId") long commentId){
        return new ResponseEntity<>(commentService.getCommentById(postId,commentId),HttpStatus.OK);
    }

    @PutMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateCommentById(@Valid @RequestBody CommentDto commentDto,@PathVariable(name = "postId") long postId,@PathVariable(name = "commentId") long commentId){
        return new ResponseEntity<>(commentService.updateCommentById(commentDto,postId,commentId),HttpStatus.OK);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteCommentById(@PathVariable(name = "postId") long postId,@PathVariable(name = "commentId") long commentId){
        commentService.deleteCommentById(postId,commentId);
        return new ResponseEntity<String>("Delete Success",HttpStatus.OK);
    }
}
