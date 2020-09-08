package disciple.online.portal.scopes.resetPassword.forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ResetDto {

    @NotBlank
    @Size(max = 200)
    public String email;

    @NotBlank
    @Size(max = 200)
    public String password;

    @NotBlank
    @Size(max = 200)
    public String passwordAgain;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordAgain() {
        return passwordAgain;
    }

    public void setPasswordAgain(String passwordAgain) {
        this.passwordAgain = passwordAgain;
    }

    @Override
    public String toString() {
        return "ResetDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", passwordAgain='" + passwordAgain + '\'' +
                '}';
    }
}
