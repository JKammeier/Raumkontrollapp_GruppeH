package de.fhbielefeld.swe.raumkontrollapp_h;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Raum> raumListe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonOpen = findViewById(R.id.ButtonOpen);
        EditText eingabeRaumNr = findViewById(R.id.EingabeRaumNr);

        buttonOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eingabeRaumNr.setText("");
                Raum aktuellerRaum = getRaum(eingabeRaumNr.getText().toString());

                Intent raumAkt = new Intent(MainActivity.this, RaumActivity.class);
                raumAkt.putExtra("Raum", aktuellerRaum);
                startActivity(raumAkt);
                // in RaumActivity: aktuellerRaum = getIntent().getExtras().getParcelable("Raum");
            }
        });
    }

    public Raum getRaum (String raumNr) {
        for (Raum aktuellerRaum : raumListe) {
            if (aktuellerRaum.nr.equals(raumNr)) {
                return aktuellerRaum;
            }
        }

        Raum neuerRaum = new Raum(raumNr);
        raumListe.add(neuerRaum);
        return neuerRaum;
    }
    // Dies ist ein zweiter Test!
    // moin
}