package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

     Button scrapeButton;
     TextView scrapedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button scrapeButton = findViewById(R.id.button);
        scrapedTextView = findViewById(R.id.textView);

        scrapeButton.setOnClickListener(v -> scrapeTextFromUrl());
    }

    private void scrapeTextFromUrl() {
        // Start a new thread for network operations as it will crash if you perform network operations on the ui thread
        new Thread(() -> {
            try {
                // URL to scrape from the website.
                String url = "https://quotes.toscrape.com/";

                // Create a Jsoup Document Object from the URL.
                Document doc = Jsoup.connect(url).get();

                // Select the HTML elements that contain the quotes.
                Elements quoteElements = doc.select("div.quote span.text");

                // Extract and concatenate the quotes.
                final StringBuilder scrapedText = new StringBuilder();
                for (Element element : quoteElements) {
                    scrapedText.append(element.text()).append("\n\n");
                }

                // Update the UI on the main thread
                runOnUiThread(() -> scrapedTextView.setText(scrapedText.toString()));
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception here (e.g., show an error message to the user)
            }
        }).start();
    }
}
