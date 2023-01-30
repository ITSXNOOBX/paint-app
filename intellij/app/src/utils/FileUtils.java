package utils;

import person.coach;
import person.player;
import teams.team;

import java.io.*;
import java.util.ArrayList;

public class FileUtils {

    public static final String appdata = System.getenv("APPDATA");
    public static final String root = appdata + "/paint-app";
    public static final String path_teams = root + "/teams_data.bin";
    public static final String path_players = root + "/players_data.bin";
    public static final String path_coach = root + "/coach_data.bin";



    public boolean writeObjectToFile(Object obj) {
        String path = "";
        if(obj instanceof coach) {
             path = path_teams;
        } else if(obj instanceof player) {
            path = path_players;
        } else if(obj instanceof team) {
            path = path_coach;
        }

        try {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
            oos.close();
            fos.close();
        } catch (IOException e) {
            //e.printStackTrace();
            return false;
        }
        return true;
    }

    public Object readObjectsFromFile(String obj_type) {
        String path = "";
        if(obj_type.equals("coach")) {
            path = path_teams;
        } else if(obj_type.equals("player")) {
            path = path_players;
        } else if(obj_type.equals("team")) {
            path = path_coach;
        }

        ArrayList<Object> temp = new ArrayList<Object>();
        try {
            FileInputStream fis=new FileInputStream(path);
            ObjectInputStream ois=new ObjectInputStream (fis);

            Object obj;
            while ((obj = ois.readObject()) != null) {
                temp.add(obj);
            }
            fis.close();
            ois.close();

        } catch (ClassNotFoundException d) {
            // TODO Auto-generated catch block
            //d.printStackTrace();
        } catch (IOException d) {
            // TODO Auto-generated catch block
            //d.printStackTrace();
        }
        return temp;
    }
}
