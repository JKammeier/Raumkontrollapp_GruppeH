package de.fhbielefeld.swe.raumkontrollapp_h;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class AusstattungDetailActivity extends AppCompatActivity
{
    EditText editTextNumber_counter, editText_Kommentar;
    TextView counterTxt, value, raumnummer, ConditionView, textView_Raumnummer, ItemViewer;
    Button minusBtn, plusBtn, resetBtn, move, confirmBtn, button_abbrechen;
    private int counter;
    int count = 0;
    private String raumNr;
    private DocumentReference raum;





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

        raumNr = getIntent().getExtras().getString("raumNr");
        raum = FirebaseFirestore.getInstance().document("raeume/" + raumNr);
        raumnummer.setText(raumNr);













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