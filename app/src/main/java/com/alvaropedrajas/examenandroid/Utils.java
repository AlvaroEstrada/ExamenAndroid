package com.alvaropedrajas.examenandroid;


import android.app.Activity;
import android.content.Intent;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class Utils {

    private static int position;


    public static void getDatos(Contacto pos, Contacto cont, Activity activity){

        readFile(activity);

        cont.setNombre(pos.getNombre().toString());
        cont.setMail(pos.getMail().toString());
        cont.setTelefono(Integer.parseInt(pos.getTelefono().toString()));
    }

    public static void goDelete(Activity activity, Contacto cont){
        Intent intDel = new Intent(activity, DeleteActivity.class);
        intDel.putExtra("cont", cont);
        activity.startActivity(intDel);
        Toast.makeText(activity, "Has seleccionado Borrar", Toast.LENGTH_SHORT).show();
    }

    public static void goEdit(Activity activity, Contacto cont){
        Intent intEdit = new Intent(activity, EditActivity.class);
        intEdit.putExtra("contForEdit", cont);
        activity.startActivity(intEdit);
        Toast.makeText(activity, "Has seleccionado Editar", Toast.LENGTH_SHORT).show();
    }

    public static void rellenaCampos(Contacto c, TextView et_name, TextView et_mail, TextView et_phone){
        et_name.setText(c.getNombre().toString());
        et_mail.setText(c.getMail().toString());
        et_phone.setText(c.getTelefono().toString());
    }

    public static void setPosition(int pos){
        position = pos;
    }

    public static int getPosition(){
        return position;
    }

    public static ArrayList<Contacto> readFile(Activity activity){
        ObjectInputStream file = null;
        ArrayList<Contacto> listaContactos = new ArrayList<>();

        try {
            FileInputStream in = activity.openFileInput("ListaContactos.dat");
            file = new ObjectInputStream(in);
            listaContactos = (ArrayList<Contacto>) file.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (file != null) {
                    file.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return listaContactos;
        }
    }

    public static void escribirFichero(Activity activity, ArrayList<Contacto> lista){
        ObjectOutputStream x = null;
        try {
            FileOutputStream archivo = activity.openFileOutput("ListaContactos.dat", activity.MODE_PRIVATE);
            x = new ObjectOutputStream(archivo);
            x.writeObject(lista);
            x.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
