package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class Project {
    @JsonCreator
    public Project(@JsonProperty("_id")            ObjectId _id,
                   @JsonProperty("name")           String name,
                   @JsonProperty("description")    String description,
                   @JsonProperty("ownerId")        ObjectId ownerId,
                   @JsonProperty("userIds")        List<ObjectId> userIds,
                   @JsonProperty("messageBoardId") ObjectId messageBoardId) {
        this._id            = _id;
        this.name           = name;
        this.description    = description;
        this.ownerId        = ownerId;
        this.userIds        = userIds;
        this.messageBoardId = messageBoardId;
    }

    private ObjectId _id;
    private String name;
    private String description;
    private ObjectId ownerId;
    private List<ObjectId> userIds;
    private ObjectId messageBoardId;

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ObjectId getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(ObjectId ownerId) {
        this.ownerId = ownerId;
    }

    public List<ObjectId> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<ObjectId> userIds) {
        this.userIds = userIds;
    }

    public ObjectId getMessageBoardId() {
        return messageBoardId;
    }

    public void setMessageBoardId(ObjectId messageBoardId) {
        this.messageBoardId = messageBoardId;
    }

    public static List<Project> _projects = List.of(
            new Project(new ObjectId(), "Project 1", "Description 1", User._users.get(0).get_id(), List.of(User._users.get(1).get_id(), User._users.get(2).get_id()), new ObjectId())
    );

    public static Project _findById(ObjectId _id) {
        Project result = null;
        for(Project pro : Project._projects) {
            if(pro._id.equals(_id)) {
                result = pro;
                break;
            }
        }
        return result;
    }
}
