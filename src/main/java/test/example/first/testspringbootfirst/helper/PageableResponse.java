package test.example.first.testspringbootfirst.helper;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import test.example.first.testspringbootfirst.payloads.PostPDO;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PageableResponse {
    private List<PostPDO> data;
    private Long total;
    private int pageNo;
    private int pageSize;
    private int totalPages;
    private boolean isLast;

    private Map<String,ArrayList<String>> links = new HashMap<String, ArrayList<String>>();

//    public void links() {
//        for (int i = (pageNo+1); i < totalPages; i++) {
//            String link = "http://localhost:8080/api/posts?sort_by=id&sort_dir=DESC&page_no="+i+"&per_page=";
//            links.add(link);
//        }
//    }
}
