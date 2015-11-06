package com.joshbgold.PirateSpanish;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class questsActivity extends MainActivity{

    private int rank;
    private TextView questText;

    private String mVoyage[] = {"Maiden Voyage", "Nombre de Dios", "Porto Bello",  "Panama City", "Santa Marta", "Cartegena",
            "Forteleza", "Granada","Nueva Cádiz", "Nueva Cádiz" };

    String mVoyageDescription[] = {"The Spanish ship Encarnación is headed to Havana on her maiden voyage.  Your mission is to intercept and capture the ship.",
            "Your orders are to blockade the city Nombre de Dios until the government submits and surrenders.  Take no quarter.",
            "The fortifications of Porto Bello face the sea, but the city is unprepared for a land-based attack. Land your crew 8 kilometers south of Portobello, " +
                    "trek through the jungle, and attack city from the rear.",
            "Voyage to Panama City and kidnap the archbishop of Panama. Ransom the archbishop for 10,000 gold doubloons.",
            "Your orders are to enlist the support of natives and escaped former slaves for a joint attack on Santa Marta.",
            "Locate Francis Drake's fleet, currently thought to be in the southwest of the Caribbean Sea.  Join forces with his fleet for a direct frontal attack on Cartegena.",
            "The Customs House of Forteleza is one of the richest establishments of the entire New World. Liberate the Customs House of its silver and gold.",
            "Your mission is to sail up the Rio San Juan, and plunder the town of Granada.",
            "Nueva Cádiz is well known for it's prosperous pearl production.  Under the cover of darkness, plunder a pirate's share of the pearls."
    };


    public String getVoyage(int rank) {
        return mVoyage[rank];
    }

    public String getVoyageDescription(int rank) {
        return mVoyageDescription[rank];
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quests);

        final Button exitButton = (Button) findViewById(R.id.exitButton);
        final Button continueButton = (Button) findViewById(R.id.continueButton);

        //This line obtains shared preferences, which contain the rank of the user
        rank = LoadPreferences("UserRank", rank);
        questText = (TextView) findViewById(R.id.questText);
        questText.setText(getVoyageDescription(rank) + "");

        //continue button
        View.OnClickListener proceed = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQuiz();
            }
        };

        //Exit button
        View.OnClickListener walkThePlank = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        };

        continueButton.setOnClickListener(proceed);
        exitButton.setOnClickListener(walkThePlank);
    }

    //save prefs
    public void savePrefs(String key, int value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    //get prefs
    private int LoadPreferences(String key, int value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int data = sharedPreferences.getInt(key, value);
        return data;
    }

    //go to the quiz page
    private void startQuiz() {
        //get user rank
        rank = LoadPreferences("UserRank", rank);

        if (rank == 9){ //User is a captain, and has won the game!
            startCaptainActivity();
        }
        else {
            Intent intent = new Intent(questsActivity.this, quizActivity.class);
            startActivity(intent);
        }
    }

    //go to the settings page
    private void startCaptainActivity(){

        Intent intent = new Intent(questsActivity.this, CaptainActivity.class);
        startActivity(intent);
    }
}

