package com.example.apiservices;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class EquipoAdapter extends ArrayAdapter<Competition> {

    public EquipoAdapter(@NonNull Context context, @NonNull ArrayList<Competition> competitions) {
        super(context, 0, competitions);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Competition comp = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_equipos, parent, false);
        }

        // IDs corregidos (respetando mayúsculas y minúsculas)
        TextView id = convertView.findViewById(R.id.txtId);
        TextView name = convertView.findViewById(R.id.txtName);
        TextView web = convertView.findViewById(R.id.txtWeb);
        TextView fundado = convertView.findViewById(R.id.txtFundado);
        TextView code = convertView.findViewById(R.id.txtCode);

        // Conversión segura a String si es necesario
        id.setText(String.valueOf(comp.getId()));
        name.setText(comp.getName());
        web.setText(comp.getWeb());
        fundado.setText(String.valueOf(comp.getFundado()));
        code.setText(comp.getCode());

        return convertView;
    }
}