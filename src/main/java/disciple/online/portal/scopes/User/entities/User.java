package disciple.online.portal.scopes.user.entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class User implements UserDetails , Serializable {
    @Id
    @GeneratedValue
    private long id;

    @Column
    @NotBlank
    private String firstname;

    @Column
    @NotBlank
    @NotEmpty
    @NotNull
    private String lastname;
    
    @Column(unique = true)
    @NotBlank
    @NotNull
    private String email;

    @Column
    @NotEmpty
    @NotNull
    public String hashedPassword;

    @Column
    @ElementCollection
    @Fetch(FetchMode.JOIN)
    @NotNull
    private Set<UserRole> grantedAuthorities = new HashSet<>();

    @Column
    @NotBlank
    @NotEmpty
    private String phone;

    @Column
    @NotBlank
    @NotEmpty
    @NotNull
    private String city;

    @Column
    @NotBlank
    @NotEmpty
    @NotNull
    private String street;


    @Column
    @NotBlank
    @NotEmpty
    @NotNull
    private String discipleMakerMail;

    @Column
    private String validationToken;

    @SuppressWarnings("unused")
    public User(){};

    public User(String firstname , String lastname , String email , String password , Set<UserRole> grantedAuthorities,
                String city , String street , String phone , String discipleMakerMail){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.hashedPassword = password;
        this.grantedAuthorities = grantedAuthorities;
        this.city = city;
        this.street = street;
        this.phone = phone;
        this.discipleMakerMail = discipleMakerMail;
    };
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public Set<UserRole> getGrantedAuthorities() {
        return grantedAuthorities;
    }

    public void setGrantedAuthorities(Set<UserRole> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.grantedAuthorities == null) {
            return Collections.emptySet();
        } else {
            return this.grantedAuthorities.stream()
                    .map(ga -> new SimpleGrantedAuthority(ga.toString()))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
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

    public String getValidationToken() {
        return validationToken;
    }

    public void setValidationToken(String validationToken) {
        this.validationToken = validationToken;
    }

    public boolean hasAutorithy(String auth){
      for(UserRole role : getGrantedAuthorities()){
          if(role.toString().equals(auth.toUpperCase()))
              return true;
      }
      return false;
    };

    @Override
    public String toString(){
        return "User{" +
                "id="+id +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                ", phone='" + phone + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", grantedAuthorities='" + grantedAuthorities + '\'' +
                ", disciple maker mail='"+ discipleMakerMail + '}';
    }
}
