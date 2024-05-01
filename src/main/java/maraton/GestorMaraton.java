package maraton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class GestorMaraton {

    GestorValidacionDatos validar = new GestorValidacionDatos();
   
    private Maraton maraton;

    public GestorMaraton() {
        this.maraton = new Maraton();
    }

    public boolean eliminarParticipante(String cedula) {
        Participante p = this.buscarParticipantePorCedula(cedula);
        return this.maraton.eliminarParticipante(p);
    }

    public Participante crearParticipante() {

        //Creamos un participante
        Participante p = new Participante();

        //Validamos la cedula del participante
        do {
            p.setNumeroCedula(JOptionPane.showInputDialog(null, "Ingrese el número de cédula: "));
            if (p.getNumeroCedula() == null) {
                return null;
            }
        } while (!validar.validarCedula(p.getNumeroCedula()));

        //Buscamos que la cedula no se repita
        if (buscarParticipantePorCedula(p.getNumeroCedula()) != null) {
            JOptionPane.showMessageDialog(null, "El participante con la cédula " + p.getNumeroCedula() + " ya está registrado.");
            return null;
        }

        p.setNombre(JOptionPane.showInputDialog(null, "Ingrese el nombre: "));
        if (p.getNombre() == null) {
            return null;
        }
        p.setApellido(JOptionPane.showInputDialog(null, "Ingrese el apellido: "));
        if (p.getApellido() == null) {
            return null;
        }
        p.setEdad(0);
        try {

            p.setEdad(Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la edad: ")));

        } catch (Exception e) {
            return null;
        }

        p.setSexo(JOptionPane.showInputDialog(null, "Ingrese el sexo: "));
        if (p.getSexo() == null) {
            return null;
        }
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea agregar patrocinadores? ");

        while (respuesta == 0) {

            Patrocinador patrocinador = new Patrocinador();

            patrocinador.setNombre(JOptionPane.showInputDialog(null, "Ingrese el nombre del patrocinador: "));
            if (patrocinador.getNombre() == null) {
                return null;
            }
            patrocinador.setTipo(JOptionPane.showInputDialog(null, "Ingrese el tipo de patrocinador: "));
            if (patrocinador.getTipo() == null) {
                return null;
            }
            p.agregarPatrocinador(patrocinador);

            respuesta = JOptionPane.showConfirmDialog(null, "¿Desea agregar otro patrocinador? (S/N): ");

        }

        return p;
    }

    public boolean registrarLlegada() {
        if (this.maraton.isMaratonAbierto()) {

            String cedula = JOptionPane.showInputDialog(null, "Cedula de Participante a modificar:");
            if (cedula == null) {
                return false;
            }
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
        if (cedula == null) {
            return false;
        }
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
            mensaje = mensaje + "Categoria " + i + "\n";
            for (Participante participante : participantesCategoria) {
                if (participante.getHoraLlegada() != null) {
                    mensaje = mensaje + " - " + participante.getNombre() + " " + participante.getApellido()
                            + " " + participante.getHoraLlegada() + "\n";
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
        String mensaje = "-----inscritos pero no participaron----\n";
        ArrayList<Participante> participantesCategoria = obtenerParticipantes();
        for (Participante participante : participantesCategoria) {
            if (participante.isAusencia()) {
                mensaje = mensaje + " - " + participante.getNombre() + " " + participante.getApellido() + "\n";
            }
        }
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public void listadoinscritosnoterminar() {
        String mensaje = "-----inscritos que no terminaron----\n";
        ArrayList<Participante> participantesCategoria = obtenerParticipantes();
        for (Participante participante : participantesCategoria) {
            if (!participante.isAusencia() && participante.getHoraLlegada() == null) {
                mensaje = mensaje + " - " + participante.getNombre() + " " + participante.getApellido() + "\n";
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
