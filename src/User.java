import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class User extends Man {

    private List<CreditCard> cards = new ArrayList<>();
    private Account account;
    private List<History> spending = new ArrayList<>();
    private UserRole userRole;

    private String password;
    private String login;

    public User(String name, String secondName, String surname,
                Date birthday, String address, int telephone, String sex,
                String nPassport, String password, String login) {
        super(name, secondName, surname, birthday, address, telephone, sex, nPassport);
        this.password = password;
        this.login = login;
    }

    public User(String name, String secondName, String surname,
                Date birthday, String address, int telephone, String sex,
                String nPassport) {
        super(name, secondName, surname, birthday, address, telephone, sex, nPassport);
    }

    public List<CreditCard> getCards() {
        return cards;
    }

    public void setCard(CreditCard card) {
        cards.add(card);
    }

    public void removeCard(CreditCard card){
        cards.remove(card);
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<History> getHistory() {
        return spending;
    }

    public void setSpending(History spending) {
        this.spending.add(spending);
    }

    public void clearHistory(){
        spending.clear();
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "User{" +
                "cards=" + cards +
                ", account=" + account +
                ", spending=" + spending +
                ", userRole=" + userRole +
                ", password='" + password + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
