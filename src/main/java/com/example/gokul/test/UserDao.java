package com.example.gokul.test;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    public void addUser(User user);
    public List<User> getUser();
    public User findById(int id);
    public User update(User user, int id);
    public User updateCountry(User user, int id);
    public void delete(int id);
}
