package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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

                // Select the HTML elements that contain the quotes.
                Elements quoteElements = doc.select("div.quote span.text");

                // Extract and concatenate the quotes.
                StringBuilder scrapedText = new StringBuilder();
                for (Element element : quoteElements) {
                    scrapedText.append(element.text()).append("\n\n");
                }

                scrapedTextView.setText(scrapedText.toString());
            }
        });
    }
}
