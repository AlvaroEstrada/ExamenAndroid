package com.alvaropedrajas.examenandroid;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends Activity implements View.OnClickListener{

    private static ListView listView;
    private static ArrayList<Contacto> contactos = new ArrayList<Contacto>();
    private static int pos;
    private static Contacto cont = new Contacto();
    private Activity activity = this;
    private static ContactoAdaptador adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_list);

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        contactos = Utils.readFile(this);
        listView = (ListView) activity.findViewById(R.id.lv_main);
        updateList();


        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
            {
                contactos = Utils.readFile(activity);
                pos = position;
                contactos.get(pos);
                Utils.setPosition(pos);
                dialog();
                updateList();
            }
        });

    }

    public void dialog() {

        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setMessage("¿Que desea hacer?");
        alerta.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface diag, int i) {
                Utils.getDatos(contactos.get(pos), cont, activity);
                Utils.goDelete(activity, cont);
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
                Utils.getDatos(contactos.get(pos), cont, activity);
                Utils.goEdit(activity, cont);
                updateList();
            }
        });
        alerta.show();
    }

    @Override
    public void onClick (View v){
        finish();
    }

    @Override
    protected void onResume() {
        updateList();
        super.onResume();
    }

    @Override
    protected void onStart() {
        updateList();
        super.onStart();
    }

    public void updateList(){
        adaptador = new ContactoAdaptador(activity, Utils.readFile(this));
        listView.setAdapter(adaptador);
    }
}
