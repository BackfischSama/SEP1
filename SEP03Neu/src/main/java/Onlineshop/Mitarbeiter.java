package Onlineshop;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mitarbeiter {
    private String name;
    private String password;
    private EMail EMail;

    public Mitarbeiter(String name, String password, EMail email) {
        this.name = name;
        this.password = password;
        this.EMail = email;
    }

    public Mitarbeiter
    public String getPassword() {
        return password;
    }



    public void setPassword(String password) {
        this.password = password;
    }

    public EMail getEMail() {
        return EMail;
    }

    public void setEMail(EMail eMail) {
        this.EMail = eMail;
    }


    public String getEmail() {

    }
}
