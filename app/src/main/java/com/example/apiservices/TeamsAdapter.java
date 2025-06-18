package com.example.apiservices;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.TeamsViewHolder> {

    private List<Teams> lista;

    public TeamsAdapter(List<Teams> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public TeamsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_teams_competencia, parent, false);
        return new TeamsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamsViewHolder holder, int position) {
        Teams t = lista.get(position);
        holder.id.setText(t.getId());
        holder.name.setText(t.getName());
        holder.web.setText(t.getWeb());
        holder.fundado.setText(t.getFundado());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class TeamsViewHolder extends RecyclerView.ViewHolder {
        TextView id, name, web, fundado;

        public TeamsViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.txtId);
            name = itemView.findViewById(R.id.txtName);
            web = itemView.findViewById(R.id.txtWeb);
            fundado = itemView.findViewById(R.id.txtFundado);
        }
    }
}
