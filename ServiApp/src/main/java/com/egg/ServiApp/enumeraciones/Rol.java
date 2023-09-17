/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.egg.ServiApp.enumeraciones;

/**
 *
 * @author Juanp
 */
public enum Rol {

PROVEEDOR(1, "Proveedor"), USUARIO(2, "Usuario"), ADMIN(3, "Admin");

    private Integer codigo;
    private String valor;

    private Rol(Integer codigo, String valor) {
        this.codigo = codigo;
        this.valor = valor;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    
}
