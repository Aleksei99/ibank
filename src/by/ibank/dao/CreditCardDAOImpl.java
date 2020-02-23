package by.ibank.dao;

import by.ibank.entity.Account;
import by.ibank.entity.CreditCard;
import by.ibank.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CreditCardDAOImpl implements CreditCardDAO {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost/my_bank2?serverTimezone=UTC";

    private static final String USER = "root";
    private static final String PASSWORD = "MySQLicui4cuL";

    @Override
    public List<CreditCard> findAllUserCards(User user) throws ClassNotFoundException {
        return null;
    }

    @Override
    public void addCard(Account account, CreditCard creditCard) throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("insert into credit_cards (card_number," +
                     "exp_month,exp_year,account_id) values (?,?,?,?)")) {
            preparedStatement.setInt(1,creditCard.getCardNumber());
            preparedStatement.setString(2,creditCard.getDateExpire().getMonth().toString());
            preparedStatement.setString(3,""+creditCard.getDateExpire().getYear());
            preparedStatement.setInt(4,account.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
