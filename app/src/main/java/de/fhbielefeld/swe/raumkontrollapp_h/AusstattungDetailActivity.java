package de.fhbielefeld.swe.raumkontrollapp_h;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class AusstattungDetailActivity extends AppCompatActivity
{
    EditText editTextNumber_counter, kommentarET;
    TextView counterTxt, value, raumnummer, ConditionView, textView_Raumnummer, ItemViewer, gegenstandTV;
    Button minusBtn, plusBtn, resetBtn, move, confirmBtn, abbrechenBt, bestaetigenBt;
    private int counter;
    int count = 0;
    private String raumNr;
    private String gegenstandName;

    private DocumentReference gegenstand;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent mainAct = new Intent(AusstattungDetailActivity.this, RaumActivity.class);
        mainAct.putExtra("raumNr", raumNr);
        startActivity(mainAct);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ausstattung_detail);

        ConditionView = findViewById(R.id.ConditionView);
        textView_Raumnummer = findViewById(R.id.textView_Raumnummer);

        ConditionView.setPaintFlags(ConditionView.getPaintFlags()
                | Paint.UNDERLINE_TEXT_FLAG);
        textView_Raumnummer.setPaintFlags(textView_Raumnummer.getPaintFlags()
                |Paint.UNDERLINE_TEXT_FLAG);
        //ItemViewer.setPaintFlags(ItemViewer.getPaintFlags()
          //      |Paint.UNDERLINE_TEXT_FLAG);



        raumnummer = findViewById(R.id.raumnummer);
        counterTxt = (TextView) findViewById(R.id.editTextNumber_counter);
        gegenstandTV = findViewById(R.id.GegenstandTV);
        kommentarET = findViewById(R.id.editText_Kommentar);
        bestaetigenBt = findViewById(R.id.confirmBtn);
        abbrechenBt = findViewById(R.id.button_abbrechen);

        raumNr = getIntent().getExtras().getString("raumNr");
        gegenstandName = getIntent().getExtras().getString("gegenstandName");
        gegenstand = FirebaseFirestore.getInstance().document("raeume/" + raumNr + "/ausstattung/" + gegenstandName);

        raumnummer.setText(raumNr);
        gegenstandTV.setText("Gegenstand: " + gegenstandName);
        gegenstand.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    counterTxt.setText(documentSnapshot.get("anzahl").toString());
                    kommentarET.setText(documentSnapshot.get("kommentar").toString());
                }
            }
        });

        bestaetigenBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> dataToSave = new HashMap<String, Object>();
                dataToSave.put("anzahl", Integer.parseInt(counterTxt.getText().toString()));
                dataToSave.put("kommentar", kommentarET.getText().toString());
                gegenstand.update(dataToSave);

                Intent raumAct = new Intent (AusstattungDetailActivity.this, RaumActivity.class);
                raumAct.putExtra("raumNr", raumNr);
                startActivity(raumAct);
            }
        });

        abbrechenBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent raumAct = new Intent (AusstattungDetailActivity.this, RaumActivity.class);
                raumAct.putExtra("raumNr", raumNr);
                startActivity(raumAct);
            }
        });


/*
        minusBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (count <= 0) count = 0;

                else count--;
                value.setText("" + count);
            }
        });

        plusBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                count++;
                value.setText("" + count);
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (count > 0)
                    count = 0;
            }
        });

 */

    }


    /*
    public void
    increment(View v)
    {
        count++;
        value.setText("" + count);
    }

    public void decrement(View v)
    {
        if (count <= 0) count = 0;
        else count--;
        value.setText("" + count);
    }

 */
}