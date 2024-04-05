package model;

import database.CRUD;
import database.ConfigDb;
import entity.Paciente;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDb.openConnection();
        Paciente objPaciente = (Paciente) obj;
        try {
            String sql = "INSERT INTO paciente (nombre,apellidos,fecha_nacimiento,documento_identidad) VALUES (?,?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objPaciente.getNombre());
            objPrepare.setString(2,objPaciente.getApellido());
            objPrepare.setString(3,objPaciente.getFechaNacimiento());
            objPrepare.setString(4,objPaciente.getDocumentoIdentidad());

            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()){
                objPaciente.setIdPaciente(objResult.getInt(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
            ConfigDb.closeConnection();
        }
        return objPaciente;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listaPacientes = new ArrayList<>();
        Connection objConnection = ConfigDb.openConnection();

        try {
            String sql = "SELECT * FROM paciente;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){

                Paciente objPaciente = new Paciente();

                objPaciente.setIdPaciente(objResult.getInt("id_paciente"));
                objPaciente.setNombre(objResult.getString("nombre"));
                objPaciente.setApellido(objResult.getString("apellidos"));
                objPaciente.setFechaNacimiento(objResult.getString("fecha_nacimiento"));
                objPaciente.setDocumentoIdentidad(objResult.getString("documento_identidad"));

                listaPacientes.add(objPaciente);
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ConfigDb.closeConnection();
        }

        return listaPacientes;
    }

    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDb.openConnection();
        Paciente objPaciente = (Paciente) obj;
        boolean isDeleted = false;
        try {
            String sql = "DELETE FROM paciente WHERE id_paciente = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objPaciente.getIdPaciente());

            int totalRowsAffected = objPrepare.executeUpdate();

            if (totalRowsAffected > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"El paciente fue eliminado exitosamente!");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ConfigDb.closeConnection();
        }

        return isDeleted;    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDb.openConnection();
        Paciente objPaciente = (Paciente) obj;
        boolean isUpdated = false;
        try {
            String sql = "UPDATE paciente SET nombre = ?, apellidos = ?, fecha_nacimiento = ?, documento_identidad = ? WHERE id_paciente = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,objPaciente.getNombre());
            objPrepare.setString(2,objPaciente.getApellido());
            objPrepare.setString(3,objPaciente.getFechaNacimiento());
            objPrepare.setString(4,objPaciente.getDocumentoIdentidad());
            objPrepare.setInt(5,objPaciente.getIdPaciente());

            int totalRowsAffected = objPrepare.executeUpdate();

            if (totalRowsAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null,"El paciente fue actualizado exitosamente!");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ConfigDb.closeConnection();
        }

        return isUpdated;
    }

    public Paciente findById(int id){
        Connection objConnection = ConfigDb.openConnection();
        Paciente objPaciente = null;
        try {
            String sql = "SELECT * FROM paciente WHERE id_paciente = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,id);
            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()){
                objPaciente = new Paciente();
                objPaciente.setIdPaciente(objResult.getInt("id_paciente"));
                objPaciente.setNombre(objResult.getString("nombre"));
                objPaciente.setApellido(objResult.getString("apellidos"));
                objPaciente.setFechaNacimiento(objResult.getString("fecha_nacimiento"));
                objPaciente.setDocumentoIdentidad(objResult.getString("documento_identidad"));
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
            ConfigDb.closeConnection();
        }

        return objPaciente;
    }
}
