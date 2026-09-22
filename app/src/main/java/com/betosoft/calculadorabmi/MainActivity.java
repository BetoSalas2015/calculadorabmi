package com.betosoft.calculadorabmi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView txtResultado;
    private EditText edtPeso, edtEstatura;
    private Button btnCalculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResultado = findViewById(R.id.txtResultado);
        edtPeso = findViewById(R.id.edtPeso);
        edtEstatura = findViewById(R.id.edtEstatura);
        btnCalculo = findViewById(R.id.btnCalculo);

        btnCalculo.setOnClickListener(this);
    }

    public void calculaBMI()
    {
        int masa = Integer.parseInt( edtPeso.getText().toString() );
        double estatura = Double.parseDouble( edtEstatura.getText().toString() ) / 100.0;
        double bmi = masa / Math.pow(estatura,2);
        txtResultado.setText( "Su BMIv es " + String.valueOf(bmi));  // Hard coded. Se resolverá mas tarde
    }

    @Override
    public void onClick(View view) {
        calculaBMI();
    }
}