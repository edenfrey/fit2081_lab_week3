package com.example.mymovieapp;

public class MovieClass implements MovieInterface {

    public String Title, Country, Genre, Keywords;
    public int Year;
    public double Cost;

    public MovieClass(String title, String country, String genre, String keywords, int year, double cost) {
        Title = title;
        Country = country;
        Genre = genre;
        Keywords = keywords;
        Year = year;
        Cost = cost;
    }



    @Override
    public void showTitle() {
        //Do something
    }

    @Override
    public void showYear() {
        //Do something
    }
}
