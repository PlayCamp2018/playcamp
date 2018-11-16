package models.Database;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import java.util.*;
import java.util.List;

/**
 * This class describes the messagepost and its attributes of playcamp.
 */

public class MessagePost {

    //Leerer Konstruktor für Tests
    public MessagePost() {
    }

    /**
     *
     * @param id The id of the messagepost as org.bson.types.ObjectId.
     * @param projectID The id of the project which the messagepost belongs to as org.bson.types.ObjectId.
     * @param createDate The date when the messagepost was created as java.util.Date.
     * @param authorID The id of the user which created the messagepost as org.bson.types.ObjectId.
     * @param attachmentList The list of files which are attached to the messagepost as java.util.List.
     * @param message The content of the messagepost as String.
     * @param parentID The id of the parent messagepost (if existing) as org.bson.types.ObjectId.
     */

    @JsonCreator
    public MessagePost(@JsonProperty("_id") ObjectId id,
                       @JsonProperty("projectID") ObjectId projectID,
                       @JsonProperty("createDate") Date createDate,
                       @JsonProperty("authorID") ObjectId authorID,
                       @JsonProperty("attachmentList") List<ObjectId> attachmentList,
                       @JsonProperty("message") String message,
                       @JsonProperty("parentID") ObjectId parentID
    ) {
        this.id = id;
        this.projectID = projectID;
        this.createDate = createDate;
        this.authorID = authorID;
        this.attachmentList = attachmentList;
        this.message = message;
        this.parentID = parentID;
    }

    /** The id of the messagepost as org.bson.types.ObjectId.*/
    @JsonProperty("_id")
    private ObjectId id;
    /** The id of the project which the messagepost belongs to as org.bson.types.ObjectId. */
    @JsonProperty("projectID")
    private ObjectId projectID;
    /** The date when the messagepost was created as java.util.Date. */
    @JsonProperty("createDate")
    private Date createDate;
    /** The id of the user which created the messagepost as org.bson.types.ObjectId. */
    @JsonProperty("authorID")
    private ObjectId authorID;
    /** The id of the user which created the messagepost as org.bson.types.ObjectId. */
    @JsonProperty("attachmentList")
    private List<ObjectId> attachmentList;
    /** The content of the messagepost as String. */
    @JsonProperty("message")
    private String message;
    /** The id of the parent messagepost (if existing) as org.bson.types.ObjectId. */
    @JsonProperty("parentID")
    private ObjectId parentID;


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getProjectID() {
        return projectID;
    }

    public void setProjectID(ObjectId projectID) {
        this.projectID = projectID;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public ObjectId getAuthorID() {
        return authorID;
    }

    public void setAuthorID(ObjectId authorID) {
        this.authorID = authorID;
    }

    public List<ObjectId> getAttachments() {
        return attachmentList;
    }

    public void setAttachments(List<ObjectId> attachments) {
        this.attachmentList = attachments;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ObjectId getParentID() {
        return parentID;
    }

    public void setParentID(ObjectId parentID) {
        this.parentID = parentID;
    }
}

