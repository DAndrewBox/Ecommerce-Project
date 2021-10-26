package com.example.proyectosqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MenuInforme extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_informe);
    }
    public void ir_producto_mas_vendido(View view){
        ir_a(Informes.class);
    }


    public void ir_Menu(View view){
        ir_a(MenuPrincipal.class);
    }

    public void ir_a(Class objetivo){
        Intent intent = new Intent(MenuInforme.this,objetivo);
        startActivity(intent);
    }
}
