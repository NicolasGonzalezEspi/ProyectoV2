package com.vedruna.gonzalezespinosa01;

// Producto.java
public class Producto {
    private int codigo;
    private String nombre;
    private String descripcion;
    private double precio;

    public Producto(int codigo, String nombre, String descripcion, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    // Getters
    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }
}
