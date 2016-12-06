package services;

import models.Champion;
import models.Pool;
import models.Pref;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Formation on 26/10/2016.
 */
public class ChampionService {

    public static void enregistrer(Champion champion) {
        if(!champion.nom.isEmpty()) {
            List<Champion> listeToFindMaxPrefValue = Champion.find("ligne=? AND camp=?",champion.ligne,champion.camp).fetch();
            List<Champion> liste = Champion.find("nom = ? AND ligne=? AND camp=?",champion.nom,champion.ligne,champion.camp).fetch();
            if(liste.size() ==0){
                champion.preference=getMaxValuePreference(listeToFindMaxPrefValue)+1;
                champion.save();
            }
        }
    }

    public static List<Champion> getAllChampionsFromLane(String ligne,String camp){
        return Champion.find("ligne=? AND camp=?",ligne,camp).fetch();
    }

    public static void supprimer(Long idChampion) {
        Champion.delete("id=?",idChampion);
    }

    public static void supprimerPref(Long idPref) {
        Pref.delete("id=?",idPref);
    }

    public static void changerPreference(List<Long> idChampion,List<Integer> championPreference) {
        for (int i=0;i<idChampion.size();i++){
            Champion champion = Champion.findById(idChampion.get(i));
            champion.preference=championPreference.get(i);
            champion.save();
        }
    }

    public static List<Champion> getAllMyChampions(){
        List<Champion> listeChampions =Champion.find("camp=?","monCamp").fetch();
        return listeChampions;
    }

    public static List<Champion> getAllAdverseChampions(){
        List<Champion> listeChampions =Champion.find("camp=?","autreCamp").fetch();
        return listeChampions;
    }

    public static List<Pref> getAllPref(){
        List<Pref> listePref = Pref.findAll();
        return listePref;
    }

    public static List<Pref> getAllPrefOrdre(List<Integer> ordre){
        List<Pref> listePref = Pref.findAll();
        int taille = listePref.size();
        List<Pref> listePrefTriee= new ArrayList();
        Pref PrefTriee = new Pref();
        for (Pref pref : listePref) {
            switch (ordre.get(0)) {
                case 1:
                    PrefTriee.nomChampionTop = pref.nomChampionTop;
                    break;
                case 2:
                    PrefTriee.nomChampionTop = pref.nomChampionJungle;
                    break;
                case 3:
                    PrefTriee.nomChampionTop = pref.nomChampionMid;
                    break;
                case 4:
                    PrefTriee.nomChampionTop = pref.nomChampionAdc;
                    break;
                case 5:
                    PrefTriee.nomChampionTop = pref.nomChampionSupport;
                    break;
            }
            switch (ordre.get(1)) {
                case 1:
                    PrefTriee.nomChampionJungle = pref.nomChampionTop;
                    break;
                case 2:
                    PrefTriee.nomChampionJungle = pref.nomChampionJungle;
                    break;
                case 3:
                    PrefTriee.nomChampionJungle = pref.nomChampionMid;
                    break;
                case 4:
                    PrefTriee.nomChampionJungle = pref.nomChampionAdc;
                    break;
                case 5:
                    PrefTriee.nomChampionJungle = pref.nomChampionSupport;
                    break;
            }
            switch (ordre.get(2)) {
                case 1:
                    PrefTriee.nomChampionMid = pref.nomChampionTop;
                    break;
                case 2:
                    PrefTriee.nomChampionMid = pref.nomChampionJungle;
                    break;
                case 3:
                    PrefTriee.nomChampionMid = pref.nomChampionMid;
                    break;
                case 4:
                    PrefTriee.nomChampionMid = pref.nomChampionAdc;
                    break;
                case 5:
                    PrefTriee.nomChampionMid = pref.nomChampionSupport;
                    break;
            }
            switch (ordre.get(3)) {
                case 1:
                    PrefTriee.nomChampionAdc = pref.nomChampionTop;
                    break;
                case 2:
                    PrefTriee.nomChampionAdc = pref.nomChampionJungle;
                    break;
                case 3:
                    PrefTriee.nomChampionAdc = pref.nomChampionMid;
                    break;
                case 4:
                    PrefTriee.nomChampionAdc = pref.nomChampionAdc;
                    break;
                case 5:
                    PrefTriee.nomChampionAdc = pref.nomChampionSupport;
                    break;
            }
            switch (ordre.get(4)) {
                case 1:
                    PrefTriee.nomChampionSupport = pref.nomChampionTop;
                    break;
                case 2:
                    PrefTriee.nomChampionSupport = pref.nomChampionJungle;
                    break;
                case 3:
                    PrefTriee.nomChampionSupport = pref.nomChampionMid;
                    break;
                case 4:
                    PrefTriee.nomChampionSupport = pref.nomChampionAdc;
                    break;
                case 5:
                    PrefTriee.nomChampionSupport = pref.nomChampionSupport;
                    break;
            }
            listePrefTriee.add(PrefTriee);
            }
        return listePrefTriee;
    }

    public static List<Integer> getMaxValueLigne(List<Champion> listeChampion){
        List<Integer> longueurLigne = new ArrayList();
        List<Integer> resultat = new ArrayList();
        longueurLigne.add(listeChampion.stream().filter(champion -> champion.ligne.equals("Top")).collect(Collectors.toList()).size());
        longueurLigne.add(getMaxValuePreference(listeChampion.stream().filter(champion -> champion.ligne.equals("Top")).collect(Collectors.toList())));
        longueurLigne.add(listeChampion.stream().filter(champion -> champion.ligne.equals("Jungle")).collect(Collectors.toList()).size());
        longueurLigne.add(getMaxValuePreference(listeChampion.stream().filter(champion -> champion.ligne.equals("Jungle")).collect(Collectors.toList())));
        longueurLigne.add(listeChampion.stream().filter(champion -> champion.ligne.equals("Mid")).collect(Collectors.toList()).size());
        longueurLigne.add(getMaxValuePreference(listeChampion.stream().filter(champion -> champion.ligne.equals("Mid")).collect(Collectors.toList())));
        longueurLigne.add(listeChampion.stream().filter(champion -> champion.ligne.equals("Adc")).collect(Collectors.toList()).size());
        longueurLigne.add(getMaxValuePreference(listeChampion.stream().filter(champion -> champion.ligne.equals("Adc")).collect(Collectors.toList())));
        longueurLigne.add(listeChampion.stream().filter(champion -> champion.ligne.equals("Support")).collect(Collectors.toList()).size());
        longueurLigne.add(getMaxValuePreference(listeChampion.stream().filter(champion -> champion.ligne.equals("Support")).collect(Collectors.toList())));
        for(int i = 0; i <= 9; i++)
        {
            resultat.add(longueurLigne.get(i));
        }
        List<Integer> listeTronquee = new ArrayList();
        listeTronquee.add(longueurLigne.get(0));
        listeTronquee.add(longueurLigne.get(2));
        listeTronquee.add(longueurLigne.get(4));
        listeTronquee.add(longueurLigne.get(6));
        listeTronquee.add(longueurLigne.get(8));
        resultat.add(Collections.max(listeTronquee));
        return resultat;
    }

    public static int getMaxValuePreference(List<Champion> listeChampion) {
        List<Integer> preferences = new ArrayList();
        Integer max =0;
        for (int i = 0; i < listeChampion.size(); ++i) {
            preferences.add(listeChampion.get(i).preference);
        }
        if(preferences.size()!=0){
            max = Collections.max(preferences);
        }
        List<Integer> nonContenus = new ArrayList();
        for (int i = 1; i <=max; ++i) {
            if(!preferences.contains(i)){
                nonContenus.add(i);
            }
        }
        if(nonContenus.size()>0){
            return nonContenus.get(0)-1;
        }
        else{
            return max;
        }
    }

    public static List<Champion> trier(List<Champion> listeChampion){
        List<Champion> listeChampionTriee = new ArrayList();
        for(int i=1; i<=10; i++){
            for(int j=0; j<listeChampion.size(); j++){
                if(listeChampion.get(j).preference==i){
                    listeChampionTriee.add(listeChampion.get(j));
                }
            }
        }
        return listeChampionTriee;
    }

    public static List<Pool> trierParNote(List<Pool> listePool){
        List<Pool> listePoolTriee = new ArrayList();
        for(int i=100;i>-1;i--){
            for (Pool pool:listePool) {
                if(pool.note==i){
                    listePoolTriee.add(pool);
                }
            }
        }
        return listePoolTriee;
    }

    public static List<Pool> combinaisons(List<Champion> listeChampion, List<Champion> listeAdverseChampion, List<Integer> ordre){
        int note=1;
        final int TOP=0;
        final int JUNGLE=4;
        final int MID=1;
        final int ADC=2;
        final int SUPPORT=3;
        List<Champion> listeStatique1 = new ArrayList();
        listeStatique1=remplissageListe(listeChampion,ordre.get(0));
        List<Champion> listeStatique2 = new ArrayList();
        listeStatique2=remplissageListe(listeChampion,ordre.get(1));
        List<Champion> listeStatique3 = new ArrayList();
        listeStatique3=remplissageListe(listeChampion,ordre.get(2));
        List<Champion> listeStatique4 = new ArrayList();
        listeStatique4=remplissageListe(listeChampion,ordre.get(3));
        List<Champion> listeStatique5 = new ArrayList();
        listeStatique5=remplissageListe(listeChampion,ordre.get(4));
        List<Champion> liste1 = new ArrayList();
        liste1.addAll(listeStatique1);
        List<Champion> liste2 = new ArrayList();
        liste2.addAll(listeStatique2);
        List<Champion> liste3 = new ArrayList();
        liste3.addAll(listeStatique3);
        List<Champion> liste4 = new ArrayList();
        liste4.addAll(listeStatique4);
        List<Champion> liste5 = new ArrayList();
        liste5.addAll(listeStatique5);

        LinkedList<Champion> listeChampions = new LinkedList<>();
        LinkedList<Champion> listeTemp = new LinkedList<>();
        List<Pool> listePoolPossibles = new ArrayList();
        int j =1;
        int res =1;
        listeTemp.addAll(liste1);

        while (j < 6) {
            if (listeTemp.size() > 0) {
                Champion champManipule = listeTemp.pollFirst();
                listeChampions.add(champManipule);
                if (test(listeChampions,listeAdverseChampion,note)) {
                    switch (j) {
                        case 5:
//                            System.out.print("Resultat " + res + " : ");
//                            System.out.print(listeChampions.get(TOP).name + "(top), ");
//                            System.out.print(listeChampions.get(JUNGLE).name + "(jungle), ");
//                            System.out.print(listeChampions.get(MID).name + "(mid), ");
//                            System.out.print(listeChampions.get(ADC).name + "(adc), ");
//                            System.out.print(listeChampions.get(SUPPORT).name + "(support)");
//                            System.out.println("");
                            Pool pool=creerPool(listeChampions);
                            pool.note=notation(pool,ordre);
                            listePoolPossibles.add(pool);
                            listeChampions.removeLast();
                            res = res + 1;
                            break;
                        case 4:
                            listeTemp.clear();
                            liste4.remove(champManipule);
                            listeTemp.addAll(liste5);
                            j = j + 1;
                            break;
                        case 3:
                            listeTemp.clear();
                            liste3.remove(champManipule);
                            listeTemp.addAll(liste4);
                            j = j + 1;
                            break;
                        case 2:
                            listeTemp.clear();
                            liste2.remove(champManipule);
                            listeTemp.addAll(liste3);
                            j = j + 1;
                            break;
                        case 1:
                            listeTemp.clear();
                            liste1.remove(champManipule);
                            listeTemp.addAll(liste2);
                            j = j + 1;
                            break;
                    }
                }
                else {
                    listeChampions.removeLast();
                }
            }
            else {
                if (j == 1) {
                    //System.out.println("fin");
                    j=6;
                }
                else {
                    listeChampions.removeLast();
                    j = j - 1;
                    switch (j) {
                        case 4:
                            listeTemp.clear();
                            listeTemp.addAll(liste4);
                            break;
                        case 3:
                            listeTemp.clear();
                            liste4.addAll(listeStatique4);
                            listeTemp.addAll(liste3);
                            break;
                        case 2:
                            listeTemp.clear();
                            liste3.addAll(listeStatique3);
                            listeTemp.addAll(liste2);
                            break;
                        case 1:
                            listeTemp.clear();
                            liste2.addAll(listeStatique2);
                            listeTemp.addAll(liste1);
                            break;
                    }
                }
            }
        }
        return listePoolPossibles;
    }

    public static Pool creerPool(List<Champion> combinaison){
        Pool pool = new Pool();
        pool.championTop=combinaison.get(0);
        pool.championJungle=combinaison.get(1);
        pool.championMid=combinaison.get(2);
        pool.championAdc=combinaison.get(3);
        pool.championSupport=combinaison.get(4);
        pool.note=0;
        return pool;
    }

    public static Integer notation(Pool pool, List<Integer> ordre){
        Collection<Pref> listePref = ChampionService.getAllPrefOrdre(ordre);
        int note=0;
        int noteMax=0;
        for (Pref pref:listePref) {
            if(pref.nomChampionTop.equals(pool.championTop.nom)){
                note=note+1;
            }
            if(pref.nomChampionJungle.equals(pool.championJungle.nom)){
                note=note+1;
            }
            if(pref.nomChampionMid.equals(pool.championMid.nom)){
                note=note+1;
            }
            if(pref.nomChampionAdc.equals(pool.championAdc.nom)){
                note=note+1;
            }
            if(pref.nomChampionSupport.equals(pool.championSupport.nom)){
                note=note+1;
            }
            if(note>noteMax){
                noteMax=0;
                noteMax=noteMax+note;
            }
            note=0;
        }
        if(noteMax<1){
            return 0;
        }
        else{
            return noteMax-1;
        }
    }

    public static boolean test(LinkedList<Champion> listeChampion, List<Champion> listeAdverseChampion, Integer note){
        return true;
    }

    public static void ajouterPref(String nomChampionTop,String nomChampionJungle,String nomChampionMid,
                                   String nomChampionAdc,String nomChampionSupport){
        Pref pref = new Pref();
        pref.nomChampionTop=nomChampionTop;
        pref.nomChampionJungle=nomChampionJungle;
        pref.nomChampionMid=nomChampionMid;
        pref.nomChampionAdc=nomChampionAdc;
        pref.nomChampionSupport=nomChampionSupport;
        int compteur=0;
        if(pref.nomChampionTop.length()>0){
            compteur=compteur+1;
        }
        if(pref.nomChampionJungle.length()>0){
            compteur=compteur+1;
        }
        if(pref.nomChampionMid.length()>0){
            compteur=compteur+1;
        }
        if(pref.nomChampionAdc.length()>0){
            compteur=compteur+1;
        }
        if(pref.nomChampionSupport.length()>0){
            compteur=compteur+1;
        }
        if(compteur>1) {
            pref.save();
        }
    }

    public static List<Champion> remplissageListe(List<Champion> listeChampion, int ordre){
        List<Champion> listeStatique = new ArrayList();
        if(ordre==1){
            for (int i = 0; i < listeChampion.size(); i++) {
                if (listeChampion.get(i).ligne.equals("Top")) {
                    listeStatique.add(listeChampion.get(i));
                }
            }
        }
        if(ordre==2){
            for (int i = 0; i < listeChampion.size(); i++) {
                if (listeChampion.get(i).ligne.equals("Jungle")) {
                    listeStatique.add(listeChampion.get(i));
                }
            }
        }
        if(ordre==3){
            for (int i = 0; i < listeChampion.size(); i++) {
                if (listeChampion.get(i).ligne.equals("Mid")) {
                    listeStatique.add(listeChampion.get(i));
                }
            }
        }
        if(ordre==4){
            for (int i = 0; i < listeChampion.size(); i++) {
                if (listeChampion.get(i).ligne.equals("Adc")) {
                    listeStatique.add(listeChampion.get(i));
                }
            }
        }
        if(ordre==5){
            for (int i = 0; i < listeChampion.size(); i++) {
                if (listeChampion.get(i).ligne.equals("Support")) {
                    listeStatique.add(listeChampion.get(i));
                }
            }
        }
        return listeStatique;
    }
}
