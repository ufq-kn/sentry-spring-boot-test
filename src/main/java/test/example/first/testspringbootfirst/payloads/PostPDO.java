package test.example.first.testspringbootfirst.payloads;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class PostPDO {

    private Long Id;
    private String title;
    private String subTitle;
    private String description;
    private String content;
    private String type;

}
