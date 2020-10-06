package com.cieep.ejercicio02_blocdenotas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cieep.ejercicio02_blocdenotas.modelos.Nota;

public class EditNotaActivity extends AppCompatActivity {

    private EditText txtTitulo, txtContenido;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_nota);

        txtTitulo = findViewById(R.id.txtTituloEdit);
        txtContenido = findViewById(R.id.txtContenidoEdit);

        btnGuardar = findViewById(R.id.btnGuardarEdit);
        final Nota nota = getIntent().getExtras().getParcelable("NOTA");
        final int posicion = getIntent().getExtras().getInt("POS");

        txtTitulo.setText(nota.getTitulo());
        txtContenido.setText(nota.getContenido());

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nota.setTitulo(txtTitulo.getText().toString());
                nota.setContenido(txtContenido.getText().toString());
                Bundle bundle = new Bundle();
                bundle.putParcelable("NOTA", nota);
                bundle.putInt("POS", posicion);
                Intent intent = new Intent();
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}