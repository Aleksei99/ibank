package by.ibank.dao;

import by.ibank.entity.History;

import java.sql.*;
import java.time.LocalDate;

public class HistoryDAOImpl implements HistoryDAO{
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost/my_bank2?serverTimezone=UTC";

    private static final String USER = "root";
    private static final String PASSWORD = "MySQLicui4cuL";
    @Override
    public void addToHistory(int card_number, LocalDate date, int amount) throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        try(Connection connection = DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement("insert into history (card_number," +
                    "date, amount) values (?,?,?)")) {
           preparedStatement.setInt(1,card_number);
           preparedStatement.setDate(2, Date.valueOf(date));
           preparedStatement.setInt(3,amount);
           preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
