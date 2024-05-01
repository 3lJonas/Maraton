package maraton;

import javax.swing.JOptionPane;

public class Menu {

    Gestormaraton gm;

    public Menu() {
        this.gm = new Gestormaraton();
    }

    public void ejecutarMenu() {
        int opcion = -1;
        do {
            opcion = this.menuMaraton();
        } while (opcion != 4 && !this.gm.getMaraton().isMaratonAbierto());
        opcion = -1;
        this.menuCompeticion();
    }

    public void menuCompeticion() {
        int opcion = -1;
        do {
            String[] opciones = {
                    "Registrar llegada participante",
                    "Listado de participantes",
                    "Registrar ausencia de participante",
                    "Cerrar Maraton",
                    "Mostrar Reportes",
                    "Salir"
            };
            opcion = JOptionPane.showOptionDialog(null, "=== Menú Competicion ===", "Competicion", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

            switch (opcion) {
                case 0:
                    gm.registrarLlegada();
                    break;
                case 1:
                    gm.listarParticipantes();
                    break;
                case 2:
                    gm.registrarausencia();
                    break;
                case 3:
                    this.gm.getMaraton().cerrarMaraton();
                    break;
                case 4:
                    this.menuReportes();
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Saliendo del programa. ¡Hasta luego!");
                    System.exit(0);

                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 5);
    }

    public void menuReportes() {
        int opcion;
        do {
            String[] opciones = {
                    "Listado de participantes por auspiciante",
                    "Listado de participantes por categoría en orden de llegada",
                    "Listado de inscritos que no participaron",
                    "Listado de quienes no completaron la carrera",
                    "Listado de inscritos",
                    "Regresar"
            };
            opcion = JOptionPane.showOptionDialog(null, "=== Menú de Reportes ===", "Reportes", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

            switch (opcion) {
                case 0:
                    gm.listadoporpatrocinador();
                    break;
                case 1:
                    gm.listadoporcategoriallegada();
                    break;
                case 2:
                    gm.listadoinscritosnoparticipar();
                    break;
                case 3:
                    gm.listadoinscritosnoterminar();
                    break;
                case 4:
                    gm.listarParticipantes();
                    break;
                case 5:
                    menuCompeticion();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida. Intente de nuevo.");
                    break;
            }

        } while (opcion != 5);
    }

    public int menuMaraton() {
        int opcion = -1;
        String[] opciones = {
                "Registrar participante",
                "Modificar Participante",
                "Eliminar participante",
                "Listado de participantes",
                "Iniciar Maraton",
                "Salir"
        };

        opcion = JOptionPane.showOptionDialog(null, "                                                                                                                   == Menú Maratón ===", "Maratón", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
        System.out.println(opcion);
        switch (opcion) {
            case 0:
                Participante p = gm.crearParticipante();
                if (p != null) {
                    this.gm.getMaraton().registrarParticipante(p);
                    JOptionPane.showMessageDialog(null, "Participante registrado exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "Participante no registrado");
                }
                break;
            case 1:
                String cedulaM = JOptionPane.showInputDialog("Cedula de Participante a modificar:");
                if (gm.eliminarParticipante(cedulaM)) {
                    Participante pM = gm.crearParticipante();
                    if (pM != null) {
                        this.gm.getMaraton().registrarParticipante(pM);
                        JOptionPane.showMessageDialog(null, "Participante modificado exitosamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Participante no modificado");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Participante no existe");
                }
                break;
            case 2:
                String cedula = JOptionPane.showInputDialog("Cedula de Participante a eliminar:");
                if (gm.eliminarParticipante(cedula)) {
                    JOptionPane.showMessageDialog(null, "Participante eliminado");
                } else {
                    JOptionPane.showMessageDialog(null, "Participante no existe");
                }
                break;
            case 3:
                gm.listarParticipantes();
                break;
            case 4:
                if (!this.gm.getMaraton().iniciarMaraton()) {
                    opcion = -1;
                }
                break;
            case 5:
                JOptionPane.showMessageDialog(null, "Saliendo del programa. ¡Hasta luego!");
                System.exit(0);
                break;
            default:
                System.exit(0);
                break;
        }
        return opcion;
    }

}
