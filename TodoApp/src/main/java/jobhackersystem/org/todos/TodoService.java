package jobhackersystem.org.todos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    private TodoRepo repo;
    public List<TodoEntity> findAllTodos() {
        return repo.findAll();
    }
    public Optional<TodoEntity> findTodoById(Long id) {
      return repo.findById(id);
    }
    public TodoEntity saveTodo(TodoEntity todoEntity){
        return repo.save(todoEntity);
    }
    public void deleteTodo(Long id){
       repo.deleteById(id);
    }
    public TodoEntity findByTitle(String title) {
        return repo.findByTitle(title);
    }
}
