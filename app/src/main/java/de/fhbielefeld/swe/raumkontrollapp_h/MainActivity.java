package de.fhbielefeld.swe.raumkontrollapp_h;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Raum> raumListe;
    private ArrayAdapter<String> arad;
    private ArrayList<String> raumNrListe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        raumListe = new ArrayList<Raum>();
        raumNrListe = new ArrayList<String>();

        Button buttonOpen = findViewById(R.id.ButtonOpen);
        EditText eingabeRaumNr = findViewById(R.id.EingabeRaumNr);
        ListView raumListView = findViewById(R.id.RaumListView);

        //arad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, raumNrListe);
        //(raumListView);

        buttonOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Raum aktuellerRaum = getRaum(eingabeRaumNr.getText().toString());
                eingabeRaumNr.setText("");

                Intent raumAkt = new Intent(MainActivity.this, RaumActivity.class);
                raumAkt.putExtra("Raum", aktuellerRaum);
                startActivity(raumAkt);
            }
        });
    }

    public Raum getRaum (String raumNr) {
        for (Raum aktuellerRaum : raumListe) {
            if (aktuellerRaum.getRaumNr().equals(raumNr)) {
                return aktuellerRaum;
            }
        }

        Raum neuerRaum = new Raum(raumNr);
        raumListe.add(neuerRaum);
        raumNrListe.add(raumNr);
        return neuerRaum;
    }
}