package Onlineshop;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
@Getter
@Setter
@Entity
public class Kunde {
    private String name;
    private String Straßenname;
    private String password;
    private String hausnummer;
    private Integer postleitzahl;
    private EMail email;
    private String ort;
    @Enumerated(EnumType.STRING)
    private Land land;
    @Id
    private Long id;

    public Kunde() {
    }

    public Kunde(String name, String email, String password) {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
