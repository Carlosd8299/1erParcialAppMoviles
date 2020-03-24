package com.cdlc.parcial1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Listar extends AppCompatActivity {

    ListView listado;
    ArrayList<String> peli;
    ArrayAdapter<Pelicula> arrayAdapter;
    ArrayList<Pelicula> peliculas;
    Registrar re;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        peli = new ArrayList<>();
        Intent in = getIntent();

        peliculas = in.getParcelableArrayListExtra("est");
        Toast.makeText(this,"Pelicula = "+ peliculas.size(),Toast.LENGTH_LONG).show();

        listado = findViewById(R.id.lstlistado);
        arrayAdapter = new PeliculaAdapter(this, peliculas);

        listado.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ordenar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.mnEliminar:
                arrayAdapter.notifyDataSetChanged();

                AlertEliminar("Eliminando primer");
            //    EliminarPrimer();
                arrayAdapter.notifyDataSetChanged();
                break;

            case R.id.mnOrGenero:
                OrdenarXGenero();
                arrayAdapter.notifyDataSetChanged();
                break;

            case R.id.mnOrNombre:
                OrdenarXNombre();
                arrayAdapter.notifyDataSetChanged();
                break;
            case R.id.mnInvertir:
                InvertirLista();
                arrayAdapter.notifyDataSetChanged();
                break;


        }return super.onOptionsItemSelected(item);
    }


    public void OrdenarXGenero(){

        Collections.sort(peliculas, new Comparator<Pelicula>() {
            @Override
            public int compare(Pelicula p1, Pelicula p2) {

                return new String(p1.getGenero()).compareTo(new String(p2.getGenero()));

            }
        });

    }

    public void OrdenarXNombre(){
        Collections.sort(peliculas, new Comparator<Pelicula>() {
            @Override
            public int compare(Pelicula p1, Pelicula p2) {

                return new String(p1.getNombre()).compareTo(new String(p2.getNombre()));

            }
        });

    }

    public void InvertirLista(){
        Collections.reverse(peliculas);

    }

    public void EliminarPrimer(){

        if (!peliculas.isEmpty()){
            peliculas.remove(0);

        }
    }

    private void AlertEliminar(String mensaje){

        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("Importante");
        dialogo1.setMessage(mensaje);
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogo1, int id) {
                aceptar2();

            }
        });
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                cancelar2();
            }
        });
        dialogo1.show();


    }
    public void aceptar2() {

        Toast t=Toast.makeText(this,"Se ha ejecutado la accion", Toast.LENGTH_SHORT);
        t.show();
        arrayAdapter.notifyDataSetChanged();

        EliminarPrimer();
        arrayAdapter.notifyDataSetChanged();


    }

    public void cancelar2() {
        finish();
    }


}
