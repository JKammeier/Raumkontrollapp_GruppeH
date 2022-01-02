package de.fhbielefeld.swe.raumkontrollapp_h;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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

    private Button buttonOpen;
    private EditText eingabeRaumNr;
    private ListView raumListView;
    private SwipeRefreshLayout swipeRefresh;

    private ArrayList<String> raumNrListe;
    private ArrayAdapter arrayAdapter;

    private CollectionReference raumListeFirebase = FirebaseFirestore.getInstance().collection("raeume");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        raumNrListe = new ArrayList<String>();

        buttonOpen = findViewById(R.id.ButtonOpen);
        eingabeRaumNr = findViewById(R.id.EingabeRaumNr);
        raumListView = findViewById(R.id.RaumListView);
        swipeRefresh = findViewById(R.id.swipeRefreshMain);


        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, raumNrListe);
        raumListView.setAdapter(arrayAdapter);

        getRaeumeFirebase();

        buttonOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!eingabeRaumNr.getText().toString().equals("")) {
                    arrayAdapter.notifyDataSetChanged();

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

        raumListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                raumListeFirebase.document(raumNrListe.get(position)).delete();

                raumNrListe.remove(position);
                arrayAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Raum erfolgreich gelöscht", Toast.LENGTH_LONG).show();

                return false;
            }
        });

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                raumNrListe.clear();
                getRaeumeFirebase();

                arrayAdapter.notifyDataSetChanged();
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    private void enterRaum (String zielRaumNr) {
        eingabeRaumNr.setText("");
        boolean existiert = false;
        for (String nr : raumNrListe) {
            if (nr.equals(zielRaumNr)) {
                existiert = true;
                break;
            }
        }

        Intent raumAkt = new Intent(MainActivity.this, RaumActivity.class);
        raumAkt.putExtra("RaumNr", zielRaumNr);

        if (!existiert) {
            raumNrListe.add(zielRaumNr);
            arrayAdapter.notifyDataSetChanged();

            Map<String, Object> dataToSave = new HashMap<String, Object>();
            dataToSave.put("raumNr", zielRaumNr);
            raumListeFirebase.document(zielRaumNr).set(dataToSave);
            raumListeFirebase.document(zielRaumNr).collection("ausstattung");
            raumAkt.putExtra("raumNeu", true);

        } else {
            raumAkt.putExtra("raumNeu", false);
        }

        startActivity(raumAkt);
    }

    public void getRaeumeFirebase() {
        raumListeFirebase.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot doc: snapshotList) {
                    raumNrListe.add(doc.getId());
                }
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }
}