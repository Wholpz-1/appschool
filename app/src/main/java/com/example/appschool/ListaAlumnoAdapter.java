package com.example.appschool;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appschool.Entidades.EntAlu;

import java.util.ArrayList;

public class ListaAlumnoAdapter extends RecyclerView.Adapter<ListaAlumnoAdapter.ViewHolderDatos> {

    ArrayList<EntAlu> listAlumno;
    public ListaAlumnoAdapter(ArrayList<EntAlu> listUsuario) {

        this.listAlumno = listUsuario;
    }
    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from((parent.getContext())).inflate(R.layout.itemalumno,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {

        holder.itemId.setText(listAlumno.get(position).getId());
        holder.itemNombre.setText(listAlumno.get(position).getNombre());
        holder.itemApellido.setText(listAlumno.get(position).getApellido());
        holder.itemTelefono.setText(listAlumno.get(position).getTelefono());
        holder.itemDireccion.setText(listAlumno.get(position).getDireccion());


    }

    @Override
    public int getItemCount() {
        return listAlumno.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView itemId,  itemNombre, itemApellido, itemTelefono, itemDireccion;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            itemId=(TextView) itemView.findViewById(R.id.itemId);
            itemNombre=(TextView) itemView.findViewById(R.id.itemNombre);
            itemApellido=(TextView) itemView.findViewById(R.id.itemApellidos);
            itemTelefono=(TextView) itemView.findViewById(R.id.itemTelefono);
            itemDireccion=(TextView) itemView.findViewById(R.id.itemDireccion);
        }
    }

}

