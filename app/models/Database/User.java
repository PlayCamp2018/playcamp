package models.Database;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;

/**
 * This Class describe the User and his attributes of playcamp.
 */
public class User {
    //FixMe: lehrer Construtor?
    // braucht das der DB-Treiber? wenn nicht würde ich das entfernen, denn einen User zieht man
    // aus der BD oder erstellt ihn mit der Mailadresse.
    public User() { }

    /**
     * The constructor as json-creator used by jongo.
     * @param id The id of the user as org.bson.types.ObjectId.
     * @param firstname The first name of the user as String.
     * @param lastname The last name of the user as String.
     * @param password The password hash of the user as String.
     * @param email The email address of the user as String.
     * @param profilePicID The id of the user profile pic as org.bson.types.ObjectId.
     */
    @JsonCreator
    public User(@JsonProperty("id") ObjectId id,
                @JsonProperty("firstname") String firstname,
                @JsonProperty("lastname")String lastname,
                @JsonProperty("password")String password,
                @JsonProperty("email") String email,
                @JsonProperty("profilepicID") ObjectId profilePicID) {

        //FixMe: wenn der Constructor auch im java-code genutzt wird dann würde ich diese mittels der setter setzten,
        // da diese dort auch gleich validiert werden. (ausser id, die kommt ja nur aus der DB)
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.profilePicID = profilePicID;
    }

    /** The id of the user as org.bson.types.ObjectId. */
    @JsonProperty("id")
    private ObjectId id;
    /** The first name of the user as String. */
    @JsonProperty("firstname")
    private String firstname;
    /** The last name of the user as String. */
    @JsonProperty("lastname")
    private String lastname;
    /** The password hash of the user as String. */
    @JsonProperty("password")
    private String password;
    /**  The email address of the user as String. */
    @JsonProperty("email")
    private String email;
    /**  The id of the user profile pic as org.bson.types.ObjectId. */
    @JsonProperty("profilePicID")
    private ObjectId profilePicID;

    /**
     * Get the id of the user.
     * @return Returns the id as org.bson.types.ObjectId.
     */
    public ObjectId getId() {
        return id;
    }
    /**
     * Get the first name of the user.
     * @return Returns the first name as String.
     */
    public String getFirstname() {
        return firstname;
    }
    /**
     * Get the last name of the user.
     * @return Returns the last name as String.
     */
    public String getLastname() {
        return lastname;
    }
    /**
     * Get the password hash of user.
     * @return Returns the password hash as String.
     */
    public String getPassword() {
        return password;
    }
    /**
     * Get the email address of the user.
     * @return Returns the email as String.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get the id of the user profile pic.
     * @return Returns the id od the profile pic as org.bson.types.ObjectId.
     */
    public ObjectId getProfilePicID() {
        return profilePicID;
    }

    /**
     * FixME: die Methode würde ich entfernen ... nur die DB setzt die ID's
     */
    public void setId(ObjectId _id) {
        this.id = _id;
    }

    /**
     * Set the first name of the user.
     * @param firstname The first name as String.
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Set the last name of the user.
     * @param lastname The last name as String.
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Set the email address of the user.
     * @param email The email as String.
     */
    public void setEmail(String email) {
        //ToDo: validierung der mail-addresse (musterprüfung)
        this.email = email;
    }

    /**
     * Set the password hash of the user.
     * @param password The password hash as String.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Set the id of the user profile pic.
     * @param profilePicID The id of the user profile pic as org.bson.types.ObjectId.
     */
    public void setProfilePicID(ObjectId profilePicID) {
        this.profilePicID = profilePicID;
    }
}
