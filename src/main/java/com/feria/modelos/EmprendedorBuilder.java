/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.feria.modelos;

/**
 *
 * @author nicor
 */
public class EmprendedorBuilder {

    private String nombre;
    private String id;
    private String telefono;
    private String email;
    private String categoria;

    public EmprendedorBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this; // Retornamos 'this' para poder encadenar los métodos
    }

    public EmprendedorBuilder conId(String id) {
        this.id = id;
        return this;
    }

    public EmprendedorBuilder conTelefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public EmprendedorBuilder conEmail(String email) {
        this.email = email;
        return this;
    }

    public EmprendedorBuilder conCategoria(String categoria) {
        this.categoria = categoria;
        return this;
    }

    public Emprendedor construir() {
        return new Emprendedor(nombre, id, telefono, email, categoria);
    }
}

