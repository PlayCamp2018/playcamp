package models.Database;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.Map;


public class Meeting {

    public Meeting() {
    }

    @JsonCreator
    public Meeting(@JsonProperty("id") ObjectId id,
                   @JsonProperty("projectId") ObjectId projectId,
                   @JsonProperty("name") String name,
                   @JsonProperty("timestamp") Date timestamp,
                   @JsonProperty("authorId") ObjectId authorId,
                   @JsonProperty("teilnahmeMeeting") Map.Entry <ObjectId,Enum> teilnahmeMeeting) {

        this.id = id;
        this.projectId = projectId;
        this.name = name;
        this.timestamp = timestamp;
        this.authorId = authorId;
        this.teilnahmeMeeting = teilnahmeMeeting;
    }

    @JsonProperty("id")
    public ObjectId id;
    @JsonProperty("projectId")
    public ObjectId projectId;
    @JsonProperty("name")
    public String name;
    @JsonProperty("timestamp")
    public Date timestamp;
    @JsonProperty("authorId")
    public ObjectId authorId;
    @JsonProperty("teilnahmeMeeting")
    public Map.Entry teilnahmeMeeting;


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getProjectId() {
        return projectId;
    }

    public void setProjectId(ObjectId projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public ObjectId getAuthorId() {
        return authorId;
    }

    public void setAuthorId(ObjectId authorId) {
        this.authorId = authorId;
    }

    public Map.Entry getTeilnahmeMeeting() {
        return teilnahmeMeeting;
    }

    public void setTeilnahmeMeeting(Map.Entry teilnahmeMeeting) {
        this.teilnahmeMeeting = teilnahmeMeeting;
    }
}