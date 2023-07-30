package jobhackersystem.org.todos;

import jobhackersystem.org.error.TodoDuplicateIDException;
import jobhackersystem.org.error.TodoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todo/v1")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping({"", "/"})
    public ResponseEntity<List<TodoEntity>> getAllTodos() {
        List<TodoEntity> result=todoService.findAllTodos();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<TodoEntity>> getTodoById(@PathVariable Long id) {
       Optional<TodoEntity> result=todoService.findTodoById(id);
       if(result.isPresent()){
           return new ResponseEntity<>(result,HttpStatus.OK);
       }
       else{
           throw new TodoNotFoundException("OOPS TodoEntity With ID: "+id+" Not Found");
       }
    }
    @PostMapping({"", "/"})
    public ResponseEntity<TodoEntity> createTodo(@RequestBody TodoEntity todoEntity) {
        if (todoService.findByTitle(todoEntity.getTitle()) == null) {
            TodoEntity result = todoService.saveTodo(todoEntity);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }else{
            throw new TodoDuplicateIDException("OOPS TodoEntity With Title: "+ todoEntity.getTitle()+" Is Duplicated");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
