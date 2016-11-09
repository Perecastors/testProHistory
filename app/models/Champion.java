package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Champion extends Model {
    public String nom;
    public String ligne;
    public int preference;

    public String getUrlImage(){
        switch (nom){
            case "Leesin":
                return "//ddragon.leagueoflegends.com/cdn/6.20.1/img/champion/LeeSin.png";
        }
        return "//ddragon.leagueoflegends.com/cdn/6.20.1/img/champion/"+nom+".png";
    }
}
