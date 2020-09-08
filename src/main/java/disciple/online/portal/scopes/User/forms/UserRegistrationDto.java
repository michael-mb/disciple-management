package disciple.online.portal.scopes.user.forms;

import disciple.online.portal.util.validation.Regexes;
import disciple.online.portal.util.validation.ValidEmail;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRegistrationDto {
    @NotBlank
    @Size(max=200)
    @ValidEmail
    public String email;

    @NotBlank
    @Size(max = 200, message = "{error.invalid.length}")
    @Pattern(regexp = Regexes.TEXT, message = "{error.invalid.field.text}")
    public String firstName;

    @NotBlank
    @Size(max = 200, message = "{error.invalid.length}")
    @Pattern(regexp = Regexes.TEXT, message = "{error.invalid.field.text}")
    public String lastName;

    @NotBlank
    @Size(max = 200, message = "{error.invalid.length}")
    @Pattern(regexp = Regexes.PASSWORD, message = "{error.registration.invalid.password}")
    public String password;

    @NotBlank
    @Size(max = 200, message = "{error.invalid.length}")
    @Pattern(regexp = Regexes.PASSWORD, message = "{error.registration.invalid.password}")
    public String passwordAgain;

    @NotBlank
    public String phone;

    @NotBlank
    @Size(max = 200, message = "{error.invalid.length}")
    @Pattern(regexp = Regexes.TEXT, message = "{error.invalid.field.text}")
    public String city;

    @NotBlank
    @Size(max = 200, message = "{error.invalid.length}")
    @Pattern(regexp = Regexes.TEXT, message = "{error.invalid.field.text}")
    public String street;

    @NotBlank
    @Size(max=200)
    public String discipleMakerMail;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
    public String getDiscipleMakerMail() {
        return discipleMakerMail;
    }

    public void setDiscipleMakerMail(String discipleMakerMail) {
        this.discipleMakerMail = discipleMakerMail;
    }

    @Override
    public String toString(){
        return "UserRegistrationDto{"+
                "email='" + email + '\'' +
                ", firstname='" + firstName + '\'' +
                ", lastname='" + lastName + '\'' +
                ", Password='" + password + '\'' +
                ", PasswordAgain='" + passwordAgain + '\'' +
                ", phone='" + phone + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", discipleMakerMail='" + discipleMakerMail + '\'' +
                '}' ;
    }
}
