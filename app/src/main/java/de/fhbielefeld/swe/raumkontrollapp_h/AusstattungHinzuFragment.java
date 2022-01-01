package de.fhbielefeld.swe.raumkontrollapp_h;

import android.os.Bundle;
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

    //private View view;

    private CollectionReference ausstattungsListe;

    private TextView raumNrTV;
    private EditText nameET, anzahlET, kommentarET;
    private Button hinzu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentAusstattungHinzuBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ausstattungsListe = ((RaumActivity) getActivity()).getRaum().collection("ausstattung");

        view = getView();

        raumNrTV = view.findViewById(R.id.raumnummerTV);
        //TextView th = view.findViewById(R.id.textView3Hinzu);
        nameET = view.findViewById(R.id.nameHinzu);
        anzahlET = view.findViewById(R.id.anzahlHinzu);
        kommentarET = view.findViewById(R.id.kommentarHinzu);
        hinzu = view.findViewById(R.id.buttonHinzu);

        raumNrTV.setText(((RaumActivity) getActivity()).getRaum().getId());

        hinzu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Map<String, Object> dataToSave = new HashMap<String, Object>();
                dataToSave.put("name", nameET.getText().toString());
                dataToSave.put("anzahl", Integer.parseInt(anzahlET.getText().toString()));
                dataToSave.put("zustand", kommentarET.getText().toString());
                ausstattungsListe.document(nameET.getText().toString()).set(dataToSave);

                // Vom ButtonHinzufügen  zurück zu Raumactivity
                setContentView(R.layout.activity_raum);
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_ausstattung_hinzu);
    }

    /*private TextView findViewById(int raumnummerHinzu) {
        return null;
    }*/

    private void setContentView(int fragment_ausstattung_hinzu) {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}