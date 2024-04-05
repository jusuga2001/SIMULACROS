package controller;

import entity.Especialidad;
import model.EspecialidadModel;
import utils.Utils;

import javax.swing.*;

public class EspecialidadController {
    public static void create() {
        EspecialidadModel objEspModel = new EspecialidadModel();

        String nombre = JOptionPane.showInputDialog("Ingresa el nombre de la especialidad");
        String descrip = JOptionPane.showInputDialog("Ingresa una descripcion para la especialidad");

        Especialidad objEspecialidad = new Especialidad();

        objEspecialidad.setNombre(nombre);
        objEspecialidad.setDescripcion(descrip);

        objEspecialidad = (Especialidad) objEspModel.insert(objEspecialidad);
        JOptionPane.showMessageDialog(null, objEspecialidad.toString());
    }

    public static void getAll() {
        EspecialidadModel objEspModel = new EspecialidadModel();
        String listaEspec = "LISTA ESPECIALIDADES\n";

        /*objEspModel.findAll().forEach(temp -> {
            Especialidad obj = (Especialidad) temp;
            listaEspec.concat(obj.toString() + "\n");
        });*/
        for (Object iterator: objEspModel.findAll()){
            Especialidad objEsp = (Especialidad) iterator;
            listaEspec += objEsp.toString()+ "\n" ;
        }
        JOptionPane.showMessageDialog(null,listaEspec);
    }

    public static String getAllString(){
        EspecialidadModel objEspModel = new EspecialidadModel();
        String listaEspec = "LISTA ESPECIALIDADES\n";

        /*objEspModel.findAll().forEach(temp -> {
            Especialidad obj = (Especialidad) temp;
            listaEspec.concat(obj.toString() + "\n");
        });*/

        for (Object iterator: objEspModel.findAll()){
            Especialidad objEsp = (Especialidad) iterator;
            listaEspec += objEsp.toString()+ "\n" ;
        }
            return listaEspec;
    }

    public static void delete(){
        EspecialidadModel objEspModel = new EspecialidadModel();
        Object[] opciones = Utils.fromListToArray(objEspModel.findAll());
        Especialidad idDelete = (Especialidad) JOptionPane.showInputDialog( null,
                "Ingresa el id de la especialidad a eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]);

        Especialidad objEspecialidad = objEspModel.findById(idDelete.getIdEspecialidad());

        int confirmed = 1;

        if (objEspecialidad == null){
            JOptionPane.showMessageDialog(null,"Especialidad no encontrada");
        } else {
            confirmed = JOptionPane.showConfirmDialog(null,"Estas seguro de eliminar la especialidad?" + objEspecialidad.toString());
            if (confirmed == 0) {
                objEspModel.delete(objEspecialidad);
            }
        }
    }

    public static void update(){
        EspecialidadModel objEspModel = new EspecialidadModel();
        Object[] opciones = Utils.fromListToArray(objEspModel.findAll());
        Especialidad idUpdate = (Especialidad) JOptionPane.showInputDialog(null,
                "Ingresa el id de la especialidad a eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]);

        Especialidad objEspecialidad = objEspModel.findById(idUpdate.getIdEspecialidad());

        String nombre = JOptionPane.showInputDialog("Ingresa el nombre de la especialidad",objEspecialidad.getNombre());
        String descrip = JOptionPane.showInputDialog("Ingresa una descripcion para la especialidad",objEspecialidad.getDescripcion());

        objEspecialidad.setNombre(nombre);
        objEspecialidad.setDescripcion(descrip);

        objEspModel.update(objEspecialidad);
    }
}
