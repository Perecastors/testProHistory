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

    public static List<Pool> combinaisons(List<Champion> listeChampion, List<Champion> listeAdverseChampion, int ordre){
        int note=1;
        final int TOP=0;
        final int JUNGLE=4;
        final int MID=1;
        final int ADC=2;
        final int SUPPORT=3;
        List<Champion> listeStatique1 = new ArrayList();
        List<Champion> listeStatique2 = new ArrayList();
        List<Champion> listeStatique3 = new ArrayList();
        List<Champion> listeStatique4 = new ArrayList();
        List<Champion> listeStatique5 = new ArrayList();
        List<Champion> liste1 = new ArrayList();
        List<Champion> liste2 = new ArrayList();
        List<Champion> liste3 = new ArrayList();
        List<Champion> liste4 = new ArrayList();
        List<Champion> liste5 = new ArrayList();
        switch (ordre) {
            case 1:
                for(int i=0;i<listeChampion.size();i++){
                    if(listeChampion.get(i).ligne.equals("Top")){
                        listeStatique1.add(listeChampion.get(i));
                    }
                    if(listeChampion.get(i).ligne.equals("Jungle")){
                        listeStatique2.add(listeChampion.get(i));
                    }
                    if(listeChampion.get(i).ligne.equals("Mid")){
                        listeStatique3.add(listeChampion.get(i));
                    }
                    if(listeChampion.get(i).ligne.equals("Adc")){
                        listeStatique4.add(listeChampion.get(i));
                    }
                    if(listeChampion.get(i).ligne.equals("Support")){
                        listeStatique5.add(listeChampion.get(i));
                    }
                }
                liste1.addAll(listeStatique1);
                liste2.addAll(listeStatique2);
                liste3.addAll(listeStatique3);
                liste4.addAll(listeStatique4);
                liste5.addAll(listeStatique5);
                break;
            case 2:
                for(int i=0;i<listeChampion.size();i++){
                    if(listeChampion.get(i).ligne.equals("Jungle")){
                        listeStatique1.add(listeChampion.get(i));
                    }
                    if(listeChampion.get(i).ligne.equals("Top")){
                        listeStatique2.add(listeChampion.get(i));
                    }
                    if(listeChampion.get(i).ligne.equals("Mid")){
                        listeStatique3.add(listeChampion.get(i));
                    }
                    if(listeChampion.get(i).ligne.equals("Adc")){
                        listeStatique4.add(listeChampion.get(i));
                    }
                    if(listeChampion.get(i).ligne.equals("Support")){
                        listeStatique5.add(listeChampion.get(i));
                    }
                }
                liste1.addAll(listeStatique1);
                liste2.addAll(listeStatique2);
                liste3.addAll(listeStatique3);
                liste4.addAll(listeStatique4);
                liste5.addAll(listeStatique5);
                break;
        }
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
                            pool.note=notation(pool);
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

    public static Integer notation(Pool pool){
        Collection<Pref> listePref = ChampionService.getAllPref();
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
}
