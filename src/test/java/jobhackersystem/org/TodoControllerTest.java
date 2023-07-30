package jobhackersystem.org;

import com.fasterxml.jackson.databind.ObjectMapper;
import jobhackersystem.org.todos.TodoEntity;
import jobhackersystem.org.todos.TodoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    @Test
    public void getAllTodosTest() throws Exception {
        TodoEntity todo1 = new TodoEntity(1L, "todo test1", "todo desc test1");
        TodoEntity todo2 = new TodoEntity(2L, "todo test2", "todo desc test2");
        List<TodoEntity> data = Arrays.asList(todo1, todo2);

        given(todoService.findAllTodos()).willReturn(data);

        mockMvc.perform(get("/api/todo/v1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", equalTo(todo1.getTitle())));
    }

    @Test
    public void createTodoTest() throws Exception {
        TodoEntity todo = new TodoEntity();
        todo.setTitle("test post");
        todo.setDescription("todo desc post");
        given(todoService.saveTodo(Mockito.any(TodoEntity.class))).willReturn(todo);
        ObjectMapper mapper=new ObjectMapper();
        mockMvc.perform(post("/api/todo/v1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(todo)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title",is(todo.getTitle())));
    }
}
