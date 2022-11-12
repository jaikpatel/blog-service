package com.jp.springboot.blog.controller;

import com.jp.springboot.blog.dto.CommentDto;
import com.jp.springboot.blog.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "CRUD Rest API Comment Resources")
@RestController
@RequestMapping("/api/v1")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ApiOperation(value = "Create comment REST API")
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(
            @PathVariable("postId") long id,
            @Valid @RequestBody CommentDto commentDto
    ){
        return new ResponseEntity<>(commentService.createComment(id, commentDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get all comments by Post ID REST API")
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getAllCommentsByPostId(@PathVariable("postId") long postId){
        return new ResponseEntity<>(commentService.getAllCommentsByPostId(postId),HttpStatus.OK);
    }

    @ApiOperation(value = "Get single comment by ID REST API")
    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getAllCommentsByPostId(
            @PathVariable("postId") long postId,
            @PathVariable("commentId") long commentId){
        return new ResponseEntity<>(commentService.getCommentByPostId(postId,commentId),HttpStatus.OK);
    }


    @ApiOperation(value = "Update comment by ID REST API")
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("postId") long postId,
                                                    @PathVariable("commentId") long commentId,
                                                    @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(
                commentService.updateComment(postId,commentId,commentDto),
                HttpStatus.OK);
    }


    @ApiOperation(value = "Delete comment by ID REST API")
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable("postId") long postId,
                                                @PathVariable("commentId") long commentId){
        commentService.deleteComment(postId,commentId);
        return new ResponseEntity<>("Deleted Successfully!!", HttpStatus.OK);
    }
}
