package services;

import models.HistoryGame2;

/**
 * Created by Formation on 27/10/2016.
 */
public class GameService {

    public static Boolean saveAndConfirm(HistoryGame2 hs){
        hs.save();
        if(hs.id !=null && hs.id > 0)
        {
            return true;
        }
        return false;
    }
}
