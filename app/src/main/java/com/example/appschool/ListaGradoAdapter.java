package com.example.appschool;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.appschool.Entidades.EntGrado;

import java.util.ArrayList;

public class ListaGradoAdapter extends RecyclerView.Adapter<ListaGradoAdapter.ViewHolderDatos> {

    ArrayList<EntGrado> listGrado;
    public ListaGradoAdapter(ArrayList<EntGrado>listGrado){
        this.listGrado = listGrado;
    }
    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from((parent.getContext())).inflate(R.layout.itemgrado, null, false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.itemgraid.setText(listGrado.get(position).getId());
        holder.itemdescripcion.setText(listGrado.get(position).getDescripcion());


    }

    @Override
    public int getItemCount() {
        return  listGrado.size(); }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView itemgraid, itemdescripcion;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            itemgraid=(TextView) itemView.findViewById(R.id.itemgraid);
            itemdescripcion=(TextView) itemView.findViewById(R.id.itemdescripcion);
        }
    }
}
