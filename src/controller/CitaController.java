package controller;

import entity.Cita;
import entity.Paciente;
import model.CitaModel;
import model.MedicoModel;
import model.PacienteModel;
import utils.Utils;

import javax.swing.*;

public class CitaController {
    public static void create(){
        CitaModel objCitaModel = new CitaModel();
        PacienteModel objPacMod = new PacienteModel();
        MedicoModel objMedMod = new MedicoModel();

        int fk_id_paciente = Integer.parseInt(JOptionPane.showInputDialog(null,PacienteController.getAllString()+"\nInserta el id del paciente"));
        int fk_id_medico = Integer.parseInt(JOptionPane.showInputDialog(null,MedicoController.getAllString()+"\nInserta el id del medico"));
        String fecha_cita = JOptionPane.showInputDialog(null,"Ingresa la fecha de la cita en este formato 'YYYY-MM-DD': ");
        String hora_cita = JOptionPane.showInputDialog(null,"Ingresa la hora de la cita en este formato 'HH-MM-SS': ");
        String motivo = JOptionPane.showInputDialog(null,"Ingresa el motivo de la cita: ");

        Cita objCita = new Cita();

        objCita.setFkIdPaciente(fk_id_paciente);
        objCita.setFkIdMedico(fk_id_medico);
        objCita.setFechaCita(fecha_cita);
        objCita.setHoraCita(hora_cita);
        objCita.setMotivo(motivo);

        objCita = (Cita) objCitaModel.insert(objCita);

        objCita.setPaciente(objPacMod.findById(fk_id_paciente));
        objCita.setMedico(objMedMod.findById(fk_id_medico));
        JOptionPane.showMessageDialog(null,objCita.toString());

    }

    public static void getAll(){
        CitaModel objCitaModel = new CitaModel();
        String listaCitas = "LISTA DE CITAS\n";

        for (Object iterator: objCitaModel.findAll()){
            Cita objCita = (Cita) iterator;
            listaCitas += objCita.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null,listaCitas);
    }

    public static String getAllString(){
        CitaModel objCitaModel = new CitaModel();
        String listaCitas = "LISTA DE CITAS\n";

        for (Object iterator: objCitaModel.findAll()){
            Cita objCita = (Cita) iterator;
            listaCitas += objCita.toString() + "\n";
        }
        return listaCitas;
    }

    public static void delete(){
        CitaModel objCitaModel = new CitaModel();
        Object[] opciones = Utils.fromListToArray(objCitaModel.findAll());

        Cita idDelete = (Cita) JOptionPane.showInputDialog(null,
                "\nIngresa el id de la cita que deseas eliminar: ",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]);

        Cita objCita = objCitaModel.findById(idDelete.getIdCita());
        int confirmed = 1;

        if (objCita == null){
            JOptionPane.showMessageDialog(null, "Cita no encontrada");
        } else {
            confirmed = JOptionPane.showConfirmDialog(null,"Estás seguro de eliminar esta cita?");
            if (confirmed == 0){
                objCitaModel.delete(objCita);
            }
        }
    }

    public static void update(){
        CitaModel objCitaModel = new CitaModel();
        Object[] opciones = Utils.fromListToArray(objCitaModel.findAll());

        Cita idUpdate = (Cita) JOptionPane.showInputDialog(null,
                "\nIngresa el id de la cita que deseas actualizar: ",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]);
        Cita objCita = objCitaModel.findById(idUpdate.getIdCita());


        if (objCita == null){
            JOptionPane.showMessageDialog(null, "Cita no encontrada");
        } else {
            int fk_id_paciente = Integer.parseInt(JOptionPane.showInputDialog(null,PacienteController.getAllString()+"Inserta el id del paciente",objCita.getFkIdPaciente()));
            int fk_id_medico = Integer.parseInt(JOptionPane.showInputDialog(null,MedicoController.getAllString()+"Inserta el id del medico",objCita.getFkIdMedico()));
            String fecha_cita = JOptionPane.showInputDialog(null,"Ingresa la fecha de la cita en este formato 'YYYY-MM-DD': ",objCita.getFechaCita());
            String hora_cita = JOptionPane.showInputDialog(null,"Ingresa la hora de la cita en este formato 'HH-MM-SS': ",objCita.getHoraCita());
            String motivo = JOptionPane.showInputDialog(null,"Ingresa el motivo de la cita: ",objCita.getMotivo());

            objCita.setFkIdPaciente(fk_id_paciente);
            objCita.setFkIdMedico(fk_id_medico);
            objCita.setFechaCita(fecha_cita);
            objCita.setHoraCita(hora_cita);
            objCita.setMotivo(motivo);

            objCitaModel.update(objCita);
        }
    }


}
