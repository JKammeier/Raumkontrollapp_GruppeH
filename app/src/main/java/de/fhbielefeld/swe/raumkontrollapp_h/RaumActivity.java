package de.fhbielefeld.swe.raumkontrollapp_h;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import de.fhbielefeld.swe.raumkontrollapp_h.databinding.ActivityRaumBinding;

public class RaumActivity extends AppCompatActivity {
    private Raum aktuellerRaum;

    ImageButton imageButton_neueAusstattung;
    TextView textView_Stuehle, textView_Tische, textView_Raumnummer, textView_Eigenschaften,
            textView_Ausstattung;
    EditText editText_anzahlStuehle, editText_anzahlTische;

    private AppBarConfiguration appBarConfiguration;
    private ActivityRaumBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRaumBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //setSupportActionBar(binding.toolbar);
        aktuellerRaum = getIntent().getExtras().getParcelable("Raum");

        binding.fabAusstattungHinzufuegen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}