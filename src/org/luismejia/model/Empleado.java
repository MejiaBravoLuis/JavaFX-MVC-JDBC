/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luismejia.model;

import java.sql.Time;

/**
 *
 * @author Luis Mejia
 */
public class Empleado {
    
    private int empleadoId;
    private String nombreEmpleado;
    private String apellidoEmpleado;
    private double sueldo;
    private Time horaDeEntrada;
    private Time horaDeSalida;
    private int cargoId;
    private String cargo;
    private int encargadoId;
    private String encargado;

    public Empleado() {
    }

    public Empleado(int empleadoId, String nombreEmpleado, String apellidoEmpleado, double sueldo, Time horaDeEntrada, Time horaDeSalida,  String cargo, String encargado){
        this.empleadoId = empleadoId;
        this.nombreEmpleado = nombreEmpleado;
        this.apellidoEmpleado = apellidoEmpleado;
        this.sueldo = sueldo;
        this.horaDeEntrada = horaDeEntrada;
        this.horaDeSalida = horaDeSalida;
        this.cargo = cargo;
        this.encargado = encargado;
    }   

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getApellidoEmpleado() {
        return apellidoEmpleado;
    }

    public void setApellidoEmpleado(String apellidoEmpleado) {
        this.apellidoEmpleado = apellidoEmpleado;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public Time getHoraDeEntrada() {
        return horaDeEntrada;
    }

    public void setHoraDeEntrada(Time horaDeEntrada) {
        this.horaDeEntrada = horaDeEntrada;
    }

    public Time getHoraDeSalida() {
        return horaDeSalida;
    }

    public void setHoraDeSalida(Time horaDeSalida) {
        this.horaDeSalida = horaDeSalida;
    }

    public int getCargoId() {
        return cargoId;
    }

    public void setCargoId(int cargoId) {
        this.cargoId = cargoId;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getEncargadoId() {
        return encargadoId;
    }

    public void setEncargadoId(int encargadoId) {
        this.encargadoId = encargadoId;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }
    
    @Override
    public String toString() {
        return "Id: " + empleadoId + " | " + nombreEmpleado + " " + apellidoEmpleado;
    }
    
}
