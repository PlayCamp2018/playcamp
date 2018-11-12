package models.DBTest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;


public class Chat {

    public Chat() { }

    @JsonCreator
    public Chat(@JsonProperty("id") ObjectId id,
                @JsonProperty("name") String name,
                @JsonProperty("projectID") ObjectId projectID) {

        this.id = id;
        this.name = name;
        this.projectID = projectID;
    }

    @JsonProperty("id")
    public ObjectId id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("projectID")
    public ObjectId projectID;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ObjectId getProjectID() {
        return projectID;
    }

    public void setProjectID(ObjectId projectID) {
        this.projectID = projectID;
    }
}