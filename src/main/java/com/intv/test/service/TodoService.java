package com.intv.test.service;

import com.intv.test.exception.ForbiddenException;
import com.intv.test.exception.NotFoundException;
import com.intv.test.exception.ValidationException;
import com.intv.test.model.Todo;
import com.intv.test.repository.TodoRepository;
import com.intv.test.security.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class TodoService {

    private final TodoRepository repo;

    public TodoService(TodoRepository repo) {
        this.repo = repo;
    }

    public Todo createTodo(Todo todo, UserAuth auth) {
        validateTodo(todo);

        if (auth.isUser() && !auth.getUserId().equals(todo.getUserId())) {
            throw new ForbiddenException("USER role cannot create todos for others");
        }
        Instant now = Instant.now();
        todo.setCreatedAt(now);
        todo.setUpdatedAt(now);
        return repo.save(todo);
    }

    public List<Todo> listMyTodos(UserAuth auth) {
        return repo.findByUserId(auth.getUserId());
    }

    public List<Todo> listTodosForUser(String userId, UserAuth auth) {
        if (!auth.isAdmin()) {
            throw new ForbiddenException("Only ADMIN can list todos for any user");
        }
        return repo.findByUserId(userId);
    }

    public Todo updateTodoStatus(Long id, String status, UserAuth auth) {
        if (!"PENDING".equals(status) && !"DONE".equals(status)) {
            throw new ValidationException("Invalid status value");
        }
        Todo todo = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Todo not found"));
        if (auth.isUser() && !todo.getUserId().equals(auth.getUserId())) {
            throw new ForbiddenException("USER cannot update todos of others");
        }
        todo.setStatus(status);
        todo.setUpdatedAt(Instant.now());
        return repo.save(todo);
    }

    private void validateTodo(Todo todo) {
        if (todo.getTitle() == null || todo.getTitle().trim().isEmpty()) {
            throw new ValidationException("title cannot be blank");
        }
        int len = todo.getTitle().length();
        if (len < 3 || len > 100) {
            throw new ValidationException("title must be 3-100 characters");
        }
        if (todo.getStatus() == null || (!todo.getStatus().equals("PENDING") && !todo.getStatus().equals("DONE"))) {
            throw new ValidationException("status must be PENDING or DONE");
        }
        if (todo.getUserId() == null || todo.getUserId().trim().isEmpty()) {
            throw new ValidationException("userId is required");
        }
    }

}
