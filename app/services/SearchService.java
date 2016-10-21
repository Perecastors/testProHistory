package services;

import models.HistoryGame2;
import models.SearchForm;

import java.util.List;

/**
 * Created by Formation on 21/10/2016.
 */
public  class SearchService {

    public static List<HistoryGame2> search(SearchForm searchForm)
    {
        List<HistoryGame2> hs =  HistoryGame2.find("bAdc = ?",searchForm.champ).fetch();
        return null;
    }

    public static List<HistoryGame2> searchNoFilter(SearchForm searchForm)
    {

        List<HistoryGame2> hs =HistoryGame2.find("bTop = '" +searchForm.champ+
                "' OR bJungle = '" +searchForm.champ+
                "' OR bMid = '" +searchForm.champ+
                "' OR bAdc = '" +searchForm.champ+
                "' OR bSupport = '" +searchForm.champ+
                "' OR rTop = '" +searchForm.champ+
                "' OR rJungle = '" +searchForm.champ+
                "' OR rMid = '" +searchForm.champ+
                "' OR rSupport = '" +searchForm.champ+
                "' OR rAdc = '"+searchForm.champ+"'").fetch();
        return hs;
    }

}
