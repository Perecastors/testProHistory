package models;

import javax.persistence.Transient;
import java.sql.Date;

/**
 * Created by Formation on 20/10/2016.
 */

public class SearchForm extends HistoryGame2{
    @Transient
    public String champ;
    @Transient
    public String team;
}
