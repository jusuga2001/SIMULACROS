package controller;

import entity.Especialidad;
import entity.Medico;
import model.EspecialidadModel;
import model.MedicoModel;
import utils.Utils;

import javax.swing.*;

public class MedicoController {

    public static void create() {
        MedicoModel objMedModel = new MedicoModel();
        EspecialidadModel objEspModel = new EspecialidadModel();

        String nombre = JOptionPane.showInputDialog("Ingresa el nombre del medico");
        String apellidos = JOptionPane.showInputDialog("Ingresa los apellidos del medico");
        int fk_id_especialidad = Integer.parseInt(JOptionPane.showInputDialog(EspecialidadController.getAllString()+"\nIngresa el id de la especialidad"));

        Medico objMedico = new Medico();

        objMedico.setNombre(nombre);
        objMedico.setApellido(apellidos);
        objMedico.setFkIdEspecialidad(fk_id_especialidad);

        objMedico = (Medico) objMedModel.insert(objMedico);

        objMedico.setEspecialidad(objEspModel.findById(fk_id_especialidad));

        JOptionPane.showMessageDialog(null, objMedico.toString());
    }

    public static void getAll() {
        MedicoModel objMedModel = new MedicoModel();
        String listaMedicos = "LISTA MEDICOS\n";

        /*objMedModel.findAll().forEach(temp -> {
            Medico obj = (Medico) temp;
            listaMedicos.concat(obj.toString() + "\n");
        });*/
        for (Object iterator: objMedModel.findAll()){
            Medico objMedico = (Medico) iterator;
            listaMedicos += objMedico.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null,listaMedicos);
    }

    public static String getAllString(){
        MedicoModel objMedModel = new MedicoModel();
        String listaMedicos = "LISTA MEDICOS\n";


          /*objMedModel.findAll().forEach(temp -> {
            Medico obj = (Medico) temp;
            listaMedicos.concat(obj.toString() + "\n");
        });*/
        for (Object iterator: objMedModel.findAll()){
            Medico objMedico = (Medico) iterator;
            listaMedicos += objMedico.toString()+ "\n";
        }

        return listaMedicos;
    }

    public static void delete(){
        MedicoModel objMedModel = new MedicoModel();
        Object[] opciones = Utils.fromListToArray(objMedModel.findAll());
        Medico idDelete = (Medico) JOptionPane.showInputDialog(null,
                "Ingresa el id del medico a eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]);

        Medico objMedico = objMedModel.findById(idDelete.getIdMedico());

        int confirmed = 1;

        if (objMedico == null){
            JOptionPane.showMessageDialog(null,"Medico no encontrad@");
        } else {
            confirmed = JOptionPane.showConfirmDialog(null,"Estas seguro de eliminar el medic@?" + objMedico.toString());
            if (confirmed == 0) {
                objMedModel.delete(objMedico);
            }
        }
    }

    public static void update(){
        MedicoModel objMedModel = new MedicoModel();
        Object[] opciones = Utils.fromListToArray(objMedModel.findAll());
        Medico idUpdate = (Medico) JOptionPane.showInputDialog(null,
                "Ingresa el id del Medico a actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]);

        Medico objMedico = objMedModel.findById(idUpdate.getIdMedico());

        String nombre = JOptionPane.showInputDialog("Ingresa el nombre del medico",objMedico.getNombre());
        String apellidos = JOptionPane.showInputDialog("Ingresa los apellidos del medico",objMedico.getApellido());
        int fk_id_especialidad = Integer.parseInt(JOptionPane.showInputDialog(EspecialidadController.getAllString()+"\nIngresa el id de la especialidad"));

        objMedico.setNombre(nombre);
        objMedico.setApellido(apellidos);
        objMedico.setFkIdEspecialidad(fk_id_especialidad);

        objMedModel.update(objMedico);
    }
}
