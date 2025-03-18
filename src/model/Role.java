package model;

public enum Role {
    USER,
    ADMIN,
    BLOCKED;

    Role() {
    }
    Role getRole(String role) {

        return Role.valueOf(role);
    }
}