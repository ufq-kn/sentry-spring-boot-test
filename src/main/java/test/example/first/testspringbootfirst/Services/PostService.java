package test.example.first.testspringbootfirst.Services;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import test.example.first.testspringbootfirst.helper.PageableResponse;
import test.example.first.testspringbootfirst.payloads.PostPDO;

import java.util.ArrayList;
import java.util.List;

public interface PostService {
    PostPDO createPost(PostPDO postPDO);
//    @Query("update #{#posts} p set p.delete=true where p.id=false")
    PageableResponse getPosts(String sortBy, String sortDir, int pageNo, int perPage);
    PostPDO postById(Long id);
    //Soft delete.
//    @Query("update #{#posts} p set p.delete=true where p.id=?1")
//    @Modifying
    void deletePostById(Long id);
}
