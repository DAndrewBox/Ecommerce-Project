package com.example.proyectosqlite;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MantenedorCliente extends SQLiteOpenHelper {
    private static final int       VERSION_DB = 1;
    private static final String    NAME_DB = "ecommerce.db";
    private static final String    TABLE_CLIENTES = "CREATE TABLE cliente (rut TEXT PRIMARY KEY, nombre TEXT, edad TEXT, prev TEXT, phone TEXT, email TEXT);";

    public MantenedorCliente(Context context) {
        super(context, NAME_DB, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CLIENTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS cliente;");
    }

    // Insert Cliente
    public void insertarCliente(Cliente cliente) {
        SQLiteDatabase db = getWritableDatabase();

        if (db != null) {
            db.execSQL
                    ( "INSERT INTO cliente (rut, nombre, edad, prev, phone, email) " +
                      "VALUES ('" + cliente.getRut() + "', '"
                                  + cliente.getNombre() + "', '"
                                  + cliente.getEdad() + "', '"
                                  + cliente.getPrev() + "', '"
                                  + cliente.getPhone() + "', '"
                                  + cliente.getEmail()
                                  + "');"
                    );

            db.close();
        }
    }

    // Update Cliente
    public void actualizarCliente(Cliente cliente) {
        SQLiteDatabase db = getWritableDatabase();

        if (db != null) {
            db.execSQL
                    ( "UPDATE cliente SET nombre = '" + cliente.getNombre()
                            + "', edad = '" + cliente.getEdad()
                            + "', prev = '" + cliente.getPrev()
                            + "', phone = '" + cliente.getPhone()
                            + "', email = '" + cliente.getEmail()
                            + "' WHERE rut = '"
                            + cliente.getRut()
                            +"';"
                    );

            db.close();
        }
    }

    // Delete Cliente
    public void eliminarCliente(String rut) {
        SQLiteDatabase db = getWritableDatabase();

        if (db != null) {
            db.execSQL
                    ( "DELETE FROM cliente "
                            + "WHERE rut = '"
                            + rut
                            + "';"
                    );

            db.close();
        }
    }

    // Delete ALL
    public void eliminarAllCliente() {
        SQLiteDatabase db = getWritableDatabase();

        if (db != null) {
            db.execSQL("DELETE FROM cliente;");
            db.close();
        }
    }

    // Retornar todos los Clientes
    public List<Cliente> consultarCliente() {
        SQLiteDatabase db = getReadableDatabase();
        List<Cliente> auxListaCliente = new ArrayList<>();
        Cursor auxCursor = db.rawQuery("SELECT rut, nombre, edad, prev, phone, email FROM cliente;", null);

        auxCursor.moveToFirst();

        do {
            Cliente auxCliente = new Cliente();
            auxCliente.setRut(auxCursor.getString(0));
            auxCliente.setNombre(auxCursor.getString(1));
            auxCliente.setEdad(auxCursor.getString(2));
            auxCliente.setPrev(auxCursor.getString(3));
            auxCliente.setPhone(auxCursor.getString(4));
            auxCliente.setEmail(auxCursor.getString(5));

            auxListaCliente.add(auxCliente);
        } while (auxCursor.moveToNext());

        db.close();
        auxCursor.close();

        return auxListaCliente;
    }

    // Buscar Cliente
    public Cliente buscarCliente(String rut) {
        SQLiteDatabase db = getReadableDatabase();
        Cliente auxCliente = new Cliente();
        Cursor auxCursor = db.rawQuery("SELECT rut, nombre, edad, prev, phone, email FROM cliente " + "WHERE rut = '" + rut + "';", null);

        auxCursor.moveToFirst();

        if (auxCursor != null) {
            auxCliente.setRut(auxCursor.getString(0));
            auxCliente.setNombre(auxCursor.getString(1));
            auxCliente.setEdad(auxCursor.getString(2));
            auxCliente.setPrev(auxCursor.getString(3));
            auxCliente.setPhone(auxCursor.getString(4));
            auxCliente.setEmail(auxCursor.getString(5));
        } else {
            auxCliente.setRut("");
            auxCliente.setNombre("");
            auxCliente.setEdad("");
            auxCliente.setPrev("");
            auxCliente.setPhone("");
            auxCliente.setEmail("");
        }

        db.close();
        auxCursor.close();

        return auxCliente;
    }
}
