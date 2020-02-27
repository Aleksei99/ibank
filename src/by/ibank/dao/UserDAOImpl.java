package by.ibank.dao;

import by.ibank.entity.User;
import by.ibank.entity.UserRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static final String FIND_ALL = "select * from users";
    private static final String FIND_BY_ID = "select * from users where id = ?";
    private static final String SAVE = "INSERT into users (name,second_name, surname, birthday, address," +
            " phone_number, sex,  passport_number," +
            " email, password, login,role) values (?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String DELETE_BY_ID = "delete from users where id = ?";

    @Override
    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setSurname(resultSet.getString("surname"));
                    user.setBirthday(resultSet.getDate("birthday"));
                    user.setAddress(resultSet.getString("address"));
                    user.setTelephone(resultSet.getString("phone_number"));
                    user.setSex(resultSet.getString("sex"));
                    user.setSecondName(resultSet.getString("second_name"));
                    user.setPassportNumber(resultSet.getString("passport_number"));
                    user.setUserRole(UserRole.valueOf(resultSet.getString("role")));
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));
                    user.setEmail(resultSet.getString("email"));
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findUser(int id) {
        User user = new User();
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setSurname(resultSet.getString("surname"));
                    user.setBirthday(resultSet.getDate("birthday"));
                    user.setAddress(resultSet.getString("address"));
                    user.setTelephone(resultSet.getString("phone_number"));
                    user.setSex(resultSet.getString("sex"));
                    user.setSecondName(resultSet.getString("second_name"));
                    user.setPassportNumber(resultSet.getString("passport_number"));
                    user.setUserRole(UserRole.valueOf(resultSet.getString("role")));
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));
                    user.setEmail(resultSet.getString("email"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void save(User user) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSecondName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setDate(4, user.getBirthday());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setString(6, user.getTelephone());
            preparedStatement.setString(7, user.getSex());
            preparedStatement.setString(8, user.getPassportNumber());
            preparedStatement.setString(9, user.getEmail());
            preparedStatement.setString(10, user.getPassword());
            preparedStatement.setString(11, user.getLogin());
            preparedStatement.setString(12, user.getUserRole().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {

    }
}
