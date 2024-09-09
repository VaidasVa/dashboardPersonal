package act.bench.project.dashboardpersonal.web.controller;

import act.bench.project.dashboardpersonal.model.Todo;
import act.bench.project.dashboardpersonal.service.TodoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
