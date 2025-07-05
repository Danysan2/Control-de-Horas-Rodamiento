package gm.horas.rodamiento.servicio;

import gm.horas.rodamiento.modelo.Empleado;
import gm.horas.rodamiento.repositorio.EmpleadoRepositorio;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class EmpleadoServicio implements IEmpleadoServicio{

    @Autowired
    private EmpleadoRepositorio empleadoRespositorio;

    @Override
    public List<Empleado> listarRegistros() {
        return empleadoRespositorio.findAll();
    }

    @Override
    public Empleado buscarRegistroPorId(Integer idEmpleado) {
        Empleado empleado = empleadoRespositorio.findById(idEmpleado).orElse(null);
        return empleado;
    }

    @Override
    public void guardarRegistro(Empleado empleado) {
        empleadoRespositorio.save(empleado);

    }

    @Override
    public void eliminarRegistro(Empleado empleado) {
        empleadoRespositorio.delete(empleado);
    }


    public List<Empleado> listarRegistrosOrdenadosPorFecha() {
        return empleadoRespositorio.findAllByOrderByFechaAsc();
    }

    public List<Empleado> buscarPorFecha(Date fecha) {
        return empleadoRespositorio.findByFecha(fecha);
    }
}