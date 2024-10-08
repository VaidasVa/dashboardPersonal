package act.bench.project.dashboardpersonal.repository;

import act.bench.project.dashboardpersonal.repository.dao.TodoDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TodoRepository extends JpaRepository<TodoDAO, UUID> {

    Optional<List<TodoDAO>> findAllByContent(String content);
}
