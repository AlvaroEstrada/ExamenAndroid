package com.alvaropedrajas.examenandroid;

import android.app.Dialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

public class Dialogo extends DialogFragment {

    public Dialogo() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return createSingleListDialog();
    }

    public AlertDialog createSingleListDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final CharSequence[] items = new CharSequence[2];

        items[0] = "Editar";
        items[1] = "Borrar";

        builder.setTitle("¿Que desea hacer?")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Seleccionaste:" + items[which], Toast.LENGTH_SHORT).show();
                    }
                });

        return builder.create();
    }


}

