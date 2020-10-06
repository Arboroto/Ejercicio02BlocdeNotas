package com.cieep.ejercicio02_blocdenotas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cieep.ejercicio02_blocdenotas.modelos.Nota;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final int EDIT_NOTA = 2;
    private final int CREAR_ACTIVITY = 1;
    // Variables Vista
    private LinearLayout contenedor;
    private Button btnAgregar;

    // Variables para los modelos
    private ArrayList<Nota> listaNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contenedor = findViewById(R.id.contenedorMain);
        btnAgregar = findViewById(R.id.btnAgregarMain);
        listaNotas = new ArrayList<>();

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CrearActivity.class);
                startActivityForResult(intent, CREAR_ACTIVITY);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CREAR_ACTIVITY && resultCode == RESULT_OK) {
            if (data != null) {
                final Nota nota = data.getExtras().getParcelable("NOTA");
                listaNotas.add(nota);
                repintarNotas();
            }
        }
        if (requestCode == EDIT_NOTA && resultCode==RESULT_OK) {
            if (data != null){
                int posicion = data.getExtras().getInt("POS");
                Nota nota = data.getExtras().getParcelable("NOTA");
                listaNotas.get(posicion).setTitulo(nota.getTitulo());
                listaNotas.get(posicion).setContenido(nota.getContenido());
                repintarNotas();
            }
        }
    }

    private void repintarNotas() {
        contenedor.removeAllViews();
        for (int i = 0; i < listaNotas.size(); i++) {
            final Nota nota = listaNotas.get(i);
            final int posicion = i;

            View filanota =LayoutInflater.from(this).inflate(R.layout.fila_nota, null);

            // TextView
            TextView txtTitulo = filanota.findViewById(R.id.txtTituloFilanota);
            txtTitulo.setText(nota.getTitulo());

            txtTitulo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("POS", posicion);
                    bundle.putParcelable("NOTA", nota);
                    Intent intent = new Intent(MainActivity.this, EditNotaActivity.class);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, EDIT_NOTA);
                }
            });

            // Button
            ImageButton btnEliminar =filanota.findViewById(R.id.btnEliminarFilanota);

            btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listaNotas.remove(posicion);
                    repintarNotas();
                }
            });

            contenedor.addView(filanota);
        }
    }
}