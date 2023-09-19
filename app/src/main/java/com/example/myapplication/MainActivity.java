package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class MainActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Connect to the website
                    Connection.Response response = Jsoup.connect("https://quotes.toscrape.com/").execute();

                    // Check if the request was successful (HTTP 200)
                    if (response.statusCode() == 200) {
                        Document doc = response.parse();

                        // Do something with the parsed HTML document

                        // For example, print the HTML content
                        System.out.println(doc.html());
                    } else {
                        System.err.println("Failed to fetch the webpage. Status code: " + response.statusCode());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}