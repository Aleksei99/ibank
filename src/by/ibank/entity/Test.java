package by.ibank.entity;

import by.ibank.dao.UserDAO;
import by.ibank.dao.UserDAOImpl;

public class Test {
    public static void checkFunctional(String userRole) {
        if (userRole.equals(UserRole.ADMIN.name())) {
            System.out.println("welcome administrator");
        }else if(userRole.equals(UserRole.USER.name())) {
            System.out.println("welcome user");
        }else if(userRole.equals(UserRole.MODERATOR.name())) {
            System.out.println("welcome user");
        }
        else {
            System.out.println("register please");
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        checkFunctional("ADMIN");
        checkFunctional("MODERATOR");
        System.out.println(UserRole.ADMIN.getValue());
        UserDAO userDAO = new UserDAOImpl();
        User user1 = new User("Alex","Alexandrovich","Smuraha",1999,12,2,"home","+375298592223","mail","mc2245492","sm@gamil.com","dsgff584","smura",UserRole.ADMIN);
        userDAO.save(user1);
    }


}
