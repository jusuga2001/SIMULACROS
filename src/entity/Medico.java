package entity;

import model.EspecialidadModel;

public class Medico {

    private int idMedico;
    private String nombre;
    private String apellido;
    private int fkIdEspecialidad;
    private Especialidad especialidad;

    private static final EspecialidadModel objModel = new EspecialidadModel();

    public Medico() {
    }

    public Medico(int idMedico, String nombre, String apellido, int fkIdEspecialidad, Especialidad especialidad) {
        this.idMedico = idMedico;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fkIdEspecialidad = fkIdEspecialidad;
        this.especialidad = especialidad;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getFkIdEspecialidad() {
        return fkIdEspecialidad;
    }

    public void setFkIdEspecialidad(int fkIdEspecialidad) {
        this.fkIdEspecialidad = fkIdEspecialidad;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
         if( especialidad != null) return"Medico: " +
                "idMedico=" + idMedico +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", \n" + especialidad.toString();

         else  return"Medico: " +
                 "idMedico=" + idMedico +
                 ", nombre='" + nombre + '\'' +
                 ", apellido='" + apellido + '\'' + "\n" ;

    }
}
