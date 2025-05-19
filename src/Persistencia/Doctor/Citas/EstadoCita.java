/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.Doctor.Citas;

/**
 *
 * @author Osvaldo
 */
public class EstadoCita {
    private final int estado;
    private final boolean fueAtendida;
    private final String fecha;

    public EstadoCita(int estado, boolean fueAtendida, String fecha) {
        this.estado = estado;
        this.fueAtendida = fueAtendida;
        this.fecha = fecha;
    }

    public int getEstado() {
        return estado;
    }

    public boolean getFueAtendida() {
        return fueAtendida;
    }

    public String getFecha() {
        return fecha;
    }
}
