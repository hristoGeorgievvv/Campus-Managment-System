package nl.tudelft.oopp.demo.models;

public class User {

    /**
     * Username of the user.
     */
    private String userName;

    /**
     * Password of the user.
     */
    private String password;

    /**
     * Email address of the user.
     */
    private String email;

    /**
     * Type of the user (admin/teacher/student?).
     */
    private String type;

    /**
     * If the user currently has a bike reserved.
     */
    private boolean bike;


    /**
     * No arg constructor.
     */
    public User() {

    }

    /**
     * Main Constructor.
     * @param userName user's username.
     * @param password user's pass.
     * @param email user's email.
     * @param type user's type.
     * @param bike if the user has a bike.
     */
    public User(String type, String email, String password, String userName, boolean bike) {
        this.password = password;
        this.email = email;
        this.type = type;
        this.userName = userName;
        this.bike = bike;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public boolean getBike() {
        return bike;
    }

    public String getType() {
        return type;
    }

    public String getUsername() {
        return userName;
    }

    /**.
     * Equals method for Users
     * @param other Object to be compared to
     * @return Whether or not they are equal
     */
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (other instanceof User) {
            User that = (User) other;
            return email.equals(that.email);
        }
        return false;
    }
}
