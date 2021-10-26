package com.example.proyectosqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MenuMantenedor extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_mantenedor);
    }
    public void ir_medicamentos(View view){
        ir_a(MainActivity.class);
    }

    public void ir_clientes(View view){
        ir_a(ActivityMostrarCliente.class);
    }

    public void ir_Menu(View view){
        ir_a(MenuPrincipal.class);
    }

    public void ir_a(Class objetivo){
        Intent intent = new Intent(MenuMantenedor.this, objetivo);
        startActivity(intent);
    }
}
