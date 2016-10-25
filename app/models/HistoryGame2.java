package models;

import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by Formation on 20/10/2016.
 */
@Entity
public class HistoryGame2 extends Model {

    @Required
    @NotEmpty
    public String blueTeam;
    @Required
    @NotEmpty
    public String redTeam;
    @Required
    @NotEmpty
    public String bTop;
    @Required
    @NotEmpty
    public String bJungle;
    @Required
    @NotEmpty
    public String bMid;
    @Required
    @NotEmpty
    public String bAdc;
    @Required
    @NotEmpty
    public String bSupport;
    @Required
    @NotEmpty
    public String bBan1;
    @Required
    @NotEmpty
    public String bBan2;
    @Required
    @NotEmpty
    public String bBan3;
    @Required
    @NotEmpty
    public String rTop;
    @Required
    @NotEmpty
    public String rJungle;
    @Required
    @NotEmpty
    public String rMid;
    @Required
    @NotEmpty
    public String rAdc;
    @Required
    @NotEmpty
    public String rSupport;
    @Required
    @NotEmpty
    public String rBan1;
    @Required
    @NotEmpty
    public String rBan2;
    @Required
    @NotEmpty
    public String rBan3;
    @Required
    @NotEmpty
    public String gameName;
    @Required
    @NotEmpty
    public String gameLink;
    @Required
    @NotEmpty
    public String region;
    @Required
    @NotEmpty
    public String patch;
    @Required
    public Date gameDate;
}
