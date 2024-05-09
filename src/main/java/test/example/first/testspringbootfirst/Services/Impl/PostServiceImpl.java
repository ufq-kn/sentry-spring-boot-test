package test.example.first.testspringbootfirst.Services.Impl;

import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import test.example.first.testspringbootfirst.Repositories.PostRepository;
import test.example.first.testspringbootfirst.Services.PostService;
import test.example.first.testspringbootfirst.exceptions.ResourceNotFoundException;
import test.example.first.testspringbootfirst.helper.PageableResponse;
import test.example.first.testspringbootfirst.helper.Pagination;
import test.example.first.testspringbootfirst.models.Post;
import test.example.first.testspringbootfirst.payloads.PostPDO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public PostPDO createPost(PostPDO postPDO) {

        Post post = new Post();
        post.setTitle(postPDO.getTitle());
        post.setSubTitle(postPDO.getSubTitle());
        post.setDescription(postPDO.getDescription());
        post.setContent(postPDO.getContent());
        post.setType(postPDO.getType());

        Post newPost = postRepository.save(post);

        PostPDO pdo = new PostPDO();
        pdo.setTitle(newPost.getTitle());
        pdo.setSubTitle(newPost.getSubTitle());
        pdo.setDescription(newPost.getDescription());
        pdo.setContent(newPost.getContent());
        pdo.setType(newPost.getType());


        return pdo;
    }

    @Override
    public PageableResponse getPosts(String sortBy, String sortDir, int pageNo, int perPage) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, perPage, sort);

        Page<Post> posts = postRepository.findAll(pageable);

        List<Post> listOfPage = posts.getContent();

        List<PostPDO> postPDO = listOfPage.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
        System.out.println((postPDO));

        PageableResponse response = new PageableResponse();
        response.setData(postPDO);
        response.setTotal(posts.getTotalElements());
        response.setTotalPages(posts.getTotalPages());
        response.setPageSize(posts.getSize());
        response.setPageNo(posts.getNumber());
        response.setLast(posts.isLast());

        Pagination pagination = new Pagination(sortDir, sortBy, pageNo, posts.getTotalPages(), perPage);
        response.setLinks(pagination.links());
        return response;
    }

    @Override
    public PostPDO postById(Long id) {
       Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id+""));
       PostPDO postPDO = mapToDTO(post);
       return postPDO;
    }

    @Override
    public void deletePostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id+""));
        postRepository.delete(post);
    }


    //Convert Entity to DTO
    private PostPDO mapToDTO(Post post){
        PostPDO postDto = new PostPDO();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
    }

    // Convert DTO to Entity
    private Post mapToEntity(PostPDO postDto){
        Post post = new Post();
        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }
}
