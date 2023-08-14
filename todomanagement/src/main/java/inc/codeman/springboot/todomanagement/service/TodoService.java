package inc.codeman.springboot.todomanagement.service;

import java.util.List;

import inc.codeman.springboot.todomanagement.dto.TodoDto;

public interface TodoService {
  TodoDto createTodo(TodoDto todoDto);

  TodoDto getTodo(Long id);

  List<TodoDto> getTodo();

  TodoDto updateTodo(TodoDto todoDto);

  void deleteTodo(Long id);

  TodoDto completeTodo(Long id);

  TodoDto inCompleteTodo(Long id);
}
