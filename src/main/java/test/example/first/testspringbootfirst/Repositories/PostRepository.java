package test.example.first.testspringbootfirst.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import test.example.first.testspringbootfirst.models.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
