package com.example.mymovieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Movie;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("checker","onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        EditText movieTitleInput = findViewById(R.id.titleInput);
        EditText movieCostInput = findViewById(R.id.costInput);
        EditText movieYearInput = findViewById(R.id.yearInput);
        EditText movieGenreInput = findViewById(R.id.genreInput);
        EditText movieKeywordsInput = findViewById(R.id.keywordsInput);
        EditText movieCountryInput = findViewById(R.id.countryInput);

        SharedPreferences myData = getSharedPreferences("savedData", 0);
        movieTitleInput.setText(myData.getString("movieTitle",""));
        movieCostInput.setText(myData.getString("movieCost", ""));
        movieYearInput.setText(myData.getString("movieYear", ""));
        movieGenreInput.setText(myData.getString("movieGenre", ""));
        movieKeywordsInput.setText(myData.getString("movieKeywords", ""));
        movieCountryInput.setText(myData.getString("movieCountry", ""));

        Log.i("checker","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("checker","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("checker","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("checker","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("checker","onDestroy");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        EditText movieTitleInput = findViewById(R.id.titleInput);
        EditText movieGenreInput = findViewById(R.id.genreInput);
        outState.putString("movieTitle",movieTitleInput.getText().toString());
        outState.putString("movieGenre",movieGenreInput.getText().toString().toLowerCase());
        Log.i("checker","SaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        EditText movieTitleInput = findViewById(R.id.titleInput);
        EditText movieGenreInput = findViewById(R.id.genreInput);
        movieTitleInput.setText(savedInstanceState.getString("movieTitle").toUpperCase());
        movieGenreInput.setText(savedInstanceState.getString("movieGenre"));
        Log.i("checker","RestoreInstanceState");
    }

    public void showFailToast(View view){
        Toast message = Toast.makeText(this,"Please input all fields!",Toast.LENGTH_SHORT);
        message.show();
    }

    public void showFailToast2(View view){
        Toast message = Toast.makeText(this,"Please input relevant fields!",Toast.LENGTH_SHORT);
        message.show();
    }

    public void showSuccessAddToast(View view, String text){
        Toast message = Toast.makeText(this,"Movie (" +text+ ") successfully added!",Toast.LENGTH_LONG);
        message.show();
    }

    public void showSuccessDoubledToast(View view){
        Toast message = Toast.makeText(this,"Successfully doubled cost!",Toast.LENGTH_LONG);
        message.show();
    }

    public void showSuccessClearedToast(View view){
        Toast message = Toast.makeText(this,"Successfully cleared fields!",Toast.LENGTH_LONG);
        message.show();
    }

    public void addMovie(View view){
        try {
            EditText movieTitleInput = findViewById(R.id.titleInput);
            EditText movieCostInput = findViewById(R.id.costInput);
            EditText movieYearInput = findViewById(R.id.yearInput);
            EditText movieGenreInput = findViewById(R.id.genreInput);
            EditText movieKeywordsInput = findViewById(R.id.keywordsInput);
            EditText movieCountryInput = findViewById(R.id.countryInput);

            if(movieCountryInput.getText().toString().equals("") || movieYearInput.getText().toString().equals("")|| movieTitleInput.getText().toString().equals("") || movieCostInput.getText().toString().equals("") || movieKeywordsInput.getText().toString().equals("") || movieGenreInput.getText().toString().equals("")){
                throw new Exception("MISSING VALUES");
            }
            else {
                SharedPreferences mySavedData = getSharedPreferences("savedData", 0);
                SharedPreferences.Editor myEditor = mySavedData.edit();
                myEditor.putString("movieTitle", movieTitleInput.getText().toString());
                myEditor.putString("movieGenre", movieGenreInput.getText().toString());
                myEditor.putString("movieYear", movieYearInput.getText().toString());
                myEditor.putString("movieCost", movieCostInput.getText().toString());
                myEditor.putString("movieKeywords", movieKeywordsInput.getText().toString());
                myEditor.putString("movieCountry", movieCountryInput.getText().toString());
                myEditor.commit();

                showSuccessAddToast(view, movieTitleInput.getText().toString() + ", " + movieYearInput.getText().toString());
            }
        }
        catch (Exception e){
            showFailToast(view);
        }
    }

    public void doubleSavedCost(View view){
        try {
            SharedPreferences myData = getSharedPreferences("savedData", 0);
            if (!myData.contains("movieCost")) {
                throw new Exception("NO INFO");
            }
            else{
                SharedPreferences.Editor myEditor = myData.edit();
                EditText movieCostInput = findViewById(R.id.costInput);
                double cost = Double.parseDouble(myData.getString("movieCost",""))*2;
                myEditor.putString("movieCost",cost + "");
                movieCostInput.setText(cost + "");
                myEditor.commit();
                Toast message = Toast.makeText(this,"Successfully Loaded and Doubled!", Toast.LENGTH_LONG);
                message.show();
            }
        }catch(Exception e){
            Toast message = Toast.makeText(this,"No Data Available!", Toast.LENGTH_LONG);
            message.show();
        }
    }

    public void clearFields(View view) {
        EditText title, country, genre, keywords, cost, year;
        String empty = "";
        try {
            title = findViewById(R.id.titleInput);
            country = findViewById(R.id.countryInput);
            genre = findViewById(R.id.genreInput);
            keywords = findViewById(R.id.keywordsInput);
            cost = findViewById(R.id.costInput);
            year = findViewById(R.id.yearInput);
            title.setText(empty);
            country.setText(empty);
            genre.setText(empty);
            keywords.setText(empty);
            cost.setText(empty);
            year.setText(empty);
            showSuccessClearedToast(view);
        } catch (Exception e) {
            showFailToast(view);
        }

    }

    public void clearData(View view){
        SharedPreferences myData = getSharedPreferences("savedData", 0);
        SharedPreferences.Editor myEditor = myData.edit();
        myEditor.clear();
        myEditor.commit();
        Toast message = Toast.makeText(this,"Successfully Cleared Data!", Toast.LENGTH_LONG);
        message.show();
    }

    public void doubleCost(View view){
        EditText cost;
        double cost_double;
        try {
            cost = findViewById(R.id.costInput);
            cost_double = Double.parseDouble(cost.getText().toString()) * 2;
            cost.setText(cost_double + "");
            showSuccessDoubledToast(view);

        }
        catch (Exception e){
            showFailToast2(view);
        }
    }

}