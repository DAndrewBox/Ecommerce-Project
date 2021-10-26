package com.example.proyectosqlite;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class Informes extends AppCompatActivity {

    private Producto productoMasVendido;
    private TextView codigo, nombre, precio, cantidad_vendida;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informe_producto_mas_vendido);
        productoMasVendido = carga_datos();
        codigo = findViewById(R.id.informe_txt_codigo);
        nombre = findViewById(R.id.informe_txt_producto);
        precio = findViewById(R.id.informe_txt_precio);
        cantidad_vendida = findViewById(R.id.informe_txt_cantidad);

        codigo.setText(productoMasVendido.getCodigo());
        nombre.setText(productoMasVendido.getNombre());
        precio.setText(productoMasVendido.getPrecio());
        cantidad_vendida.setText(productoMasVendido.getCantidad_vendida());

    }


    public Producto carga_datos(){
        MantenedorProducto auxMantenedorProducto = new MantenedorProducto(this);
        Producto masVendido = auxMantenedorProducto.obtenerMasVendido();

        return masVendido;
    }
}
