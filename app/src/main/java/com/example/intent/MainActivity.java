
package com.example.intent;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView txtPlace;
    private final HashMap<String, String[]> places = new HashMap<>();
    private final String[] names = {"תל אשקלון" ,"בית שאן","קיסריה" ,"מצדה"};
    private final Random random = new Random();
    private String selectedPlace = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        places.put("קיסריה", new String[]{
                "https://en.parks.org.il/reserve-park/caesarea-national-park/",
                "geo:0,0?q=Caesarea+National+Park"});
        places.put("תל אשקלון", new String[]{
                "https://en.parks.org.il/reserve-park/caesarea-national-park/",
                "geo:0,0?q=Caesarea+National+Park"});
        places.put("בית שאן", new String[]{
                "https://en.parks.org.il/reserve-park/caesarea-national-park/",
                "geo:0,0?q=Caesarea+National+Park"});
        places.put("מצדה", new String[]{
                "https://en.parks.org.il/reserve-park/caesarea-national-park/",
                "geo:0,0?q=Caesarea+National+Park"});
    }

}
