package gm.horas.rodamiento.servicio;

import gm.horas.rodamiento.modelo.Empleado;

import java.sql.Date;
import java.util.List;

public interface IEmpleadoServicio {

    public List<Empleado> listarRegistros();

    public Empleado buscarRegistroPorId(Integer idEmpleado);

    public void guardarRegistro(Empleado empleado);

    public void eliminarRegistro(Empleado empleado);
}
