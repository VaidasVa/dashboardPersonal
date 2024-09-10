package act.bench.project.dashboardpersonal.web.controller;

import act.bench.project.dashboardpersonal.model.Todo;
import act.bench.project.dashboardpersonal.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<Todo> getAll() {
        return service.getAllToDos();
    }

    @PostMapping("/")
    public void save(@RequestBody Todo todo)  {
        service.saveTodo(todo);
    }

    @GetMapping("/{content}")
    public Optional<List<Todo>> getByContent(@PathVariable String content) {
        return service.findByContent(content);
    }

    @PutMapping("/{id}")
    public Todo update(@PathVariable UUID id, @RequestBody Todo todo) {
        return service.amendTodo(id, todo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.deleteTodo(id);
    }
}
