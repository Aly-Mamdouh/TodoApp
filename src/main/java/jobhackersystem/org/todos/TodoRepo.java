package jobhackersystem.org.todos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepo extends JpaRepository<TodoEntity,Long> {
    TodoEntity findByTitle(String title);
}
