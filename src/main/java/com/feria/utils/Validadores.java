package com.feria.utils;

import com.feria.modelos.Emprendedor;

public class Validadores {

    public static boolean emailValido(String email) {
        if (email == null) 
            return false;
        if (!email.contains("@")) 
            return false;
        if (email.length() < 5) 
            return false;
        return true;
    }
    
    public static boolean telefonoValido(String t) {
        if (t == null) return false;
        if (t.length() < 8) return false;
        return true;
    } 

    private static boolean esCategoriaValida(String categoria) {
        if (categoria == null) 
            return false;
        return  categoria.equals("comida") || 
                categoria.equals("artesania") || 
                categoria.equals("tecnologia") || 
                categoria.equals("ropa");
    }

    public static void validarEmprendedor(Emprendedor e) {
        if (e == null) {
            throw new IllegalArgumentException("El objeto Emprendedor no puede ser nulo.");
        }
        if (!emailValido(e.emailEmprendedor)) {
            throw new IllegalArgumentException("El formato del email es inválido.");
        }
        if (!telefonoValido(e.telefonoEmprendedor)) {
            throw new IllegalArgumentException("El número de teléfono es inválido.");
        }
        if (e.nombreEmprendedor == null || e.nombreEmprendedor.trim().length() < 2) {
            throw new IllegalArgumentException("El nombre es demasiado corto o nulo.");
        }
        if (!esCategoriaValida(e.categoriaEmprendedor)) {
            throw new IllegalArgumentException("La categoría provista no está permitida.");
        }
    }
    
    public static boolean validarPrecioStock(double precio, int stock) {
        if (precio <= 0) return false;
        if (stock < 0) return false;
        return true;
    }
}