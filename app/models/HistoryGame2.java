package models;

import org.joda.time.DateTime;
import play.db.jpa.Model;

import javax.persistence.Entity;
import java.sql.Date;

/**
 * Created by Formation on 20/10/2016.
 */
@Entity
public class HistoryGame2 extends Model {

    public String blueTeam;
    public String redTeam;
    public String bTop;
    public String bJungle;
    public String bMid;
    public String bAdc;
    public String bSupport;
    public String bBan1;
    public String bBan2;
    public String bBan3;
    public String rTop;
    public String rJungle;
    public String rMid;
    public String rAdc;
    public String rSupport;
    public String rBan1;
    public String rBan2;
    public String rBan3;
    public String gameName;
    public String gameLink;
    public String region;
    public String patch;
    public Date gameDate;
}
