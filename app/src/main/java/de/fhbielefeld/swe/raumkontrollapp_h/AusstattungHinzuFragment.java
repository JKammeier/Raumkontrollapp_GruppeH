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
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import de.fhbielefeld.swe.raumkontrollapp_h.databinding.FragmentAusstattungHinzuBinding;

public class AusstattungHinzuFragment<raumnr> extends Fragment {

    private FragmentAusstattungHinzuBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = FragmentAusstattungHinzuBinding.inflate(inflater, container, false);
        return binding.getRoot();


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //created method
        setContentView(R.layout.fragment_ausstattung_hinzu);

        TextView rn = findViewById(R.id.RaumnummerHinzu);

        TextView fg = findViewById(R.id.FragetextWasHinzu);
        TextView an = findViewById(R.id.EingabeAnzahlHinzu);
        //casting vorgeschlagen
        EditText so = (EditText) findViewById(R.id.sonstiges);
        //cast
        Button fu = (Button) findViewById(R.id.buttonfürsSonstiges);


// von OK Button zurück zu raumactivity
        fu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                so.setText(so.getText().toString());
                setContentView(R.layout.activity_raum);

            }


        });





    }


//methode
    private TextView findViewById(int raumnummerHinzu) {
        return null;
    }



    private void setContentView(int fragment_ausstattung_hinzu) {
    }

    /*
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AusstattungHinzuFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
*/

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}