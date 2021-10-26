package com.example.proyectosqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AdapterCliente extends BaseAdapter {
    private Context context;
    private ArrayList<Cliente> listItems;

    public AdapterCliente(Context context, ArrayList<Cliente> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        int size = listItems.size();
        return size;
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
        Cliente Item = (Cliente) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.item_clientes, null);

        FloatingActionButton auxBtnEdit = convertView.findViewById(R.id.btnEditClient);
        TextView auxTxtRut = convertView.findViewById(R.id.txtItemRut);
        TextView auxTxtNombre = convertView.findViewById(R.id.txtItemNombre);
        TextView auxTxtEdad = convertView.findViewById(R.id.txtItemEdad);
        TextView auxTxtPrev = convertView.findViewById(R.id.txtItemPrev);
        TextView auxTxtPhone = convertView.findViewById(R.id.txtItemPhone);
        TextView auxTxtEmail = convertView.findViewById(R.id.txtItemEmail);

        auxTxtRut.setText(Item.getRut());
        auxTxtNombre.setText(Item.getNombre());
        auxTxtEdad.setText(Item.getEdad() + " AÑOS");
        auxTxtPrev.setText(Item.getPrev());
        auxTxtPhone.setText(Item.getPhone());
        auxTxtEmail.setText(Item.getEmail());
        auxBtnEdit.setTag(Item.getRut());

        return convertView;
    }
}
