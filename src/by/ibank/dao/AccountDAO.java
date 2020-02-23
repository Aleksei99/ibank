package by.ibank.dao;

import by.ibank.entity.Account;
import by.ibank.entity.User;

import java.util.List;

public interface AccountDAO {
    void save(User user, Account account) throws ClassNotFoundException;
    void delete(String account_number) throws ClassNotFoundException;
    void addMoney(Account account,int money) throws ClassNotFoundException;
    List<Account> findAllAccounts() throws ClassNotFoundException;
    Account findAccount(String account_number) throws ClassNotFoundException;
    void transferMoney(Account fromAccount,int money, Account toAccount) throws  ClassNotFoundException;
}
