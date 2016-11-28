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

   public static void create(String ligne,String camp) {
        List<Champion> listeChampion;
        switch (camp){
            case("monCamp"):
                listeChampion  = ChampionService.trier(ChampionService.getAllChampionsFromLane(ligne,"monCamp"));
                renderTemplate("/Lignes/create.html",ligne,listeChampion);
                break;
            case("autreCamp"):
                listeChampion = ChampionService.trier(ChampionService.getAllChampionsFromLane(ligne,"autreCamp"));
                renderTemplate("/Lignes/createAdverse.html",ligne,listeChampion);
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

    public static void changementPreference(String ligne,List<Integer> championPreference,List<Long> idChampion,String camp){
        ChampionService.changerPreference(idChampion,championPreference);
        create(ligne,camp);
    }

    public static void save(Champion champion,String camp) {
        ChampionService.enregistrer(champion);
        create(champion.ligne,camp);
    }

    public static void delete(Long idChampion,String ligne,String camp) {
        ChampionService.supprimer(idChampion);
        create(ligne,camp);
    }

    public static void deletePref(Long idPref) {
        ChampionService.supprimerPref(idPref);
        gestionPrefCont();
    }

    public static void test(){
        renderTemplate("/Lignes/testjQuery.html");
    }
}

