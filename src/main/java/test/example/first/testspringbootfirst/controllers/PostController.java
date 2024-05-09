package test.example.first.testspringbootfirst.controllers;

import java.lang.Exception;
import io.sentry.Sentry;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.example.first.testspringbootfirst.Services.PostService;
import test.example.first.testspringbootfirst.Utils.AppConstants;
import test.example.first.testspringbootfirst.helper.CustomResponse;
import test.example.first.testspringbootfirst.helper.PageableResponse;
import test.example.first.testspringbootfirst.payloads.PostPDO;

import java.util.*;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/api")
public class PostController {

    private PostService postService;
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public ResponseEntity<CustomResponse> getPosts(

                           @RequestParam(name = "sort_by", defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
                           @RequestParam(name = "sort_dir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir,
                           @RequestParam(name = "page_no", defaultValue = AppConstants.DEFAULT_PAGE_NO, required = false) int pageNo,
                           @RequestParam(name = "per_page", defaultValue = AppConstants.DEFAULT_PER_PAGE, required = false) int perPage){


        PageableResponse response = postService.getPosts(sortBy, sortDir, pageNo, perPage);

        CustomResponse customResponse = new CustomResponse(200,"Show all posts", response, new Date().toString());

        ResponseEntity<CustomResponse> responseEntity = new ResponseEntity<>(customResponse, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<CustomResponse> getPost(@PathVariable(name = "id", required = true) Long postId) {
        PostPDO postPDO = postService.postById(postId);
        List<PostPDO> postPDOList = new ArrayList<PostPDO>(Collections.singleton(postPDO));
        CustomResponse customResponse = new CustomResponse(200, "Fetch post successfully!", postPDOList, new Date().toString());
        ResponseEntity<CustomResponse> response = new ResponseEntity<>(customResponse, HttpStatus.OK);
        return response;
    }

    @PostMapping("/post/store")
    public ResponseEntity<CustomResponse> store(@RequestBody PostPDO postPDO) {
        int i = 0;
        int a = 10;
        int s = (a/i);
        PostPDO newPostPDO = postService.createPost(postPDO);
        ArrayList<PostPDO> arrayList = new ArrayList<>();
        arrayList.add(newPostPDO);
        CustomResponse customResponse = new CustomResponse(201, "Post created successfully!",arrayList, new Date().toString() );
        ResponseEntity<CustomResponse> response = new ResponseEntity<>(customResponse, HttpStatus.CREATED);
        return response;
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<CustomResponse> deletePost(@PathVariable(name = "id", required = true) Long postId) {
        postService.deletePostById(postId);

        CustomResponse customResponse = new CustomResponse(200, "Post deleted successfully!", null, new Date().toString());
        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }


}
