package disciple.online.portal.statics;

public class RegisterUser{

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String passwordAgain;
    private String phone;
    private String city;
    private String street;
    private String discipleMakerMail;

    public RegisterUser(String firstname , String lastname , String email , String password ,String passwordAgain,
                        String city , String street , String phone , String discipleMakerMail){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.passwordAgain = passwordAgain;
        this.city = city;
        this.street = street;
        this.phone = phone;
        this.discipleMakerMail = discipleMakerMail;
    };

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordAgain() {
        return passwordAgain;
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
        return this.discipleMakerMail;
    }

    public void setDiscipleMakerMail(String discipleMakerMail) {
        this.discipleMakerMail = discipleMakerMail;
    }


    @Override
    public String toString(){
        return "User{" +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone='" + phone + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", disciple maker mail='"+ discipleMakerMail + '}';
    }
}
