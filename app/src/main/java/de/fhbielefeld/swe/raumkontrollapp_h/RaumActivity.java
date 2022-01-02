package de.fhbielefeld.swe.raumkontrollapp_h;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import de.fhbielefeld.swe.raumkontrollapp_h.databinding.ActivityRaumBinding;

public class RaumActivity extends AppCompatActivity
{
    private DocumentReference raum;
    private ArrayList<String> ausstattungsListe;
    private ArrayAdapter arrayAdapter;

    TextView textView_RaumnummerZahl, textView_ZimmergroesseZahl, textView_Raumnummer, textView_Eigenschaften, textView_Ausstattung;
    FloatingActionButton fab_AusstattungHinzufuegen;
    ListView listView;

    private ActivityRaumBinding binding;

    private String raumNr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRaumBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        textView_RaumnummerZahl = findViewById(R.id.textView_RaumnummerZahl);
        textView_ZimmergroesseZahl = findViewById(R.id.textView_ZimmergroesseZahl);
        textView_Raumnummer = findViewById(R.id.textView_Raumnummer);
        textView_Eigenschaften = findViewById(R.id.textView_Eigenschaften);
        textView_Ausstattung = findViewById(R.id.textView_Ausstattung);
        fab_AusstattungHinzufuegen = findViewById(R.id.fab_AusstattungHinzufuegen);

        textView_Eigenschaften.setPaintFlags(textView_Eigenschaften.getPaintFlags()
                |Paint.UNDERLINE_TEXT_FLAG);
        textView_Ausstattung.setPaintFlags(textView_Ausstattung.getPaintFlags()
                |Paint.UNDERLINE_TEXT_FLAG);
        textView_Raumnummer.setPaintFlags(textView_Raumnummer.getPaintFlags()
                |Paint.UNDERLINE_TEXT_FLAG);

        listView = (ListView)findViewById(R.id.Ausstattungen_listView);

        ausstattungsListe = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ausstattungsListe);
        listView.setAdapter(arrayAdapter);

        // Raumnummer etc. von MainActivity übergeben
        raumNr = getIntent().getExtras().getString("RaumNr");
        raum = FirebaseFirestore.getInstance().document("raeume/" + raumNr);
        textView_RaumnummerZahl.setText(raum.getId());

        if (getIntent().getExtras().getBoolean("raumNeu")) {
            showPopup(raum);
        }

        raum.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    try {
                        textView_ZimmergroesseZahl.setText(documentSnapshot.get("flaeche").toString());
                    } catch (Exception e) {
                        Log.d("EigeneException", "kein Feld flaeche vorhanden");
                    }
                }
            }
        });

        getAusstattungFirebase();

        // Von ListView zu Ausstattung_detail
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
            {
                setContentView(R.layout.fragment_ausstattungs_detail);
            }
        });

        // Ausstattung löschen
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                raum.collection("ausstattung").document(ausstattungsListe.get(position)).delete();

                ausstattungsListe.remove(position);
                arrayAdapter.notifyDataSetChanged();
                Toast.makeText(RaumActivity.this, "Ausstattung erfolgreich gelöscht", Toast.LENGTH_LONG).show();

                return false;
            }
        });

        // Vom Button unten rechts zu Ausstattung_hinzufügen
        binding.fabAusstattungHinzufuegen.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                //AusstattungHinzuFragment ausstattungHinzuFragment = new AusstattungHinzuFragment();
                //getSupportFragmentManager().beginTransaction().replace(R.id.linearLayout, new AusstattungHinzuFragment()).commit();
                setContentView(R.layout.fragment_ausstattung_hinzu);
            }
        });
    }

    private void showPopup(DocumentReference neuerRaum) {
        PopupDialog dialog = new PopupDialog(this, neuerRaum);
        dialog.show();
    }

    public DocumentReference getRaum() {
        return raum;
    }

    public void getAusstattungFirebase() {
        raum.collection("ausstattung").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot doc: snapshotList) {
                    ausstattungsListe.add(doc.getId());
                }
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }
}