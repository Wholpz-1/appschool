package com.example.appschool;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appschool.Entidades.EntIns;

import java.util.ArrayList;

public class ListaInscripcionAdapter extends RecyclerView.Adapter<ListaInscripcionAdapter.ViewHolderDatos> {

    ArrayList<EntIns> listInscripcion;
    public ListaInscripcionAdapter(ArrayList<EntIns> listInscripcion){
        this.listInscripcion = listInscripcion;
    }
    @NonNull
    @Override
    public ListaInscripcionAdapter.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from((parent.getContext())).inflate(R.layout.iteminscripcion, null, false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaInscripcionAdapter.ViewHolderDatos holder, int position) {

        holder.iteminsID.setText(listInscripcion.get(position).getId());
        holder.campoinsalu.setText(listInscripcion.get(position).getNombrealu());
        holder.campoinsgra.setText(listInscripcion.get(position).getGradoalu());
        holder.campoinssec.setText(listInscripcion.get(position).getSecalu());
        holder.campoinsfecha.setText(listInscripcion.get(position).getFehca_ins());
        Glide.with(holder.imgalu.getContext()).load(listInscripcion.get(position).getInsimg()).into(holder.imgalu);



    }

    @Override
    public int getItemCount() { return  listInscripcion.size(); }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        ImageView imgalu;

        TextView iteminsID, campoinsalu, campoinsgra, campoinssec, campoinsfecha;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            iteminsID = (TextView) itemView.findViewById(R.id.iteminsID);
            campoinsalu = (TextView) itemView.findViewById(R.id.iteminsNombre);
            campoinsgra = (TextView) itemView.findViewById(R.id.iteminsGrado);
            campoinssec = (TextView) itemView.findViewById(R.id.iteminsSeccion);
            campoinsfecha = (TextView) itemView.findViewById(R.id.iteminsfecha);
            imgalu = (ImageView) itemView.findViewById(R.id.imgalu);
        }
    }
}
