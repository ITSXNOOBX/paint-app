package utils;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;

/*
 * @version 1.1.0
 */
public class NotifyUtils {
	private static Boolean supported = SystemTray.isSupported();
	private static Image image = Toolkit.getDefaultToolkit().getImage(NotifyUtils.class.getResource("/assets/painballogo.png"));
	private static SystemTray tray = SystemTray.getSystemTray();
	private static String cacheMsg;
	
    /**
     * * This function send a notification using java windows notifications.
     * @Param message : String
     * @Param title : String
     * @Param type : MessageType
     *
     * @output status : Boolean
     */
	private static Boolean showTray(String message, String title, MessageType type) {
		if (!supported) return false;
		if (message == null) return false;
		if (title == null) title = "paint-app";
		if (message.equals(cacheMsg)) return false;
		TrayIcon trayIcon = new TrayIcon(image, "paint-app");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return false;
		}

        trayIcon.displayMessage(title, message, type);
        cacheMsg = message;
        return true;

	}
	
    /**
     * * This function send a success windows tray notification.
     * @Param message : String
     * @Param title : String
     *
     * @output status : Boolean
     */
	public static Boolean succeed(String message, String title) {
		return showTray(message, title, MessageType.INFO);
	}
	
    /**
     * * This function send a warn windows tray notification.
     * @Param message : String
     * @Param title : String
     *
     * @output status : Boolean
     */
	public static Boolean warn(String message, String title) {
		return showTray(message, title, MessageType.WARNING);
	}
	
    /**
     * * This function send a error windows tray notification.
     * 
     * @Param message : String
     * @Param title : String
     *
     * @output status : Boolean
     */
	public static Boolean error(String message, String title) {
		return showTray(message, title, MessageType.ERROR);
	}
}
