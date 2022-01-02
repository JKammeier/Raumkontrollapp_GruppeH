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



public class AusstattungsDetailFragment extends AppCompatActivity
{

    private TextView counterTxt;
    private Button minusBtn;
    private Button plusBtn;
    private Button resetBtn;
    private int counter;
    private Button move;
    private FragmentAusstattungsDetailBinding binding;

    TextView value;
    int count = 0;

    TextView tv_total;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        tv_total = findViewById(R.id.tv_total);
        //value = (TextView)getfindViewById(R.id.tv_total);
        counterTxt = (TextView) findViewById(R.id.counterTxt);
        minusBtn = (Button) findViewById(R.id.minusBtn);
        //minusBtn.setOnClickListener(clickListener);
        plusBtn = (Button) findViewById(R.id.plusBtn);
        //plusBtn.setOnClickListener(clickListener);
        resetBtn = (Button) findViewById(R.id.resetBtn);


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

    }





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








    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentAusstattungsDetailBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        tv_total = view.findViewById(R.id.tv_total);
        value = (TextView)view.findViewById(R.id.tv_total);






        return view;
    }







   /* public class MainActivity extends AppCompatActivity {

        TextView value;
        int count =  0;

        @Override
        protected void onCreate(Bundle savedInstanceState){
            {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                value = (TextView) findViewById(R.id.tv_total);

            }
        }

        public void increment (View v)
        {
            count++;
            value.setText("" + count);

        }

        public void decrement(View v)
        {
            if  (count <= 0) count = 0;

            else count--;
            value.setText("" + count);}
        }


    */


    /*
    public class MainActivity extends AppCompatActivity {


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

/*
            initCounter();
        }
*/

    /*
        private void initCounter()
        {
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
     */

}





