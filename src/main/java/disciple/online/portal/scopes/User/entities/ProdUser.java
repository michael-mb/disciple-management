package disciple.online.portal.scopes.user.entities;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum ProdUser {

    ADMIN("admin", "admin" , "admin@admin.com",
            "Admin237+" , "000000000" , "dresden", "dresden 14",
            new HashSet<UserRole>(Arrays.asList(UserRole.DISCIPLE , UserRole.DISCIPLEMAKER , UserRole.ADMIN)) ,
            "admin@admin.com"); 

    public final String firstName;
    public final String lastName;
    public final String mailAddress;
    public final String password;
    public final String phone;
    public final String city;
    public final String street;
    public final Set<UserRole> grantedAuthorities;
    public final String discipleMakerMail;

    ProdUser(String firstName , String lastName , String mailAddress , String password , String phone , String city, String street,
             Set<UserRole> grantedAuthorities , String discipleMakerMail){
        this.firstName = firstName;
        this.lastName = lastName;
        this.mailAddress = mailAddress;
        this.password = password;
        this.phone = phone;
        this.city = city;
        this.street = street;
        this.grantedAuthorities = grantedAuthorities;
        this.discipleMakerMail = discipleMakerMail;
    }
}
