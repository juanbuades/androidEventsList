package com.example.ej05_juan_buades;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MiAdapter extends ArrayAdapter {

    Context context;
    int itemLayout;
    Elemento[] datos;

    public MiAdapter(@NonNull Context context, int resource, @NonNull Elemento[] objects) {
        super(context, resource, objects);
        this.context = context;
        itemLayout = resource;
        datos = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(itemLayout, parent, false);
        }

        TextView textoTV = convertView.findViewById(R.id.textoTV);
        textoTV.setText(datos[position].texto);
        TextView textoT2V = convertView.findViewById(R.id.textoTV);
        textoTV.setText(datos[position].texto2);


        return convertView;
    }
}
