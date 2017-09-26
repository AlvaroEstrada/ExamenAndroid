package com.alvaropedrajas.examenandroid;

import android.app.Activity;
import android.content.DialogInterface;
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
    TextView tv_name = (TextView) findViewById(R.id.tv_name);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        FileInputStream in = null;
        ObjectInputStream b = null;
        try {
            in = this.openFileInput("prueba.dat");
            b = new ObjectInputStream(in);
            contactos = (ArrayList<Contacto>) b.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //contactos = (ArrayList<Contacto>)getIntent().getSerializableExtra("listaC");

        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_list);

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        this.listView = (ListView) findViewById(R.id.lv_main);
        this.listView.setAdapter(new ContactoAdaptador(this, contactos));

        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
            {
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


                Toast.makeText(ListActivity.this, "Has seleccionado Editar", Toast.LENGTH_SHORT).show();

            }
        });
        alerta.show();
    }

    @Override
    public void onClick (View v){
        finish();
    }

}
