package by.ibank.dao;

import by.ibank.entity.User;
import by.ibank.entity.UserRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost/my_bank2?serverTimezone=UTC";

    private static final String USER = "root";
    private static final String PASSWORD = "MySQLicui4cuL";

    @Override
    public List<User> findAllUsers() throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        List<User> users = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            try(ResultSet resultSet = statement.executeQuery("select * from users")) {
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
    public User findUser(int id) throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        User user = new User();
        try(Connection connection = DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where id = ?")){
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            try(ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()){
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
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void save(User user) throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        try(Connection connection = DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT into users (name,second_name, surname, birthday, address," +
                    " phone_number, sex,  passport_number," +
                    " email, password, login,role) values (?,?,?,?,?,?,?,?,?,?,?,?)")){
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getSecondName());
            preparedStatement.setString(3,user.getSurname());
            preparedStatement.setDate(4,user.getBirthday());
            preparedStatement.setString(5,user.getAddress());
            preparedStatement.setString(6,user.getTelephone());
            preparedStatement.setString(7,user.getSex());
            preparedStatement.setString(8,user.getPassportNumber());
            preparedStatement.setString(9,user.getEmail());
            preparedStatement.setString(10,user.getPassword());
            preparedStatement.setString(11,user.getLogin());
            preparedStatement.setString(12,user.getUserRole().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        try(Connection connection = DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement("delete from users where id = ?")){
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) throws ClassNotFoundException {

    }
}
