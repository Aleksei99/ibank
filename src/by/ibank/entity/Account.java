package by.ibank.entity;

import java.util.Objects;

public class Account extends AbstractEntity{
    private String accountNumber;
    private int amount;

    public Account(String accountNumber, int amount) {
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public Account(int id, String accountNumber, int amount) {
        super(id);
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public Account() {
    }


    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Account account = (Account) o;
        return amount == account.amount &&
                Objects.equals(accountNumber, account.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), accountNumber, amount);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", amount=" + amount +
                '}';
    }
}
