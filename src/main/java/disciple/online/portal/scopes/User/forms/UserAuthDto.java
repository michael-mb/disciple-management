package disciple.online.portal.scopes.user.forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserAuthDto {

    @NotBlank
    @Size(max=200)
    public String authority;

    @NotBlank
    @Size(max=200)
    public String discipleMakerMail;


    public String getDiscipleMakerMail() {
        return discipleMakerMail;
    }

    public void setDiscipleMakerMail(String discipleMakerMail) {
        this.discipleMakerMail = discipleMakerMail;
    }

    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString(){
        return "UserAuthDto{"+ "authority='" + authority  + '\'' +
                ",discipleMakerMail='" + discipleMakerMail +'}' ;
    }
}
