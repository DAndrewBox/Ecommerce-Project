package com.example.proyectosqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);
    }
    //Menu Principal
    public void ir_mantenedores(View view){
        ir_a(MenuMantenedor.class);
    }

    public void ir_informes(View view){
       ir_a(MenuInforme.class);
    }

    public void ir_transacciones(View view){
        //ir_a(Transacciones.class);
    }

    public void ir_a(Class objetivo){
        Intent intent = new Intent(this, objetivo);
        startActivity(intent);
    }
}
