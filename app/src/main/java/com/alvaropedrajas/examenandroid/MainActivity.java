package com.alvaropedrajas.examenandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ContentFrameLayout;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private ArrayList<Contacto> listaContactos = new ArrayList<>();
    //FileOutputStream archivo;
    private Activity activity = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        Button btnDel = (Button) findViewById(R.id.btnDel);
        Button btnList = (Button) findViewById(R.id.btnList);
        btnAdd.setOnClickListener(this);
        btnDel.setOnClickListener(this);
        btnList.setOnClickListener(this);
        /*try {
            archivo = this.openFileOutput("ListaContactos.dat", this.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/

        //test();
    }

    /*public void test(){
        listaContactos.add(new Contacto("Alvaro", "a@a.com", 123456));
        listaContactos.add(new Contacto("Margarita", "e@e.com", 98462));

        ObjectOutputStream x = null;
        try {
            archivo = this.openFileOutput("ListaContactos.dat", this.MODE_PRIVATE);
            x = new ObjectOutputStream(archivo);
            x.writeObject(listaContactos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/


    @Override
    public void onClick(View v) {
        listaContactos = Utils.readFile(activity);
        switch (v.getId()){
            case R.id.btnAdd:
                Intent intAdd = new Intent(this, AddActivity.class);
                this.startActivity(intAdd);
                break;

            case R.id.btnDel:
                if (listaContactos.size() > 0){
                    Intent intDel = new Intent(this, DeleteActivity.class);
                    this.startActivity(intDel);
                }else{
                    Toast.makeText(this, "¡La lista de contactos está vacia!", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.btnList:
                if (listaContactos.size() > 0){
                    Intent intList = new Intent(this, ListActivity.class);
                    this.startActivity(intList);
                }else{
                    Toast.makeText(this, "¡La lista de contactos está vacia!", Toast.LENGTH_LONG).show();
                }

                break;
        }

    }
}
