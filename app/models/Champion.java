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
            case "Reksai":
                return "//ddragon.leagueoflegends.com/cdn/6.20.1/img/champion/RekSai.png";
            case "Aurelion Sol":
                return "//ddragon.leagueoflegends.com/cdn/6.20.1/img/champion/AurelionSol.png";
            case "Jarvan IV":
                return "//ddragon.leagueoflegends.com/cdn/6.20.1/img/champion/JarvanIV.png";
            case "KhaZix":
                return "//ddragon.leagueoflegends.com/cdn/6.20.1/img/champion/Khazix.png";
            case "Maitre Yi":
                return "//ddragon.leagueoflegends.com/cdn/6.20.1/img/champion/MasterYi.png";
            case "Miss Fortune":
                return "//ddragon.leagueoflegends.com/cdn/6.20.1/img/champion/MissFortune.png";
            case "Tahm Kench":
                return "//ddragon.leagueoflegends.com/cdn/6.20.1/img/champion/TahmKench.png";
            case "Twisted Fate":
                return "//ddragon.leagueoflegends.com/cdn/6.20.1/img/champion/TwistedFate.png";
            case "Xin Zhao":
                return "//ddragon.leagueoflegends.com/cdn/6.20.1/img/champion/XinZhao.png";
            case "Kogmaw":
                return "//ddragon.leagueoflegends.com/cdn/6.20.1/img/champion/KogMaw.png";
        }
        return "//ddragon.leagueoflegends.com/cdn/6.20.1/img/champion/"+nom+".png";
    }
}
