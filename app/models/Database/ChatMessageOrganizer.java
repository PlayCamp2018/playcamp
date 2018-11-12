package models.Database;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;


public class ChatMessageOrganizer {

    public ChatMessageOrganizer() {
    }

    @JsonCreator
    public ChatMessageOrganizer(@JsonProperty("id") ObjectId id,
                                @JsonProperty("chatMessageID") ObjectId chatMessageID,
                                @JsonProperty("userID") ObjectId userID) {

        this.id = id;
        this.chatMessageID = chatMessageID;
        this.userID = userID;
    }

    @JsonProperty("id")
    public ObjectId id;
    @JsonProperty("chatMessageID")
    public ObjectId chatMessageID;
    @JsonProperty("userID")
    public ObjectId userID;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getChatMessageID() {
        return chatMessageID;
    }

    public void setChatMessageID(ObjectId chatMessageID) {
        this.chatMessageID = chatMessageID;
    }

    public ObjectId getUserID() {
        return userID;
    }

    public void setUserID(ObjectId userID) {
        this.userID = userID;
    }
}

