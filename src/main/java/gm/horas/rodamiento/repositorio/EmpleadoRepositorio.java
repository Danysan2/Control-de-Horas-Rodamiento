package gm.horas.rodamiento.repositorio;

import gm.horas.rodamiento.modelo.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface EmpleadoRepositorio extends JpaRepository<Empleado, Integer> {

    List<Empleado> findAllByOrderByFechaAsc();

    List<Empleado> findByFecha(Date fecha);
}
