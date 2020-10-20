package disciple.online.portal.scopes.general;

import disciple.online.portal.scopes.user.entities.ProdUser;
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
                       final @Value("${spring.jpa.hibernate.ddl-auto}") String database ,
                       final @Value("${spring.profiles.active}") String profile){

        this.userService = userService;

        if(setupDemoUsers(database)){
            if(isProdProfile(profile))
                setupProdUsers();
            else
                setupTestUsers();
        }
    }

    private void setupTestUsers() {
        LOG.info("Creating default Test Users.");
        User user ;
        for (TestUser testUser : TestUser.values()) {
            user = new User(testUser.firstName , testUser.lastName , testUser.mailAddress ,
                    testUser.password , testUser.grantedAuthorities , testUser.city , testUser.street ,testUser.phone , testUser.discipleMakerMail);

            userService.generateAndSaveNewValidationTokenForUser(user);
            userService.rehashPassword(testUser.password , user);
        }
    }

    private void setupProdUsers() {
        LOG.info("Creating default Prod Users.");
        User user ;
        for (ProdUser prodUser : ProdUser.values()) {
            user = new User(prodUser.firstName , prodUser.lastName , prodUser.mailAddress ,
                    prodUser.password , prodUser.grantedAuthorities , prodUser.city , prodUser.street ,
                    prodUser.phone , prodUser.discipleMakerMail);

            userService.generateAndSaveNewValidationTokenForUser(user);
            userService.rehashPassword(prodUser.password , user);
        }
    }

    private boolean isProdProfile(String profile){
        return "prod".equals(profile);
    }
    private boolean setupDemoUsers(String database){
        return "create".equals(database);
    }

}
