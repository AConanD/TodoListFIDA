package demo.rest.TodoList.persistence.dao;

import demo.rest.TodoList.persistence.TodoEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoEntryDao extends JpaRepository<TodoEntry, Long> {
}
