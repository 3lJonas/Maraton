
package maraton;

import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Maraton {

    private ArrayList<Participante> participantesCategoria1;
    private ArrayList<Participante> participantesCategoria2;
    private ArrayList<Participante> participantesCategoria3;
    private boolean maratonAbierto;
    private LocalTime horaInicio;
    private LocalTime horaCierre;

    public Maraton() {
        this.participantesCategoria1 = new ArrayList<>();
        this.participantesCategoria2 = new ArrayList<>();
        this.participantesCategoria3 = new ArrayList<>();
        this.maratonAbierto = false;
    }

    public void registrarParticipante(Participante participante) {
        int categoria = determinarCategoria(participante.getEdad());

        switch (categoria) {
            case 1:
                participantesCategoria1.add(participante);
                break;
            case 2:
                participantesCategoria2.add(participante);
                break;
            case 3:
                participantesCategoria3.add(participante);
                break;
        }
    }
    
    public boolean eliminarParticipante(Participante p) {
        if (p != null) {
            ArrayList<Participante> participantes = null;
            int categoria = determinarCategoria(p.getEdad());
            switch (categoria) {
                case 1:
                    participantes = this.participantesCategoria1;
                    break;
                case 2:
                    participantes = this.participantesCategoria2;
                    break;
                case 3:
                    participantes = this.participantesCategoria3;
                    break;
            }
            for (int i = 0; i < participantes.size(); i++) {
                if (participantes.get(i).equals(p)) {
                    participantes.remove(p);
                    return true;
                }
            }
        }
        return false;
    }

    public int determinarCategoria(int edad) {
        if (edad <= 18) {
            return 1;
        } else if (edad <= 40) {
            return 2;
        } else {
            return 3;
        }
    }

   public void iniciarMaraton() {
    if (maratonAbierto) {
        JOptionPane.showMessageDialog(null, "El maratón ya está iniciado.");
    } else if (participantesRegistrados() == 0) {
        JOptionPane.showMessageDialog(null, "No hay participantes registrados. Registre participantes antes de iniciar el maratón.");
    } else {
        maratonAbierto = true;
        horaInicio = obtenerHoraActual();
         JOptionPane.showMessageDialog(null, "Maratón iniciado con " + participantesRegistrados() + " participantes. A la hora: " + horaInicio);
    }
}


    public void cerrarMaraton() {
        if (maratonAbierto) {
            maratonAbierto = false;
            horaCierre = obtenerHoraActual();
             JOptionPane.showMessageDialog(null, "Maratón cerrado. Hora de cierre: " + horaCierre);
        } else {
            JOptionPane.showMessageDialog(null, "El maratón ya está cerrado.");
        }
    }

    public LocalTime obtenerHoraActual() {
        LocalTime horaActual = LocalTime.now();
        return horaActual;  
    }
    public int participantesRegistrados() {
        return this.participantesCategoria1.size() + this.participantesCategoria3.size() + this.participantesCategoria2.size();
    }

    public ArrayList<Participante> getParticipantesCategoria1() {
        return participantesCategoria1;
    }

    public ArrayList<Participante> getParticipantesCategoria2() {
        return participantesCategoria2;
    }

    public ArrayList<Participante> getParticipantesCategoria3() {
        return participantesCategoria3;
    }

    public boolean isMaratonAbierto() {
        return maratonAbierto;
    }

    public void setMaratonAbierto(boolean maratonAbierto) {
        this.maratonAbierto = maratonAbierto;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(LocalTime horaCierre) {
        this.horaCierre = horaCierre;
    }


}
