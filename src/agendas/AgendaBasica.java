/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendas;

import java.util.ArrayList;

/**
 *
 * @author Estudiante
 */
public class AgendaBasica extends Agenda {
    ArrayList<String> usuarios_reserva;

    public AgendaBasica(String propietario, String descripcion) {
        super(propietario, descripcion);
        this.usuarios_reserva = new ArrayList<String>();
    }
    
    protected Turno reservar(String usuario){
        Turno turno_antiguo = null;
        for(Turno turno : this.turnos){
            if(turno_antiguo == null){
                if(!this.consultar_turno(turno)){
                    turno_antiguo = turno;
                }
            }else if(turno_antiguo.getFecha().isAfter(turno.getFecha())){
                if(this.consultar_turno(turno)){
                    turno_antiguo = turno;
                }
            }
        }
        if(turno_antiguo != null){
            turno_usuario.put(usuario, turno_antiguo);
        }
        return turno_antiguo;
    }
    
    
}
