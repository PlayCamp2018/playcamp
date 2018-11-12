package models.Database;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;


public class User {

    public User() { }

    @JsonCreator
    public User(@JsonProperty("id") ObjectId id,
                @JsonProperty("firstname") String firstname,
                @JsonProperty("lastname")String lastname,
                @JsonProperty("password")String password,
                @JsonProperty("email") String email,
                @JsonProperty("profilepicID") ObjectId profilePicID) {

        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.profilePicID = profilePicID;
    }

    @JsonProperty("id")
    public ObjectId id;
    @JsonProperty("firstname")
    public String firstname;
    @JsonProperty("lastname")
    public String lastname;
    @JsonProperty("password")
    public String password;
    @JsonProperty("email")
    public String email;
    @JsonProperty("profilePicID")
    public ObjectId profilePicID;



    public ObjectId getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }

    public ObjectId getProfilePicID() {
        return profilePicID;
    }

    public void setId(ObjectId _id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProfilePicID(ObjectId profilePicID) {
        this.profilePicID = profilePicID;
    }
}
