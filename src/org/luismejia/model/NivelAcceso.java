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
public class NivelAcceso {
    private int nivelAccesoId;
    private String nivelAcceso;

    public NivelAcceso() {
    }

    public NivelAcceso(int nivelAccesoId, String nivelAcceso) {
        this.nivelAccesoId = nivelAccesoId;
        this.nivelAcceso = nivelAcceso;
    }

    public int getNivelAccesoId() {
        return nivelAccesoId;
    }

    public void setNivelAccesoId(int nivelAccesoId) {
        this.nivelAccesoId = nivelAccesoId;
    }

    public String getNivelAcceso() {
        return nivelAcceso;
    }

    public void setNivelAcceso(String nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }

    @Override
    public String toString() {
        return "Id: " + nivelAccesoId + " | " + nivelAcceso;
    }
    
    
}
