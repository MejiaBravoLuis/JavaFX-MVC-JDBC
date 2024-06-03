/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luismejia.model;

/**
 *
 * @author Luis Mejia
 */
public class Usuario {
    private int usuarioId;
    private String usuario;
    private String contrasenia;
    private int nivelAccesoId;
    private int empleadoId;

    public Usuario() {
    }

    public Usuario(int usuarioId, String usuario, String contrasenia, int nivelAccesoId, int empleadoId) {
        this.usuarioId = usuarioId;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.nivelAccesoId = nivelAccesoId;
        this.empleadoId = empleadoId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public int getNivelAccesoId() {
        return nivelAccesoId;
    }

    public void setNivelAccesoId(int nivelAccesoId) {
        this.nivelAccesoId = nivelAccesoId;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    @Override
    public String toString() {
        return "Id: " + usuarioId + " | " + usuario;
    }
    
    
}
