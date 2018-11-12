package models.Database;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import java.util.*;
import java.util.List;


public class Project {

    public Project() { }

    @JsonCreator
    public Project(@JsonProperty("id") ObjectId id,
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

    @JsonProperty("id")
    public ObjectId id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("beschreibung")
    public String beschreibung;
    @JsonProperty("ownerid")
    public ObjectId ownerid;
    @JsonProperty("userList")
    public List<ObjectId> userList = new ArrayList<ObjectId>();
    @JsonProperty("messageBoardID")
    public ObjectId messageBoardID;


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
