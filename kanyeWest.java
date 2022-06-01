/*------------------------------------------------------------------------------

File:   kanyeWest.java
Date:   Mar 16, 2022
Purpose:
Author: Nathan Grabowski

------------------------------------------------------------------------------*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

public class kanyeWest {

    String albumTitle;
    int numTracks, releaseYear, rating, topChart;
    boolean datachanged;
    ArrayList<album> albumList = new ArrayList<album>();

    Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        new kanyeWest();
    }

    public kanyeWest() {
        menu();
    }

    public void addAlbum() {
        Scanner sc = new Scanner(System.in);
        System.out.println("What is the name of the album?");
        albumTitle = sc.nextLine();
        System.out.println("What year did the album release?");
        releaseYear = input.nextInt();
        System.out.println("How many tracks did the album have?");
        numTracks = input.nextInt();
        System.out.println("What was the album's peak on the Billboard charts?");
        topChart = input.nextInt();
        System.out.println("What rating would you give the album?(1-10)");
        rating = input.nextInt();
        while (rating < 1 || rating > 10) {
            System.out.println("The rating must be between 1 and 10");
            System.out.println("What rating would you give the album?(1-10)");
            rating = input.nextInt();
        }
        albumList.add(new album(albumTitle, releaseYear, numTracks, topChart, rating));
    }

    public void display() {

        System.out.println("---------------------------------------------------------------------------");
        System.out.println("Album Title" + "|" + "Release Year" + "|" + "Number of Tracks" + "|" + "Peak Chart Position" + "|" + "Rating out of ten");
        for (int i = 0; i < albumList.size(); i++) {
            System.out.println(albumList.get(i).albumTitle + "|" + albumList.get(i).releaseYear + "|" + albumList.get(i).numTracks + "|" + albumList.get(i).topChart + "|" + albumList.get(i).rating);

        }
    }

    public void removeAlbum() {
        System.out.println("Which album do you want to remove?");
        int remove;
        for (int i = 0; i < albumList.size(); i++) {
            System.out.println(i + "-" + "\t" + albumList.get(i).albumTitle);
        }
        remove = input.nextInt();
        albumList.remove(remove);
        System.out.println("The album has been deleted");
    }

    public String editAlbum() {
        int edit;
        System.out.println("Which album would you like to edit?");
        for (int i = 0; i < albumList.size(); i++) {
            System.out.println(i + "-" + "\t" + albumList.get(i).albumTitle);//loops through to display every album
        }
        edit = input.nextInt();

        System.out.println("Album Title" + "|" + "Release Year" + "|" + "Number of Tracks" + "|" + "Peak Chart Position" + "|" + "Rating out of ten");
        System.out.println(albumList.get(edit).albumTitle + "|" + albumList.get(edit).releaseYear + "|" + albumList.get(edit).numTracks + "|" + albumList.get(edit).topChart + "|" + albumList.get(edit).rating);//displays the album that is going to be edited
        System.out.println("");
        System.out.println("What field would you like to edit?");
        System.out.println("0 - Quit");
        System.out.println("1 - Album Title");
        System.out.println("2 - Release Year");
        System.out.println("3 - Number of Tracks");
        System.out.println("4 - Top Chart Position");
        System.out.println("5 - Rating");
        int editf = input.nextInt();//takes a variable for the field that needs to be changed
        album a = albumList.get(edit);//loads in the album that is being edited

        if (editf == 0) {
            return null;
        }
        if (editf == 1) {
            System.out.println("What would you like to change the title to?");
            String newAlbumTitle = input.nextLine();
            a.albumTitle = newAlbumTitle;//changes the album title to what is inputted
        }
        if (editf == 2) {
            System.out.println("What would you like to change the release year to?");
            int newReleaseYear = input.nextInt();
            a.releaseYear = newReleaseYear;//changes the release year to what is inputted
        }
        if (editf == 3) {
            System.out.println("What would you like to change the number of tracks to?");
            int newNumTracks = input.nextInt();
            a.numTracks = newNumTracks;//changes the number of tracks to what is inputted
        }
        if (editf == 4) {
            System.out.println("What would you like to change the top chart position to?");
            int newTopChart = input.nextInt();
            a.topChart = newTopChart;//changes the top chart position to what is inputted
        }
        if (editf == 5) {
            System.out.println("What would you like to change the rating to?");
            int newRating = input.nextInt();
            a.rating = newRating;//changes the rating to what is inputted
        }
        return null;
    }

    public void sortAlbum() {

        Collections.sort(albumList);

    }

    public void save() {

        try {
            FileWriter fw = new FileWriter(new File("roster.txt"));
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i < albumList.size(); i++) {
                album a = albumList.get(i);
                pw.println(a.albumTitle + "|" + a.releaseYear + "|" + a.numTracks + "|" + a.topChart + "|" + a.rating);
            }
            fw.close();
        } catch (IOException ex) {
            System.out.println(ex);
            // Logger.getLogger(kanyeWest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void load() {

        String aTit;
        int nTra, rYea, rat, tCha;
        albumList.clear();
        try {
            BufferedReader input = new BufferedReader(new FileReader("roster.txt"));
            String line;
//Attempt to read from the file
            line = input.readLine(); //Prime the pump
            while (line != null) //goes to the end of file
            {
                StringTokenizer st = new StringTokenizer(line, "|"); //| is the delimiter 
//Now break up the line
                aTit = st.nextToken();
                rYea = Integer.parseInt(st.nextToken());
                nTra = Integer.parseInt(st.nextToken());
                tCha = Integer.parseInt(st.nextToken());
                rat = Integer.parseInt(st.nextToken());
                albumList.add(new album(aTit, rYea, nTra, tCha, rat));
                line = input.readLine();
            }
            input.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void menu() {
        int choice;
        while (true) {
            System.out.println("---------------------------------------------------------------------------");
            System.out.println("1 - Display Albums");
            System.out.println("2 - Load in from disk file");
            System.out.println("3 - Add a new album");
            System.out.println("4 - Edit an existing album");
            System.out.println("5 - Remove an album");
            System.out.println("6 - Sort the list");
            System.out.println("7 - Save the List Back to Disk File");
            System.out.println("8 - Exit this program");
            System.out.println("---------------------------------------------------------------------------");
            System.out.println("What is your choice? (1-8)");
            choice = input.nextInt();

            switch (choice) {

                case 1 ->
                    display();
                case 2 ->
                    load();
                case 3 ->
                    addAlbum();
                case 4 ->
                    editAlbum();
                case 5 ->
                    removeAlbum();
                case 6 ->
                    sortAlbum();
                case 7 ->
                    save();
                case 8 ->
                    System.exit(0);
            }
        }
    }
}
