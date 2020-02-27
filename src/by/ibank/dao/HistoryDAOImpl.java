package by.ibank.dao;

import java.sql.*;
import java.time.LocalDate;

public class HistoryDAOImpl implements HistoryDAO{
    @Override
    public void addToHistory(int card_number, LocalDate date, int amount){
        try(Connection connection = Connect.getConnection();
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
