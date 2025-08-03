package gm.horas.rodamiento.servicio;

import gm.horas.rodamiento.modelo.Empleado;
import gm.horas.rodamiento.repositorio.EmpleadoRepositorio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class EmpleadoServicio implements IEmpleadoServicio{

    @Autowired
    private EmpleadoRepositorio empleadoRespositorio;

    @PersistenceContext
    private EntityManager entityManager;

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

    @Transactional
    public void eliminarTodoYResetearId() {
        empleadoRespositorio.deleteAll();
        entityManager.createNativeQuery("ALTER TABLE empleado AUTO_INCREMENT = 1").executeUpdate();
    }

}