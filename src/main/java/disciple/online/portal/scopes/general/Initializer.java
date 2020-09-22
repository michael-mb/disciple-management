package disciple.online.portal.scopes.general;

import disciple.online.portal.scopes.user.entities.TestUser;
import disciple.online.portal.scopes.user.entities.User;
import disciple.online.portal.scopes.user.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Initializer {
    private static final Logger LOG = LoggerFactory.getLogger(Initializer.class);

    public UserService userService;
    @Autowired
    public Initializer(UserService userService,
                       final @Value("${spring.jpa.hibernate.ddl-auto}") String database){
        this.userService = userService;

        if(setupDemoUsers(database)){
            this.setupTestUsers();
        }
    }

    private void setupTestUsers() {
        LOG.info("Creating default Users.");
        User user ;
        for (TestUser testUser : TestUser.values()) {
            user = new User(testUser.firstName , testUser.lastName , testUser.mailAddress ,
                    testUser.password , testUser.grantedAuthorities , testUser.city , testUser.street ,testUser.phone , testUser.discipleMakerMail);

            userService.generateAndSaveNewValidationTokenForUser(user);
            userService.rehashPassword(testUser.password , user);
        }
    }

    private boolean setupDemoUsers(String database){
        return "create".equals(database);
    }

}
