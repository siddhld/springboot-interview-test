package com.intv.test.controller;

import com.intv.test.model.Todo;
import com.intv.test.security.CurrentUser;
import com.intv.test.security.UserAuth;
import com.intv.test.service.TodoService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/todos")
@Validated
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    private UserAuth getAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserAuth) authentication.getPrincipal();
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        UserAuth auth = getAuth();
        Todo created = service.createTodo(todo, auth);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/me")
    public List<Todo> listMyTodos() {
        UserAuth auth = getAuth();
        return service.listMyTodos(auth);
    }

    @GetMapping("/user/{userId}")
    public List<Todo> listTodosForUser(@PathVariable String userId) {
        UserAuth auth = getAuth();
        return service.listTodosForUser(userId, auth);
    }

    @PatchMapping("/{id}/status")
    public Todo updateStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        UserAuth auth = getAuth();
        String status = body.get("status");
        if (status == null) {
            throw new ValidationException("status is required");
        }
        return service.updateTodoStatus(id, status, auth);
    }

}
