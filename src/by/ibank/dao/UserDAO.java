package by.ibank.dao;

import by.ibank.entity.User;

import java.util.List;

public interface UserDAO {
    List<User> findAllUsers();

    User findUser(int id);

    void save(User user);

    void remove(int id);

    void update(User user);
}
