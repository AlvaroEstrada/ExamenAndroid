package com.alvaropedrajas.examenandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactoAdaptador extends BaseAdapter{

    private Context context;
    private ArrayList<Contacto> contactos;


    public ContactoAdaptador(Context context, ArrayList<Contacto> contactos) {
        this.context = context;
        this.contactos = contactos;
    }

    @Override
    public int getCount() {
        return this.contactos.size();
    }

    @Override
    public Object getItem(int position) {
        return this.contactos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View item_lista = convertView;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            item_lista = inflater.inflate(R.layout.item_contact, parent, false);
        }

        TextView nombre = (TextView) item_lista.findViewById(R.id.tv_name);
        TextView telefono = (TextView) item_lista.findViewById(R.id.tv_phone);
        TextView mail = (TextView) item_lista.findViewById(R.id.tv_mail);
        ImageView img = (ImageView) item_lista.findViewById(R.drawable.ic_action_name);

        Contacto contacto = this.contactos.get(position);
        nombre.setText(contacto.getNombre().toString());
        telefono.setText(contacto.getTelefono().toString());
        mail.setText(contacto.getMail().toString());

        return item_lista;
    }


}
