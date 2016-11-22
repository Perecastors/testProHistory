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
}

