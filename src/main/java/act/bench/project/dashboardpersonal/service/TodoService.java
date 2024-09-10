package act.bench.project.dashboardpersonal.service;


import act.bench.project.dashboardpersonal.model.Todo;
import act.bench.project.dashboardpersonal.repository.TodoRepository;
import act.bench.project.dashboardpersonal.repository.dao.TodoDAO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TodoService {

    private final TodoRepository repository;
    private final ModelMapper mapper = new ModelMapper();

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public List<Todo> getAllToDos() {
        List<TodoDAO> todos = new ArrayList<>(repository.findAll());
        List<Todo> todoList = new ArrayList<>();

        for (TodoDAO todoDAO : todos) {
            todoList.add(mapper.map(todoDAO, Todo.class));
        }
        return todoList;
    }

    public void saveTodo(Todo todo) {
        repository.save(mapper.map(todo, TodoDAO.class));
    }

    public Optional<List<Todo>> findByContent(String title) {
        Optional<List<TodoDAO>> todoDAOs = repository.findAllByContent(title);
        List<Todo> todoList = new ArrayList<>();
        todoDAOs.ifPresent(items -> items.forEach(item -> {todoList.add(mapper.map(item, Todo.class));}));

        return Optional.of(todoList);
    }

    public Todo amendTodo(UUID id, Todo todo) {
        if (id.toString().equals(todo.getId().toString())) {
            TodoDAO todoDAO = repository.findById(id).get();

            if (!todo.getContent().equals(todoDAO.getContent())) {
                todoDAO.setContent(todo.getContent());
            } else if (!todo.isStatus() == todoDAO.isStatus()) {
                todoDAO.setStatus(todo.isStatus());
            } else if (!todo.isArchived() == todoDAO.isArchived()) {
                todoDAO.setArchived(todo.isArchived());
            }
            repository.save(todoDAO);
        }

        return mapper.map(repository.findById(id).get(), Todo.class);
    }

    public void deleteTodo(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }

}
