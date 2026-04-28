package com.intv.test.service;

import com.intv.test.model.User;
import com.intv.test.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    private final TestRepository repository;

    @Autowired
    public TestService(TestRepository repository) {
        this.repository = repository;
    }

    public User createUser(User user) {
        return repository.save(user);
    }

    public User getUserById(int id) {
        return repository.getById(id);
    }

    public List<User> getAllUsers() {
        return repository.getAll();
    }

}
