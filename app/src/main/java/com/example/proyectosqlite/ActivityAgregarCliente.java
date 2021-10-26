package com.example.proyectosqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ActivityAgregarCliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_clientes);
    }

    // Guardar nuevo cliente
    public void saveNewClient(View view) {
        try {
            EditText auxTxtRut      = findViewById(R.id.txtRut);
            EditText auxTxtNombre   = findViewById(R.id.txtNombre);
            EditText auxTxtEdad     = findViewById(R.id.txtEdad);
            Spinner  auxDropPrev    = findViewById(R.id.dropPrevision);
            EditText auxTxtPhone    = findViewById(R.id.txtTelefono);
            EditText auxTxtEmail    = findViewById(R.id.txtEmail);

            String   valRut     = auxTxtRut.getText().toString();
            String   valNombre  = auxTxtNombre.getText().toString();
            String   valEdad    = auxTxtEdad.getText().toString();
            String   valPrev    = (String) auxDropPrev.getSelectedItem();
            String   valPhone   = auxTxtPhone.getText().toString();
            String   valEmail   = auxTxtEmail.getText().toString();

            if (valRut.length() > 0 && valNombre.length() > 0 && valEdad.length() > 0 && valPrev.length() > 0) {
                Cliente auxCliente = new Cliente();
                auxCliente.setRut(valRut);
                auxCliente.setNombre(valNombre);
                auxCliente.setEdad(valEdad);
                auxCliente.setPrev(valPrev);
                auxCliente.setPhone(valPhone);
                auxCliente.setEmail(valEmail);

                MantenedorCliente auxNegocio = new MantenedorCliente(this);

                auxNegocio.insertarCliente(auxCliente);
                this.message("¡Datos guardados!");

                auxTxtRut.setText("");
                auxTxtNombre.setText("");
                auxTxtEdad.setText("");
                auxDropPrev.setSelection(0);
                auxTxtPhone.setText("");
                auxTxtEmail.setText("");

                auxTxtRut.requestFocus();
            } else {
                this.message("Datos no guardados.\nAún hay campos obligatorios incompletos.");
            }
        } catch (Exception e) {
            this.message("Datos no guardados.\n" + e.getMessage());
        }
    }

    // Mostrar mensajes
    public void message(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    // Volver al Menu
    public void backMenu(View view) {
        Intent intent = new Intent(ActivityAgregarCliente.this, ActivityMostrarCliente.class);
        startActivity(intent);
    }
}