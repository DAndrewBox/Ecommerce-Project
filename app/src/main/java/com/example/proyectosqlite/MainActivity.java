package com.example.proyectosqlite;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lvitems;
    private Adaptador adaptador;
    private static ArrayList<Entidad> arrayListItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_productos);
        arrayListItem = getListItem();
        mostrar(arrayListItem);

        lvitems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetalleItem.class);
                intent.putExtra("objetoData", arrayListItem.get(position));
                startActivity(intent);
            }
        });
    }

    public void guardar(View view){
        try{
            EditText auxTxtCodigo = findViewById(R.id.inp_codigo);
            EditText auxTxtNombre = findViewById(R.id.inp_nombre);
            EditText auxTxtPrecio = findViewById(R.id.inp_precio);
            EditText auxTxtCantidad = findViewById(R.id.inp_cantidad);

            Producto auxProducto = new Producto();
            auxProducto.setCodigo(auxTxtCodigo.getText().toString());
            auxProducto.setNombre(auxTxtNombre.getText().toString());
            auxProducto.setPrecio(auxTxtPrecio.getText().toString());
            auxProducto.setCantidad(auxTxtCantidad.getText().toString());

            MantenedorProducto auxMantenedor = new MantenedorProducto(this);
            auxMantenedor.insertarProducto(auxProducto);

            this.mensaje("Datos Guardados Correctamente");
            auxTxtCodigo.setText("");
            auxTxtNombre.setText("");
            auxTxtPrecio.setText("");
            auxTxtCantidad.setText("");
            auxTxtCodigo.requestFocus();
        }
        catch(Exception ex){
            this.mensaje("Datos no guardados " + ex.getMessage());
        }
    }


    public ArrayList<Entidad> getListItem(){
        MantenedorProducto auxMantenedorProducto = new MantenedorProducto(this);
        List<Producto> auxLista = auxMantenedorProducto.consultaProductos();
        ArrayList<Entidad> auxListaEntidad = new ArrayList<>();

        Iterator iter = auxLista.iterator();

        while(iter.hasNext()){
            Producto auxProducto = new Producto();
            auxProducto = (Producto) iter.next();

            auxListaEntidad.add(new Entidad(auxProducto.getCodigo(),auxProducto.getNombre(),auxProducto.getPrecio(),auxProducto.getCantidad()));

        }
        return auxListaEntidad;
    }

    //Borrar
    public void mostrar(ArrayList<Entidad> array){
        lvitems = (ListView) findViewById(R.id.listaProductos);
        adaptador = new Adaptador(this,array);
        lvitems.setAdapter(adaptador);
    }

    public void mensaje(String auxMensaje){
      Toast.makeText(this,auxMensaje,Toast.LENGTH_LONG).show();
    }

    public void cambiar(View view){
        setContentView(R.layout.mantenedor_productos);
    }

    public void limpiar_campo(View view){
        EditText auxTxtCodigo = findViewById(R.id.inp_codigo);
        EditText auxTxtNombre = findViewById(R.id.inp_nombre);
        EditText auxTxtPrecio = findViewById(R.id.inp_precio);
        EditText auxTxtCantidad = findViewById(R.id.inp_cantidad);

        auxTxtCodigo.setText("");
        auxTxtNombre.setText("");
        auxTxtPrecio.setText("");
        auxTxtCantidad.setText("");
        auxTxtCodigo.requestFocus();

        this.mensaje("Campos limpiados");
    }

    public void volver(View view){
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
        //setContentView(R.layout.lista_productos);
        //mostrar(arrayListItem);
    }

}