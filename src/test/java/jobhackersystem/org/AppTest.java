package jobhackersystem.org;

/**
 * Unit test for simple App.
 */

import jobhackersystem.org.todos.TodoEntity;
import jobhackersystem.org.todos.TodoRepo;
import jobhackersystem.org.todos.TodoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {
    @MockBean
    private TodoRepo todoRepo;
    @Autowired
    private TodoService todoService;

    @TestConfiguration
    static class TodoServiceTestConfiguration {
        @Bean(name = "testTodoService")
        @Primary
        public TodoService todoService() {
            return new TodoService();
        }
    }

    @Test
    public void finAllTodoListTest() {
        TodoEntity todo1 = new TodoEntity(1L, "todo test1", "todo desc test1");
        TodoEntity todo2 = new TodoEntity(2L, "todo test2", "todo desc test2");
        List<TodoEntity> data = Arrays.asList(todo1, todo2);
        given(todoRepo.findAll()).willReturn(data);
        assertThat(todoService.findAllTodos())
                .hasSize(2)
                .contains(todo1, todo2);

    }

    @Test
    public void findTodoByIdTest() {
        TodoEntity todo = new TodoEntity(1L, "todo test1", "todo desc test1");
        given(todoRepo.findById(1L)).willReturn(Optional.ofNullable(todo));
        Optional<TodoEntity> result = todoService.findTodoById(1L);
        assertThat(result.get().getId()).isEqualTo(1L);
    }

}
