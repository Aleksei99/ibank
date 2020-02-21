package by.ibank.entity;

import java.time.LocalDate;
import java.util.Objects;

public class CreditCard extends AbstractEntity{
    private int cardNumber;
    private LocalDate dateExpire; //???????????????

    public CreditCard(int cardNumber, LocalDate dateExpire) {
        this.cardNumber = cardNumber;
        this.dateExpire = dateExpire;
    }

    public CreditCard(int id, int cardNumber, LocalDate dateExpire) {
        super(id);
        this.cardNumber = cardNumber;
        this.dateExpire = dateExpire;
    }

    public CreditCard() {
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getDateExpire() {
        return dateExpire;
    }

    public void setDateExpire(LocalDate dateExpire) {
        this.dateExpire = dateExpire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return cardNumber == that.cardNumber &&
                Objects.equals(dateExpire, that.dateExpire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, dateExpire);
    }

    @Override
    public String toString() {
        return "by.ibank.entity.CreditCard{" +
                "card_number=" + cardNumber +
                ", dateExpire=" + dateExpire +
                '}';
    }
}
