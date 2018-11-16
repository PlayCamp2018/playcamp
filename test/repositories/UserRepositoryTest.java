// not working jet, because it is an integration test, @Inject not working for UnitTests
//
//package repositories;
//
//import models.Database.User;
//import org.bson.types.ObjectId;
//import org.junit.Test;
//import play.Application;
//import play.inject.guice.GuiceApplicationBuilder;
//import play.test.WithApplication;
//
//import javax.inject.Inject;
//import java.util.concurrent.ThreadLocalRandom;
//
//import static org.junit.Assert.assertEquals;
//
//public class UserRepositoryTest extends WithApplication {
//    @Override
//    protected Application provideApplication() {
//        return new GuiceApplicationBuilder().build();
//    }
//
//    @Inject
//    UserRepository users;
//
//    public static User getRandomUser() {
//        int randomNum = ThreadLocalRandom.current().nextInt(1000000, 2000000 + 1);
//        User u = new User();
//        u.setFirstname("" + randomNum);
//        u.setLastname("" + (randomNum + 1));
//        u.setEmail(randomNum + "." + (randomNum + 1) + "@test.test");
//        u.setPassword("test1234");
//        u.setProfilePicID(new ObjectId());
//
//        return u;
//    }
//
//    @Test
//    public void testInsertUser() {
//        User u = getRandomUser();
//        System.out.println(u);
//        System.out.println("=============================================");
//        System.out.println(users == null);
//        System.out.println("=============================================");
//        boolean bRes = users.insert(u);
//        assertEquals(bRes, true);
//    }
//}
