package com.intv.test.repository;

import com.intv.test.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TestRepository {

    private Map<Integer, User> map = new HashMap<>();

    public User save(User user) {
        user.setId(user.getId());
        map.put(user.getId(), user);
        return user;
    }

    public List<User> getAll() {
        return new ArrayList<>(map.values());
    }

    public User getById(int id){
        return map.get(id);
    }



}
