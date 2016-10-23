package controllers;

import org.joda.time.DateTime;
import play.mvc.*;

import models.*;
import services.SearchService;

import java.sql.Date;
import java.util.List;

public class Application extends Controller {

    public static void index() {

        HistoryGame2 hss = new HistoryGame2();
      /*  java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        hss.gameDate = new java.sql.Date(utilDate.getTime());
        hss.save();*/
        List<HistoryGame2> hs =  HistoryGame2.findAll();
           render(hs);
    }

    public static void search(SearchForm searchForm){
        renderTemplate("Application/index.html",SearchService.searchNoFilter(searchForm));
    }

    public static void searchNoFilter(SearchForm searchForm){
        renderArgs.put("PostChamp", searchForm.champ);
        List<HistoryGame2> hs =SearchService.searchNoFilter(searchForm);
        renderTemplate("Application/index.html",hs);
    }
    public static void searchWithFilter(SearchForm searchForm){
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
        List<HistoryGame2> hs =SearchService.searchWithFilter(searchForm);
        renderTemplate("Application/index.html",hs);
    }

}