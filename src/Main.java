import java.io.File;

import gui.MainForm;
import gui.RegisterTeam;
import person.coach;
import person.player;
import teams.team;
import utils.DataUtils;
import utils.FileUtils;
import utils.NotifyUtils;
import utils.SingleInstanceUtils;

/**
 * 
 */

/**
 * @author admin
 *
 */
public class Main {

	
    public static Boolean first_run = false;
    public static Boolean isrunning = false;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       @SuppressWarnings("unused")
		Boolean developer = false;
       	isrunning = SingleInstanceUtils.checkIfAlreadyRunning();

        if(args.length != 0 && args[0].equals("developer")) {
//            System.out.println("Running app as developer mode!");
            FileUtils.logToFile("Developer parameter found, running app as developer.");
            developer = true;
            isrunning = false;
        }
        
        if (isrunning) {
        	NotifyUtils.error("Another instance is running. Closing automatically this one.", "ERROR_SINGLE_INSTANCE_APP");
        	System.exit(1152); // ERROR_SINGLE_INSTANCE_APP, 1152 (0x480) // https://learn.microsoft.com/en-us/windows/win32/debug/system-error-codes--1000-1299-#ERROR_SINGLE_INSTANCE_APP
        }
        

        File app_folder = new File(FileUtils.root);
        if (!app_folder.isDirectory()){
            app_folder.mkdirs();
            new File(FileUtils.logs_root).mkdirs();
            new File(FileUtils.cache_root).mkdirs();
            first_run = true;
            FileUtils.logToFile("Folders created successfully.");
        }
        
        if (DataUtils.read()) {
        	 FileUtils.logToFile("Successfully read all the data stored.");
        } else {
        	FileUtils.logToFile("Failed to read data from previous sessions.");
        }
		
		MainForm.main(args);
//		RegisterTeam.main(args);
        FileUtils.logToFile("Program succesfully executed.");   
        
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // place your code here
            	
            	FileUtils.logToFile("Program is closing, running pre closing actions");
            	SingleInstanceUtils.unlockFile();
            	if(isrunning) return;
            	
                if (DataUtils.write()) {
               	 	FileUtils.logToFile("Successfully saved all data in this session.");
                } else {
                	FileUtils.logToFile("Could not store all the data from this session.");
                }
            }

        });
	}

}
