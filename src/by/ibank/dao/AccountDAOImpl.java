package by.ibank.dao;

import by.ibank.entity.Account;
import by.ibank.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOImpl implements AccountDAO {
    @Override
    public void save(User user, Account account) {
        try (Connection connection = Connect.getConnection();
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
    public void delete(String account_number) {
        try(Connection connection = Connect.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM  accounts WHERE (account_number = ?)")) {
            preparedStatement.setString(1,account_number);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addMoney(Account account, int money) {
        try(Connection connection = Connect.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE  accounts SET amount = ? WHERE account_number = ?")){
            preparedStatement.setInt(1,account.getAmount()+money);
            preparedStatement.setString(2,account.getAccountNumber());
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Account> findAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        try(Connection connection = Connect.getConnection();
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
    public Account findAccount(String account_number){
        Account account = new Account();
        try(Connection connection = Connect.getConnection();
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
    public void transferMoney(Account fromAccount, int money, Account toAccount) {
        try (Connection connection = Connect.getConnection();
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
