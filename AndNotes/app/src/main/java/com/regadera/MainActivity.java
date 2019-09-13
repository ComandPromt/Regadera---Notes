package com.regadera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText text,tipo,descripcion;

    static String notaBusqueda;
    static String notaTipo;
    static String notaDescripcion;

    public static String getNotaBusqueda() {
        return notaBusqueda;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (EditText)findViewById(R.id.nombre_nota);
        tipo = (EditText)findViewById(R.id.tipo);
        descripcion = (EditText)findViewById(R.id.descripcion);
    }

    public void Notas(View view){
        notaBusqueda = text.getText().toString();
        notaTipo = tipo.getText().toString();
        notaDescripcion = descripcion.getText().toString();
        Intent notas = new Intent(this, Nota.class);
        startActivity(notas);
    }

    public static String getNotaTipo() {
        return notaTipo;
    }

    public static String getNotaDescripcion() {
        return notaDescripcion;
    }
}
