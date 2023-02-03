package utils;

import java.util.ArrayList;

import person.*;
import teams.team;

public class DataUtils {
	public static ArrayList<player> players = new ArrayList<player>();
	public static ArrayList<coach> coaches = new ArrayList<coach>();
	public static ArrayList<team> teams = new ArrayList<team>();
	
	@SuppressWarnings({ "null", "unchecked" })
	public static Boolean read() {
		Boolean status = true;
		ArrayList<player> temp_players = (ArrayList<player>) FileUtils.readObjectsFromFile("player");
		ArrayList<coach> temp_coaches = (ArrayList<coach>) FileUtils.readObjectsFromFile("coach");
		ArrayList<team> temp_teams = (ArrayList<team>) FileUtils.readObjectsFromFile("team");

		if(temp_players != null || temp_players.size() != 0) 
			players.addAll(temp_players);
		else status = false;
		if(temp_coaches != null || temp_coaches.size() != 0) 
			coaches.addAll(temp_coaches);
		else status = false;
		if(temp_teams != null || temp_teams.size() != 0) 
			teams.addAll(temp_teams);
		else status = false;
		
		return status;
	}
	
	public static Boolean write() {
		Boolean status = true;
		if (players.size() != 0 && !FileUtils.writeObjectToFile(players)) status = false;
		if (coaches.size() != 0 && !FileUtils.writeObjectToFile(coaches)) status = false;
		if (teams.size() != 0 && !FileUtils.writeObjectToFile(teams)) status = false;

		return status;
	}
}
