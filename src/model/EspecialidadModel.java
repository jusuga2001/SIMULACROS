package model;

import database.CRUD;
import database.ConfigDb;
import entity.Especialidad;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadModel implements CRUD {


    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDb.openConnection();
        Especialidad objEspecialidad = (Especialidad) obj;
        try {
            String sql = "INSERT INTO especialidad (nombre,descripcion) VALUES (?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objEspecialidad.getNombre());
            objPrepare.setString(2,objEspecialidad.getDescripcion());

            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()){
                objEspecialidad.setIdEspecialidad(objResult.getInt(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
            ConfigDb.closeConnection();
        }
        return objEspecialidad;
    }

    @Override
    public List<Object> findAll() {

        List<Object> listEspecialidad = new ArrayList<>();
        Connection objConnection = ConfigDb.openConnection();

        try {
            String sql = "SELECT * FROM especialidad;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Especialidad objEspe = new Especialidad();

                objEspe.setIdEspecialidad(objResult.getInt("id"));
                objEspe.setNombre(objResult.getString("nombre"));
                objEspe.setDescripcion(objResult.getString("descripcion"));

                listEspecialidad.add(objEspe);
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ConfigDb.closeConnection();
        }

        return listEspecialidad;
    }

    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDb.openConnection();
        Especialidad objEspe = (Especialidad) obj;
        boolean isDeleted = false;
        try {
            String sql = "DELETE FROM especialidad WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objEspe.getIdEspecialidad());

            int totalRowsAffected = objPrepare.executeUpdate();

            if (totalRowsAffected > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"La especialidad fue eliminada exitosamente!");
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
        Especialidad objEspe = (Especialidad) obj;
        boolean isUpdated = false;
        try {
            String sql = "UPDATE especialidad SET nombre = ?, descripcion = ? WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,objEspe.getNombre());
            objPrepare.setString(2,objEspe.getDescripcion());
            objPrepare.setInt(3,objEspe.getIdEspecialidad());

            int totalRowsAffected = objPrepare.executeUpdate();

            if (totalRowsAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null,"La especialidad fue actualizada exitosamente!");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ConfigDb.closeConnection();
        }

        return isUpdated;
    }

    public Especialidad findById(int id){
        Connection objConnection = ConfigDb.openConnection();
        Especialidad objEspecialidad = null;
        try {
            String sql = "SELECT * FROM especialidad WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,id);
            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()){
                objEspecialidad = new Especialidad();
                objEspecialidad.setIdEspecialidad(objResult.getInt("id"));
                objEspecialidad.setNombre(objResult.getString("nombre"));
                objEspecialidad.setDescripcion(objResult.getString("descripcion"));
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
            ConfigDb.closeConnection();
        }

        return objEspecialidad;
    }
}
