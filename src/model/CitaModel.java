package model;

import database.CRUD;
import database.ConfigDb;
import entity.Cita;
import entity.Medico;
import entity.Paciente;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CitaModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        Cita objCita = (Cita) obj;

        Connection objConnection = ConfigDb.openConnection();

        try {
            String sql = "INSERT INTO cita (fk_id_paciente,fk_id_medico,fecha_cita,hora_cita,motivo) VALUE (?,?,?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setInt(1,objCita.getFkIdPaciente());
            objPrepare.setInt(2,objCita.getFkIdMedico());
            objPrepare.setString(3,objCita.getFechaCita());//YYYY-MM-DD
            objPrepare.setString(4,objCita.getHoraCita());//HH:MM:SS
            objPrepare.setString(5,objCita.getMotivo());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()){
                objCita.setIdCita(objResult.getInt(1));
            }

        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
           ConfigDb.closeConnection();
        }

        return objCita;
    }

    @Override
    public List<Object> findAll() {

        Connection objConnection = ConfigDb.openConnection();
        List<Object> listaCitas = new ArrayList<>();
        try {

            String sql = "SELECT * FROM cita INNER JOIN medico ON medico.id_medico = cita.fk_id_medico" +
                    " INNER JOIN paciente ON paciente.id_paciente = cita.fk_id_paciente;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                Cita objCita = new Cita();
                Medico objMedico = new Medico();
                Paciente objPaciente = new Paciente();

                objCita.setIdCita(objResult.getInt("cita.id_cita"));
                objCita.setFkIdMedico(objResult.getInt("cita.fk_id_medico"));
                objCita.setFkIdPaciente(objResult.getInt("cita.fk_id_paciente"));
                objCita.setFechaCita(objResult.getString("cita.fecha_cita"));
                objCita.setHoraCita(objResult.getString("cita.hora_cita"));
                objCita.setMotivo(objResult.getString("cita.motivo"));

                objMedico.setIdMedico(objResult.getInt("medico.id_medico"));
                objMedico.setNombre(objResult.getString("medico.nombre"));
                objMedico.setApellido(objResult.getString("medico.apellidos"));
                objMedico.setFkIdEspecialidad(objResult.getInt("medico.fk_id_especialidad"));

                objPaciente.setIdPaciente(objResult.getInt("paciente.id_paciente"));
                objPaciente.setNombre(objResult.getString("paciente.nombre"));
                objPaciente.setApellido(objResult.getString("paciente.apellidos"));
                objPaciente.setFechaNacimiento(objResult.getString("paciente.fecha_nacimiento"));
                objPaciente.setDocumentoIdentidad(objResult.getString("paciente.documento_identidad"));

                objCita.setMedico(objMedico);
                objCita.setPaciente(objPaciente);

                listaCitas.add(objCita);
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
            ConfigDb.closeConnection();
        }
        return listaCitas;
    }

    @Override
    public boolean delete(Object obj) {
        Cita objCita = (Cita) obj;
        Connection objConnection = ConfigDb.openConnection();
        boolean isDeleted = false;
        try {
            String sql = "DELETE FROM cita WHERE id_cita = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objCita.getIdCita());

            int totalRowsAffected = objPrepare.executeUpdate();
            if (totalRowsAffected > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"La cita fue eliminada exitosamente!");
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }finally {
            ConfigDb.closeConnection();
        }
        return isDeleted;
    }

    @Override
    public boolean update(Object obj) {
        Cita objCita = (Cita) obj;
        Connection objConnection = ConfigDb.openConnection();
        boolean isUpdate = false;

        try {
            String sql = "UPDATE cita SET fk_id_paciente = ?, fk_id_medico = ?, fecha_cita = ?, hora_cita = ?, motivo = ? WHERE id_cita = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,objCita.getFkIdPaciente());
            objPrepare.setInt(2,objCita.getFkIdMedico());
            objPrepare.setString(3, objCita.getFechaCita());
            objPrepare.setString(4,objCita.getHoraCita());
            objPrepare.setString(5,objCita.getMotivo());
            objPrepare.setInt(6,objCita.getIdCita());

            int totalRowsAffected = objPrepare.executeUpdate();
            if (totalRowsAffected > 0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null,"La cita fue actualizada exitosamente!!");
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
            ConfigDb.closeConnection();
        }
        return isUpdate;
    }

    public Cita findById(int idCita){
        Connection objConnection = ConfigDb.openConnection();
        Cita objCita = null;
        try {
            String sql = "SELECT * FROM cita WHERE id_cita = ? ;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,idCita);

            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()){
                objCita = new Cita();
                objCita.setIdCita(objResult.getInt("id_cita"));
                objCita.setFkIdPaciente(objResult.getInt("fk_id_paciente"));
                objCita.setFkIdMedico(objResult.getInt("fk_id_medico"));
                objCita.setFechaCita(objResult.getString("fecha_cita"));
                objCita.setHoraCita(objResult.getString("hora_cita"));
                objCita.setMotivo(objResult.getString("motivo"));
            }

        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
            ConfigDb.closeConnection();
        }
        return objCita;
    }
}
