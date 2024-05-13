/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luismejia.model;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Luis Mejia
 */
public class Facturas{
    private int facturaId;
    private Date fecha;
    private Time hora;
    private int clienteId;
    private String Cliente;
    private int empleadoId;
    private String Empleado;
    private double Total;

    public Facturas() {
    }
    
    public Facturas(int facturaId, Date fecha, Time hora, String Cliente, String Empleado, double Total) {
        this.facturaId = facturaId;
        this.fecha = fecha;
        this.hora = hora;
        this.Cliente = Cliente;
        this.Empleado = Empleado;
        this.Total = Total;
    }

    public int getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String Cliente) {
        this.Cliente = Cliente;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public String getEmpleado() {
        return Empleado;
    }

    public void setEmpleado(String Empleado) {
        this.Empleado = Empleado;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

    @Override
    public String toString() {
        return "Facturas{" + "facturaId=" + facturaId + ", fecha=" + fecha + ", hora=" + hora + ", clienteId=" + clienteId + ", Cliente=" + Cliente + ", empleadoId=" + empleadoId + ", Empleado=" + Empleado + ", Total=" + Total + '}';
    }
    
    

}
