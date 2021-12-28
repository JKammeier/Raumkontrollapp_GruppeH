package de.fhbielefeld.swe.raumkontrollapp_h;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.TextureView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;

import de.fhbielefeld.swe.raumkontrollapp_h.databinding.ActivityRaumBinding;

public class RaumActivity extends AppCompatActivity implements View.OnClickListener
{
    private Raum aktuellerRaum;
    String prefNeueAusstattung = "NeueAussattung";

    TextView textView_RaumnummerZahl, textView_ZimmergroesseZahl, textView_AnzahlStuehle,
            textView_AnzahlTische, textView_Stuehle, textView_Tische,
            textView_Raumnummer, textView_Eigenschaften, textView_Ausstattung;
    FloatingActionButton fab_AusstattungHinzufuegen;
    ListView listView;

    private AppBarConfiguration appBarConfiguration;
    private ActivityRaumBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRaumBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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

        listView = (ListView)findViewById(R.id.listview);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("test1");
        arrayList.add("test2");
        arrayList.add("test2");
        ArrayAdapter arrayAdapter = new ArrayAdapter(
                this, android.R.layout.simple_expandable_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
            {
                setContentView(R.layout.fragment_ausstattungs_detail);
            }
        });


        // Raumnummer etc. von MainActivity übergeben
        //setSupportActionBar(binding.toolbar);
        aktuellerRaum = getIntent().getExtras().getParcelable("Raum");
        textView_RaumnummerZahl.setText(aktuellerRaum.getRaumNr());
        //textView_AnzahlStuehle.setText(aktuellerRaum.anzahl_stuehle);
        //textView_AnzahlTische.setText(aktuellerRaum.anzahl_tische);

        // Button unten rechts
        binding.fabAusstattungHinzufuegen.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.fragment_ausstattung_hinzu);
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