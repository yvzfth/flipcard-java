package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Write {
    /** Add new text line by line to the given path */
    public static void add(String path, String text) throws IOException {
        FileWriter writer = new FileWriter(path);
        writer.write(text + "\n");
        writer.close();
    }

    /** Add front and back cards text to the file */
    public static void add(String path, String front, String back) throws IOException {
        FileWriter writer = new FileWriter(path, true);
        writer.append(front + "|" + back + "\n");
        writer.close();
    }

    /** Add user's information to users file */
    public static void add(String path, String email, String username, String password) throws IOException {
        FileWriter writer = new FileWriter(path, true);
        writer.append(email + "|" + username + "|" + password + "\n");
        writer.close();
    }

    /** Write user information to the userInfo file */
    public static void add(String path, String email, String username, String password, String userIcon)
            throws IOException {

        FileWriter writer = new FileWriter(path, true);
        writer.append(email + "|" + username + "|" + password + "|" + userIcon +
                "\n");
        writer.close();
    }

    /** Add the username in the post's '-likes' file */
    public static void addLike(String username, String title, String senderUsername) throws IOException {
        FileWriter writer = new FileWriter(HomePageNavigation.PUBLIC_PATH +
                senderUsername + "/" + title + "-likes.txt",
                true);
        writer.append(username + "\n");
        writer.close();
    }

    /** Add liked post path to 'liked' file */
    public static void addToLiked(String username, String title, String senderUsername) throws IOException {
        FileWriter writer = new FileWriter(HomePageNavigation.DATA_PATH + username +
                "/liked.txt", true);
        writer.append(HomePageNavigation.PUBLIC_PATH + senderUsername + "/" + title +
                ".txt" + "\n");
        writer.close();
    }

    /** Remove liked path from 'liked' file */
    public static void removeFromLiked(String username, String title, String senderUsername) throws IOException {
        String likedListPath = HomePageNavigation.DATA_PATH + username +
                "/liked.txt";
        String removePath = HomePageNavigation.PUBLIC_PATH + senderUsername + "/" +
                title + ".txt";
        ArrayList<String> likedLists = Read.getLikedLists(username);

        if (likedLists.contains(removePath)) {
            likedLists.remove(removePath);
        }

        deleteContent(likedListPath);
        FileWriter writer = new FileWriter(likedListPath, true);
        for (int i = 0; i < likedLists.size(); i++) {
            writer.append(likedLists.get(i) + "\n");
        }
        writer.close();
    }

    /** Remove username from '-likes' file */
    public static void removeLike(String username, String title, String senderUsername) throws IOException {

        String filePath = HomePageNavigation.PUBLIC_PATH + senderUsername + "/" + title + "-likes.txt";
        ArrayList<String> likesList = Read.getLikesList(title, senderUsername);

        if (likesList.contains(username)) {
            likesList.remove(username);
        }

        deleteContent(filePath);
        FileWriter writer = new FileWriter(filePath, true);
        for (int i = 0; i < likesList.size(); i++) {
            writer.append(likesList.get(i) + "\n");
        }
        writer.close();

    }

    /** share library file to public directory */
    public static void shareFile(String username, String title) throws IOException {

        String filePath = HomePageNavigation.DATA_PATH + username + "/" + title + ".txt";
        String filePathPublic = HomePageNavigation.PUBLIC_PATH + username + "/" + title + ".txt";
        // Instantiating the Scanner class to read the file
        Scanner sc = new Scanner(new File(filePath));
        // instantiating the StringBuffer class
        StringBuffer buffer = new StringBuffer();
        // Reading lines of the file and appending them to StringBuffer
        while (sc.hasNextLine()) {
            buffer.append(sc.nextLine() + System.lineSeparator());
        }
        String fileContents = buffer.toString();

        // closing the Scanner object
        sc.close();
        String oldLine = Read.getTxtFirstLine(filePath);
        String newLine = oldLine + "|" + username;
        // Replacing the old line with new line
        fileContents = fileContents.replace(oldLine, newLine);
        try (// instantiating the FileWriter class
                FileWriter writer = new FileWriter(filePathPublic)) {

            writer.append(fileContents);
            writer.flush();
            writer.close();
        }

    }

    /** delete all content from the file */
    public static void deleteContent(String path) {
        try (FileWriter writer = new FileWriter(path)) {
            writer.write("");
            writer.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

}