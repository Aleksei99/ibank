import java.time.LocalDate;
import java.util.Objects;

public class History {
    private int spent_money;
    private LocalDate date_purchase;

    public History(int spent_money, LocalDate date_purchase) {
        this.spent_money = spent_money;
        this.date_purchase = date_purchase;
    }

    public History() {
    }

    public int getSpent_money() {
        return spent_money;
    }

    public void setSpent_money(int spent_money) {
        this.spent_money = spent_money;
    }

    public LocalDate getDate_purchase() {
        return date_purchase;
    }

    public void setDate_purchase(LocalDate date_purchase) {
        this.date_purchase = date_purchase;
    }

    @Override
    public String toString() {
        return "History{" +
                "spent_money=" + spent_money +
                ", date_purchase=" + date_purchase +
                '}';
    }
}
