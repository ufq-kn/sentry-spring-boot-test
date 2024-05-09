package test.example.first.testspringbootfirst.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponse {

    private int statusCode;
    private String message;
    private Object body;
    private String timestamps;
}
