package com.intv.test.repository;

import com.intv.test.model.Todo;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class TodoRepository {

    private final Map<Long, Todo> todos = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public Todo save(Todo todo) {
        if (todo.getId() == null) {
            todo.setId(idCounter.getAndIncrement());
        }
        todos.put(todo.getId(), todo);
        return todo;
    }

    public Optional<Todo> findById(Long id) {
        return Optional.ofNullable(todos.get(id));
    }

    public void delete(Long id) {
        todos.remove(id);
    }

    public List<Todo> findByUserId(String userId) {
        return todos.values().stream()
                .filter(t -> t.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    public List<Todo> findAll() {
        return new ArrayList<>(todos.values());
    }
}
