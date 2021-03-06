package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * Created by Formation on 22/11/2016.
 */
@Entity
public class Pref extends Model {
    public String nomChampionTop;
    public String nomChampionJungle;
    public String nomChampionMid;
    public String nomChampionAdc;
    public String nomChampionSupport;

    public String getUrlImagePref(String nom) {
        String baseUrl = "http://ddragon.leagueoflegends.com/cdn/6.20.1/img/champion/";
        switch (nom) {
            case "ChoGath":
                return baseUrl + "Chogath.png";
            case "Leesin":
                return baseUrl + "LeeSin.png";
            case "Reksai":
                return baseUrl + "RekSai.png";
            case "Aurelion Sol":
                return baseUrl + "AurelionSol.png";
            case "Jarvan IV":
                return baseUrl + "JarvanIV.png";
            case "KhaZix":
                return baseUrl + "Khazix.png";
            case "Maitre Yi":
                return baseUrl + "MasterYi.png";
            case "Miss Fortune":
                return baseUrl + "MissFortune.png";
            case "Tahm Kench":
                return baseUrl + "TahmKench.png";
            case "Twisted Fate":
                return baseUrl + "TwistedFate.png";
            case "Xin Zhao":
                return baseUrl + "XinZhao.png";
            case "Kogmaw":
                return baseUrl + "KogMaw.png";
            case "":
                return "http://www.howtogeek.com/wp-content/uploads/2012/12/Plain-Black-Wallpaper.png";
        }
        return baseUrl + nom + ".png";
    }
}

