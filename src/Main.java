import controller.CitaController;
import controller.EspecialidadController;
import controller.MedicoController;
import controller.PacienteController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String optionMain = "";
        do {
            optionMain = JOptionPane.showInputDialog("""
                    Elige la opción a administrar
                    1. Paciente.
                    2. Medico.
                    3. Especialidad(Medico).
                    4. Citas.
                    5. Salir.
                    
                    """);
            switch (optionMain) {
                case "1" -> {
                    String option1 = "";
                    do {
                        option1 = JOptionPane.showInputDialog("""
                                1. Listar Pacientes.
                                2. Insertar Paciente.
                                3. Actualizar Paciente.
                                4. Eliminar paciente.
                                5. Salir
                                """);
                        switch (option1){
                            case "1" -> PacienteController.getAll();
                            case "2" -> PacienteController.create();
                            case "3" -> PacienteController.update();
                            case "4" -> PacienteController.delete();
                        }
                    } while (!option1.equals("5"));
                }
                case "2" -> {
                    String option2 = "";
                    do {
                        option2 = JOptionPane.showInputDialog("""
                                1. Listar Medicos.
                                2. Insertar Medico.
                                3. Actualizar Medico.
                                4. Eliminar Medico.
                                5. Salir
                                """);
                        switch (option2){
                            case "1" -> MedicoController.getAll();
                            case "2" -> MedicoController.create();
                            case "3" -> MedicoController.update();
                            case "4" -> MedicoController.delete();
                        }
                    } while (!option2.equals("5"));
                }

                case "3" -> {
                    String option3 = "";
                    do {
                        option3 = JOptionPane.showInputDialog("""
                                1. Listar Especialidades.
                                2. Insertar Especialidad.
                                3. Actualizar Especialidad.
                                4. Eliminar Especialidad.
                                5. Salir
                                """);
                        switch (option3){
                            case "1" -> EspecialidadController.getAll();
                            case "2" -> EspecialidadController.create();
                            case "3" -> EspecialidadController.update();
                            case "4" -> EspecialidadController.delete();
                        }
                    } while (!option3.equals("5"));
                }

                case "4" -> {
                    String option4 = "";
                    do {
                        option4 = JOptionPane.showInputDialog("""
                                1. Listar Citas.
                                2. Insertar Cita.
                                3. Actualizar Cita.
                                4. Eliminar Cita.
                                5. Salir
                                """);
                        switch (option4){
                            case "1" -> CitaController.getAll();
                            case "2" -> CitaController.create();
                            case "3" -> CitaController.update();
                            case "4" -> CitaController.delete();
                        }
                    } while (!option4.equals("5"));
                }
            }
        } while (!optionMain.equals("5"));
    }
}