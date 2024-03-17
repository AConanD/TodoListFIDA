package demo.rest.TodoList.service;

import demo.rest.TodoList.Requests.CreateTodoEntryRequest;
import demo.rest.TodoList.persistence.TodoEntry;
import demo.rest.TodoList.persistence.dao.TodoEntryDao;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TodoEntryService {

    private final TodoEntryDao todoEntryDao;


    public TodoEntryService(TodoEntryDao todoEntryDao) {
        this.todoEntryDao = todoEntryDao;
    }

    public Page<TodoEntry> findAll(Pageable page) {
        return todoEntryDao.findAll(page);
    }

    public TodoEntry findById(Long id) {

        Optional<TodoEntry> todoEntry = todoEntryDao.findById(id);

        if (todoEntry.isPresent()) {
            return todoEntry.get();
        } else {
            throw new EntityNotFoundException();
        }
    }

    public void deleteById(Long id) {
        todoEntryDao.deleteById(id);
    }

    public TodoEntry create(CreateTodoEntryRequest request) {

        TodoEntry entry = new TodoEntry();
        entry.setDescription(request.getDescription());
        entry.setCreateDate(new Date());
        entry.setDone(false);

        return todoEntryDao.save(entry);
    }


}
