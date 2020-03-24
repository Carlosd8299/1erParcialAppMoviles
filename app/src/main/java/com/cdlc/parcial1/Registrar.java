package com.cdlc.parcial1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;



public class Registrar extends AppCompatActivity implements View.OnClickListener {

    public Registrar() {
    }

    Button grabar, listar,cancelar;
    ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();
    EditText nombres, director;
    RadioButton español,ingles;
    RadioButton accion,suspenso,drama,comedia,otro;
    RadioGroup Genero;
    RadioGroup Idioma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        Intent i = getIntent();
        String nombre = i.getStringExtra("user");

        grabar= findViewById(R.id.btnGrabar);
        listar= findViewById(R.id.btnListar);
        cancelar = findViewById(R.id.btnCancelar);

        grabar.setOnClickListener(this);
        listar.setOnClickListener(this);
        cancelar.setOnClickListener(this);



        //Guardando los atributos
        nombres = findViewById(R.id.txtNombreL);
        director = findViewById(R.id.txtDirector);
        //Idioma
        español= findViewById(R.id.chEspañol);
        ingles= findViewById(R.id.chIngles);
        //Genero
        accion= findViewById(R.id.rdAccion);
        drama= findViewById(R.id.rdDrama);
        comedia=findViewById(R.id.rdComedia);
        suspenso= findViewById(R.id.rdSuspenso);
        otro=findViewById(R.id.rdOtro);
        //Grpos
        Genero= findViewById(R.id.radioGroup);
        Idioma= findViewById(R.id.chGroup);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.btnGrabar:
                String Mensaje="Esta agregando un nuevo Elemento \n ¿Desea continuar?";
                AlertDInsertar(Mensaje);

                break;

            case R.id.btnListar :
                Intent i = new Intent(this,Listar.class);
                i.putParcelableArrayListExtra("est", peliculas);
                startActivity(i);

                break;

            case R.id.btnCancelar:
                finish();
                break;

        }

    }


    private void RegistrarPelicula(){

        String nombre = nombres.getText().toString();
        String directors = director.getText().toString();
        String idioma="";
        String genero="";

        if(drama.getId() == Genero.getCheckedRadioButtonId()){
            genero = "Drama";
        }else{
            if(accion.getId()== Genero.getCheckedRadioButtonId()){
                genero = "Accion";}
            if(comedia.getId()== Genero.getCheckedRadioButtonId()){
                genero = "Comedia";}
            if(suspenso.getId()== Genero.getCheckedRadioButtonId()){
                genero = "Suspenso";}
            if(otro.getId()==Genero.getCheckedRadioButtonId()){

            }
        }

        if(español.getId()== Idioma.getCheckedRadioButtonId()){
            idioma = "Español";
        }else{
            if(ingles.getId()== Idioma.getCheckedRadioButtonId()){
                idioma = "Ingles";
            }
        }

        peliculas.add(new Pelicula(nombre,directors,idioma,genero));
        Toast.makeText(getApplicationContext(),"Registrado", Toast.LENGTH_SHORT).show();

    }

    public void OrdenarXGenero(){

        Collections.sort(peliculas, new Comparator<Pelicula>() {
            @Override
            public int compare(Pelicula p1, Pelicula p2) {

                return new Integer(p1.getGenero()).compareTo(new Integer(p2.getGenero()));

            }
        });

    }

    public void OrdenarXNombre(){
        Collections.sort(peliculas, new Comparator<Pelicula>() {
            @Override
            public int compare(Pelicula p1, Pelicula p2) {

                return new Integer(p1.getNombre()).compareTo(new Integer(p2.getNombre()));

            }
        });

    }

    public void InvertirLista(){
        Collections.reverse(peliculas);

}

    public void EliminarPrimer(){

        if (!peliculas.isEmpty()){
            peliculas.remove(1);

        }
    }


    public void AlertDInsertar(String mensaje){

        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("Importante");
        dialogo1.setMessage(mensaje);
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogo1, int id) {
                aceptar();

            }
        });
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                cancelar();
            }
        });
        dialogo1.show();


    }
    public void aceptar() {
        Toast t=Toast.makeText(this,"Se ha ejecutado la accion", Toast.LENGTH_SHORT);
        t.show();
        RegistrarPelicula();
    }

    public void cancelar() {
        finish();
    }

    public void AlertEliminar(String mensaje){

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
        EliminarPrimer();
    }

    public void cancelar2() {
        finish();
    }


}

