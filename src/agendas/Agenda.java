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
import java.util.Objects;

/**
 *
 * @author Estudiante
 */
public abstract class Agenda {
    protected String propietario;
    protected String descripcion;
    protected ArrayList<Turno> turnos;
    HashMap<String, Turno> turno_usuario = new HashMap<String, Turno>();

    public Agenda(String propietario, String descripcion) {
        this.propietario = propietario;
        this.descripcion = descripcion;
        this.turnos = new ArrayList<Turno>();
    }
    
    public boolean añadir_turno(LocalDate fecha, String franja){
        Turno turno = new Turno(fecha, franja);
        boolean sw = false;
        for(Turno e : this.turnos){
            if(e.equals(turno)){
                sw = true;
            }
        }
        if(!sw){
            this.turnos.add(turno);
            return true;
        }else{
            return false;
        }
    }
    public void ajustar_dias(int numero){
        for(Turno turno : this.turnos){
            if(numero > 0){
                turno.setFecha(turno.getFecha().plusDays(numero));
            }else{
                turno.setFecha(turno.getFecha().minusDays(numero));
            }
        }
    }
    
    public ArrayList<Turno> consultar_turno(LocalDate fecha){
        ArrayList<Turno> retornar_turnos = new ArrayList<Turno>();
        for(Turno e : this.turnos){
            if(e.getFecha().isEqual(fecha)){
                retornar_turnos.add(e);
            }
        }
        return retornar_turnos;
    }
    
    public boolean reservar(String usuario, Turno turno){
        boolean retorno = false;
        for(Turno e: this.turnos){
            if(e.equals(turno)){
                if(!this.consultar_turno(turno)){
                    turno_usuario.put(usuario, turno);
                    retorno = true;
                }
            }
        }
        return retorno;
    }
    
    protected abstract Turno reservar(String usuario);
    
    public String consultar_usuario(Turno turno){
        String usuario = null;
        for(Map.Entry<String, Turno> e : turno_usuario.entrySet()){
            if(turno.equals(e.getValue())){
                usuario = e.getKey();
            }
        }
        return usuario;
    }
    public boolean consultar_turno(Turno turno){
        boolean retorno = false;
        for(Map.Entry<String, Turno> e : turno_usuario.entrySet()){
            if(e.getValue().equals(turno)){
                retorno = true;
            }
        }
        return retorno;
    }
    
    public boolean cancelar_reserva(Turno turno, String usuario){
        String eliminado = null;
        for(Map.Entry<String, Turno> e : turno_usuario.entrySet()){
            System.out.println(e.getKey()+" = "+usuario);
            System.out.println(e.getValue().getFranja()+" = "+turno.getFranja());
            if(Objects.equals(e.getKey(), usuario) && e.getValue().equals(turno)){
                eliminado = e.getKey();
            }
        }
        if(eliminado != null){
            turno_usuario.remove(eliminado);
            System.out.println("cancelado");
            return true;
        }else{
            return false;
        }
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(ArrayList<Turno> turnos) {
        this.turnos = turnos;
    }
    
    
}
