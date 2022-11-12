package com.jp.springboot.blog.controller;

import com.jp.springboot.blog.dto.PostDto;
import com.jp.springboot.blog.dto.PostResponse;
import com.jp.springboot.blog.service.PostService;
import com.jp.springboot.blog.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * 1. Versioning through URI path [/api/v1/products] MOST COMM USED
 * 2. Versioning through query parameters [/api/products?version=1]
 * 3. Versioning through custom headers [/api/products with headers[X-API-VERSION:1]]
 * 4. Versioning through content negotiation [/api/products with headers [Accept=application/vnd.jpinc-v1+json]]
 *
 */
@Api(value = "CRUD REST APIs for blog post resources")
@RestController
@RequestMapping()
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // Create blog POST API
    // http://localhost:8080/api/posts
    @ApiOperation(value = "Create Post REST API")
    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/v1/posts")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    // get ALL blog post [GET API], get all blogs with more details.
    // Pagination and Sorting
    //  http://localhost:8080/api/posts?pageNo=0&pageSize=10&sortBy=title&sortBy=asc
    @ApiOperation(value = "Get All Post REST API")
    @GetMapping("/api/v1/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTORY, required = false) String sortDir
    ){
        return ResponseEntity.ok(postService.getAllPosts(pageNo, pageSize, sortBy, sortDir));
    }

    // get blog by post ID [GET API]
    // http://localhost:8080/api/posts/1
    @ApiOperation(value = "Get Post by ID REST API")
    @GetMapping("/api/v1/posts/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long postId){
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    // update blog by post ID [PUT API]
    // http://localhost:8080/api/posts/1
    @ApiOperation(value = "Update Post By ID  REST API")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/api/v1/posts/{id}")
    public ResponseEntity<PostDto> updatePostById(@Valid @RequestBody PostDto postDto,
                                                  @PathVariable("id") long postId){
        return ResponseEntity.ok(postService.updatePostById(postDto,postId));
    }

    // delete blog by post ID [DELETE API]
    // http://localhost:8080/api/posts/1
    @ApiOperation(value = "Delete Post by ID REST API")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/api/v1/posts/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable("id") long postId){
        postService.deletePostById(postId);
        return new ResponseEntity<>("Deleted successfully!",HttpStatus.OK);
    }

    // get ALL blog post [GET API], get all blogs with more details.
    // Pagination and Sorting
    //  http://localhost:8080/api/posts?pageNo=0&pageSize=10&sortBy=title
    /*@GetMapping
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy
    ){
        return ResponseEntity.ok(postService.getAllPosts(pageNo, pageSize, sortBy));
    }*/

    // get ALL blog post [GET API], get all blogs with more details.
    // http://localhost:8080/api/posts
    /*@GetMapping
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ){
        return ResponseEntity.ok(postService.getAllPosts(pageNo, pageSize));
    }*/

    // get ALL blog post [GET API] with default or u can pass page # and size
    // http://localhost:8080/api/posts
    // http://localhost:8080/api/posts?pageNo=0&pageSize=5
    /*@GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ){
        return ResponseEntity.ok(postService.getAllPosts(pageNo, pageSize));
    }*/

    // get ALL blog post [GET API]
    // http://localhost:8080/api/posts
    /*@GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(){
        return ResponseEntity.ok(postService.getAllPosts());
    }*/


}
