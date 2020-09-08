package disciple.online.portal.scopes.user.entities;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum TestUser {

    DISCIPLE_ONE("good" , "disciple" , "gooddisciple@test.com" ,
            "test" , "017644566712","dresden", "st petersburgerStrasse 44",
            new HashSet<UserRole>(Arrays.asList(UserRole.DISCIPLE)), "gooddisciplemaker@test.com"),

    DISCIPLE_TWO("not good" , "disciple" , "notgooddisciple@test.com" ,
            "test" , "014555466877","aalen" , "karabastrasse 22",
            new HashSet<UserRole>(Arrays.asList(UserRole.DISCIPLE)) , "notgooddisciplemaker@test.com"),

    DISCIPLEMAKER_ONE("good" , "disciplemaker" , "gooddisciplemaker@test.com" ,
            "test" , "0145865668645","frankfurt", "Berlinerstrasse 28",
            new HashSet<UserRole>(Arrays.asList(UserRole.DISCIPLE , UserRole.DISCIPLEMAKER)), "admin@admin.com"),

    DISCIPLEMAKER_TWO("not good" , "disciplemaker" , "notgooddisciplemaker@test.com" ,
            "test" , "014558465556","essen", "Kamerunerstraße 78",
            new HashSet<UserRole>(Arrays.asList(UserRole.DISCIPLE , UserRole.DISCIPLEMAKER)) ,
            "superadmin@admin.com"),

    ADMIN("admin", "admin" , "admin@admin.com",
            "admin" , "028876955654" , "dresden", "wardastrasse 14",
            new HashSet<UserRole>(Arrays.asList(UserRole.DISCIPLE , UserRole.DISCIPLEMAKER , UserRole.ADMIN)) ,
            "admin@admin.com"),
    SUPERADMIN("superadmin", "superadmin" , "superadmin@admin.com",
            "admin" , "245559643455" , "dresden", "nonostrasse 89",
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

    TestUser(String firstName , String lastName , String mailAddress , String password , String phone , String city, String street,
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