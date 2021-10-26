package com.example.proyectosqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ActivityMostrarCliente extends AppCompatActivity {
    private ListView LVItems;
    private Adapter adapter;
    public static final String EXTRA_MESSAGE = "com.example.ProyectoSQLite.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar_clientes);

        try {
            showClients(LVItems);
        } catch (Exception e) {
            // Do nothing
        }
    }

    // Mostrar Clientes
    public void showClients(View view) {
        MantenedorCliente mantenedor = new MantenedorCliente(this);
        List<Cliente> auxLista =  mantenedor.consultarCliente();

        ArrayList<Cliente> itemsArray = new ArrayList<>();
        int pos = 0;

        Iterator iter = auxLista.iterator();

        while (iter.hasNext()) {
            Cliente auxCliente;
            auxCliente = (Cliente) iter.next();
            itemsArray.add(pos, auxCliente);
            pos++;
        }

        LVItems = (ListView) findViewById(R.id.listClientes);
        adapter = new AdapterCliente(this, itemsArray);

        LVItems.setAdapter((ListAdapter) adapter);
    }

    // Editar Cliente
    public void editCliente(View view) {
        String valRut = view.getTag().toString();

        Intent intent = new Intent(this, ActivityEditarCliente.class);
        intent.putExtra(EXTRA_MESSAGE, valRut);
        startActivity(intent);
    }

    // Volver al Menu
    public void toMenu(View view) {
        Intent intent = new Intent(this, MenuMantenedor.class);
        startActivity(intent);
    }

    // Ir a Agregar Cliente
    public void toAdd(View view) {
        Intent intent = new Intent(this, ActivityAgregarCliente.class);
        startActivity(intent);
    }
}
