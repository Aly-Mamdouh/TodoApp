package jobhackersystem.org.todos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "todos")
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Title is required")
    @Size(min = 5,max = 255, message = "Title should not be less than 5 chars and no more than 255 chars")
    private String title;
    @NotBlank(message = "Description is required")
    @Size(min = 5,max = 1000, message = "Description should not  be less than 5 chars and no more than 1000 chars")
    private String description;

    public TodoEntity() {
    }

    public TodoEntity(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
