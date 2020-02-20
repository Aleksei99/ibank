public enum UserRole {
    USER(1),
    MODERATOR(2),
    ADMIN(3);

    private final int name;

    UserRole(int i) {
        name = i;
    }

    public int getValue() {
        return name;
    }
}
