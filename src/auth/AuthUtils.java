package auth;

import java.io.File;
import java.util.ArrayList;
import java.util.Base64;

import utils.FileUtils;

public class AuthUtils {
	public static ArrayList<user> users = new ArrayList<user>();
	public static user logged_user;
	
	
	public static String readCred() {
		String data = FileUtils.readFromFile(FileUtils.auth_root + "last_user.save");
		if (data == null || data.equals("")) return null;
		return new String(Base64.getDecoder().decode(data));
	}
	
	public static Boolean writeCred(Boolean store, String username, String password) {
		Boolean status = true;
		if (store) {			
			byte[] data = Base64.getEncoder().encode((username +":"+password).getBytes());
			status = FileUtils.writeToFile(FileUtils.auth_root + "last_user.save", new String(data), false);
		} else {
			status = new File(FileUtils.auth_root + "last_user.save").delete();
		}
		return status;
	}
	
	public static Boolean read() {
		Boolean status = true;
		ArrayList<user> temp_users = (ArrayList<user>) FileUtils.readObjectsFromFile("user");

		if(temp_users != null || temp_users.size() != 0) 
			users.addAll(temp_users);
		else status = false;
		return status;
	}
	
	public static Boolean save() {
		Boolean status = true;
		if (users.size() != 0 && !FileUtils.writeObjectToFile(users)) status = false;
		return status;
	}
	

}
