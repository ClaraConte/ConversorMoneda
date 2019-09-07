package com.example.conversormoneda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
private TextView resul;
private EditText euro;
private EditText dolar;
private RadioButton rbEuro;
private RadioButton rbDolar;
private Button boton;
private RadioGroup group;
private double valor = 0;
private double cotizacion = 0;

    private double euroCotizacion = 0.9;
    private double dolarCotizacion = 1.11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resul = findViewById(R.id.etValor);

        euro = findViewById(R.id.euro);
        euro.setEnabled(false);

        dolar = findViewById(R.id.dolar);
        dolar.setEnabled(false);

        group = findViewById(R.id.rbgGroup);
        rbEuro = findViewById(R.id.rbEuro);
        rbDolar = findViewById(R.id.rbDolar);

        boton = findViewById(R.id.btnConvertir);

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                View radioButton = group.findViewById(checkedId);
                int op = group.indexOfChild(radioButton);
                switch (op) {
                    case 0:
                        euro.setEnabled(true);
                        dolar.setEnabled(false);
                        dolar.setText("");
                        resul.setText("");
                        cotizacion = dolarCotizacion;
                        break;
                    case 1:
                        euro.setEnabled(false);
                        dolar.setEnabled(true);
                        euro.setText("");
                        resul.setText("");
                        cotizacion = euroCotizacion;
                        break;
                }
            }
        });

        boton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(!euro.getText().toString().isEmpty()){
                    valor = Double.valueOf(euro.getText().toString());
                } else if (!dolar.getText().toString().isEmpty()){
                    valor = Double.valueOf(dolar.getText().toString());
                }else{
                    valor = 0;
                    Toast.makeText(getApplicationContext(), "Debe ingresar un valor", 500).show();
                }

                String value = calcula(valor,cotizacion);
                resul.setText(value);
            }

            private String calcula (double value, double monto){

                DecimalFormat df = new DecimalFormat("0.00");
                return df.format(value * monto);
            }
        });
    }


}
