package by.ibank.dao;

import by.ibank.entity.Account;
import by.ibank.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOImpl implements AccountDAO {
    private static final String NEW_ACCOUNT = "insert into accounts (account_number,amount,user_id) values (?,?,?)";
    private static final String DELETE_ACCOUNT = "DELETE FROM  accounts WHERE (account_number = ?)";
    private static final String ADD_MONEY = "UPDATE  accounts SET amount = ? WHERE account_number = ?";
    private static final String FIND_ALL_ACCOUNTS = "select * from accounts";
    private static final String FIND_ACCOUNT = "select * from accounts where account_number = ?";
    private static final String TRANSFER_MONEY = "update accounts set amount = ? where account_number = ?";

    @Override
    public void save(User user, Account account) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(NEW_ACCOUNT)) {
            preparedStatement.setString(1, account.getAccountNumber());
            preparedStatement.setInt(2, account.getAmount());
            preparedStatement.setInt(3, user.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String account_number) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ACCOUNT)) {
            preparedStatement.setString(1, account_number);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addMoney(Account account, int money) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_MONEY)) {
            preparedStatement.setInt(1, account.getAmount() + money);
            preparedStatement.setString(2, account.getAccountNumber());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Account> findAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(FIND_ALL_ACCOUNTS)) {
                while (resultSet.next()) {
                    Account account = new Account();
                    account.setAccountNumber(resultSet.getString("account_number"));
                    account.setAmount(resultSet.getInt("amount"));
                    accounts.add(account);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public Account findAccount(String account_number) {
        Account account = new Account();
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ACCOUNT)) {
            preparedStatement.setString(1, account_number);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    account.setAccountNumber(resultSet.getString("account_number"));
                    account.setAmount(resultSet.getInt("amount"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public void transferMoney(Account fromAccount, int money, Account toAccount) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(TRANSFER_MONEY);
             PreparedStatement preparedStatement1 = connection.prepareStatement(TRANSFER_MONEY)) {

            preparedStatement.setInt(1, fromAccount.getAmount() - money);
            preparedStatement.setString(2, fromAccount.getAccountNumber());

            preparedStatement1.setInt(1, toAccount.getAmount() + money);
            preparedStatement1.setString(2, toAccount.getAccountNumber());

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
