package by.ibank.dao;

import by.ibank.entity.Account;
import by.ibank.entity.User;

public interface AccountDAO {
    void save(User user, Account account) throws ClassNotFoundException;
    void delete(Account account) throws ClassNotFoundException;
    void addMoney(Account account,int money) throws ClassNotFoundException;
    Account findAccount(String account_number) throws ClassNotFoundException;
    void transferMoney(Account fromAccount,int money, Account toAccount) throws  ClassNotFoundException;
}
