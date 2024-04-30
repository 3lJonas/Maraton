package maraton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Gestormaraton {

    Scanner scanner = new Scanner(System.in);
    private Maraton maraton;

    public Gestormaraton() {
        this.maraton = new Maraton();
    }

    public boolean eliminarParticipante(String cedula) {
        Participante p = this.buscarParticipantePorCedula(cedula);
        return this.maraton.eliminarParticipante(p);
    }

    public Participante crearParticipante() {

        String numeroCedula = JOptionPane.showInputDialog(null, "Ingrese el número de cédula: ");

        if (buscarParticipantePorCedula(numeroCedula) != null) {
            JOptionPane.showMessageDialog(null, "El participante con la cédula " + numeroCedula + " ya está registrado.");
            return null;
        }

        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre: ");

        String apellido = JOptionPane.showInputDialog(null, "Ingrese el apellido: ");
        int edad = 0;
        try {

            edad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la edad: "));

        } catch (Exception e) {
            return null;
        }

        String sexo = JOptionPane.showInputDialog(null, "Ingrese el sexo: ");

        Participante nuevoParticipante = new Participante(numeroCedula, nombre, apellido, edad, sexo);

        String respuesta = JOptionPane.showInputDialog(null, "¿Desea agregar patrocinadores? (S/N): ");

        while (respuesta.equalsIgnoreCase("S")) {

            String nombrePatrocinador = JOptionPane.showInputDialog(null, "Ingrese el nombre del patrocinador: ");

            String tipoPatrocinador = JOptionPane.showInputDialog(null, "Ingrese el tipo de patrocinador: ");

            Patrocinador patrocinador = new Patrocinador(nombrePatrocinador, tipoPatrocinador);
            nuevoParticipante.agregarPatrocinador(patrocinador);

            respuesta = JOptionPane.showInputDialog(null, "¿Desea agregar otro patrocinador? (S/N): ");
        }

        return nuevoParticipante;
    }

    public boolean registrarLlegada() {
        if (this.maraton.isMaratonAbierto()) {

            String cedula = JOptionPane.showInputDialog(null, "Cedula de Participante a modificar:");
            Participante p = this.buscarParticipantePorCedula(cedula);
            if (p != null && !p.isAusencia()) {
                p.setHoraLlegada(this.maraton.obtenerHoraActual());
                return true;
            }
            JOptionPane.showMessageDialog(null, "Participante no esta en la competencia");
        } else {
            JOptionPane.showMessageDialog(null, "Competencia Cerrada");
        }
        return false;

    }

    public boolean registrarausencia() {

        String cedula = JOptionPane.showInputDialog(null, "Cedula del participante: ");
        Participante p = this.buscarParticipantePorCedula(cedula);
        if (p != null) {
            p.setAusencia(true);
            return true;
        }
        JOptionPane.showMessageDialog(null, "Participante no Existe");
        return false;
    }

    public void listarParticipantes() {
        String reporte = "Reportes de la maraton:";
        for (int i = 1; i <= 3; i++) {
            reporte = reporte + "\nCategoría " + i + ":";
            ArrayList<Participante> participantesCategoria = obtenerParticipantesCategoria(i);
            for (Participante participante : participantesCategoria) {
                reporte = reporte + "\n - " + participante.getNombre() + " " + participante.getApellido();
            }
        }
        JOptionPane.showMessageDialog(null, reporte);
    }

    public ArrayList<Participante> obtenerParticipantesCategoria(int categoria) {
        switch (categoria) {
            case 1:
                return this.maraton.getParticipantesCategoria1();
            case 2:
                return this.maraton.getParticipantesCategoria2();
            case 3:
                return this.maraton.getParticipantesCategoria3();
            default:
                return null;
        }
    }

    public Participante buscarParticipantePorCedula(String numeroCedula) {
        for (int i = 1; i <= 3; i++) {
            ArrayList<Participante> participantesCategoria;
            switch (i) {
                case 1:
                    participantesCategoria = this.maraton.getParticipantesCategoria1();
                    break;
                case 2:
                    participantesCategoria = this.maraton.getParticipantesCategoria2();
                    break;
                default:
                    participantesCategoria = this.maraton.getParticipantesCategoria3();
                    break;
            }
            if (participantesCategoria != null) {
                for (Participante participante : participantesCategoria) {
                    if (participante.getNumeroCedula().equals(numeroCedula)) {
                        return participante;
                    }
                }
            }
        }
        return null;
    }

    public ArrayList<Participante> obtenerParticipantes() {
        ArrayList<Participante> arraysParticipantes = new ArrayList<>();

        arraysParticipantes.addAll(this.maraton.getParticipantesCategoria1());
        arraysParticipantes.addAll(this.maraton.getParticipantesCategoria2());
        arraysParticipantes.addAll(this.maraton.getParticipantesCategoria3());

        return arraysParticipantes;
    }

    public void listadoporpatrocinador() {
        ArrayList<Participante> participantes = obtenerParticipantes();
        if (participantes != null && !participantes.isEmpty()) {
            String mensaje = "Listado de participantes por patrocinador:\n\n";

            for (Participante p : participantes) {
                mensaje = mensaje + ("Número de Cédula: ") + (p.getNumeroCedula() + "\n");
                mensaje = mensaje + ("Nombre: ") + (p.getNombre()) + ("\n");
                mensaje = mensaje + ("Apellido: ") + (p.getApellido()) + ("\n");
                mensaje = mensaje + ("Edad: ") + (p.getEdad()) + ("\n");
                mensaje = mensaje + ("Sexo: ") + (p.getSexo()) + ("\n");

                ArrayList<Patrocinador> patrocinadores = p.getPatrocinadores();

                if (patrocinadores != null && !patrocinadores.isEmpty()) {
                    mensaje = mensaje + ("Patrocinadores:\n");

                    for (Patrocinador patrocinador : patrocinadores) {
                        if (patrocinador != null) {
                            mensaje = mensaje + (" - Nombre: ") + (patrocinador.getNombre()) + ("\n");
                            mensaje = mensaje + (" - Tipo: ") + (patrocinador.getTipo()) + ("\n");
                        }
                    }
                }
                 mensaje = mensaje + "----------------------------------\n";
            }
            JOptionPane.showMessageDialog(null, mensaje, "Listado de participantes por patrocinador", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No hay participantes registrados.", "Listado de participantes por patrocinador", JOptionPane.INFORMATION_MESSAGE);
        }
    }

public void listadoporcategoriallegada() {
    String mensaje = "Reportes de la maraton:\n";

    for (int i = 1; i <= 3; i++) {
        ArrayList<Participante> participantesCategoria = obtenerParticipantesCategoria(i);
        Collections.sort(participantesCategoria);
        mensaje=mensaje+"Categoria "+i+"\n";
        for (Participante participante : participantesCategoria) {
            if (participante.getHoraLlegada() != null) {
                mensaje=mensaje+" - "+participante.getNombre()+" "+participante.getApellido()+                    
                        " "+participante.getHoraLlegada()+"\n";
            }
        }
    }
    JOptionPane.showMessageDialog(null, mensaje, "Reportes de la maratón", JOptionPane.INFORMATION_MESSAGE);
}

    public ArrayList<Participante> obtenerinscritos() {
        ArrayList<Participante> arraysParticipantes = new ArrayList<>();

        arraysParticipantes.addAll(this.maraton.getParticipantesCategoria1());
        arraysParticipantes.addAll(this.maraton.getParticipantesCategoria2());
        arraysParticipantes.addAll(this.maraton.getParticipantesCategoria3());

        return arraysParticipantes;
    }

    public void listadoinscritosnoparticipar() {
       String mensaje="-----inscritos pero no participaron----\n";
        ArrayList<Participante> participantesCategoria = obtenerParticipantes();
        for (Participante participante : participantesCategoria) {
            if (participante.isAusencia()) {
                mensaje=mensaje+" - " + participante.getNombre() + " " + participante.getApellido()+"\n";
            }
        }
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public void listadoinscritosnoterminar() {
       String mensaje="-----inscritos que no terminaron----\n";
        ArrayList<Participante> participantesCategoria = obtenerParticipantes();
        for (Participante participante : participantesCategoria) {
            if (!participante.isAusencia() && participante.getHoraLlegada() == null) {
               mensaje=mensaje+" - " + participante.getNombre() + " " + participante.getApellido()+"\n";
            }
        }
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public Maraton getMaraton() {
        return maraton;
    }

    public void setMaraton(Maraton maraton) {
        this.maraton = maraton;
    }

}
