package services;


import models.HistoryGame2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by CinnaLocal on 23/10/2016.
 */
public class ParamService {

    static String root = System.getProperty("user.dir");
    static String[] tabLane = new String[]{"bTop","bJungle","bMid","bAdc","bSupport","rTop","rJungle","rMid","rAdc","rSupport","region"};
    public static void initDropDownList() {
        for(String lane:tabLane) {
            try {
                try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(root + "/app/views/tags/dropDown" + lane + ".html"))) {
                    writer.flush();
                    writer.write(getDistinctChampionOnLane(lane));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getDistinctChampionOnLane(String lane) {
        List<String> hs = HistoryGame2.find("SELECT DISTINCT " + lane + " from " + HistoryGame2.class.getName() + " ORDER BY " + lane).fetch();
        String createHtml = new String();
        createHtml += "<option value=''>" + "--------" + "</option>\n";
        for (String s : hs) {
            createHtml += "<option value='" + s + "'>" + s + "</option>\n";
        }
        return createHtml;
    }
}
