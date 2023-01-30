import java.io.File;

import gui.MainForm;
import utils.FileUtils;

public class Main {

    public static Boolean first_run = false;

    public static void main(String[] args) {
        boolean developer = false;

        if(args.length != 0 && args[0].equals("developer")) {
            System.out.println("Running app as developer mode!");
            developer = true;
        }

        File app_folder = new File(FileUtils.root);
        if (!app_folder.isDirectory()){
            app_folder.mkdirs();
            first_run = true;
            System.out.println("Created folder.");
        }

        // JOptionPane.showMessageDialog(null, "Ez duzu talderik autatu/idatzi ezabatzeko.", "Error!", JOptionPane.ERROR_MESSAGE);
        MainForm.main(args);
    }
}