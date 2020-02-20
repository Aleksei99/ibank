package by.ibank.entity;

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

    public static void main(String[] args) {
        checkFunctional("ADMIN");
        checkFunctional("MODERATOR");
        System.out.println(UserRole.ADMIN.getValue());
    }


}
