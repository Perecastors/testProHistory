package controllers;

import jdk.nashorn.internal.ir.IdentNode;
import models.Champion;
import models.Pool;
import models.Pref;
import play.mvc.Controller;
import services.ChampionService;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.bitCount;
import static java.lang.Integer.parseInt;

public class ChampionsController extends Controller {

    public static void changementPreference(String ligne,List<Integer> nombreChampions,List<Integer> championPreference,List<Long> idChampion){
        ChampionService.changerPreference(idChampion,championPreference);
        switch(ligne){
            case("Top"):
                create("Top",nombreChampions.get(1)+1,nombreChampions);
                break;
            case("Jungle"):
                create("Jungle",nombreChampions.get(3)+1,nombreChampions);
                break;
            case("Mid"):
                create("Mid",nombreChampions.get(5)+1,nombreChampions);
                break;
            case("Adc"):
                create("Adc",nombreChampions.get(7)+1,nombreChampions);
                break;
            case("Support"):
                create("Support",nombreChampions.get(9)+1,nombreChampions);
                break;
        }
    }

    public static void create(String ligne, int preference,List<Integer> nombreChampions) {
        List<Champion> listeChampion = ChampionService.trier(ChampionService.getAllMyChampions());
        renderTemplate("/Lignes/create.html",ligne,preference,listeChampion,nombreChampions);
    }

    public static void createAdverse(String ligne, int preference,List<Integer> nombreChampions) {
        List<Champion> listeChampion = ChampionService.trier(ChampionService.getAllAdverseChampions());
        renderTemplate("/Lignes/create adverse.html",ligne,preference,listeChampion,nombreChampions);
    }

    public static void save(Champion champion) {
        ChampionService.enregistrer(champion);
        List<Champion> listeChampion = ChampionService.getAllMyChampions();
        List<Integer> nombreChampions=ChampionService.getMaxValueLigne(listeChampion);
        switch(champion.ligne){
            case("Top"):
                create("Top",nombreChampions.get(1)+1,nombreChampions);
                break;
            case("Jungle"):
                create("Jungle",nombreChampions.get(3)+1,nombreChampions);
                break;
            case("Mid"):
                create("Mid",nombreChampions.get(5)+1,nombreChampions);
                break;
            case("Adc"):
                create("Adc",nombreChampions.get(7)+1,nombreChampions);
                break;
            case("Support"):
                create("Support",nombreChampions.get(9)+1,nombreChampions);
                break;
        }
    }

    public static void saveAdverse(Champion champion) {
        ChampionService.enregistrer(champion);
        List<Champion> listeAdverseChampion = ChampionService.getAllAdverseChampions();
        List<Integer> nombreAdverseChampions=ChampionService.getMaxValueLigne(listeAdverseChampion);
        switch(champion.ligne){
            case("Top"):
                createAdverse("Top",nombreAdverseChampions.get(1)+1,nombreAdverseChampions);
                break;
            case("Jungle"):
                createAdverse("Jungle",nombreAdverseChampions.get(3)+1,nombreAdverseChampions);
                break;
            case("Mid"):
                createAdverse("Mid",nombreAdverseChampions.get(5)+1,nombreAdverseChampions);
                break;
            case("Adc"):
                createAdverse("Adc",nombreAdverseChampions.get(7)+1,nombreAdverseChampions);
                break;
            case("Support"):
                createAdverse("Support",nombreAdverseChampions.get(9)+1,nombreAdverseChampions);
                break;
        }
    }

    public static void delete(Long idChampion,String ligne) {
        ChampionService.supprimer(idChampion);
        List<Champion> listeChampion = ChampionService.getAllMyChampions();
        List<Integer> nombreChampions=ChampionService.getMaxValueLigne(listeChampion);
        switch(ligne){
            case("Top"):
                create("Top",nombreChampions.get(1)+1,nombreChampions);
                break;
            case("Jungle"):
                create("Jungle",nombreChampions.get(3)+1,nombreChampions);
                break;
            case("Mid"):
                create("Mid",nombreChampions.get(5)+1,nombreChampions);
                break;
            case("Adc"):
                create("Adc",nombreChampions.get(7)+1,nombreChampions);
                break;
            case("Support"):
                create("Support",nombreChampions.get(9)+1,nombreChampions);
                break;
        }
    }

    public static void deleteAdverse(Long idChampion,String ligne) {
        ChampionService.supprimer(idChampion);
        List<Champion> listeChampion = ChampionService.getAllAdverseChampions();
        List<Integer> nombreChampions=ChampionService.getMaxValueLigne(listeChampion);
        switch(ligne){
            case("Top"):
                createAdverse("Top",nombreChampions.get(1)+1,nombreChampions);
                break;
            case("Jungle"):
                createAdverse("Jungle",nombreChampions.get(3)+1,nombreChampions);
                break;
            case("Mid"):
                createAdverse("Mid",nombreChampions.get(5)+1,nombreChampions);
                break;
            case("Adc"):
                createAdverse("Adc",nombreChampions.get(7)+1,nombreChampions);
                break;
            case("Support"):
                createAdverse("Support",nombreChampions.get(9)+1,nombreChampions);
                break;
        }
    }

    public static void afficher() {
        List<Integer> tailleListes = ChampionService.getMaxValueLigne(ChampionService.getAllMyChampions());
        List<Integer> tailleAdverseListes = ChampionService.getMaxValueLigne(ChampionService.getAllAdverseChampions());
        List<Champion> listeChampion = ChampionService.trier(ChampionService.getAllMyChampions());
        List<Champion> listeAdverseChampion = ChampionService.trier(ChampionService.getAllAdverseChampions());
        int ordre = 1;
        List<Pool> poolChampions = ChampionService.trierParNote(ChampionService.combinaisons(listeChampion,listeAdverseChampion,ordre));
        renderTemplate("/Lignes/afficher.html",listeChampion,listeAdverseChampion,tailleListes,tailleAdverseListes,poolChampions);
    }

    public static void gestionPrefCont(){
        List<Pref> listePref=ChampionService.getAllPref();
        renderTemplate("/Lignes/pref-cont.html",listePref);
    }

    public static void ajouterPref(String nomChampionTop,String nomChampionJungle,String nomChampionMid,
                                 String nomChampionAdc,String nomChampionSupport){
        ChampionService.ajouterPref(nomChampionTop,nomChampionJungle,nomChampionMid,nomChampionAdc,nomChampionSupport);
        gestionPrefCont();
    }
}

