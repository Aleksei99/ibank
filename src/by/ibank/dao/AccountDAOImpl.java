package by.ibank.dao;

import by.ibank.entity.Account;
import by.ibank.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOImpl implements AccountDAO {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost/my_bank2?serverTimezone=UTC";

    private static final String USER = "root";
    private static final String PASSWORD = "MySQLicui4cuL";
    @Override
    public void save(User user, Account account) throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("insert into accounts (account_number," +
                     "amount,user_id) values (?,?,?)")) {
            preparedStatement.setString(1,account.getAccountNumber());
            preparedStatement.setInt(2,account.getAmount());
            preparedStatement.setInt(3,user.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String account_number) throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        try(Connection connection = DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM  accounts WHERE (account_number = ?)")) {
            preparedStatement.setString(1,account_number);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addMoney(Account account, int money) throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        try(Connection connection = DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE  accounts SET amount = ? WHERE account_number = ?")){
            preparedStatement.setInt(1,account.getAmount()+money);
            preparedStatement.setString(2,account.getAccountNumber());
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Account> findAllAccounts() throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        List<Account> accounts = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
            Statement statement = connection.createStatement()){
            try(ResultSet resultSet = statement.executeQuery("select * from accounts")) {
                while (resultSet.next()){
                    Account account = new Account();
                    account.setAccountNumber(resultSet.getString("account_number"));
                    account.setAmount(resultSet.getInt("amount"));
                    accounts.add(account);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public Account findAccount(String account_number) throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        Account account = new Account();
        try(Connection connection = DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement("select * from accounts where account_number = ?")){
            preparedStatement.setString(1,account_number);
            preparedStatement.execute();
            try(ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()){
                    account.setAccountNumber(resultSet.getString("account_number"));
                    account.setAmount(resultSet.getInt("amount"));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public void transferMoney(Account fromAccount, int money, Account toAccount) throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("update accounts set amount = ?" +
                     " where account_number = ?");
             PreparedStatement preparedStatement1 = connection.prepareStatement("update accounts set amount = ?" +
                     " where account_number = ?")) {

            preparedStatement.setInt(1,fromAccount.getAmount()-money);
            preparedStatement.setString(2,fromAccount.getAccountNumber());

            preparedStatement1.setInt(1,toAccount.getAmount()+money);
            preparedStatement1.setString(2,toAccount.getAccountNumber());

            connection.setAutoCommit(false);
            preparedStatement.executeUpdate();
            preparedStatement1.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
