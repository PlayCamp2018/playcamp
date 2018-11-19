package models.Database;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;

/**
 * This class describes the chat and its attributes of playcamp.
 */

public class Chat {

    //Leerer Konstruktor für Tests
    public Chat() { }

    /**
     * @param id The id of the chat as org.bson.types.ObjectId.
     * @param name The name of the chat as String
     * @param projectID The id of the project the chat belongs to
     */

    @JsonCreator
    public Chat(@JsonProperty("_id") ObjectId id,
                @JsonProperty("name") String name,
                @JsonProperty("projectID") ObjectId projectID) {

        this.id = id;
        this.name = name;
        this.projectID = projectID;
    }

    /** The id of the chat as org.bson.types.ObjectId. */
    @JsonProperty("_id")
    private ObjectId id;
    /** The name of the chat String */
    @JsonProperty("name")
    private String name;
    /** The id of the project the chat belongs to */
    @JsonProperty("projectID")
    private ObjectId projectID;


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