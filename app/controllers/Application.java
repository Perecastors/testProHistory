package controllers;

import org.joda.time.DateTime;
import play.mvc.*;

import models.*;
import services.ParamService;
import services.SearchService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Application extends Controller {

    @Before
    private void getNumberTotal(){
        int nbTotalGames = HistoryGame2.findAll().size();
        ParamService.initDropDownList();
        renderArgs.put("nbTotalGames", nbTotalGames);
    }

    public static void index() {
        List<HistoryGame2> hs = HistoryGame2.find("ORDER BY gameDate DESC ").fetch(50);
        renderArgs.put("nbFoundGames", hs.size());
           render(hs);
    }

    public static void searchNoFilter(SearchForm searchForm){
        List<HistoryGame2> hs =SearchService.searchNoFilter(searchForm);
        renderArgs.put("PostChamp", searchForm.champ);
        renderArgs.put("Postpatch", searchForm.patch);
        renderArgs.put("Postregion", searchForm.region);
        renderArgs.put("nbFoundGames", hs.size());
        renderTemplate("Application/index.html",hs);
    }
    public static void searchWithFilter(SearchForm searchForm){
        List<HistoryGame2> hs =SearchService.searchWithFilter(searchForm);
        renderArgs.put("PostbTop", searchForm.bTop);
        renderArgs.put("PostbJungle", searchForm.bJungle);
        renderArgs.put("PostbMid", searchForm.bMid);
        renderArgs.put("PostbAdc", searchForm.bAdc);
        renderArgs.put("PostbSupport", searchForm.bSupport);
        renderArgs.put("PostrTop", searchForm.rTop);
        renderArgs.put("PostrJungle", searchForm.rJungle);
        renderArgs.put("PostrMid", searchForm.rMid);
        renderArgs.put("PostrAdc", searchForm.rAdc);
        renderArgs.put("PostrSupport", searchForm.rSupport);
        renderArgs.put("Postpatch", searchForm.patch);
        renderArgs.put("Postregion", searchForm.region);
        renderArgs.put("nbFoundGames", hs.size());
        renderTemplate("Application/index.html",hs);
    }
}