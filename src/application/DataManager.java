package application;

import java.io.File;
import java.io.IOException;

public class DataManager {
    /** create folder that has a name signed up */
    public static void createFolder(String username) {
        File folder = new File(HomePageNavigation.DATA_PATH + username);
        // Creating a folder using mkdirs() method
        if (!folder.exists()) {

            folder.mkdirs();

        }
    }

    /** create public folder that has a name signed up */
    public static void createPublicFolder(String username) {
        File folder = new File(HomePageNavigation.PUBLIC_PATH + username);
        // Creating a folder using mkdirs() method
        if (!folder.exists()) {

            folder.mkdirs();

        }
    }

    /** create file that has a name of shared list's title in public folder */
    public static void createPublicFile(String username, String title) {
        File file = new File(HomePageNavigation.PUBLIC_PATH + username + "/" + title + ".txt");

        try {
            // creates a new file
            file.createNewFile();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** create a file that has a name of created list's title */
    public static void createFile(String username, String title) {
        // initialize File object and passing path as argument
        File file = new File(HomePageNavigation.DATA_PATH + username + "/" + title + ".txt");

        try {
            // creates a new file
            file.createNewFile();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** returns true if list in local directory also in public directory */
    public static boolean isFileShared(String username, String title) {
        File file = new File(HomePageNavigation.PUBLIC_PATH + username + "/" + title + ".txt");
        return file.exists();
    }

    /** delete edited file from public directory */
    public static void deleteEditedFileInPublic(String username, String title) {
        File file = new File(HomePageNavigation.PUBLIC_PATH + username + "/" + title + ".txt");
        file.delete();
    }

    /** delete shared file from public directory */
    public static void deleteSharedFile(String username, String title) {
        File file = new File(HomePageNavigation.PUBLIC_PATH + username + "/" + title + ".txt");
        File likesFile = new File(HomePageNavigation.PUBLIC_PATH + username + "/" + title + "-likes.txt");
        file.delete();
        likesFile.delete();
    }

    /** return true if file is exists in local directory */
    public static boolean isFileExists(String username, String title) {
        File file = new File(HomePageNavigation.DATA_PATH + username + "/" + title + ".txt");
        return file.exists();
    }

    /** return true if file is exists in any directory */
    public static boolean isFileExists(String path) {
        File file = new File(path);
        return file.exists();
    }

    /** enter new name and old name then rename it to new name in local directory */
    public static void renameFileTo(String username, String oldTitle, String newTitle) throws IOException {
        // File (or directory) with old name
        File file = new File(HomePageNavigation.DATA_PATH + username + "/" + oldTitle + ".txt");

        // File (or directory) with new name
        File file2 = new File(HomePageNavigation.DATA_PATH + username + "/" + newTitle + ".txt");

        // Rename file (or directory)
        file.renameTo(file2);
    }

    /**
     * enter new name and old name then rename it to new name in public directory
     */
    public static void renamePublicFileTo(String username, String oldTitle, String newTitle) {
        // File (or directory) with old name
        File file = new File(HomePageNavigation.PUBLIC_PATH + username + "/" + oldTitle + ".txt");

        // File (or directory) with new name
        File file2 = new File(HomePageNavigation.PUBLIC_PATH + username + "/" + newTitle + ".txt");

        // Rename file (or directory)

        File likesFile = new File(HomePageNavigation.PUBLIC_PATH + username + "/" + oldTitle + "-likes.txt");
        File likesFile2 = new File(HomePageNavigation.PUBLIC_PATH + username + "/" + newTitle + "-likes.txt");
        file.renameTo(file2);
        likesFile.renameTo(likesFile2);
    }

    /** delete file from local directory */
    public static void deleteFile(String username, String filename) {
        File file = new File(HomePageNavigation.DATA_PATH + username + "/" + filename + ".txt");
        file.delete();
    }
}
