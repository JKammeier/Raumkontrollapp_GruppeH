package de.fhbielefeld.swe.raumkontrollapp_h;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button buttonOpen;
    EditText eingabeRaumNr;
    ListView raumListView;

    //private ArrayList<Raum> raumListe;
    private ArrayList<String> raumNrListe;
    private ArrayAdapter arrayAdapter;

    private CollectionReference raumListeFirebase = FirebaseFirestore.getInstance().collection("raeume");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //raumListe = new ArrayList<Raum>();
        raumNrListe = new ArrayList<String>();

        buttonOpen = findViewById(R.id.ButtonOpen);
        eingabeRaumNr = findViewById(R.id.EingabeRaumNr);
        raumListView = findViewById(R.id.RaumListView);

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, raumNrListe);
        raumListView.setAdapter(arrayAdapter);

        getRaeumeFirebase();

        buttonOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!eingabeRaumNr.getText().toString().equals("")) {
                    //Raum aktuellerRaum = getRaum(eingabeRaumNr.getText().toString());
                    //getRaeumeFirebase();
                    arrayAdapter.notifyDataSetChanged();
                    eingabeRaumNr.setText("");

                    //enterRaum(aktuellerRaum)
                    enterRaum(eingabeRaumNr.getText().toString());
                }
            }
        });

        raumListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                enterRaum(raumNrListe.get(position));
            }
        });

    }

    /*
    private void enterRaum (Raum zielRaum) {
        Intent raumAkt = new Intent(MainActivity.this, RaumActivity.class);
        raumAkt.putExtra("Raum", zielRaum);
        startActivity(raumAkt);
    }
    */

    private void enterRaum (String zielRaumNr) {
        raumListeFirebase.document(zielRaumNr).get().addOnCanceledListener(new OnCanceledListener() {
            @Override
            public void onCanceled() {
                Map<String, Object> dataToSave = new HashMap<String, Object>();
                dataToSave.put("raumNr", zielRaumNr);
                raumListeFirebase.document(zielRaumNr).set(dataToSave);
                raumListeFirebase.document(zielRaumNr).collection("eigenschaftListe");
            }
        });

        Intent raumAkt = new Intent(MainActivity.this, RaumActivity.class);
        raumAkt.putExtra("RaumNr", zielRaumNr);
    }

    /*
    public Raum getRaum (String raumNr) {
        for (Raum aktuellerRaum : raumListe) {
            if (aktuellerRaum.getRaumNr().equals(raumNr)) {
                return aktuellerRaum;
            }
        }

        Raum neuerRaum = new Raum(raumNr);
        raumListe.add(neuerRaum);
        raumNrListe.add(raumNr);
        Map<String, Object> dataToSave = new HashMap<String, Object>();
        dataToSave.put("raumNr", raumNr);
        raumListeFirebase.document(raumNr).set(dataToSave);
        raumListeFirebase.document(raumNr).collection("eigenschaftListe");
        return neuerRaum;
    }
    */

    public void getRaeumeFirebase() {
        raumListeFirebase.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot doc: snapshotList) {
                    //raumListe.add(doc.toObject(Raum.class));
                    raumNrListe.add(doc.getId());
                }
                arrayAdapter.notifyDataSetChanged();
            }
        });

    }
}