package by.ibank.dao;

import java.sql.*;
import java.time.LocalDate;

public class HistoryDAOImpl implements HistoryDAO {
    private static final String ADD_TO_HISTORY = "insert into history (card_number,date, amount) values (?,?,?)";

    @Override
    public void addToHistory(int card_number, LocalDate date, int amount) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_TO_HISTORY)) {
            preparedStatement.setInt(1, card_number);
            preparedStatement.setDate(2, Date.valueOf(date));
            preparedStatement.setInt(3, amount);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
