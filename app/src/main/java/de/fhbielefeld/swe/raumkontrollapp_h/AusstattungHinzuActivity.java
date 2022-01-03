package de.fhbielefeld.swe.raumkontrollapp_h;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AusstattungHinzuActivity extends AppCompatActivity {

    private String raumNr;

    private CollectionReference ausstattungsListe;

    private TextView raumNrTV;
    private EditText nameET, anzahlET, kommentarET;
    private Button hinzu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ausstattung_hinzu);

        raumNr = getIntent().getExtras().getString("raumNr");
        ausstattungsListe = FirebaseFirestore.getInstance().collection("raeume/" + raumNr + "/ausstattung");

        raumNrTV = findViewById(R.id.raumnummerTV);
        //TextView th = view.findViewById(R.id.textView3Hinzu);
        nameET = findViewById(R.id.nameHinzu);
        anzahlET = findViewById(R.id.anzahlHinzu);
        kommentarET = findViewById(R.id.kommentarHinzu);
        hinzu = findViewById(R.id.buttonHinzu);

        raumNrTV.setText(raumNr);

        hinzu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!nameET.getText().toString().equals("")) {
                    ausstattungsListe.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            boolean neu = true;
                            List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot doc: snapshotList) {
                                if (nameET.getText().toString().equals(doc.getId())) {
                                    neu = false;
                                }
                            }
                            if (neu) {
                                gegenstandHinzufuegen();
                            } else {
                                Toast.makeText(AusstattungHinzuActivity.this, "Ein Gegenstand mit diesem Namen existiert bereits!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private void gegenstandHinzufuegen() {
        Map<String, Object> dataToSave = new HashMap<String, Object>();
        dataToSave.put("name", nameET.getText().toString());
        //if (Integer.parseInt(anzahlET.getText().toString()) == 0 || Integer.parseInt(anzahlET.getText().toString()))
        dataToSave.put("anzahl", Integer.parseInt(anzahlET.getText().toString()));
        dataToSave.put("kommentar", kommentarET.getText().toString());
        ausstattungsListe.document(nameET.getText().toString()).set(dataToSave);

        // Vom ButtonHinzufügen  zurück zu Raumactivity
        Intent raumAct = new Intent(AusstattungHinzuActivity.this, RaumActivity.class);
        raumAct.putExtra("raumNr", raumNr);
        startActivity(raumAct);
    }
}