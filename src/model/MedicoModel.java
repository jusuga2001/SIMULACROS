package model;

import database.CRUD;
import database.ConfigDb;
import entity.Especialidad;
import entity.Medico;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDb.openConnection();
        Medico objMedico = (Medico) obj;
        try {
            String sql = "INSERT INTO medico (nombre,apellidos,fk_id_especialidad) VALUES (?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objMedico.getNombre());
            objPrepare.setString(2,objMedico.getApellido());
            objPrepare.setInt(3,objMedico.getFkIdEspecialidad());

            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()){
                objMedico.setIdMedico(objResult.getInt(1));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
            ConfigDb.closeConnection();
        }
        return objMedico;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listaMedicos = new ArrayList<>();
        Connection objConnection = ConfigDb.openConnection();

        try {
            String sql = "SELECT * FROM medico INNER JOIN especialidad ON medico.fk_id_especialidad = especialidad.id;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){

                Medico objMedico = new Medico();

                objMedico.setIdMedico(objResult.getInt("id_medico"));
                objMedico.setNombre(objResult.getString("nombre"));
                objMedico.setApellido(objResult.getString("apellidos"));
                objMedico.setFkIdEspecialidad(objResult.getInt("fk_id_especialidad"));
                objMedico.setEspecialidad(new Especialidad(objResult.getInt("especialidad.id"),objResult.getString("especialidad.nombre"),objResult.getString("especialidad.descripcion")));

                listaMedicos.add(objMedico);
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ConfigDb.closeConnection();
        }

        return listaMedicos;
    }

    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDb.openConnection();
        Medico objMedico = (Medico) obj;
        boolean isDeleted = false;
        try {
            String sql = "DELETE FROM medico WHERE id_medico = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objMedico.getIdMedico());

            int totalRowsAffected = objPrepare.executeUpdate();

            if (totalRowsAffected > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"El medico fue eliminado exitosamente!");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ConfigDb.closeConnection();
        }

        return isDeleted;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDb.openConnection();
        Medico objMedico = (Medico) obj;
        boolean isUpdated = false;
        try {
            String sql = "UPDATE medico SET nombre = ?, apellidos = ?, fk_id_especialidad = ? WHERE id_medico = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,objMedico.getNombre());
            objPrepare.setString(2,objMedico.getApellido());
            objPrepare.setInt(3,objMedico.getFkIdEspecialidad());
            objPrepare.setInt(4,objMedico.getIdMedico());

            int totalRowsAffected = objPrepare.executeUpdate();

            if (totalRowsAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null,"El medico fue actualizado exitosamente!");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ConfigDb.closeConnection();
        }

        return isUpdated;
    }

    public Medico findById(int id){
        Connection objConnection = ConfigDb.openConnection();
        Medico objMedico = null;
        try {
            String sql = "SELECT * FROM medico INNER JOIN especialidad ON medico.fk_id_especialidad = especialidad.id WHERE id_medico = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,id);
            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()){
                objMedico = new Medico();
                objMedico.setIdMedico(objResult.getInt("id_medico"));
                objMedico.setNombre(objResult.getString("nombre"));
                objMedico.setApellido(objResult.getString("apellidos"));
                objMedico.setFkIdEspecialidad(objResult.getInt("fk_id_especialidad"));
                objMedico.setEspecialidad(new Especialidad(objResult.getInt("especialidad.id"),objResult.getString("especialidad.nombre"),objResult.getString("especialidad.descripcion")));
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
            ConfigDb.closeConnection();
        }

        return objMedico;
    }
}
