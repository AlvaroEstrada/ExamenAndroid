package com.alvaropedrajas.examenandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private ArrayList<Contacto> listaContactos = new ArrayList<>();
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
    }

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
