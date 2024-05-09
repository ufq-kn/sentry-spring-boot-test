package test.example.first.testspringbootfirst.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(
        name = "posts",
        uniqueConstraints = {
                @UniqueConstraint(name = "title_unique", columnNames = {"title"}),
                @UniqueConstraint(name = "description_type_unique", columnNames = {"description" ,"type"})
        }
)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true,nullable = false)
    private String title;

    @Column(nullable = true)
    private String subTitle;

    @Column(length = 500)
    private String description;

    @Column(length = 500)
    private String content;

    @Column
    private String type;

    @Column(name="deleted")
    private boolean deleted = Boolean.FALSE;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
