package act.bench.project.dashboardpersonal.service;


import act.bench.project.dashboardpersonal.model.Todo;
import act.bench.project.dashboardpersonal.repository.TodoRepository;
import act.bench.project.dashboardpersonal.repository.dao.TodoDAO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    private final TodoRepository repository;
    private final ModelMapper modelMapper = new ModelMapper();

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public List<Todo> getAllToDos() {
        List<TodoDAO> todos = new ArrayList<>(repository.findAll());
        List<Todo> todoList = new ArrayList<>();

        for (TodoDAO todoDAO : todos) {
            todoList.add(modelMapper.map(todoDAO, Todo.class));
        }
        return todoList;
    }

}
