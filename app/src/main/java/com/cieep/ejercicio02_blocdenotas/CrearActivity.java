package com.cieep.ejercicio02_blocdenotas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cieep.ejercicio02_blocdenotas.modelos.Nota;

public class CrearActivity extends AppCompatActivity {

    // Variables para la Vista
    private EditText txtTitulo, txtContenido;
    private Button btnGuardar;

    // Variables para el modelo de datos


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);

        txtTitulo = findViewById(R.id.txtTituloCrear);
        txtContenido = findViewById(R.id.txtContenidoCrear);
        btnGuardar = findViewById(R.id.btnGuardarCrear);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtTitulo.getText().toString().isEmpty() &&
                    !txtContenido.getText().toString().isEmpty()) {
                    Nota nota = new Nota(txtTitulo.getText().toString(), txtContenido.getText().toString());
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("NOTA", nota);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                else {
                    Toast.makeText(CrearActivity.this, "Los dos campos son obligatorios", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}