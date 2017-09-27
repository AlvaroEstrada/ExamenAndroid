package com.alvaropedrajas.examenandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Objects;

public class EditActivity extends AppCompatActivity implements View.OnClickListener{

    EditText et_name, et_mail, et_phone;
    String nom, mail;
    Integer tel;

    private static final int EDIT_OK = 100;

    boolean delFlag;

    Contacto contacto;
    FileOutputStream archivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Button btnBack = (Button) findViewById(R.id.btnBack);
        Button btnDel = (Button) findViewById(R.id.btnEdit);
        et_name = (EditText) findViewById(R.id.et_name);
        et_mail = (EditText) findViewById(R.id.et_mail);
        et_phone = (EditText) findViewById(R.id.et_phone);
        btnBack.setOnClickListener(this);
        btnDel.setOnClickListener(this);

        if (getIntent().hasExtra("cont")){
            Contacto c = (Contacto) getIntent().getSerializableExtra("cont");

            et_name.setText(c.getNombre().toString());
            et_mail.setText(c.getMail().toString());
            et_phone.setText(c.getTelefono().toString());
        }

    }

    public void getDatos(View v){

        if (!TextUtils.isEmpty(et_name.getText().toString())){
            nom = et_name.getText().toString();
        }else{
            et_name.setError("Debes introducir un nombre!");
            nom = null;
        }

        if (!TextUtils.isEmpty(et_mail.getText().toString())){
            mail = et_mail.getText().toString();
        }else{
            et_mail.setError("Debes introducir un email!");
            mail = null;
        }

        if (!TextUtils.isEmpty(et_phone.getText().toString())){
            tel = Integer.parseInt(et_phone.getText().toString());
        }else{
            et_phone.setError("Debes introducir un teléfono!");
            tel = null;
        }

        if (!Objects.equals(nom, null) && !Objects.equals(mail, null) && !Objects.equals(tel, null)){
            delFlag = true;
        }else{
            delFlag = false;
            Toast.makeText(this, "¡Debes rellenar todos los campos!", Toast.LENGTH_LONG).show();
            return;
        }
    }

    public Contacto regDatos(String nom, String mail, Integer tel){
        contacto.setNombre(nom);
        contacto.setMail(mail);
        contacto.setTelefono(tel);

        return contacto;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data.hasExtra("cont")){
            Contacto c = (Contacto) data.getSerializableExtra("cont");

            et_name.setText(c.getNombre().toString());
            et_mail.setText(c.getMail().toString());
            et_phone.setText(Integer.parseInt(c.getTelefono().toString()));

            ObjectOutputStream x = null;
            try {
                archivo = this.openFileOutput("ListaContactos.dat", this.MODE_PRIVATE);
                x = new ObjectOutputStream(archivo);
                x.writeObject(c);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(this, c.getNombre() + " ha sido editado", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBack:
                setResult(RESULT_CANCELED);
                finish();
                break;
            case R.id.btnEdit:
                getDatos(v);
                if (delFlag){
                    ObjectOutputStream x = null;
                    try {
                        archivo = this.openFileOutput("ListaContactos.dat", this.MODE_PRIVATE);
                        x = new ObjectOutputStream(archivo);
                        x.writeObject(contacto);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(this, contacto.getNombre() + " ha sido editado", Toast.LENGTH_LONG).show();
                    finish();


                }
                break;
        }
    }
}
