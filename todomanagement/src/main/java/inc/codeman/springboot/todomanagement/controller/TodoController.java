package inc.codeman.springboot.todomanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import inc.codeman.springboot.todomanagement.dto.TodoDto;
import inc.codeman.springboot.todomanagement.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Controller
@Getter
@Setter
@NoArgsConstructor
@Tag(name = "REST APIs for Todo Management")
@RequestMapping("/api/v1/todos")
public class TodoController {
  private TodoService todoService;

  @Autowired
  public TodoController(TodoService todoService) {
    this.todoService = todoService;
  }

  @PostMapping("/")
  @PreAuthorize("hasRole('ADMIN')")
  @Operation(summary = "POST Request to create new Todo Entry")
  public ResponseEntity<TodoDto> createTodo(@Valid @RequestBody TodoDto todoDto) {
    TodoDto savedTodoDto = todoService.createTodo(todoDto);
    return new ResponseEntity<TodoDto>(savedTodoDto, HttpStatus.CREATED);
  }

  @GetMapping("{id}")
  // @PreAuthorize("hasAnyRole('ADMIN','USER')")
  @Operation(summary = "GET Request to get Todo details by Id")
  public ResponseEntity<TodoDto> getTodoById(@PathVariable Long id) {
    TodoDto todoDto = todoService.getTodo(id);
    return new ResponseEntity<TodoDto>(todoDto, HttpStatus.OK);
  }

  @GetMapping("/")
  // @PreAuthorize("hasAnyRole('ADMIN','USER')")
  @Operation(summary = "GET Request to get all Todo Details")
  public ResponseEntity<List<TodoDto>> getTodos() {
    List<TodoDto> todoDtos = todoService.getTodo();
    return new ResponseEntity<List<TodoDto>>(todoDtos, HttpStatus.OK);
  }

  @PutMapping("{id}")
  @PreAuthorize("hasRole('ADMIN')")
  @Operation(summary = "PUT Request to modify exiting Todo Details by id")
  public ResponseEntity<TodoDto> modifyTodo(@PathVariable Long id, @Valid @RequestBody TodoDto todoDto) {
    todoDto.setId(id);
    TodoDto responseDto = todoService.updateTodo(todoDto);
    return new ResponseEntity<TodoDto>(responseDto, HttpStatus.OK);
  }

  @DeleteMapping("{id}")
  @PreAuthorize("hasRole('ADMIN')")
  @Operation(summary = "DELETE Request to remove exiting Todo Details by id")
  public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
    todoService.deleteTodo(id);
    return new ResponseEntity<String>("Todo Entry with id: " + id + " deleted sucessfully", HttpStatus.OK);
  }

  @PatchMapping("complete/{id}")
  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  @Operation(summary = "PATCH Request to make the todo item as completed")
  public ResponseEntity<TodoDto> completeTodo(@PathVariable Long id) {
    TodoDto todoDto = todoService.completeTodo(id);
    return new ResponseEntity<TodoDto>(todoDto, HttpStatus.OK);
  }

  @PatchMapping("incomplete/{id}")
  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  @Operation(summary = "PATCH Request to make the todo item as incomplete")
  public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable Long id) {
    TodoDto todoDto = todoService.inCompleteTodo(id);
    return new ResponseEntity<TodoDto>(todoDto, HttpStatus.OK);
  }
}