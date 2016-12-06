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
        List<Integer> ordre = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        List<Pool> poolChampions = ChampionService.trierParNote(ChampionService.combinaisons(listeChampion,listeAdverseChampion,ordre));
        renderTemplate("/Lignes/afficher.html",listeChampion,listeAdverseChampion,tailleListes,tailleAdverseListes,poolChampions);
    }

    public static void afficherOrdre(Integer ordre1,Integer ordre2,Integer ordre3,Integer ordre4,Integer ordre5){
        List<Integer> ordre = new ArrayList<>(Arrays.asList(ordre1,ordre2,ordre3,ordre4,ordre5));
        List<Champion> listeChampion = ChampionService.trier(ChampionService.getAllMyChampions());
        List<Champion> listeAdverseChampion = ChampionService.trier(ChampionService.getAllAdverseChampions());
        List<Pool> poolChampions = ChampionService.trierParNote(ChampionService.combinaisons(listeChampion,listeAdverseChampion,ordre));
        renderJSON(poolChampions);
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

    public static void api(){
        renderTemplate("/Lignes/testApi.html");
    }

    public static void accesPageChampionsJson(){
        renderTemplate("/Lignes/essaiJson.html");
    }

    public static void getChampionsJson(String top,String jungle,String mid,String adc,String support){
        List<String> rep = new ArrayList();
        rep.add(top);
        rep.add(jungle);
        rep.add(mid);
        rep.add(adc);
        rep.add(support);

        List<Champion> championsTop = new ArrayList();
        List<Champion> championsJungle = new ArrayList();
        List<Champion> championsMid = new ArrayList();
        List<Champion> championsAdc = new ArrayList();
        List<Champion> championsSupport = new ArrayList();
        if(rep.get(0).equals("true")){
            championsTop=ChampionService.getAllChampionsFromLane("top","monCamp");
        }
        if(rep.get(1).equals("true")){
            championsJungle=ChampionService.getAllChampionsFromLane("jungle","monCamp");
        }
        if(rep.get(2).equals("true")){
            championsMid=ChampionService.getAllChampionsFromLane("mid","monCamp");
        }
        if(rep.get(3).equals("true")){
            championsAdc=ChampionService.getAllChampionsFromLane("adc","monCamp");
        }
        if(rep.get(4).equals("true")){
            championsSupport=ChampionService.getAllChampionsFromLane("support","monCamp");
        }
        List<String> Top = new ArrayList();
        for (Champion champion:championsTop) {
            Top.add(champion.nom);
        }
        List<String> Jungle = new ArrayList();
        for (Champion champion:championsJungle) {
            Jungle.add(champion.nom);
        }
        List<String> Mid = new ArrayList();
        for (Champion champion:championsMid) {
            Mid.add(champion.nom);
        }
        List<String> Adc = new ArrayList();
        for (Champion champion:championsAdc) {
            Adc.add(champion.nom);
        }
        List<String> Support = new ArrayList();
        for (Champion champion:championsSupport) {
            Support.add(champion.nom);
        }
        List<List<String>> liste = new ArrayList();
        liste.add(Top);
        liste.add(Jungle);
        liste.add(Mid);
        liste.add(Adc);
        liste.add(Support);
        renderJSON(liste);
    }
}

