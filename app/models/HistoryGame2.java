package models;

import net.sf.oval.constraint.MinLength;
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

    @Required(message = "Blue Team cannot be empty")
    @NotEmpty
    public String blueTeam;
    @Required(message = "Red Team cannot be empty")
    @NotEmpty
    public String redTeam;
    @Required(message = "Blue Top cannot be empty")
    @NotEmpty
    public String bTop;
    @Required(message = "Blue Jungle cannot be empty")
    @NotEmpty
    public String bJungle;
    @Required(message = "Blue Mid cannot be empty")
    @NotEmpty
    public String bMid;
    @Required(message = "Blue Adc cannot be empty")
    @NotEmpty
    public String bAdc;
    @Required(message = "Blue Support cannot be empty")
    @NotEmpty
    public String bSupport;
    @Required(message = "Blue Ban 1 cannot be empty")
    @NotEmpty
    public String bBan1;
    @Required(message = "Blue Ban 2 cannot be empty")
    @NotEmpty
    public String bBan2;
    @Required(message = "Blue Ban 3 cannot be empty")
    @NotEmpty
    public String bBan3;
    @Required(message = "Red Top cannot be empty")
    @NotEmpty
    public String rTop;
    @Required(message = "Red Jungle cannot be empty")
    @NotEmpty
    public String rJungle;
    @Required(message = "Red Mid cannot be empty")
    @NotEmpty
    public String rMid;
    @Required(message = "Red Adc cannot be empty")
    @NotEmpty
    public String rAdc;
    @Required(message = "Red Support cannot be empty")
    @NotEmpty
    public String rSupport;
    @Required(message = "Red Ban 1 cannot be empty")
    @NotEmpty
    public String rBan1;
    @Required(message = "Red Ban 2 cannot be empty")
    @NotEmpty
    public String rBan2;
    @Required(message = "Red Ban 3 cannot be empty")
    @NotEmpty
    public String rBan3;
    @Required(message = "You must put a valid game name")
    @NotEmpty
    @MinLength(value = 15,message ="write at lease 15 charactere to describe the game")
    public String gameName;
    @Required(message = "You must put a valid link")
    @NotEmpty
    public String gameLink;
    @Required(message = "You must choose a valid region")
    @NotEmpty
    public String region;
    @Required(message = "You must choose a valid patch")
    @NotEmpty
    public String patch;
    @Required(message = "You must set a valid date")
    public Date gameDate;
}
