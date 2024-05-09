package test.example.first.testspringbootfirst.helper;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Pagination {
    private String sortDir;
    private String sortAttr;
    private int currentPage;
    private int totalPage;
    private int perPage;

    private Map<String,ArrayList<String>> links = new HashMap<String, ArrayList<String>>();

    public Pagination(String sortDir, String sortAttr, int currentPage, int totalPage, int perPage) {
        this.sortDir = sortDir;
        this.sortAttr = sortAttr;
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.perPage = perPage;
    }


    public Map<String,ArrayList<String>> links() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        ArrayList<String> current = new ArrayList<>();
        current.add(request.getRequestURL().toString()+"?sort_by="+this.sortAttr+"&sort_dir="+this.sortDir+"C&page_no="+currentPage+"&per_page="+perPage);
        ArrayList<String> next = new ArrayList<>();
        ArrayList<String> previous = new ArrayList<>();

        for (int i = (this.currentPage +1); i < this.totalPage; i++) {
            String link = request.getRequestURL().toString()+"?sort_by="+this.sortAttr+"&sort_dir="+this.sortDir+"C&page_no="+i+"&per_page="+perPage;
            next.add(link);
        }
        for (int i = 0; i < this.currentPage; i++) {
            String link = request.getRequestURL().toString()+"?sort_by="+this.sortAttr+"&sort_dir="+this.sortDir+"C&page_no="+i+"&per_page="+perPage;
            previous.add(link);
        }
        links.put("current",current);
        links.put("next",next);
        links.put("previous",previous);
        return links;
    }
}
