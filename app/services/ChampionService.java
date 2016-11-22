package services;

import models.Champion;

import java.util.List;

/**
 * Created by Formation on 26/10/2016.
 */
public class ChampionService {

    public static void enregistrer(Champion champion) {
        //quelles sont mes criteres pour ajouter une ligne ??
        if(!champion.nom.isEmpty()) {// le champ nom pas vide
            // et il faut que le couple (nom,ligne) n'existe pas
            List<Champion> liste = Champion.find("nom = ? AND ligne=? AND camp=?",champion.nom,champion.ligne,champion.camp).fetch();
            if(liste.size() ==0){
                champion.save();
            }
        }
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


}
