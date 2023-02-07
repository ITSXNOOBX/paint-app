package utils;

import person.coach;
import person.player;
import teams.team;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import matches.match;

public class FileUtils {

    String formattedDate = new SimpleDateFormat("HH:mm:ss").format(new Date());
    public static final String appdata = System.getenv("APPDATA");
    public static final String root = appdata + "/paint-app/";
    public static final String logs_root = root + "logs/";
    public static final String cache_root = root + "cache/";
    
    public static final String path_log = logs_root + new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date()) + "_paint-app.log";
    public static final String path_teams = root + "teams_data.bin";
    public static final String path_players = root + "players_data.bin";
    public static final String path_matches = root + "match_data.bin";
    public static final String path_coach = root + "coach_data.bin";


    public static boolean logToFile(String text, Boolean... supressConsole) {   
    	String prefix = "[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] ";
    	if (!(supressConsole.length > 0
    			? supressConsole[0] : false)) {
    		System.out.println(prefix + text);
    	}
		try {
			FileWriter fileWriter = new FileWriter(path_log, true);
			PrintWriter opened = new PrintWriter(fileWriter);
			
			opened.println(prefix + text);
			
			opened.close();
		} catch (IOException ioe) {
//			ioe.printStackTrace();
			return false;
		}
        return true;
    }
    public static Boolean clearDirectory(File dir) {
    	for(File file : dir.listFiles()) 
    	    if (!file.isDirectory()) 
    	        file.delete();
    	return true;
    }
    
    public static boolean writeObjectToFile(ArrayList<?> obj) {
        Boolean status = true;
    	String path = "";
        if (obj == null || obj.size() == 0) {
        	return false;
        } else if(obj.get(0) instanceof coach) {
        	path = path_coach;
        } else if(obj.get(0) instanceof player) {
            path = path_players;
        } else if(obj.get(0) instanceof match) {
        	path = path_matches;
        } else if(obj.get(0) instanceof team) {
            path = path_teams;
        } else {
        	status = false;
        }

        try {
        	FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
			
            for (int i = 0; i < obj.size(); i++) {
				oos.writeObject(obj.get(i));
			}
            
            oos.close();
            fos.close();
        } catch (IOException e) {
        	// e.printStackTrace();
        	status = false;
        }
        return status;
    }

    public static Object readObjectsFromFile(String obj_type) {
        String path = "";
        if(obj_type.equals("coach")) {
            path = path_coach;
        } else if(obj_type.equals("player")) {
            path = path_players;
        } else if(obj_type.equals("match")) {
        	path = path_matches;
        } else if(obj_type.equals("team")) {
            path = path_teams;
        } else {
        	return null;
        }

        ArrayList<Object> temp = new ArrayList<Object>();
        try {
            FileInputStream fis =new FileInputStream(path);
            ObjectInputStream ois =new ObjectInputStream (fis);

            Object obj;
            while ((obj = ois.readObject()) != null) {
                temp.add(obj);
            }
            fis.close();
            ois.close();
        } catch (ClassNotFoundException d) {
            // TODO Auto-generated catch block
        	// d.printStackTrace();
        } catch (InvalidClassException d) {
            // TODO Auto-generated catch block
        	File corrupted = new File(path);
    		logToFile("Corrupted file: '" + corrupted.getAbsolutePath() + "'");
    		if (corrupted.delete()) {
    			logToFile("Successfully removed corrupted file.");
    		} else {
    			logToFile("Could not delete corrupted file.");
				NotifyUtils.warn("Could not delete corrupted file. It be overwritten when closing the program. Spect data loss.", "App Error.");
        		corrupted.deleteOnExit();
    		}
        } catch (IOException d) {
            // TODO Auto-generated catch block
        	// d.printStackTrace();
        	// return null;
        }
        return temp;
    }
}
