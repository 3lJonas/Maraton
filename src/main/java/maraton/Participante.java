/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maraton;

/**
 *
 * @author User
 */
import java.time.LocalTime;
import java.util.ArrayList;

public class Participante implements Comparable<Participante> {

    private String numeroCedula;
    private String nombre;
    private String apellido;
    private int edad;
    private String sexo;
    private ArrayList<Patrocinador> patrocinadores;
    private LocalTime horaLlegada;
    private boolean ausencia;

    public Participante(String numeroCedula, String nombre, String apellido, int edad, String sexo) {
        this.numeroCedula = numeroCedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.sexo = sexo;
        this.patrocinadores = new ArrayList<>();
        this.ausencia = false;
    }

    public Participante() {

    }

    public boolean isAusencia() {
        return this.ausencia;
    }

    public void setAusencia(boolean ausencia) {
        this.ausencia = ausencia;
    }

    public LocalTime getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(LocalTime horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public String getNumeroCedula() {
        return numeroCedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    public String getSexo() {
        return sexo;
    }

    public ArrayList<Patrocinador> getPatrocinadores() {
        return patrocinadores;
    }

    public void agregarPatrocinador(Patrocinador patrocinador) {
        patrocinadores.add(patrocinador);
    }

    public String toString() {
        return "Participante"
                + "Cedula='" + this.numeroCedula + '\''
                + "nombre='" + this.nombre + '\''
                + "apellido='" + this.apellido + '\''
                + "Edad= " + this.edad + '\''
                + "Sexo: " + this.sexo + '\'';
    }

    public void mostrarInformacionPatrocinador() {
        if (!this.patrocinadores.isEmpty()) {
            String reporte="Patrocinadores:\n";
            for (Patrocinador patrocinador : this.patrocinadores) {
               reporte=reporte+"  - Nombre: " + patrocinador.getNombre()+"\t";
                reporte=reporte+"  - Tipo: " + patrocinador.getTipo()+"\n";
            }
        }
    }

    @Override
    public int compareTo(Participante otroParticipante) {
        if (this.horaLlegada == null && otroParticipante.horaLlegada == null) {
            return 0;
        } else if (this.horaLlegada == null) {
            return -1;
        } else if (otroParticipante.horaLlegada == null) {
            return 1;
        } else {
            return this.horaLlegada.compareTo(otroParticipante.horaLlegada);
        }
    }
}
