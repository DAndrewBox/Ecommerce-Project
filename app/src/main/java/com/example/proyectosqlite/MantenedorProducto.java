package com.example.proyectosqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MantenedorProducto extends SQLiteOpenHelper
{
    private static final int VERSION_BASEDATOS = 1;
    private static final String NOMBRE_BASEDATOS = "ecommerce.bd";
    private static final String TABLA_PRODUCTO = "CREATE TABLE 'productos' (codigo TEXT PRIMARY KEY, nombre TEXT,precio INTEGER, cantidad INTEGER, cantidad_vendido INTEGER);";

    public MantenedorProducto(Context context)
    {
        super(context,NOMBRE_BASEDATOS,null,VERSION_BASEDATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(TABLA_PRODUCTO);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int old_version, int new_version)
    {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLA_PRODUCTO+"';");
    }

    // Agregar productos
    public void insertarProducto(Producto producto){
        SQLiteDatabase db = getWritableDatabase();

        if(db != null){
            db.execSQL("INSERT INTO productos (codigo, nombre, precio, cantidad) VALUES ('"
                                  + producto.getCodigo() + "','"
                                  + producto.getNombre() + "','"
                                  + producto.getPrecio() + "','"
                                  + producto.getCantidad() + "')");
        }
        db.close();
    }

    // Actualizar productos
    public void actualizarProducto(Producto producto){
        SQLiteDatabase db = getWritableDatabase();

        if(db != null){
            db.execSQL("UPDATE productos SET nombre = '" + producto.getNombre() + "'," +
                                            "precio = '" + producto.getPrecio() + "'," +
                                            "cantidad = '" + producto.getCantidad() + "'" +
                                            "WHERE codigo = '" + producto.getCodigo() + "'");
        }
        db.close();
    }

    // Borrar productos
    public void borrarProducto(String codigo){
        SQLiteDatabase db = getWritableDatabase();

        if(db != null){
            db.execSQL("DELETE FROM productos WHERE codigo = '" + codigo + "'");
        }
        db.close();
    }

    // Borrar todos productos
    public void borrarTodosProducto(){
        SQLiteDatabase db = getWritableDatabase();

        if(db != null){
            db.execSQL("DELETE FROM productos");
        }
        db.close();
    }

    // Consultar productos
    public List<Producto> consultaProductos(){
        SQLiteDatabase db = getReadableDatabase();
        List<Producto> auxListaProducto = new ArrayList<>();

        Cursor auxCursor = db.rawQuery("SELECT codigo, nombre, precio, cantidad FROM productos",null);
        auxCursor.moveToFirst();

        if(auxCursor.getCount()>0){
            do{
                Producto auxProducto = new Producto();

                auxProducto.setCodigo(auxCursor.getString(0));     //codigo
                auxProducto.setNombre((auxCursor.getString(1)));   //nombre
                auxProducto.setPrecio((auxCursor.getString(2)));   //precio
                auxProducto.setCantidad((auxCursor.getString(3))); //cantidad

                auxListaProducto.add(auxProducto);

            }while(auxCursor.moveToNext());
        }

        db.close();
        auxCursor.close();
        return auxListaProducto;
    }

    // Buscar producto
    public Producto buscaProductos(String codigo){
        SQLiteDatabase db = getReadableDatabase();
        Producto auxProducto = new Producto();

        Cursor auxCursor = db.rawQuery("SELECT codigo, nombre, precio, cantidad FROM productos "
                                          +"WHERE codigo = '" + codigo +"'" ,null);
        auxCursor.moveToFirst();

        if(auxCursor != null){
            auxProducto.setCodigo(auxCursor.getString(0));     //codigo
            auxProducto.setNombre((auxCursor.getString(1)));   //nombre
            auxProducto.setPrecio((auxCursor.getString(2)));   //precio
            auxProducto.setCantidad((auxCursor.getString(3))); //cantidad
        }
        else{
            auxProducto.setCodigo("");     //codigo
            auxProducto.setNombre("");   //nombre
            auxProducto.setPrecio("");   //precio
            auxProducto.setCantidad(""); //cantidad
        }

        db.close();
        auxCursor.close();
        return auxProducto;
    }

    public Producto obtenerMasVendido(){
        SQLiteDatabase db = getReadableDatabase();
        Producto auxProducto = new Producto();
        Cursor auxCursor = db.rawQuery("SELECT codigo, nombre, precio, cantidad_vendido FROM productos "
                +"ORDER BY cantidad_vendido DESC LIMIT 1 " ,null);
        auxCursor.moveToFirst();

        if(auxCursor != null){
            auxProducto.setCodigo(auxCursor.getString(0));     //codigo
            auxProducto.setNombre((auxCursor.getString(1)));   //nombre
            auxProducto.setPrecio((auxCursor.getString(2)));   //precio
            auxProducto.setCantidad_vendida((auxCursor.getString(3)));   //cantidad_vendido
        }
        else{
            auxProducto.setCodigo("");     //codigo
            auxProducto.setNombre("");   //nombre
            auxProducto.setPrecio("");   //precio
        }

        db.close();
        auxCursor.close();
        return auxProducto;
    }
}