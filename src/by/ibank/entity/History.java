package by.ibank.entity;

import java.time.LocalDate;

public class History extends AbstractEntity {
    private int spentMoney;
    private LocalDate datePurchase;
    private CreditCard creditCard;

    public History(int spentMoney, LocalDate datePurchase) {
        this.spentMoney = spentMoney;
        this.datePurchase = datePurchase;
    }

    public History(int id, int spentMoney, LocalDate datePurchase) {
        super(id);
        this.spentMoney = spentMoney;
        this.datePurchase = datePurchase;
    }

    public History() {
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public int getSpentMoney() {
        return spentMoney;
    }

    public void setSpentMoney(int spentMoney) {
        this.spentMoney = spentMoney;
    }

    public LocalDate getDatePurchase() {
        return datePurchase;
    }

    public void setDatePurchase(LocalDate datePurchase) {
        this.datePurchase = datePurchase;
    }

    @Override
    public String toString() {
        return "by.ibank.entity.History{" +
                "spent_money=" + spentMoney +
                ", date_purchase=" + datePurchase +
                '}';
    }
}
