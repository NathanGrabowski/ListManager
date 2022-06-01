/*------------------------------------------------------------------------------

File:   album.java
Date:   Mar 16, 2022
Purpose:
Author: Nathan Grabowski

------------------------------------------------------------------------------*/

public class album implements Comparable {
    
    String albumTitle;
    int numTracks, releaseYear, rating, topChart;

    public album(String alb, int relyea,int num,  int top,int rat) {

        albumTitle = alb;
        numTracks = num;
        releaseYear = relyea;
        rating = rat;
        topChart = top;
    }

    public album(){
        
        albumTitle="";
        numTracks=0;
        releaseYear=0;
        rating=0;
        topChart=0;
        
    }
    
    public int compareTo(Object o){
        album other =(album)o;
        if(rating ==other.rating){
            return albumTitle.compareTo(other.albumTitle);
        }
        else{
            return other.rating -rating;
        }
    }
    }
    
