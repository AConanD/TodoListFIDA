package demo.rest.TodoList.controller;

import demo.rest.TodoList.Requests.CreateTodoEntryRequest;
import demo.rest.TodoList.persistence.TodoEntry;
import demo.rest.TodoList.service.TodoEntryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodoEntryController {

    private final TodoEntryService todoEntryService;

    public TodoEntryController(TodoEntryService todoEntryService) {
        this.todoEntryService = todoEntryService;
    }

    @GetMapping("")
    public ResponseEntity<Page<TodoEntry>> getTodos(Pageable page) {

        return ResponseEntity.ok(todoEntryService.findAll(page));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(Long id) {
        todoEntryService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping("")
    public ResponseEntity<TodoEntry> create(@RequestBody CreateTodoEntryRequest request) {
        return ResponseEntity.ok(todoEntryService.create(request));
    }
}
