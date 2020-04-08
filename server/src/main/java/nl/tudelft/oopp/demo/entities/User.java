package nl.tudelft.oopp.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.hibernate.annotations.DynamicUpdate;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "users")
@DynamicUpdate
public class User extends AbstractModel {
    public User() {
    }


    /**
     * Username of the user.
     */
    @NotBlank
    @Size(min = 3, max = 100)
    private String userName;


    /**
     * Password of the user.
     */
    @NotBlank
    @Size(min = 3, max = 100)
    private String password;

    /**
     * Email address of the user.
     */
    @Size(min = 3, max = 100)
    private String email;

    /**
     * Type of the user.
     */
    @Size(min = 3, max = 100)
    private String type;

    /**
     * Boolean that keeps track if the user currently has a bike reserved.
     */
    @Column(columnDefinition = "tinyint(1) default 0")
    private boolean bike;

    /**
     * Set bike of the user.
     *
     * @param bike New bike status.
     */
    public void setBike(boolean bike) {
        this.bike = bike;
    }

    /**
     * Set new password of the user.
     *
     * @param password New password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Set new email address of the user.
     *
     * @param email New email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Set new type of the user.
     *
     * @param type New email address.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Set new username of the user.
     *
     * @param userName New email address.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Get password of the user.
     *
     * @return password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get email of the user.
     *
     * @return email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get type of the user.
     *
     * @return type of the user.
     */
    public String getType() {
        return type;
    }

    /**
     * See if the user has a bike.
     *
     * @return if the user has a bike.
     */
    public boolean getBike() {
        return bike;
    }


    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<RoomReservation> roomReservationList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
    private List<Order> ordersList = new ArrayList<>();

    /**
     * Get userName of the user.
     *
     * @return userName of the user.
     */
    public String getUserName() {
        return userName;
    }


    /*public List<RoomReservation> getRoomReservationList() {
        return RoomReservationList;
    }*/
    @JsonIgnore
    public List<RoomReservation> getRoomReservationList() {
        return roomReservationList;
    }


    /**
     * The constructor for the class User.
     *
     * @param userName username of the user
     * @param type     type of user
     * @param email    email address of the user
     * @param password the password of the user
     */
    public User(String userName, String type, String email, String password, boolean bike) {
        this.userName = userName;
        this.type = type;
        this.email = email;
        this.password = password;
        this.bike = bike;
    }

    /**
     * Equals method for User.
     *
     * @param other Other object to compare
     * @return whether or not its equal
     */

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (other == this) {
            return true;
        }

        if (other instanceof User) {
            User that = (User) other;

            //Checking every possibility for nulls would require way too many checks

            if (this.userName == null && that.userName == null) {
                return true;
            }

            return (userName.equals(that.userName) && password.equals(that.password)
                    && email.equals(that.email) && type.equals(that.type) && bike == that.bike);
        }
        return false;
    }

}
