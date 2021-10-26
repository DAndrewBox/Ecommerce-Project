package com.example.proyectosqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    private Context context;
    private ArrayList<Entidad> listItems;

    public Adaptador(Context context, ArrayList<Entidad> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Entidad item = (Entidad) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.item,null);
        TextView codigo = convertView.findViewById(R.id.codigo);
        TextView nombre = convertView.findViewById(R.id.nombre);
        TextView precio = convertView.findViewById(R.id.precio);
        TextView cantidad = convertView.findViewById(R.id.cantidad);

        codigo.setText(item.getCodigo());
        nombre.setText(item.getNombre());
        precio.setText(item.getPrecio());
        cantidad.setText(item.getCantidad());

        return convertView;
    }
}
