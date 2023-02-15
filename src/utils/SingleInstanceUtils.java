package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/*
 * @version 1.1.3
 */
public class SingleInstanceUtils {
    static File file;
    static FileChannel fileChannel;
    static FileLock lock;
    static boolean running = false;
	
    // https://stackoverflow.com/a/20015771
    
    /**
     * * This will check if the app-client.lock is locked or not to check if the app is running.
     * @output isrunning : Boolean
     */
    public static boolean checkIfAlreadyRunning() {
        file = new File(FileUtils.cache_root + "app-client.lock");
        if (!file.exists()) {
            try {
				file.createNewFile();
				running = false;
			} catch (IOException e) {
				running = true;
			}
        } else {
            file.delete();
        }

        try {
			fileChannel = new RandomAccessFile(file, "rw").getChannel();
	        lock = fileChannel.tryLock();

	        if (lock == null) {
	            fileChannel.close();
	            return true;
	        }
		} catch (IOException e) {
			running = false;
		}

        return running;
    }
    
    /**
     * * This function will unlock the file indicating the program is no longer running.
     */
    public static void unlockFile() {
        try {
            if (lock != null)
                lock.release();
            fileChannel.close();
            file.delete();
            running = false;
        } catch (IOException e) {
            // e.printStackTrace();
        }
    }
}
