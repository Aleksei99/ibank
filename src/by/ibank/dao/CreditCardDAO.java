package by.ibank.dao;

import by.ibank.entity.Account;
import by.ibank.entity.CreditCard;
import by.ibank.entity.User;

import java.util.List;

public interface CreditCardDAO {
    List<CreditCard> findAllUserCards(String user) throws ClassNotFoundException;
    void transferMoney(int fromCreditCard,int money, int toCreditCard) throws  ClassNotFoundException;
    void addCard(Account account,CreditCard creditCard) throws ClassNotFoundException;
    void deleteCard(String cardNumber) throws ClassNotFoundException;
}
