package models.Database;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import java.util.*;
import java.util.List;

/**
 * This class describes the project and its attributes of playcamp.
 */

public class Project {

    //Leerer Konstruktor für Tests
    public Project (){}

    /**
     * The constructor as json-creator used by jongo.
     * @param id The id of the project as org.bson.types.ObjectId.
     * @param name The name of the project as String.
     * @param beschreibung The description of the project as String.
     * @param ownerid The ownerid of the project as org.bson.types.ObjectId.
     * @param userList The list of users in the project with their ids as org.bson.types.ObjectId.
     * @param messageBoardID The id of the messageBoard belonging to the project as org.bson.types.ObjectId.
     */

    @JsonCreator
    public Project(@JsonProperty("_id") ObjectId id,
                   @JsonProperty("name") String name,
                   @JsonProperty("beschreibung") String beschreibung,
                   @JsonProperty("ownerid") ObjectId ownerid,
                   @JsonProperty("userList") List<ObjectId> userList,
                   @JsonProperty("messageBoardID") ObjectId messageBoardID
                   ) {
        this.id = id;
        this.name = name;
        this.beschreibung = beschreibung;
        this.ownerid = ownerid;
        this.userList = userList;
        this.messageBoardID = messageBoardID;
    }

    /** The id of the project as org.bson.types.ObjectId. */
    @JsonProperty("_id")
    private ObjectId id;
    /** The name of the project as String. */
    @JsonProperty("name")
    private String name;
    /** The description of the project as String. */
    @JsonProperty("beschreibung")
    private String beschreibung;
    /** The ownerid of the project as org.bson.types.ObjectId. */
    @JsonProperty("ownerid")
    private ObjectId ownerid;
    /** The list of users in the project with their ids as org.bson.types.ObjectId.*/
    @JsonProperty("userList")
    private List<ObjectId> userList;
    /** The id of the messageBoard belonging to the project as org.bson.types.ObjectId.*/
    @JsonProperty("messageBoardID")
    private ObjectId messageBoardID;



    public ObjectId getId() {
        return id;
    }

    public String getName() {
        return  name;
    }
    public String getBeschreibung() {
        return beschreibung;
    }
    public ObjectId getOwnerid() { return ownerid; }
    public List<ObjectId> getUserList() {
        return userList;
    }
    public ObjectId getMessageBoardID() {
        return messageBoardID;
    }


    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public void setOwnerid(ObjectId ownerid) {
        this.ownerid = ownerid;
    }

    public void setUserList(List<ObjectId> userList) {
        this.userList = userList;
    }

    public void setMessageBoardID(ObjectId messageBoardID) {
        this.messageBoardID = messageBoardID;
    }
}
