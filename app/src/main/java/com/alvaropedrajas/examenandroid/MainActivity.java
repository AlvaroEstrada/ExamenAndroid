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

    private static final int ALTA = 100;
    private static final int BAJA = 200;
    private static final int LISTAR = 300;
    private static final int EDITAR = 400;
    private static final int BORRAR = 500;

    private ArrayList<Contacto> listaContactos = new ArrayList<>();
    FileOutputStream archivo;


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
        try {
            archivo = this.openFileOutput("ListaContactos.dat", this.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        test();
    }

    public void test(){
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
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case ALTA:
                if (resultCode == Activity.RESULT_OK) {
                    if (data.hasExtra("contacto")){
                        Contacto c = (Contacto) data.getSerializableExtra("contacto");
                        listaContactos.add(c);
                        ObjectOutputStream x = null;
                        try {
                            archivo = this.openFileOutput("ListaContactos.dat", this.MODE_PRIVATE);
                            x = new ObjectOutputStream(archivo);
                            x.writeObject(listaContactos);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(this, c.getNombre() + " ha sido guardado", Toast.LENGTH_LONG).show();
                    }
                } else if(resultCode == Activity.RESULT_CANCELED) {
                    Toast.makeText(this, "No se ha guardado ningún contacto", Toast.LENGTH_LONG).show();
                }
                break;
            case BAJA:
                if (resultCode == Activity.RESULT_OK) {
                    if (data.hasExtra("contacto")){
                        Contacto c = (Contacto) data.getSerializableExtra("contacto");
                        Toast.makeText(this,listaContactos.remove(c) ? c.getNombre()+ " ha sido eliminado": c.getNombre() + " no puede ser borrado. No existe." , Toast.LENGTH_LONG).show();
                    }
                } else if(resultCode == Activity.RESULT_CANCELED) {
                    Toast.makeText(this, "No se ha borrado ningún contacto", Toast.LENGTH_LONG).show();
                }
                break;
            case LISTAR:

                break;

            case EDITAR:
                if (resultCode == Activity.RESULT_OK) {
                    if (data.hasExtra("cont")){

                    }
                } else if(resultCode == Activity.RESULT_CANCELED) {
                    Toast.makeText(this, "No se ha editado ningún contacto", Toast.LENGTH_LONG).show();
                }
                break;
            case BORRAR:
                if (resultCode == Activity.RESULT_OK) {
                    if (data.hasExtra("contacto")){
                        Contacto c = (Contacto) data.getSerializableExtra("contacto");
                        listaContactos.remove(c);
                        ObjectOutputStream x = null;
                        try {
                            archivo = this.openFileOutput("ListaContactos.dat", this.MODE_PRIVATE);
                            x = new ObjectOutputStream(archivo);
                            x.writeObject(listaContactos);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(this, c.getNombre() + "ha sido eliminado", Toast.LENGTH_LONG).show();
                    }
                } else if(resultCode == Activity.RESULT_CANCELED) {
                    Toast.makeText(this, "No se ha eliminado ningún contacto", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAdd:
                Intent intAdd = new Intent(this, AddActivity.class);
                this.startActivityForResult(intAdd, ALTA);
                break;
            case R.id.btnDel:
                Intent intDel = new Intent(this, DeleteActivity.class);
                this.startActivityForResult(intDel, BAJA);
                break;
            case R.id.btnList:
                Intent intList = new Intent(this, ListActivity.class);
                intList.putExtra("listaC", listaContactos);
                this.startActivityForResult(intList, LISTAR);
                break;
        }

    }
}
