package disciple.online.portal.scopes.user.services;

import disciple.online.portal.scopes.token.TokenServiceImpl;
import disciple.online.portal.scopes.user.entities.User;
import disciple.online.portal.scopes.user.entities.UserRole;
import disciple.online.portal.scopes.user.forms.UserRegistrationDto;
import disciple.online.portal.scopes.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public TokenServiceImpl tokenService;

    @Autowired
    public  PasswordEncoder passwordEncoder;

    public void saveUser(final User user){
        if(user == null) throw new NullPointerException("User must not be null");
        userRepository.save(user);
    }

    public void generateAndSaveNewValidationTokenForUser(final User user) {
        if (user == null) throw new NullPointerException("User must not be null.");
        String token = tokenService.createToken(user.getEmail());
        user.setValidationToken(token);
        saveUser(user);
    }


    public void updatePassword(final String newPassword, final User user) {
        if (newPassword == null) throw new NullPointerException("NewPassword must not be null.");
        if (newPassword.isEmpty()) throw new IllegalArgumentException("NewPassword must not be empty.");
        if (user == null) throw new NullPointerException("User must not be null.");

        user.hashedPassword = passwordEncoder.encode(newPassword);
        saveUser(user);
    }

    public void rehashPassword(final String password, final User user) {
        this.updatePassword(password, user);
    }

    public boolean isPasswordCorrect(final String password, final User user) {
        if (password == null) throw new NullPointerException("Password must not be null.");
        if (password.isEmpty()) throw new IllegalArgumentException("Password must not be empty.");
        if (user == null) throw new NullPointerException("User must not be null.");

        return passwordEncoder.matches(password, user.getHashedPassword());
    }

    public Optional<User> findUserByEmail(String email) {
        if (email == null) throw new NullPointerException("EMail must not be null.");
        if (email.isEmpty()) throw new NullPointerException("EMail must not be empty.");

        return userRepository.findFirstByEmail(email.toLowerCase().trim());
    }

    public Optional<User> findUserById(Long id) {
        Optional<User> byId = userRepository.findById(id);
        return byId;
    }

    public List<User> getAllDiscipleMaker() {
        return userRepository.findAllByOrderByEmailAsc().stream()
                .filter(user -> user.getGrantedAuthorities().contains(UserRole.DISCIPLEMAKER)).collect(Collectors.toList());
    }

    public List<User> getAllDisciple() {
        return userRepository.findAllByOrderByEmailAsc();
    }

    public List<User> getAllDiscipleFromDiscipleMaker(String mail){

        if (mail == null) throw new NullPointerException("mail must not be null.");
        if (mail.isEmpty()) throw new NullPointerException("User must not be empty.");

        List<User> users = new ArrayList<>();
        for(User user : userRepository.findAllByOrderByEmailAsc()){
            if(user.getDiscipleMakerMail().equals(mail))
                users.add(user);
        }
      return users;
    }
    public boolean doesEmailAlreadyExists(final UserRegistrationDto userRegistrationDto) {
        if (userRegistrationDto == null) throw new NullPointerException("UserRegistrationDto must not be null.");

        return doesEmailAlreadyExists(userRegistrationDto.email);
    }

    public boolean doesEmailAlreadyExists(final String email) {
        if (email == null) throw new NullPointerException("Email must not be null.");
        if (email.isEmpty()) throw new NullPointerException("Email must not be empty.");

        return findUserByEmail(email).isPresent();
    }

    public void deleteUser(User user){
        if (user == null) throw new NullPointerException("User must not be null.");
        userRepository.deleteById(user.getId());
    }

}
