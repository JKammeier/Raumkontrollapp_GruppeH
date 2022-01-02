package de.fhbielefeld.swe.raumkontrollapp_h;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.CollectionReference;

import java.util.HashMap;
import java.util.Map;

import de.fhbielefeld.swe.raumkontrollapp_h.databinding.FragmentAusstattungHinzuBinding;

public class AusstattungHinzuFragment extends Fragment {

    private FragmentAusstattungHinzuBinding binding;

    private CollectionReference ausstattungsListe;

    private TextView raumNrTV;
    private EditText nameET, anzahlET, kommentarET;
    private Button hinzu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentAusstattungHinzuBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Log.d("Fehlersuche", "onCreateView in AusstattungHinzuFragment");

        ausstattungsListe = ((RaumActivity) getActivity()).getRaum().collection("ausstattung");

        //this.view = view;

        raumNrTV = root.findViewById(R.id.raumnummerTV);
        //TextView th = view.findViewById(R.id.textView3Hinzu);
        nameET = root.findViewById(R.id.nameHinzu);
        anzahlET = root.findViewById(R.id.anzahlHinzu);
        kommentarET = root.findViewById(R.id.kommentarHinzu);
        hinzu = root.findViewById(R.id.buttonHinzu);

        raumNrTV.setText(((RaumActivity) getActivity()).getRaum().getId());

        Log.d("vor onClick", nameET.toString());

        hinzu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("Fehlersuche", "onClick in AusstattungHinzuFragment");
                Log.d("in onClick", "angekommen -----------------------------------------");
                if (!nameET.getText().toString().equals("")) {
                    Map<String, Object> dataToSave = new HashMap<String, Object>();
                    dataToSave.put("name", nameET.getText().toString());
                    //if (Integer.parseInt(anzahlET.getText().toString()) == 0 || Integer.parseInt(anzahlET.getText().toString()))
                    dataToSave.put("anzahl", Integer.parseInt(anzahlET.getText().toString()));
                    dataToSave.put("zustand", kommentarET.getText().toString());
                    ausstattungsListe.document(nameET.getText().toString()).set(dataToSave);

                    // Vom ButtonHinzufügen  zurück zu Raumactivity
                    setContentView(R.layout.activity_raum);
                }
            }
        });
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_ausstattung_hinzu);
    }

    private void setContentView(int fragment_ausstattung_hinzu) {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}