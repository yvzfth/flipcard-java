package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.io.BufferedInputStream;
import java.io.FilenameFilter;

public class Read {
    /**
     * read email from file and compare it with entered email
     * and return true if email matches entered email.
     */
    public static boolean isValidEmail(String path, String email) throws IOException {

        FileReader fileReader = new FileReader(path);
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(email) && String.valueOf(line.split("\\|")[0]).equals(email)) {

                    return true;
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
        return false;
    }

    /**
     * read username from file and compare it with entered username
     * and return true if username matches entered username.
     */
    public static boolean isValidUsername(String path, String username) throws IOException {

        FileReader fileReader = new FileReader(path);
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(username) && String.valueOf(line.split("\\|")[1]).equals(username)) {

                    return true;
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    /**
     * read password from file and compare it with entered password
     * and return true if password matches entered password.
     */
    public static boolean isValidPassword(String path, String username, String password) throws IOException {
        FileReader fileReader = new FileReader(path);
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String ln;

            while ((ln = bufferedReader.readLine()) != null) {

                if (isValidUsername(path, username)
                        && String.valueOf(ln.split("\\|")[2]).equals(password)) {

                    return true;
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    /** returns first line from local userInfo */
    public static String getFirstLine(String username) throws FileNotFoundException, IOException {
        String line;
        try (BufferedReader br = new BufferedReader(
                new FileReader(HomePageNavigation.DATA_PATH + username + "/" + HomePageNavigation.USER_INFO_PATH))) {

            line = br.readLine();

        }
        return line;
    }

    /** returns first line from any public or local file */
    public static String getTxtFirstLine(String txtRelativePath) throws FileNotFoundException, IOException {
        String line;
        try (BufferedReader br = new BufferedReader(
                new FileReader(txtRelativePath))) {

            line = br.readLine();
        }
        return line;
    }

    /**
     * returns username from public text file
     * enter full relative path
     */
    public static String getUsername(String txtRelativePath) throws FileNotFoundException, IOException {
        return getTxtFirstLine(txtRelativePath).split("\\|")[2];
    }

    /** returns icon path from userInfo file */
    public static String getIconPath(String username) throws IOException {
        try {
            return getFirstLine(username).split("\\|")[3];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /** returns icon path from any shared or local file */
    public static String getCategory(String path) throws FileNotFoundException,
            IOException {
        return getTxtFirstLine(path).split("\\|")[1].split("\\.")[0];

    }

    /** return relative path of category image */
    public static String getCategoryImagePath(String path) {
        try {
            return HomePageNavigation.PHOTOS_PATH +
                    getTxtFirstLine(path).split("\\|")[1];
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return null;
    }

    /** returns title from any shared or local file */
    public static String getTitle(String path) {
        try {
            return getTxtFirstLine(path).split("\\|")[0];
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return null;
    }

    /** returns the list of file paths from user's local directory */
    public static ArrayList<String> getAllFilePaths(String username) {
        File folder = new File(HomePageNavigation.DATA_PATH + username + "/");
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> fileNames = new ArrayList<String>();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()
                    && (!listOfFiles[i].getName().equals(HomePageNavigation.USER_INFO_PATH)
                            && !listOfFiles[i].getName().equals(HomePageNavigation.LIKED_PATH))) {
                fileNames.add(HomePageNavigation.DATA_PATH + username + "/" +
                        listOfFiles[i].getName());
            }
        }

        return fileNames;
    }

    /** returns all title names from user's local directory */
    public static ArrayList<String> getAllTitles(String path) {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> fileNames = new ArrayList<String>();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile() &&
                    !listOfFiles[i].getName().equals(HomePageNavigation.USER_INFO_PATH) &&
                    !listOfFiles[i].getName().equals(HomePageNavigation.LIKED_PATH)) {
                fileNames.add(listOfFiles[i].getName().split("\\.txt")[0]);
            }
        }
        return fileNames;
    }

    /** return number of cards in list from list's text file */
    public static int countCards(String path) {

        int lines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while (reader.readLine() != null)
                lines++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines - 1;

    }

    /** returns all user folder names */
    public static String[] getAllPublicFolderNames() {
        File file = new File(HomePageNavigation.PUBLIC_PATH);
        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
        return directories;
    }

    /** returns shared list names from public directory except likes files */
    public static ArrayList<String> getAllPublicFileNames() {
        ArrayList<String> fileNames = new ArrayList<String>();
        for (String folder : getAllPublicFolderNames()) {
            File[] listOfFiles = new File(HomePageNavigation.PUBLIC_PATH + folder).listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile() && !listOfFiles[i].getName().contains("-likes.txt")) {
                    fileNames.add(listOfFiles[i].getName());
                }
            }

        }
        return fileNames;
    }

    /** return all public list names */
    public static ArrayList<String> getAllPublicTitle() {
        ArrayList<String> titles = new ArrayList<String>();
        for (String fileName : getAllPublicFileNames()) {

            titles.add(fileName.split(".txt")[0]);
        }
        return titles;
    }

    /** returns all public file paths except likes file */
    public static ArrayList<String> getAllPublicTextFilePaths() {
        ArrayList<String> filePaths = new ArrayList<String>();
        try {

            for (String folder : getAllPublicFolderNames()) {
                File[] listOfFiles = new File(HomePageNavigation.PUBLIC_PATH + folder).listFiles();

                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile() && !listOfFiles[i].getName().contains("-likes.txt")) {
                        filePaths.add(HomePageNavigation.PUBLIC_PATH + folder + "/" + listOfFiles[i].getName());
                    }
                }

            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return filePaths;

    }

    /** returns front cards arraylist from any public or local file */
    public static ArrayList<String> getAllFrontCards(String path) throws FileNotFoundException {
        int numLine = countCards(path);
        ArrayList<String> frontCards = new ArrayList<String>();
        String front;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            try {
                br.readLine();
                for (int i = 0; i < numLine; i++) {
                    front = br.readLine().split("\\|")[0];
                    frontCards.add(front);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {

            e.printStackTrace();
        }

        return frontCards;
    }

    /** returns back cards arraylist from any public or local file */
    public static ArrayList<String> getAllBackCards(String path) throws FileNotFoundException {
        int numLine = countCards(path);
        ArrayList<String> backCards = new ArrayList<String>();
        String back;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            try {
                br.readLine();
                for (int i = 0; i < numLine; i++) {
                    back = br.readLine().split("\\|")[1];
                    backCards.add(back);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {

            e.printStackTrace();
        }

        return backCards;
    }

    /** returns all like username */
    public static ArrayList<String> getLikesList(String title, String senderUsername) throws IOException {
        BufferedReader br = new BufferedReader(
                new FileReader(HomePageNavigation.PUBLIC_PATH + senderUsername + "/" + title
                        + "-likes.txt"));
        ArrayList<String> lines = new ArrayList<String>();
        String line;
        while ((line = br.readLine()) != null) {
            if (!line.equals("\n"))
                lines.add(line);

        }
        br.close();
        return lines;
    }

    /** returns a list of liked post relative paths */
    public static ArrayList<String> getLikedLists(String username) throws IOException {
        String filePath = HomePageNavigation.DATA_PATH + username + "/liked.txt";
        BufferedReader br = new BufferedReader(
                new FileReader(filePath));
        ArrayList<String> lines = new ArrayList<String>();
        String line;
        while ((line = br.readLine()) != null) {
            if (!line.equals("\n") && DataManager.isFileExists(line))
                lines.add(line);

        }
        br.close();
        return lines;
    }

    /**
     * returns number of likes from reading the -likes file in public username
     * directory
     */
    public static int countLikes(String senderUsername, String title) {

        int lines = 0;

        try (InputStream is = new BufferedInputStream(
                new FileInputStream(
                        HomePageNavigation.PUBLIC_PATH + senderUsername + "/" + title + "-likes.txt"))) {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean endsWithoutNewLine = false;
            while ((readChars = is.read(c)) != -1) {
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n')
                        ++count;
                }
                endsWithoutNewLine = (c[readChars - 1] != '\n');
            }
            if (endsWithoutNewLine) {
                ++count;
            }
            lines = count;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    /**
     * returns true if current user liked the post that another user posted before
     */
    public static boolean isActiveUserLiked(String username, String title, String senderUsername) throws IOException {

        try (BufferedReader file = new BufferedReader(
                new FileReader(HomePageNavigation.PUBLIC_PATH + senderUsername + "/" + title + "-likes.txt"))) {
            String line;

            while ((line = file.readLine()) != null) {

                if (line != null && line.equals(username)) {

                    return true;

                }
            }
        }

        return false;
    }

    /** returns all text from file */
    public static String getAllContent(String path) {
        try {

            FileReader fileReader = new FileReader(path);
            int i;
            String content = "";
            while ((i = fileReader.read()) != -1) {
                content += Character.valueOf((char) i);
            }
            fileReader.close();
            return content;
        } catch (IOException e) {

            e.printStackTrace();
        }
        return null;
    }

}