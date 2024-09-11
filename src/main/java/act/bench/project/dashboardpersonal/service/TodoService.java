package act.bench.project.dashboardpersonal.service;


import act.bench.project.dashboardpersonal.model.Todo;
import act.bench.project.dashboardpersonal.repository.TodoRepository;
import act.bench.project.dashboardpersonal.repository.dao.TodoDAO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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
        List<Todo> sortedList = todoList.stream()
                .sorted(Comparator.comparing(Todo::getUpdated))
                .sorted(Comparator.comparing(Todo::getCreated))
                .collect(Collectors.toList());

        return sortedList;
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

    public Todo amendTodo(String id, Todo todo) {
        if (id.equals(todo.getId().toString())) {
            TodoDAO todoDAO = repository.findById(UUID.fromString(id)).get();

            if (todo.getContent()!=null && !todo.getContent().equals(todoDAO.getContent())) {
                todoDAO.setContent(todo.getContent());
            } else if (!todo.isStatus() == todoDAO.isStatus()) {
                todoDAO.setStatus(todo.isStatus());
            } else if (!todo.isArchived() == todoDAO.isArchived()) {
                todoDAO.setArchived(todo.isArchived());
            }
            repository.save(todoDAO);
        }

        return mapper.map(repository.findById(UUID.fromString(id)).get(), Todo.class);
    }

    public void deleteTodo(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }

}
