import java.time.LocalDate;

public class History {
    private int spentMoney;
    private LocalDate datePurchase;

    public History(int spentMoney, LocalDate datePurchase) {
        this.spentMoney = spentMoney;
        this.datePurchase = datePurchase;
    }

    public History() {
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
        return "History{" +
                "spent_money=" + spentMoney +
                ", date_purchase=" + datePurchase +
                '}';
    }
}
