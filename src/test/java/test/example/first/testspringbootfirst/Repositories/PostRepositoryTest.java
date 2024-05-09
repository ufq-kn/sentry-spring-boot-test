package test.example.first.testspringbootfirst.Repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import test.example.first.testspringbootfirst.models.Post;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    void storePost(){
        Post post = new Post();
        post.setTitle("Title 1");
        post.setSubTitle("Sub title 1");
        post.setContent("Content 1");
        post.setDescription("Description 1");
        post.setType("Type 1");
        Post savedPost = postRepository.save(post);

        System.out.println(savedPost.getId());
        System.out.println(savedPost.toString());

    }
}