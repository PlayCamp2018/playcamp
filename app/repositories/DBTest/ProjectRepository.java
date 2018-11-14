package repositories.DBTest;

import com.mongodb.WriteResult;
import models.Database.Project;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import uk.co.panaxiom.playjongo.PlayJongo;

import javax.inject.Inject;
import java.util.List;


public class ProjectRepository {

    @Inject
    public PlayJongo jongo;

    public MongoCollection projects() {
        return jongo.getCollection("DB.projects");
    }

    public Project[] getAllProjects() {
        MongoCursor<Project> lProjects = projects().find().as(Project.class);

        Project aProjects[] = new Project[lProjects.count()];
        int i = 0;

        while (lProjects.hasNext()) {
            aProjects[i] = lProjects.next();
            i++;
        }

        return aProjects;
    }

    public boolean insertRandomProject() {
        Project p = new Project();

        p.setId(new org.bson.types.ObjectId());
        p.setName("blabla");
        p.setBeschreibung("hallo");
        p.setOwnerid(new org.bson.types.ObjectId());
        p.setUserList(List.of(new org.bson.types.ObjectId(), new org.bson.types.ObjectId()));
        p.setMessageBoardID(new org.bson.types.ObjectId());

        WriteResult wRes =  projects().insert(p);
        return wRes.wasAcknowledged();
    }

    //public Project findByEmail(String email) {
       // return users().findOne("{email: #}", email).as(User.class);
    //}
}