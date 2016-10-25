package controllers;

import models.HistoryGame2;
import play.data.validation.Valid;
import play.mvc.Controller;

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
        if(validation.hasErrors()) {
            renderTemplate("Forms/createProGame.html",historyGame);
        }
        renderTemplate("Forms/saveProGame.html",historyGame);
    }

    public static void confirmProGame(HistoryGame2 historyGame)
    {
        historyGame.save();
        redirect("/");
    }
}
