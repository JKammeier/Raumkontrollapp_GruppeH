package de.fhbielefeld.swe.raumkontrollapp_h;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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

    Button buttonOpen, button_popuptest;
    EditText eingabeRaumNr;
    ListView raumListView;
    TextView textView_NeuerRaum_Zimmergroesse;

    //private ArrayList<Raum> raumListe;
    private ArrayList<String> raumNrListe;
    private ArrayAdapter arrayAdapter;
    private CollectionReference raumListeFirebase = FirebaseFirestore.getInstance().collection("raeume");


    private void showPopup(DocumentReference neuerRaum) {
        PopupDialog dialog = new PopupDialog(this, neuerRaum);
        dialog.show();
    }

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
                    //eingabeRaumNr.setText("");

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


/*
    public void NeuenRaumErstellen_Popup()
    {
        dialogueBuilder = new AlertDialog.Builder(this);
        final View NeuerRaum_PopupView = getLayoutInflater().inflate(R.layout.popup, null);
        editText_NeuerRaum_Eigenschaft = (EditText)
                editText_NeuerRaum_Eigenschaft.findViewById(R.id.editText_NeuerRaum_Eigenschaft);
        textView_NeuerRaum_Zimmergroesse = (TextView)
                textView_NeuerRaum_Zimmergroesse.findViewById(R.id.textView_NeuerRaum_Zimmergroesse);
        button_NeuerRaum_Raumhinzufuegen = (Button)
                button_NeuerRaum_Raumhinzufuegen.findViewById(R.id.button_NeuerRaum_Raumhinzufuegen);
        button_NeuerRaum_Raumhinzufuegen_abbrechen = (Button)
                button_NeuerRaum_Raumhinzufuegen_abbrechen.findViewById(R.id.button_NeuerRaum_Raumhinzufuegen_abbrechen);
    dialogueBuilder.setView(NeuerRaum_PopupView);
    dialog = dialogueBuilder.create();
    dialog.show();

    button_NeuerRaum_Raumhinzufuegen.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Hier in Firebase einlesen

        }
    });

    button_NeuerRaum_Raumhinzufuegen_abbrechen.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialog.dismiss();
        }
    });

    }

 */

    private void enterRaum(String zielRaumNr) {
        eingabeRaumNr.setText("");
        boolean existiert = false;
        for (String nr : raumNrListe) {
            if (nr.equals(zielRaumNr)) {
                existiert = true;
            }
        }

        if (!existiert) {
            raumNrListe.add(zielRaumNr);
            arrayAdapter.notifyDataSetChanged();

            Map<String, Object> dataToSave = new HashMap<String, Object>();
            dataToSave.put("raumNr", zielRaumNr);
            raumListeFirebase.document(zielRaumNr).set(dataToSave);
            showPopup(raumListeFirebase.document(zielRaumNr));
            raumListeFirebase.document(zielRaumNr).collection("ausstattung");
        }

        Intent raumAkt = new Intent(MainActivity.this, RaumActivity.class);
        raumAkt.putExtra("RaumNr", zielRaumNr);
        startActivity(raumAkt);
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
                for (DocumentSnapshot doc : snapshotList) {
                    //raumListe.add(doc.toObject(Raum.class));
                    raumNrListe.add(doc.getId());
                }
                arrayAdapter.notifyDataSetChanged();
            }
        });

    }
}

