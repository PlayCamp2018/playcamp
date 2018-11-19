package repositories;

import com.mongodb.WriteResult;
import models.Database.Files;
import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import uk.co.panaxiom.playjongo.PlayJongo;
import utils.Utils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class FilesRepository {
    @Inject
    public PlayJongo jongo;

    public MongoCollection files() {
        return jongo.getCollection("DB.files");
    }


    /**
     * Returns a List<Files> with all Files
     * @return the Files list, empty if none
     */
    public List<Files> getAllFiles() {
        MongoCursor<Files> cursor = files().find().as(Files.class);
        List<Files> files = new ArrayList<>();

        while(cursor.hasNext()) { files.add(cursor.next()); }

        return files;
    }

    /**
     * Finds Files with given id
     * @param id given Files id
     * @return the found Files or null
     */
    public Files findById(ObjectId id) { return files().findOne(id).as(Files.class); }
    /**
     * Returns a List<Files> with all Files for given authorId
     * @param authorId given authorId
     * @return the Files list, empty if none
     */
    public List<Files> findByAuthorId(ObjectId authorId) {
        MongoCursor<Files> cursor = files().find("{authorId: #}", authorId).as(Files.class);
        List<Files> files = new ArrayList<>();

        while(cursor.hasNext()) { files.add(cursor.next()); }

        return files;
    }
    /**
     * Returns a List<Files> with all Files for given filename
     * @param filename given authorId
     * @return the Files list, empty if none
     */
    public List<Files> findByFilename(String filename) {
        MongoCursor<Files> cursor = files().find("{filename: #}", filename).as(Files.class);
        List<Files> files = new ArrayList<>();

        while(cursor.hasNext()) { files.add(cursor.next()); }

        return files;
    }

    /**
     * Adds a new Files
     * @param f the Files to add
     * @return true if success else false
     */
    public boolean insert(Files f) {
        WriteResult wRes = files().insert(f);
        return wRes.wasAcknowledged();
    }

    /**
     * Updates/saves the given Files
     * @param f the Files to update
     * @return true if success else false
     */
    public boolean save(Files f) {
        WriteResult wRes = files().save(f);
        return wRes.wasAcknowledged();
    }

    /**
     * Removes the Files with the given id
     * @param id the given id
     * @return true if success else false
     */
    public boolean removeById(ObjectId id) {
        WriteResult wRes = files().remove(id);
        return wRes.wasAcknowledged();
    }

    /**
     * Inserts a random Files
     * @return true if success else false
     */
    public boolean insertRandomFiles(ObjectId id) {
        Files f = Utils.getRandomFiles(id);
        return insert(f);
    }
}
