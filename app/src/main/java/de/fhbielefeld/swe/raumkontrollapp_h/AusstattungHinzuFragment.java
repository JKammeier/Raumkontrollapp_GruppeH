package de.fhbielefeld.swe.raumkontrollapp_h;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_ausstattung_hinzu);

        TextView rn = findViewById(R.id.RaumnummerHinzu);
        TextView th = findViewById(R.id.textView3Hinzu);
        EditText wm = (EditText) findViewById(R.id.WasmöchtenSie);
        EditText mh = (EditText) findViewById(R.id.NumberHinzu);
        EditText zh = (EditText) findViewById(R.id.ZustandHinzu);
        EditText so = (EditText) findViewById(R.id.Sonstiges);
        Button hinzu = (Button) findViewById(R.id.buttonHinzu);

        hinzu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                rn.setText(wm.getText().toString());
                //wm.setText("");
                th.setText(wm.getText().toString());
                //wm.setText("");
                mh.setText("");
                zh.setText("");
                so.setText("");

          // Vom ButtonHinzufügen  zurück zu Raumactivity
                setContentView(R.layout.activity_raum);

            }
        });
    }

    private TextView findViewById(int raumnummerHinzu) {
        return null;
    }

    private void setContentView(int fragment_ausstattung_hinzu) {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}