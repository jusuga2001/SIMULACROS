package controller;

import entity.Especialidad;
import entity.Paciente;
import model.EspecialidadModel;
import model.PacienteModel;
import utils.Utils;

import javax.swing.*;

public class PacienteController {
    public static void create() {
        PacienteModel objPacModel = new PacienteModel();

        String nombre = JOptionPane.showInputDialog("Ingresa el nombre del paciente");
        String apellidos = JOptionPane.showInputDialog("Ingresa los apellidos del paciente");
        String fechaNac = JOptionPane.showInputDialog("Ingresa la fecha de nacimiento del paciente");
        String docId = JOptionPane.showInputDialog("Ingresa el documento de identidad del paciente");


        Paciente objPaciente = new Paciente();

        objPaciente.setNombre(nombre);
        objPaciente.setApellido(apellidos);
        objPaciente.setFechaNacimiento(fechaNac);
        objPaciente.setDocumentoIdentidad(docId);

        objPaciente = (Paciente) objPacModel.insert(objPaciente);
        JOptionPane.showMessageDialog(null, objPaciente.toString());
    }

    public static void getAll() {
        PacienteModel objPacModel = new PacienteModel();
        String listaPacientes = "LISTA PACIENTES\n";

        /*objPacModel.findAll().forEach(temp -> {
            Paciente obj = (Paciente) temp;
            listaPacientes.concat(obj.toString() + "\n");
        });*/
        for (Object iterator: objPacModel.findAll()){
            Paciente objPaciente = (Paciente) iterator;
            listaPacientes += objPaciente.toString()+ "\n";
        }
        JOptionPane.showMessageDialog(null,listaPacientes);
    }

    public static String getAllString(){
        PacienteModel objPacModel = new PacienteModel();
        String listaPacientes = "LISTA PACIENTES\n";

        /*objPacModel.findAll().forEach(temp -> {
            Paciente obj = (Paciente) temp;
            listaPacientes.concat(obj.toString() + "\n");
        });*/

        for (Object iterator: objPacModel.findAll()){
            Paciente objPaciente = (Paciente) iterator;
            listaPacientes += objPaciente.toString()+ "\n";
        }
        return listaPacientes;
    }

    public static void delete(){
        PacienteModel objPacModel = new PacienteModel();
        Object[] opciones = Utils.fromListToArray(objPacModel.findAll());
        Paciente idDelete = (Paciente) JOptionPane.showInputDialog(null, "Ingresa el id del paciente a eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]);

        Paciente objPaciente = objPacModel.findById(idDelete.getIdPaciente());

        int confirmed = 1;

        if (objPaciente == null){
            JOptionPane.showMessageDialog(null,"Paciente no encontrad@");
        } else {
            confirmed = JOptionPane.showConfirmDialog(null,"Estas seguro de eliminar al paciente? " + objPaciente.toString());
            if (confirmed == 0) {
                objPacModel.delete(objPaciente);
            }
        }
    }

    public static void update(){
        PacienteModel objPacModel = new PacienteModel();
        Object[] opciones = Utils.fromListToArray(objPacModel.findAll());

        Paciente idUpdate = (Paciente) JOptionPane.showInputDialog(null, "Ingresa el id del paciente a actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]);


        Paciente objPaciente = objPacModel.findById(idUpdate.getIdPaciente());

        String nombre = JOptionPane.showInputDialog("Ingresa el nombre del paciente",objPaciente.getNombre());
        String apellidos = JOptionPane.showInputDialog("Ingresa los apellidos del paciente",objPaciente.getApellido());
        String fechaNac = JOptionPane.showInputDialog("Ingresa la fecha de nacimiento del paciente",objPaciente.getFechaNacimiento());
        String docId = JOptionPane.showInputDialog("Ingresa el documento de identidad del paciente",objPaciente.getDocumentoIdentidad());

        objPaciente.setNombre(nombre);
        objPaciente.setApellido(apellidos);
        objPaciente.setFechaNacimiento(fechaNac);
        objPaciente.setDocumentoIdentidad(docId);

        objPacModel.update(objPaciente);
    }
}
