package com.example.intent;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView txtPlace;
    private final HashMap<String, String[]> places = new HashMap<>();
    private final String[] names = {"תל אשקלון" ,"בית שאן","קיסריה" ,"מצדה"};
    private final Random random = new Random();
    private Button btnMap, btnRandom, btnWebsite,btnShare;
    private String selectedPlace = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        places.put("מצדה", new String[] {
                "https://en.parks.org.il/reserve-park/masada-national-park",
                "geo:0,0?q=Masada+National+Park"
        });
        places.put("קיסריה", new String[] {
                "https://en.parks.org.il/reserve-park/caesarea-national-park",
                "geo:0,0?q=Caesarea+National+Park"
        });
        places.put("בית שאן", new String[] {
                "https://en.parks.org.il/reserve-park/bet-shean-national-park",
                "geo:0,0?q=Bet+Shean+National+Park"
        });
        places.put("תל אשקלון", new String[] {
                "https://www.parks.org.il/reserve-park/גן-לאומי-אשקלון/",
                "geo:0,0?q=Tel+Ashkelon+National+Park"
        });

        txtPlace = findViewById(R.id.tvRandom);
        btnRandom = findViewById(R.id.btnStart);
        btnMap = findViewById(R.id.btnMap);
        btnWebsite = findViewById(R.id.btnWebsite);
        btnShare = findViewById(R.id.btnShare);

        btnRandom.setOnClickListener( v -> {
            int index = random.nextInt(names.length);
            selectedPlace =names[index];
            txtPlace.setText(selectedPlace);
        });
        btnMap.setOnClickListener( v -> performAction(R.id.btnMap));
        btnWebsite.setOnClickListener( v -> performAction(R.id.btnWebsite));
        btnShare.setOnClickListener( v -> performAction(R.id.btnShare));
    }


    private void performAction(int buttonId) {
        if (selectedPlace == null) {
            Toast.makeText(this, "קודם בחרו אתר", Toast.LENGTH_SHORT).show();
            return;
        }

        String[] info = places.get(selectedPlace);
        if (info == null) return;

        Intent intent;
        if (buttonId == R.id.btnMap) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(info[1]));
        } else if (buttonId == R.id.btnWebsite) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(info[0]));
        } else if (buttonId == R.id.btnShare) {
            intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT,
                    "בואו לטייל איתי ב- " + selectedPlace + "!\n" + info[0]);
            intent = Intent.createChooser(intent, "שתפו את ההמלצה");
        } else {
            return;
        }


        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "לא נמצאה אפליקציה מתאימה", Toast.LENGTH_LONG).show();
        }
    }
}