package models.DBTest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;


public class Users {

    public Users() {}

    @JsonCreator
    public Users(@JsonProperty("_id") ObjectId _id,
                 @JsonProperty("firstname") String firstname,
                 @JsonProperty("lastname")String lastname,
                 @JsonProperty("email") String email) {
        this._id = _id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    @JsonProperty("_id")
    public ObjectId _id;
    public String firstname;
    public String lastname;
    public String email;

    public ObjectId getId() {
        return _id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
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
}
