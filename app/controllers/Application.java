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

}