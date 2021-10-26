package com.example.proyectosqlite;

public class Producto {
    private String nombre;
    private String codigo;
    private String precio;
    private String cantidad;
    private String cantidad_vendida;


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public String getCantidad_vendida() {
        return cantidad_vendida;
    }

    public void setCantidad_vendida(String cantidad_vendida) {
        this.cantidad_vendida = cantidad_vendida;
    }
}
