package inc.codeman.springboot.todomanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import inc.codeman.springboot.todomanagement.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
