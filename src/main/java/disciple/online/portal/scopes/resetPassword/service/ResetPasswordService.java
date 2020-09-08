package disciple.online.portal.scopes.resetPassword.service;

import disciple.online.portal.scopes.mail.service.MailService;
import disciple.online.portal.scopes.token.TokenServiceImpl;
import disciple.online.portal.scopes.user.entities.User;
import disciple.online.portal.scopes.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Optional;

@Service
public class ResetPasswordService {
    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Autowired
    private TokenServiceImpl tokenService;

    public void resetPassword(String email){
        Optional<User> user = userService.findUserByEmail(email);
        if(!user.isPresent())
            return;
        try {
            mailService.sendResetPasswordMail(user.get());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void changePassword(String userMail , String token , String newPassword){

        if(!userMail.equals(tokenService.getEmailFromToken(token)))
            return;

        Optional<User> user = userService.findUserByEmail(userMail);
        if(!user.isPresent())
            return;

        userService.updatePassword(newPassword,user.get());

    }
}
