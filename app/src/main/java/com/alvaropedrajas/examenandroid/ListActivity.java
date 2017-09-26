package com.alvaropedrajas.examenandroid;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ListActivity extends Activity implements View.OnClickListener{

    private ListView listView;
    ArrayList<Contacto> contactos = new ArrayList<Contacto>();
    AlertDialog diag;

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
                FragmentManager fragmentManager = getFragmentManager();
                new Dialogo().show(fragmentManager, "Dialogo");
                //Toast.makeText(ListActivity.this, "" + position, Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onClick(View v) {
        finish();
    }

}
