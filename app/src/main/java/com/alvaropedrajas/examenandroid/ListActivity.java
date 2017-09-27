package com.alvaropedrajas.examenandroid;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ListActivity extends Activity implements View.OnClickListener{

    private ListView listView;
    ArrayList<Contacto> contactos = new ArrayList<Contacto>();
    private int pos;
    private Contacto cont = new Contacto();
    Activity activity;
    ContactoAdaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //contactos = (ArrayList<Contacto>)getIntent().getSerializableExtra("listaC");

        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_list);

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);


        FileInputStream in = null;
        ObjectInputStream b = null;
        try {
            in = this.openFileInput("ListaContactos.dat");
            b = new ObjectInputStream(in);
            contactos = (ArrayList<Contacto>) b.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        this.listView = (ListView) findViewById(R.id.lv_main);
        adaptador = new ContactoAdaptador(this, contactos);
        this.listView.setAdapter(adaptador);
        adaptador.notifyDataSetChanged();

        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
            {
                pos = position;
                contactos.get(pos);
                dialog();
            }
        });

    }

    public void dialog() {

        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setMessage("¿Que desea hacer?");
        alerta.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface diag, int i) {

                getDatos(contactos.get(pos));

                Intent intDel = new Intent(ListActivity.this, DeleteActivity.class);
                intDel.putExtra("cont", cont);
                startActivityForResult(intDel, 0);
                Toast.makeText(ListActivity.this, "Has seleccionado Borrar", Toast.LENGTH_SHORT).show();
            }
        });
        alerta.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface diag, int i) {
                diag.cancel();
            }
        });
        alerta.setNegativeButton("Editar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface diag, int i) {

                getDatos(contactos.get(pos));

                Intent intEdit = new Intent(ListActivity.this, EditActivity.class);
                intEdit.putExtra("cont", cont);
                startActivityForResult(intEdit, 0);
                Toast.makeText(ListActivity.this, "Has seleccionado Editar", Toast.LENGTH_SHORT).show();

            }
        });
        alerta.show();
    }

    public void getDatos(Contacto pos){

        cont.setNombre(pos.getNombre().toString());
        cont.setMail(pos.getMail().toString());
        cont.setTelefono(Integer.parseInt(pos.getTelefono().toString()));

    }

    @Override
    public void onClick (View v){
        finish();
    }

}
