/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Estudiante
 */
public class AgendaBalanceada extends Agenda {
    private HashMap<LocalDate, Integer> balance = new HashMap<LocalDate, Integer>();

    public AgendaBalanceada(String propietario, String descripcion) {
        super(propietario, descripcion);
    }

    @Override
    public boolean añadir_turno(LocalDate fecha, String franja) {
        int contador = 0;
        if(super.añadir_turno(fecha, franja)){
            if(balance.size() > 0){
                for(Map.Entry<LocalDate, Integer> e : balance.entrySet()){
                    if(e.getKey().isEqual(fecha)){
                        balance.replace(e.getKey(), e.getValue()+1);
                        contador++;
                    }
                }
                if(contador == 0){
                    balance.put(fecha, 1);
                }
            }else{
                balance.put(fecha, 1);
            }
        }
        return true;
    }
    
    public LocalDate consultar_dia(){
        LocalDate retorno_fecha = null;
        Map.Entry<LocalDate, Integer> dia_mayor = null;
        for(Map.Entry<LocalDate, Integer> e : balance.entrySet()){
            if(dia_mayor == null){
                dia_mayor = e;
            }else{
                if(dia_mayor.getValue() <= e.getValue()){
                    dia_mayor = e;
                }
            }
        }
        return dia_mayor.getKey();
    }
    
    @Override
    protected Turno reservar(String usuario){
        Turno retorno_turno = null;
        LocalDate fecha_mayor = this.consultar_dia();
        ArrayList<Turno> turnos_dia = this.consultar_turno(fecha_mayor);
        for(Turno turno : turnos_dia){
            if(!this.consultar_turno(turno)){
                turno_usuario.put("usuario", turno);
                retorno_turno = turno;
                for(Map.Entry<LocalDate, Integer> e : balance.entrySet()){
                    if(e.getKey().isEqual(turno.getFecha())){
                        balance.replace(e.getKey(), e.getValue()-1);
                    }
                }
                break;
            }
        }
        return retorno_turno;
    }
    
       
}
