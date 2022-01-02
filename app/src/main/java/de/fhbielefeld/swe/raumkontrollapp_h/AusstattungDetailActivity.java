package de.fhbielefeld.swe.raumkontrollapp_h;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.fhbielefeld.swe.raumkontrollapp_h.databinding.FragmentAusstattungsDetailBinding;


public class AusstattungDetailActivity extends AppCompatActivity
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
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ausstattung_detail);

        tv_total = findViewById(R.id.tv_total);
        value = (TextView) findViewById(R.id.tv_total);
        counterTxt = (TextView) findViewById(R.id.counterTxt);
        minusBtn = (Button) findViewById(R.id.minusBtn);
        //minusBtn.setOnClickListener(clickListener);

        plusBtn = (Button) findViewById(R.id.plusBtn);
        //plusBtn.setOnClickListener(clickListener);

        resetBtn = (Button) findViewById(R.id.resetBtn);
        //resetBtn.setOnClickListener(clickListener);



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
}