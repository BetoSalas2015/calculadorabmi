package com.betosoft.calculadorabmi;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView txtResultado;
    private EditText edtPeso, edtEstatura;
    private Button btnCalculo;
    private SharedPreferences misDatos;
    String peso, estatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResultado = findViewById(R.id.txtResultado);
        edtPeso = findViewById(R.id.edtPeso);
        edtEstatura = findViewById(R.id.edtEstatura);
        btnCalculo = findViewById(R.id.btnCalculo);

        btnCalculo.setOnClickListener( v -> calculaBMI() );

        misDatos = getSharedPreferences("Datos", MODE_PRIVATE);
        edtPeso.setText( misDatos.getString("peso",""));
        edtEstatura.setText( misDatos.getString("estatura", ""));
    }

    public void calculaBMI()
    {
        int masa = Integer.parseInt( edtPeso.getText().toString() );
        double estatura = Double.parseDouble( edtEstatura.getText().toString() ) / 100.0;
        double bmi = masa / Math.pow(estatura,2);
        txtResultado.setText( "Su BMIv es " + String.valueOf(bmi));  // Hard coded. Se resolverá mas tarde
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = misDatos.edit();
        estatura = edtEstatura.getText().toString();
        peso = edtPeso.getText().toString();

        editor.putString("peso", peso);
        editor.putString("estatura", estatura);
        editor.apply();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        SharedPreferences.Editor editor = misDatos.edit();
        estatura = edtEstatura.getText().toString();
        peso = edtPeso.getText().toString();

        editor.putString("peso", peso);
        editor.putString("estatura", estatura);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        edtPeso.setText( misDatos.getString("peso",""));
        edtEstatura.setText( misDatos.getString("estatura", ""));
    }
}