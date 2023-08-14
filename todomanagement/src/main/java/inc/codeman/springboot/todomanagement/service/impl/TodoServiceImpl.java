package inc.codeman.springboot.todomanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inc.codeman.springboot.todomanagement.dto.TodoDto;
import inc.codeman.springboot.todomanagement.entity.Todo;
import inc.codeman.springboot.todomanagement.exception.TaskNotFoundException;
import inc.codeman.springboot.todomanagement.repository.TodoRepository;
import inc.codeman.springboot.todomanagement.service.TodoService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Service
public class TodoServiceImpl implements TodoService {

  private ModelMapper modelMapper;
  private TodoRepository todoRepository;

  @Autowired
  public TodoServiceImpl(ModelMapper modelMapper, TodoRepository todoRepository) {
    this.modelMapper = modelMapper;
    this.todoRepository = todoRepository;
  }

  @Override
  public TodoDto createTodo(TodoDto todoDto) {
    Todo todo = modelMapper.map(todoDto, Todo.class);
    Todo savedTodo = todoRepository.save(todo);
    TodoDto savedTodoDto = modelMapper.map(savedTodo, TodoDto.class);
    return savedTodoDto;
  }

  @Override
  public TodoDto getTodo(Long id) {
    if (todoRepository.findById(id).isPresent()) {
      Todo todo = todoRepository.findById(id).get();
      TodoDto todoDto = modelMapper.map(todo, TodoDto.class);
      return todoDto;
    }
    throw new TaskNotFoundException("Task with id " + id + " is not found");
  }

  @Override
  public List<TodoDto> getTodo() {
    List<Todo> todos = todoRepository.findAll();
    List<TodoDto> todoDtos = new ArrayList<TodoDto>();
    todos.forEach((i) -> todoDtos.add(modelMapper.map(i, TodoDto.class)));
    return todoDtos;
  }

  @Override
  public TodoDto updateTodo(TodoDto todoDto) {
    Todo todo = todoRepository.findById(todoDto.getId())
        .orElseThrow(() -> new TaskNotFoundException("Task with id " + todoDto.getId() + " is not found"));
    todo.setTitle(todoDto.getTitle());
    todo.setDescription(todoDto.getDescription());
    todo.setCompleted(todoDto.isCompleted());
    Todo savedTodo = todoRepository.save(todo);
    return modelMapper.map(savedTodo, TodoDto.class);
  }

  @Override
  public void deleteTodo(Long id) {
    Todo todo = todoRepository.findById(id)
        .orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " is not found"));
    todoRepository.delete(todo);
  }

  @Override
  public TodoDto completeTodo(Long id) {
    Todo todo = todoRepository.findById(id)
        .orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " is not found"));
    todo.setCompleted(true);
    Todo savedTodo = todoRepository.save(todo);
    return modelMapper.map(savedTodo, TodoDto.class);
  }

  @Override
  public TodoDto inCompleteTodo(Long id) {
    Todo todo = todoRepository.findById(id)
        .orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " is not found"));
    todo.setCompleted(false);
    Todo savedTodo = todoRepository.save(todo);
    return modelMapper.map(savedTodo, TodoDto.class);
  }

}
