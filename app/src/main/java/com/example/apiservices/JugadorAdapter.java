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

public class JugadorAdapter extends ArrayAdapter<Jugador> {

    public JugadorAdapter(@NonNull Context context, ArrayList<Jugador> jugadores) {
        super(context, 0, jugadores);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Jugador jugador = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_jugador, parent, false);
        }

        TextView id = convertView.findViewById(R.id.txtidjugador);
        TextView nombre = convertView.findViewById(R.id.txtnombrejugador);
        TextView posicion = convertView.findViewById(R.id.txtposicionjugador);

        id.setText(jugador.getId());
        nombre.setText(jugador.getNombre());
        posicion.setText(jugador.getPosicion());

        return convertView;
    }
}