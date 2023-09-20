package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    private Button scrapeButton;
    private TextView scrapedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrapeButton = findViewById(R.id.button);
        scrapedTextView = findViewById(R.id.textView);

        scrapeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    scrapeTextFromUrl();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            private void scrapeTextFromUrl() throws IOException {
                // Get the URL to scrape from the website.
                String url = "https://quotes.toscrape.com/";

                // Create a Jsoup Document Object from the URL.
                Document doc = Jsoup.connect(url).get();

                // Select the HTML element that contatins the text you want to scrape.
                String scrapedText = doc.select("span").text();

                scrapedTextView.setText((scrapedText));
            }
        });
    }
}