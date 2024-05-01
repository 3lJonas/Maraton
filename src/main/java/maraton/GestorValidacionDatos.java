package maraton;

import javax.swing.*;

public class GestorValidacionDatos {




    public boolean validarCedula(String cedula) {
        // Verificar que la cédula tenga 10 dígitos
        if (cedula.length() != 10) {
            JOptionPane.showMessageDialog(null, "La cédula debe tener 10 dígitos.");
            return false;
        }

        // Verificar que la cédula contenga solo dígitos
        if (!cedula.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "La cédula debe contener solo números.");
            return false;
        }

        // Otras validaciones específicas de la cédula ecuatoriana
        // Aquí podrías agregar otras validaciones según las reglas de las cédulas ecuatorianas

        // Si todas las validaciones pasan, la cédula es válida
        return true;
    }






}
