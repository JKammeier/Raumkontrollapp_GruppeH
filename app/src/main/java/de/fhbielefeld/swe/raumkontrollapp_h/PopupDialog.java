package de.fhbielefeld.swe.raumkontrollapp_h;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PopupDialog extends Dialog {
    Button button_NeuerRaum_Raumhinzufuegen, button_NeuerRaum_Raumhinzufuegen_abbrechen;
    EditText editText_NeuerRaum_Eigenschaft;
    DocumentReference raum; //= FirebaseFirestore.getInstance().document("raeume");

    public PopupDialog(@NonNull Context context, DocumentReference neuerRaum) {
        super(context);
        raum = neuerRaum;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);

        button_NeuerRaum_Raumhinzufuegen_abbrechen = findViewById(R.id.button_NeuerRaum_Raumhinzufuegen_abbrechen);
        button_NeuerRaum_Raumhinzufuegen = (Button) findViewById(R.id.button_NeuerRaum_Raumhinzufuegen);
        editText_NeuerRaum_Eigenschaft = (EditText) findViewById(R.id.editText_NeuerRaum_Eigenschaft);

        button_NeuerRaum_Raumhinzufuegen_abbrechen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                raum.delete();
                dismiss();
            }
        });

        button_NeuerRaum_Raumhinzufuegen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> dataToSave = new HashMap<String, Object>();
                dataToSave.put("flaeche", Integer.parseInt(editText_NeuerRaum_Eigenschaft.getText().toString()));
                raum.update(dataToSave);
                dismiss();
            }
        });
    }
}
