import java.time.LocalDate;
import java.util.Objects;

public class CreditCard {
    private int card_number;
    private LocalDate dateExpire; //???????????????

    public CreditCard(int card_number, LocalDate dateExpire) {
        this.card_number = card_number;
        this.dateExpire = dateExpire;
    }

    public CreditCard() {
    }

    public int getCard_number() {
        return card_number;
    }

    public void setCard_number(int card_number) {
        this.card_number = card_number;
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
        return card_number == that.card_number &&
                Objects.equals(dateExpire, that.dateExpire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(card_number, dateExpire);
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "card_number=" + card_number +
                ", dateExpire=" + dateExpire +
                '}';
    }
}
