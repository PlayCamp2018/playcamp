package models.Database;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import java.util.*;
import java.util.List;


public class MessagePost {

    public MessagePost() {
    }

    @JsonCreator
    public MessagePost(@JsonProperty("id") ObjectId id,
                       @JsonProperty("projectID") ObjectId projectID,
                       @JsonProperty("createDate") Date createDate,
                       @JsonProperty("authorID") ObjectId authorID,
                       @JsonProperty("attachments") List<ObjectId> attachmentList,
                       @JsonProperty("message") String message,
                       @JsonProperty("parentID") ObjectId parentID
    ) {
        this.id = id;
        this.projectID = projectID;
        this.createDate = createDate;
        this.authorID = authorID;
        this.attachments = attachments;
        this.message = message;
        this.parentID = parentID;
    }

    @JsonProperty("id")
    public ObjectId id;
    @JsonProperty("projectID")
    public ObjectId projectID;
    @JsonProperty("createDate")
    public Date createDate;
    @JsonProperty("authorID")
    public ObjectId authorID;
    @JsonProperty("userList")
    public List<ObjectId> attachments = new ArrayList<ObjectId>();
    @JsonProperty("message")
    public String message;
    @JsonProperty("parentID")
    public ObjectId parentID;


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
        return attachments;
    }

    public void setAttachments(List<ObjectId> attachments) {
        this.attachments = attachments;
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

