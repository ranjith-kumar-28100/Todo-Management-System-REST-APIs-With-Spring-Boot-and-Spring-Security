package inc.codeman.springboot.todomanagement.dto;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TodoDto {
  private Long id;
  @NotEmpty(message = "Title cannot be empty")
  private String title;
  @NotEmpty(message = "Description cannot be empty")
  private String description;
  private boolean completed;
}
