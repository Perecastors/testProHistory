package controllers;

import models.HistoryGame2;
import play.data.validation.Valid;
import play.mvc.After;
import play.mvc.Controller;
import services.GameService;

/**
 * Created by Formation on 25/10/2016.
 */
public class FormController extends Controller {

    public static void createProGame()
    {
        renderTemplate("Forms/createProGame.html");
    }

    public static void saveProGame(@Valid HistoryGame2 historyGame)
    {
        flash.clear();
        if(validation.hasErrors()) {
            validation.keep();
            renderTemplate("Forms/createProGame.html",historyGame);
        }

        if(GameService.saveAndConfirm(historyGame))
        {
            flash("success",true);
            createProGame();
        }else{
            flash("fail",true);
            renderTemplate("Forms/createProGame.html",historyGame);
        }
    }

    public static void confirmProGame(HistoryGame2 historyGame)
    {
        historyGame.save();
        redirect("/");
    }

    @After
    public static void clearValidation(){
        validation.clear();
    }
}
