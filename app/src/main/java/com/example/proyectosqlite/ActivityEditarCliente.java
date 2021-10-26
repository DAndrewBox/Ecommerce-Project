package com.example.proyectosqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityEditarCliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_clientes);

        Intent intent = getIntent();
        String rut = intent.getStringExtra(ActivityMostrarCliente.EXTRA_MESSAGE);

        MantenedorCliente mantenedor = new MantenedorCliente(this);
        Cliente auxCliente = mantenedor.buscarCliente(rut);
        int prevIndex;

        switch (auxCliente.getPrev()) {
            default:
            case "Fonasa":          prevIndex = 0; break;
            case "Banmédica":       prevIndex = 1; break;
            case "Cruz Blanca":     prevIndex = 2; break;
            case "Consalud":        prevIndex = 3; break;
            case "Colmena":         prevIndex = 4; break;
        }

        EditText auxTxtRut      = findViewById(R.id.txtRut);
        EditText auxTxtNombre   = findViewById(R.id.txtNombre);
        EditText auxTxtEdad     = findViewById(R.id.txtEdad);
        Spinner  auxDropPrev    = findViewById(R.id.dropPrevision);
        EditText auxTxtPhone    = findViewById(R.id.txtTelefono);
        EditText auxTxtEmail    = findViewById(R.id.txtEmail);

        auxTxtRut.setFocusable(false);
        auxTxtRut.setClickable(false);
        auxTxtRut.setText(auxCliente.getRut());
        auxTxtNombre.setText(auxCliente.getNombre());
        auxTxtEdad.setText(auxCliente.getEdad());
        auxDropPrev.setSelection(prevIndex);
        auxTxtPhone.setText(auxCliente.getPhone());
        auxTxtEmail.setText(auxCliente.getEmail());
    }

    // Guardar nuevo cliente
    public void saveEditedClient(View view) {
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

                MantenedorCliente mantenedor = new MantenedorCliente(this);
                mantenedor.actualizarCliente(auxCliente);

                this.message("¡Datos actualizados!");
                backView(view);
            } else {
                this.message("Datos no guardados.\nAún hay campos obligatorios incompletos.");
            }
        } catch (Exception e) {
            this.message("Datos no guardados.\n" + e.getMessage());
        }
    }

    // Eliminar Cliente
    public void deleteCliente(View view) {
        MantenedorCliente mantenedor = new MantenedorCliente(this);
        EditText auxTxtRut  = findViewById(R.id.txtRut);
        String   valRut     = auxTxtRut.getText().toString();

        mantenedor.eliminarCliente(valRut);
        backView(view);
    }

    // Mostrar mensajes
    public void message(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    // Volver a vista Mostrar Clientes
    public void backView(View view) {
        Intent intent = new Intent(this, ActivityMostrarCliente.class);
        startActivity(intent);
    }
}