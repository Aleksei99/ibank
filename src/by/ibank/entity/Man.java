package by.ibank.entity;

import java.sql.Date;

abstract class  Man {
    private String name;
    private String secondName;
    private String surname;
    private Date birthday; // ?????????????
    private String address;
    private String telephone;
    private String sex;
    private String passportNumber;
    private String email;

    public Man(String name, String secondName, String surname, int year, int month, int day,
               String address, String telephone, String sex, String passportNumber, String email) {
        this.name = name;
        this.secondName = secondName;
        this.surname = surname;
        this.birthday =new Date(year, month, day);
        this.address = address;
        this.telephone = telephone;
        this.sex = sex;
        this.passportNumber = passportNumber;
        this.email=email;

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

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getTelephone() {
        return telephone;
    }

    public String getSex() {
        return sex;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    @Override
    public String toString() {
        return "by.ibank.entity.Man{" +
                "name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                ", telephone=" + telephone +
                ", sex='" + sex + '\'' +
                ", nPassport='" + passportNumber + '\'' +
                '}';
    }
}
