import java.util.Objects;

public class Account {
    private String account_number;
    private int amount;

    public Account(String account_number, int amount) {
        this.account_number = account_number;
        this.amount = amount;
    }

    public Account() {
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
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
        Account account = (Account) o;
        return amount == account.amount &&
                Objects.equals(account_number, account.account_number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account_number, amount);
    }

    @Override
    public String toString() {
        return "Account{" +
                "account_number='" + account_number + '\'' +
                ", amount=" + amount +
                '}';
    }
}
