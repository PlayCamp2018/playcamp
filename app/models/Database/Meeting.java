package models.Database;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.Map;

/**
 * This class describes the meeting and its attributes of playcamp.
 */

public class Meeting {

    // Member eilnahme der einzelnen user als enum
    public enum Member {NO, YES, UNKNOWN};

    //Leerer Konstruktor für Tests
    public Meeting() {
    }

    /**
     *
     * @param id The id of the meeting as org.bson.types.ObjectId.
     * @param projectId The id of the project to which the meeting belongs as org.bson.types.ObjectId.
     * @param name The name of the meeting as String.
     * @param timestamp The date when the meeting will take place as java.util.Date.
     * @param authorId The id of the user who created the meeting as org.bson.types.ObjectId.
     * @param meetingMember The list of users and their individual answer to the invitation as java.util.Map;
     */

    @JsonCreator
    public Meeting(
                   @JsonProperty("id") ObjectId id,
                   @JsonProperty("projectId") ObjectId projectId,
                   @JsonProperty("name") String name,
                   @JsonProperty("timestamp") Date timestamp,
                   @JsonProperty("authorId") ObjectId authorId,
                   @JsonProperty("meetingMember") Map.Entry<ObjectId, Meeting.Member> meetingMember) {

        this.id = id;
        this.projectId = projectId;
        this.name = name;
        this.timestamp = timestamp;
        this.authorId = authorId;
        this.meetingMember = meetingMember;
    }
    /**The id of the meeting as org.bson.types.ObjectId. */
    @JsonProperty("id")
    private ObjectId id;
    /**The id of the project to which the meeting belongs as org.bson.types.ObjectId. */
    @JsonProperty("projectId")
    private ObjectId projectId;
    /** The name of the meeting as String. */
    @JsonProperty("name")
    private String name;
    /** The date when the meeting will take place as java.util.Date. */
    @JsonProperty("timestamp")
    private Date timestamp;
    /** The id of the user who created the meeting as org.bson.types.ObjectId. */
    @JsonProperty("authorId")
    private ObjectId authorId;
    /** The list of users and their individual answer to the invitation as java.util.Map; */
    @JsonProperty("meetingMember")
    private Map.Entry meetingMember;


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

    public Map.Entry getGetMeetingMember() {
        return meetingMember;
    }

    public void setMeetingMember(Map.Entry teilnahmeMeeting) {
        this.meetingMember = meetingMember;
    }
}