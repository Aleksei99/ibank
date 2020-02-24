package by.ibank.dao;

import by.ibank.entity.Account;
import by.ibank.entity.CreditCard;
import by.ibank.entity.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreditCardDAOImpl implements CreditCardDAO {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost/my_bank2?serverTimezone=UTC";

    private static final String USER = "root";
    private static final String PASSWORD = "MySQLicui4cuL";

    @Override
    public List<CreditCard> findAllUserCards(String user) throws ClassNotFoundException {
        List<CreditCard> cards = new ArrayList<>();
        Class.forName(JDBC_DRIVER);
        try(Connection connection = DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement("with su_table as( select card_number,exp_month,exp_year,account_id,user_id from credit_cards c join accounts a on c.account_id=a.id)\n" +
                "select  card_number,exp_month,exp_year,account_id,user_id,name from su_table  s join users u on s.user_id=u.id where name=?")) {
            preparedStatement.setString(1,user);
            preparedStatement.execute();
            try(ResultSet resultSet = preparedStatement.getResultSet()){
                while (resultSet.next()){
                    CreditCard creditCard = new CreditCard();
                    creditCard.setCardNumber(resultSet.getInt("card_number"));

                    String yearStr = resultSet.getString("exp_year");
                    String monthStr = resultSet.getString("exp_month");
                    int year = Integer.parseInt(yearStr);
                    int month = Integer.parseInt(monthStr);

                    LocalDate  date = LocalDate.of(year,month,1);
                    creditCard.setDateExpire(date);
                    cards.add(creditCard);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cards;
    }

    @Override
    public void transferMoney(int fromCreditCard, int money, int toCreditCard) throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("select amount from accounts a join credit_cards c on c.account_id=a.id where card_number = ?");
             PreparedStatement preparedStatement1 = connection.prepareStatement("select amount from accounts a join credit_cards c on c.account_id=a.id where card_number = ?");
             PreparedStatement preparedStatement2 = connection.prepareStatement("update accounts a join credit_cards c on c.account_id=a.id set amount = ? where card_number = ?");
             PreparedStatement preparedStatement3 = connection.prepareStatement("update accounts a join credit_cards c on c.account_id=a.id set amount = ? where card_number = ?");
             ) {

            preparedStatement.setInt(1,fromCreditCard);
            preparedStatement.execute();
            int moneyFrom = 0;
            try(ResultSet resultSet = preparedStatement.getResultSet()){
                while (resultSet.next()){
                    moneyFrom=resultSet.getInt("amount");
                }
            }

            preparedStatement1.setInt(1,toCreditCard);
            preparedStatement1.execute();
            int moneyTo = 0;
            try(ResultSet resultSet = preparedStatement1.getResultSet()){
                while (resultSet.next()){
                    moneyTo=resultSet.getInt("amount");
                }
            }

            preparedStatement2.setInt(1,moneyFrom-money);
            preparedStatement2.setInt(2,fromCreditCard);

            preparedStatement3.setInt(1,moneyTo+money);
            preparedStatement3.setInt(2,toCreditCard);

            connection.setAutoCommit(false);
            preparedStatement2.executeUpdate();
            preparedStatement3.executeUpdate();
            HistoryDAO historyDAO = new HistoryDAOImpl();
            LocalDate date = LocalDate.now();
            historyDAO.addToHistory(fromCreditCard,date,money);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    @Override
    public void deleteCard(String cardNumber) throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        try(Connection connection = DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM  credit_cards WHERE (card_number = ?)")) {
            preparedStatement.setString(1,cardNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
