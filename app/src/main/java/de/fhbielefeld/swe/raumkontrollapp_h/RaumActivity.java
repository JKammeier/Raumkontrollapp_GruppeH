package de.fhbielefeld.swe.raumkontrollapp_h;

import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

public class RaumActivity extends AppCompatActivity implements View.OnClickListener
{
    //private CollectionReference raumListeFirebase = FirebaseFirestore.getInstance().collection("raeume");
    private DocumentReference raum;
    private ArrayList<String> ausstattungsListe;
    private ArrayAdapter arrayAdapter;

    private Raum aktuellerRaum;
    String prefNeueAusstattung = "NeueAussattung";

    TextView textView_RaumnummerZahl, textView_ZimmergroesseZahl, textView_AnzahlStuehle,
            textView_AnzahlTische, textView_Stuehle, textView_Tische,
            textView_Raumnummer, textView_Eigenschaften, textView_Ausstattung,
            textView_Zimmergroesse;
    FloatingActionButton fab_AusstattungHinzufuegen;
    ListView listView;

    private AppBarConfiguration appBarConfiguration;
    private ActivityRaumBinding binding;

    private String raumNr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRaumBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        textView_Zimmergroesse = findViewById(R.id. textView_Zimmergroesse);
        textView_RaumnummerZahl = findViewById(R.id.textView_RaumnummerZahl);
        textView_ZimmergroesseZahl = findViewById(R.id.textView_ZimmergroesseZahl);
        textView_AnzahlStuehle = findViewById(R.id.textView_AnzahlStuehle);
        textView_AnzahlTische = findViewById(R.id.textView_AnzahlTische);
        textView_Stuehle = findViewById(R.id.textView_Stuehle);
        textView_Tische = findViewById(R.id.textView_Tische);
        textView_Raumnummer = findViewById(R.id.textView_Raumnummer);
        textView_Eigenschaften = findViewById(R.id.textView_Eigenschaften);
        textView_Ausstattung = findViewById(R.id.textView_Ausstattung);
        fab_AusstattungHinzufuegen = findViewById(R.id.fab_AusstattungHinzufuegen);
        fab_AusstattungHinzufuegen.setOnClickListener(this);
        textView_Eigenschaften.setPaintFlags(textView_Eigenschaften.getPaintFlags()
                |Paint.UNDERLINE_TEXT_FLAG);
        textView_Ausstattung.setPaintFlags(textView_Ausstattung.getPaintFlags()
                |Paint.UNDERLINE_TEXT_FLAG);
        textView_Raumnummer.setPaintFlags(textView_Raumnummer.getPaintFlags()
                |Paint.UNDERLINE_TEXT_FLAG);

        listView = (ListView)findViewById(R.id.Ausstattungen_listView);
        /*ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("test1");
        arrayList.add("test2");
        arrayList.add("test2");
        ArrayAdapter arrayAdapter = new ArrayAdapter(
                this, android.R.layout.simple_expandable_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);*/
        ausstattungsListe = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ausstattungsListe);
        listView.setAdapter(arrayAdapter);

        // Von ListView zu Ausstattung_detail
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
            {
                setContentView(R.layout.fragment_ausstattungs_detail);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                raum.collection("ausstattung").document(ausstattungsListe.get(position)).delete();

                ausstattungsListe.remove(position);
                arrayAdapter.notifyDataSetChanged();

                return false;
            }
        });

        // Vom Button unten rechts zu Ausstattung_hinzufügen
        binding.fabAusstattungHinzufuegen.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.fragment_ausstattung_hinzu);
            }
        });


        // Raumnummer etc. von MainActivity übergeben
        //setSupportActionBar(binding.toolbar);
        raumNr = getIntent().getExtras().getString("RaumNr");
        raum = FirebaseFirestore.getInstance().document("raeume/" + raumNr);
        //aktuellerRaum = getIntent().getExtras().getParcelable("Raum");
        //textView_RaumnummerZahl.setText(aktuellerRaum.getRaumNr());
        textView_RaumnummerZahl.setText(raum.getId());
        getEigenschaftenFirebase();
        //textView_AnzahlStuehle.setText(aktuellerRaum.anzahl_stuehle);
        //textView_AnzahlTische.setText(aktuellerRaum.anzahl_tische);
        //textView_ZimmergroesseZahl.setText(aktuellerRaum.zimmergroesse_zahl);


    }

    public DocumentReference getRaum() {
        return raum;
    }

    public void getEigenschaftenFirebase() {
        raum.collection("ausstattung").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot doc: snapshotList) {
                    //raumListe.add(doc.toObject(Raum.class));
                    ausstattungsListe.add(doc.getId());
                }
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }

    public void createDatabase()
    {

    }

    public void loadRoom()
    {

    }

    public void safeRoom()
    {

    }

    // Wenn etwas in neueAusstattung hinzugefügt wurde, ist es wahr oder falsch?
    public boolean neueAusstattung()
    {
        // SharedPreference: Speichern von Inhalten in prefNeueAusstattung
        SharedPreferences preferences = getSharedPreferences(prefNeueAusstattung, MODE_PRIVATE);
        // Wenn etwas in prefRaumListe gespeichert ist
        if (preferences.getBoolean(prefNeueAusstattung, true))
        {
            // Wert (boolean) in prefNeueAussattung ändern
            SharedPreferences.Editor editor = preferences.edit();
            // Boolean setzen
            editor.putBoolean(prefNeueAusstattung, false);
            editor.commit();
            // Neue Aussattung gefunden
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public void onClick(View view)
    {
        /*
        switch (view.getId())
        {
            case R.id.fab_AusstattungHinzufuegen:
                setContentView(R.layout.fragment_ausstattung_hinzu);
                break;

            case R.id.textView_Tische:
                setContentView(R.layout.fragment_ausstattungs_detail);
                break;

            case R.id.textView_Stuehle:
                setContentView(R.layout.fragment_ausstattungs_detail);
                break;
        }
         */
    }
}