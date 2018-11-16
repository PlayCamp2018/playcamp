package repositories;

import com.mongodb.WriteResult;
import models.Database.Meeting;
import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import uk.co.panaxiom.playjongo.PlayJongo;
import utils.Utils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class MeetingRepository {
    @Inject
    public PlayJongo jongo;

    public MongoCollection meetings() {
        return jongo.getCollection("DB.meetings");
    }

    /**
     * Returns a List<Meeting> with all meetings
     * @return the meeting list, empty if none
     */
    public List<Meeting> getAllMeetings() {
        MongoCursor<Meeting> cursor = meetings().find().as(Meeting.class);
        List<Meeting> meetings = new ArrayList<>();

        while (cursor.hasNext()) { meetings.add(cursor.next()); }

        return meetings;
    }

    /**
     * Finds Meeting with given id
     * @param id given Meeting id
     * @return the found Meeting or null
     */
    public Meeting findById(ObjectId id) { return meetings().findOne(id).as(Meeting.class); }
    /**
     * Finds Meetings by given projectId
     * @param projectId given projectId
     * @return the found Meeting list or empty if none
     */
    public List<Meeting> findByProjectId(ObjectId projectId) {
        MongoCursor<Meeting> cursor = meetings().find("{projectId: #}", projectId).as(Meeting.class);
        List<Meeting> meetings = new ArrayList<>();

        while (cursor.hasNext()) { meetings.add(cursor.next()); }

        return meetings;
    }
    /**
     * Finds Meetings by given authorId
     * @param authorId given authorId
     * @return the found Meeting list or empty if none
     */
    public List<Meeting> findByAuthorId(ObjectId authorId) {
        MongoCursor<Meeting> cursor = meetings().find("{authorId: #}", authorId).as(Meeting.class);
        List<Meeting> meetings = new ArrayList<>();

        while (cursor.hasNext()) { meetings.add(cursor.next()); }

        return meetings;
    }


    /**
     * Adds a new Meeting
     * @param m the Meeting to add
     * @return true if success else false
     */
    public boolean insert(Meeting m) {
        WriteResult wRes = meetings().insert(m);
        return wRes.wasAcknowledged();
    }

    /**
     * Updates/saves the given Meeting
     * @param m the Meeting to update
     * @return true if success else false
     */
    public boolean save(Meeting m) {
        WriteResult wRes = meetings().save(m);
        return wRes.wasAcknowledged();
    }

    /**
     * Removes the Meeting with the given id
     * @param id the given id
     * @return true if success else false
     */
    public boolean removeById(ObjectId id) {
        WriteResult wRes = meetings().remove(id);
        return wRes.wasAcknowledged();
    }

    /**
     * Inserts a random Meeting
     * @return true if success else false
     */
    public boolean insertRandomMeeting(ObjectId id) {
        Meeting m = Utils.getRandomMeeting(id);
        return insert(m);
    }
}
