package disciple.online.portal.scopes.register.services;

import disciple.online.portal.scopes.user.entities.User;
import disciple.online.portal.scopes.user.entities.UserRole;
import disciple.online.portal.scopes.user.forms.UserAuthDto;
import disciple.online.portal.scopes.user.forms.UserRegistrationDto;
import disciple.online.portal.scopes.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Service
public class RegisterService {
    @Autowired
    private UserService userService;

    public void addUser(UserRegistrationDto userRegistrationDto){
        if(userRegistrationDto == null) throw new NullPointerException("userRegistrationDto must not be null");

        User user = new User(userRegistrationDto.firstName , userRegistrationDto.lastName , userRegistrationDto.email,
                userRegistrationDto.password , new HashSet<UserRole>(Arrays.asList(UserRole.DISCIPLE)) ,
                userRegistrationDto.city , userRegistrationDto.street , userRegistrationDto.phone , userRegistrationDto.getDiscipleMakerMail());

        userService.generateAndSaveNewValidationTokenForUser(user);
        userService.rehashPassword(user.hashedPassword , user);
    }

    public void updateUser(UserRegistrationDto userRegistrationDto , User user){
        if(userRegistrationDto == null) throw new NullPointerException("userRegistrationDto must not be null");
        if(user == null) throw new NullPointerException("user must not be null");

        user.setFirstname(userRegistrationDto.firstName);
        user.setLastname(userRegistrationDto.lastName);
        user.setCity(userRegistrationDto.city);
        user.setStreet(userRegistrationDto.street);
        user.setDiscipleMakerMail(userRegistrationDto.discipleMakerMail);
        user.setPhone(userRegistrationDto.phone);

        if(userRegistrationDto.discipleMakerMail != null)
            user.setDiscipleMakerMail(userRegistrationDto.discipleMakerMail);

        userService.updatePassword(userRegistrationDto.getPassword() , user);
    }

    public void updateUser(UserAuthDto userAuthDto , User user){
        if(userAuthDto == null) throw new NullPointerException("userAuthDto must not be null");
        if(user == null) throw new NullPointerException("user must not be null");

        user.setDiscipleMakerMail(userAuthDto.discipleMakerMail);

        if(userAuthDto.getAuthority().equals(UserRole.DISCIPLE.toString()))
            user.setGrantedAuthorities(new HashSet<UserRole>(Collections.singletonList(UserRole.DISCIPLE)));

        else if(userAuthDto.getAuthority().equals(UserRole.DISCIPLEMAKER.toString()))
            user.setGrantedAuthorities(new HashSet<UserRole>(Arrays.asList(UserRole.DISCIPLE , UserRole.DISCIPLEMAKER)));

        else if(userAuthDto.getAuthority().equals(UserRole.ADMIN.toString()))
            user.setGrantedAuthorities(new HashSet<UserRole>(Arrays.asList(UserRole.DISCIPLE , UserRole.DISCIPLEMAKER , UserRole.ADMIN)));

        userService.saveUser(user);


    }
}
