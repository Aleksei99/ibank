import java.sql.Date;

abstract class  Man {
    private String name;
    private String secondName;
    private String surname;
    private Date birthday; // ?????????????
    private String address;
    private int telephone;
    private String sex;
    private String nPassport;


    public Man(String name, String secondName, String surname, Date birthday,
               String address, int telephone, String sex, String nPassport) {
        this.name = name;
        this.secondName = secondName;
        this.surname = surname;
        this.birthday = birthday;
        this.address = address;
        this.telephone = telephone;
        this.sex = sex;
        this.nPassport = nPassport;

    }

    public Man() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setnPassport(String nPassport) {
        this.nPassport = nPassport;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getSurname() {
        return surname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getAddress() {
        return address;
    }

    public int getTelephone() {
        return telephone;
    }

    public String getSex() {
        return sex;
    }

    public String getnPassport() {
        return nPassport;
    }

    @Override
    public String toString() {
        return "Man{" +
                "name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                ", telephone=" + telephone +
                ", sex='" + sex + '\'' +
                ", nPassport='" + nPassport + '\'' +
                '}';
    }
}
