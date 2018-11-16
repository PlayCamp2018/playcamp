package repositories;

import com.mongodb.WriteResult;
import models.Database.Project;
import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import uk.co.panaxiom.playjongo.PlayJongo;
import utils.Utils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepository {
    @Inject
    public PlayJongo jongo;

    public MongoCollection projects() {
        return jongo.getCollection("DB.projects");
    }

    /**
     * Returns a List<Projects> with all projects
     * @return the project list, empty if none
     */
    public List<Project> getAllProjects() {
        MongoCursor<Project> cursor = projects().find().as(Project.class);
        List<Project> projects = new ArrayList<>();

        while (cursor.hasNext()) { projects.add(cursor.next()); }

        return projects;
    }

    /**
     * Finds user with given id
     * @param id given user id
     * @return the found User or null
     */
    public Project findById(ObjectId id) { return projects().findOne(id).as(Project.class); }
    /**
     * Finds projects by given ownerId
     * @param ownerId given ownerId
     * @return the found project list or empty if none
     */
    public List<Project> findByOwnerId(ObjectId ownerId) {
        MongoCursor<Project> cursor = projects().find("{ownerid: #}", ownerId).as(Project.class);
        List<Project> projects = new ArrayList<>();

        while (cursor.hasNext()) { projects.add(cursor.next()); }

        return projects;
    }


    /**
     * Updates/saves the given project
     * @param project the project to update
     * @return true if success else false
     */
    public boolean save(Project project) {
        WriteResult wRes = projects().save(project);
        return wRes.wasAcknowledged();
    }


    /**
     * Adds a new project
     * @param project the project to add
     * @return true if success else false
     */
    public boolean insert(Project project) {
        WriteResult wRes = projects().insert(project);
        return wRes.wasAcknowledged();
    }


    /**
     * Removes the project with the given id
     * @param id the given id
     * @return true if success else false
     */
    public boolean removeById(ObjectId id) {
        WriteResult wRes = projects().remove(id);
        return wRes.wasAcknowledged();
    }


    /**
     * Inserts a random user
     * @return true if success else false
     */
    public boolean insertRandomProject(ObjectId id) {
        Project p = Utils.getRandomProject(id);
        return insert(p);
    }
}
