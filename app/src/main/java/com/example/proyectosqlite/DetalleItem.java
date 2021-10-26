package com.example.proyectosqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class DetalleItem extends AppCompatActivity {

    private Entidad item;
    private TextView codigo;
    private EditText nombre, precio, cantidad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_producto);
        item = (Entidad) getIntent().getSerializableExtra("objetoData");

        codigo = (TextView) findViewById(R.id.codigo_det);
        nombre = (EditText) findViewById(R.id.inp_nombre_det);
        precio = (EditText) findViewById(R.id.inp_precio_det);
        cantidad = (EditText) findViewById(R.id.inp_cantidad_det);

        codigo.setText(item.getCodigo());
        nombre.setText(item.getNombre());
        precio.setText(item.getPrecio());
        cantidad.setText(item.getCantidad());
    }

    public void guardar(View view){
        try{
            TextView auxTxtCodigo = findViewById(R.id.codigo_det);
            EditText auxTxtNombre = findViewById(R.id.inp_nombre_det);
            EditText auxTxtPrecio = findViewById(R.id.inp_precio_det);
            EditText auxTxtCantidad = findViewById(R.id.inp_cantidad_det);

            String TxtCodigo = auxTxtCodigo.getText().toString();
            String TxtNombre = auxTxtNombre.getText().toString();
            String TxtPrecio = auxTxtPrecio.getText().toString();
            String TxtCantidad = auxTxtCantidad.getText().toString();

            Producto auxProducto = new Producto();
            auxProducto.setCodigo(TxtCodigo);
            auxProducto.setNombre(TxtNombre);
            auxProducto.setPrecio(TxtPrecio);
            auxProducto.setCantidad(TxtCantidad);

            MantenedorProducto auxMantenedor = new MantenedorProducto(this);
            auxMantenedor.actualizarProducto(auxProducto);

            this.mensaje("Producto: " + TxtNombre + ". codigo: " + TxtCodigo + ". actualizado correctamente");
            atras();

        }
        catch(Exception ex){
            this.mensaje("Datos no guardados " + ex.getMessage());
        }
    }

    public void mensaje(String auxMensaje){
        Toast.makeText(this,auxMensaje,Toast.LENGTH_LONG).show();
    }

    public void eliminarRegistro(View view){
        TextView TxtCodigo = findViewById(R.id.codigo_det);
        EditText TxtNombre = findViewById(R.id.inp_nombre_det);
        String ProductoCodigo = TxtCodigo.getText().toString();
        String ProductoNombre = TxtNombre.getText().toString();
        MantenedorProducto Mantenedor = new MantenedorProducto(this);
        Mantenedor.borrarProducto(ProductoCodigo);
        this.mensaje("Producto: " + ProductoNombre + ". codigo: " + ProductoCodigo + ". eliminado correctamente");
        atras();

    }

    public void volver(View view){
        atras();
    }

    private void atras(){
        Intent intent = new Intent(DetalleItem.this, MainActivity.class);
        startActivity(intent);
    }

}
