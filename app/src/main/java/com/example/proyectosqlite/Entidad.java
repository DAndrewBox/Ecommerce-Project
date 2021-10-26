package com.example.proyectosqlite;

import java.io.Serializable;

public class Entidad implements Serializable {
    private String codigo;
    private String nombre;
    private String precio;
    private String cantidad;

    public Entidad(String codigo, String nombre, String precio, String cantidad){
        this.setCodigo(codigo);
        this.setNombre(nombre);
        this.setPrecio(precio);
        this.setCantidad(cantidad);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
}
