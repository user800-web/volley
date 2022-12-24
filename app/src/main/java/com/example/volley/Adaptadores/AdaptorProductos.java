package com.example.volley.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.volley.Models.Producto;
import com.example.volley.R;

import java.util.ArrayList;

public class AdaptorProductos extends ArrayAdapter<Producto> {

    public AdaptorProductos(Context context, ArrayList<Producto> datos) {
        super(context, R.layout.lyt_products, datos);
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.lyt_products, null);

        TextView txtId = (TextView)item.findViewById(R.id.txtId);
        txtId.setText("ID: "+getItem(position).getId());

        TextView txtNombre = (TextView)item.findViewById(R.id.txtNombre);
        txtNombre.setText("Nombre: "+getItem(position).getNombre());

        TextView txtClase = (TextView)item.findViewById(R.id.txtClase);
        txtClase.setText("Clase: "+getItem(position).getclase());

        TextView txtPrecioU = (TextView)item.findViewById(R.id.txtPrecioUnit);
        txtPrecioU.setText("Costo: $"+getItem(position).getPrecioUnitario());

        return(item);
    }
}
