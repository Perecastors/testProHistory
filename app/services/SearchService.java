package services;

import models.HistoryGame2;
import models.SearchForm;

import java.lang.reflect.Field;
import java.util.List;

public  class SearchService {

    public static List<HistoryGame2> search(SearchForm searchForm)
    {
        List<HistoryGame2> hs =  HistoryGame2.find("bAdc = ?",searchForm.champ).fetch();
        return null;
    }

    public static List<HistoryGame2> searchNoFilter(SearchForm searchForm)
    {
        if(searchForm.champ.isEmpty())
            return HistoryGame2.find("ORDER BY gameDate DESC").fetch();
        return HistoryGame2.find("bTop = '" +searchForm.champ+
                "' OR bJungle = '" +searchForm.champ+
                "' OR bMid = '" +searchForm.champ+
                "' OR bAdc = '" +searchForm.champ+
                "' OR bSupport = '" +searchForm.champ+
                "' OR rTop = '" +searchForm.champ+
                "' OR rJungle = '" +searchForm.champ+
                "' OR rMid = '" +searchForm.champ+
                "' OR rSupport = '" +searchForm.champ+
                "' OR rAdc = '"+searchForm.champ+"'"+
                " ORDER BY gameDate DESC ").fetch();
    }

    public static List<HistoryGame2> searchWithFilter(SearchForm searchForm)
    {
        String createQuery = "";
        Boolean premierWhere=true;
        Class ftClass = searchForm.getClass().getSuperclass();
        for(Field x: ftClass.getDeclaredFields()){
            x.setAccessible(true);
            try {
                if(x.get(searchForm) != null) {
                    if (premierWhere && !((String)x.get(searchForm)).isEmpty()) {
                        createQuery += x.getName() + " = '" + x.get(searchForm) + "'";
                        premierWhere=false;
                    } else if(!((String)x.get(searchForm)).isEmpty()) {
                        createQuery += " AND " + x.getName() + " = '" + x.get(searchForm) + "'";
                    }
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        createQuery += " ORDER BY gameDate DESC";
        return HistoryGame2.find(createQuery).fetch();
    }

}
