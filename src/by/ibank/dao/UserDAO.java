package by.ibank.dao;

import by.ibank.entity.User;

import java.util.List;

public interface UserDAO {
    List<User> findAllUsers() throws ClassNotFoundException;
    User findUser(int id) throws ClassNotFoundException;
    void save(User user) throws ClassNotFoundException;
    void remove(int id) throws ClassNotFoundException;
    void update(User user) throws ClassNotFoundException;
}
