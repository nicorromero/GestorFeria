package com.feria.modelos;

import java.util.ArrayList;
import java.util.List;


public class Emprendedor {

    public String nombreEmprendedor;      // nombre
    public String idEmprendedor;     // identificador
    public String telefonoEmprendedor;      // teléfono
    public String emailEmprendedor;      // email
    public String categoriaEmprendedor;    // categoria: comida, artesania, tecnologia, ropa
    public List<Producto> productosEmprendedor;

    public Emprendedor(String nom, String id, String tel, String mail, String categoria) {
        this.nombreEmprendedor = nom;
        this.idEmprendedor = id;
        this.telefonoEmprendedor = tel;
        this.emailEmprendedor = mail;
        this.categoriaEmprendedor = categoria;
        this.productosEmprendedor = new ArrayList<>();
    }


    public String mostrarInfoYValidar() {
        String info = "Emprendedor: " + nombreEmprendedor + "\n";
        info += "ID: " + idEmprendedor + "\n";
        info += "Contacto: " + telefonoEmprendedor + " | " + emailEmprendedor + "\n";
        info += "Categoría: " + categoriaEmprendedor + "\n";

        // VALIDACIONES
        if (nombreEmprendedor == null || nombreEmprendedor.length() < 2) {
            info += "⚠️ NOMBRE DEMASIADO CORTO\n";
        }
        if (emailEmprendedor == null || !emailEmprendedor.contains("@")) {
            info += "⚠️ EMAIL INVÁLIDO\n";
        }
        if (categoriaEmprendedor == null || (!categoriaEmprendedor.equals("comida") && !categoriaEmprendedor.equals("artesania") && 
                           !categoriaEmprendedor.equals("tecnologia") && !categoriaEmprendedor.equals("ropa"))) {
            info += "⚠️ CATEGORÍA DESCONOCIDA\n";
        }
        info += "Productos:\n";
        for (Producto p : productosEmprendedor) {
            info += "  - " + p.nombre + " ($" + p.precio + ")\n";
        }

        return info;
    }


    public boolean validarCompleto() {
        boolean valido = true;
        if (nombreEmprendedor == null || nombreEmprendedor.length() < 2) valido = false;
        if (emailEmprendedor == null || !emailEmprendedor.contains("@")) valido = false;
        if (categoriaEmprendedor == null || (!categoriaEmprendedor.equals("comida") && !categoriaEmprendedor.equals("artesania") && 
                           !categoriaEmprendedor.equals("tecnologia") && !categoriaEmprendedor.equals("ropa"))) valido = false;
        return valido;
    }


    public String getNombre() {
        return nombreEmprendedor;
    }

    public void agregarProducto(Producto p) {
        productosEmprendedor.add(p);
    }

    public String getIdEmprendedor() {
        return idEmprendedor;
    }

    public String getCategoriaEmprendedor() {
        return categoriaEmprendedor;
    }

    
    public int calcularValorTotalStock() {
        int total = 0;
        for (Producto p : productosEmprendedor) {
            total += p.precio * p.stock;
        }
        return total;
    }
}