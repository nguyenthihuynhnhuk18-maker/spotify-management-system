package model;

public class User extends Person {

    private int userId;

    private String username;

    private String role;

    public User() {
    }

    public User(
            int userId,
            String username,
            String fullName,
            String role
    ) {

        this.userId = userId;

        this.username = username;

        this.fullName = fullName;

        this.role = role;
    }

    public int getUserId() {

        return userId;
    }

    public void setUserId(
            int userId
    ) {

        this.userId = userId;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(
            String username
    ) {

        this.username = username;
    }

    @Override
    public String getFullName() {

        return fullName;
    }

    @Override
    public void setFullName(
            String fullName
    ) {

        this.fullName = fullName;
    }

    public String getRole() {

        return role;
    }

    public void setRole(
            String role
    ) {

        this.role = role;
    }

    @Override
    public void displayInfo() {

        System.out.println(
                "User: "
                + fullName
                + " | Role: "
                + role
        );
    }
}