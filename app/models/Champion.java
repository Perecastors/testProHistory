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
        String baseUrl = "http://ddragon.leagueoflegends.com/cdn/6.20.1/img/champion/";
        switch (nom){
            case "Leesin":
                return baseUrl+"LeeSin.png";
            case "Reksai":
                return  baseUrl+"RekSai.png";
            case "Aurelion Sol":
                return baseUrl+"AurelionSol.png";
            case "Jarvan IV":
                return  baseUrl+"JarvanIV.png";
            case "KhaZix":
                return  baseUrl+"Khazix.png";
            case "Maitre Yi":
                return  baseUrl+"MasterYi.png";
            case "Miss Fortune":
                return baseUrl+"MissFortune.png";
            case "Tahm Kench":
                return baseUrl+"TahmKench.png";
            case "Twisted Fate":
                return baseUrl+"TwistedFate.png";
            case "Xin Zhao":
                return  baseUrl+"XinZhao.png";
            case "Kogmaw":
                return baseUrl+"KogMaw.png";
        }
        return baseUrl+nom+".png";
    }
}
