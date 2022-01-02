package de.fhbielefeld.swe.raumkontrollapp_h;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import de.fhbielefeld.swe.raumkontrollapp_h.databinding.FragmentAusstattungsDetailBinding;

public class AusstattungsDetailFragment extends Fragment
{




    private FragmentAusstattungsDetailBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        binding = FragmentAusstattungsDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();





    }



    public class MainActivity extends AppCompatActivity {


        private TextView counterTxt;
        private Button minusBtn;
        private Button plusBtn;
        private Button resetBtn;
        private int counter;
        private Button move;






        private View.OnClickListener clickListener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.minusBtn:
                        minusCounter();
                        break;
                    case R.id.plusBtn:
                        plusCounter();
                        break;
                    case R.id.resetBtn:
                        minusCounter();
                        break;

                }
            }

        };


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            counterTxt = (TextView) findViewById(R.id.counterTxt);
            minusBtn = (Button) findViewById(R.id.minusBtn);
            minusBtn.setOnClickListener(clickListener);
            plusBtn = (Button) findViewById(R.id.plusBtn);
            plusBtn.setOnClickListener(clickListener);
            resetBtn = (Button) findViewById(R.id.resetBtn);
            resetBtn.setOnClickListener(clickListener);


 /*
            move = findViewById (R.id.confirmBtn);
            move.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this,RaumActivity.class));
                }
            }
            );
  */


            initCounter();
        }

        private void initCounter() {
            counter = 0;
            counterTxt.setText(counter+"");
        }

        private void plusCounter(){
            counter++;
            counterTxt.setText(counter +"");
        }

        private void minusCounter(){
            counter--;
            counterTxt.setText(counter+"");
        }




    }




    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AusstattungsDetailFragment.this).navigate(R.id.action_AusstattungsDetailFragment_to_AusstattungHinzuFragment);
            }


        });







}}



