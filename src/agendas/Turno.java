/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendas;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Estudiante
 */
public class Turno {
    
    private LocalDate fecha;
    private String franja;

    public Turno(LocalDate fecha, String franja) {
        this.fecha = fecha;
        this.franja = franja;
    }
    
    public boolean equals(Turno turno){
        if(this.fecha.equals(turno.fecha) && Objects.equals(this.franja, turno.franja)){
            return true;
        }else{
            return false;
        }
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getFranja() {
        return franja;
    }

    public void setFranja(String franja) {
        this.franja = franja;
    }
    
    
    
}
