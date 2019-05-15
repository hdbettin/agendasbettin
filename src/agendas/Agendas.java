/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendas;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Estudiante
 */
public class Agendas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        AgendaBasica basica = new AgendaBasica("enrique", "tutorias");
        basica.añadir_turno(LocalDate.of(2018 , 12, 12), "09:30 – 10:00 y 10:30 – 11:00");
        basica.añadir_turno(LocalDate.of(2018 , 12, 13), "10:30 – 11:00");
        
        AgendaBalanceada balanceada = new AgendaBalanceada("enrique", "revision de examen");
        balanceada.añadir_turno(LocalDate.of(2018 , 12, 12), "12:00 – 12:30 y 13:30 – 14:00");
        balanceada.añadir_turno(LocalDate.of(2018 , 12, 13), "11:00 – 11:30, 12:30 – 13:00 y 13:00 –13:30");
        
        ArrayList<Agenda> agendas = new ArrayList<Agenda>();
        agendas.add(basica);
        agendas.add(balanceada);
        for(Agenda agenda : agendas){
            System.out.println("descripcion: " + agenda.getDescripcion());
            System.out.println("numero de turnos: " + agenda.consultar_turno(LocalDate.of(2018 , 12, 13)).size());
            //agenda.reservar("juan");
            //agenda.reservar("juan");
            System.out.println("turno: "+agenda.reservar("juan").getFranja());
            System.out.println("turno: "+agenda.reservar("juan").getFranja());
            for(Turno e : agenda.getTurnos()){
                if(agenda.consultar_turno(e)){
                    System.out.println("ocupado: " + e.getFranja());
                }
            }
            agenda.cancelar_reserva(agenda.turno_usuario.get(agenda.turno_usuario.keySet().toArray()[0]), "juan");
        }
    }
    
}
