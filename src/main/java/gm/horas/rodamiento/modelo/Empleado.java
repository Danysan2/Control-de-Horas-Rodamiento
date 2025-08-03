package gm.horas.rodamiento.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idEmpleado; //id_empleado en la base de datos en Snake Case
    Date fecha;
    String horaSalidaEmpresa;
    String horaLlegadaCliente;
    String horaSalidaCliente;
    String horaLlegadaEmpresa;
    String actividad;
    int tiempoConCliente;
    int pago;
    @Column(name = "total_Horas_Rodamiento")
    private Integer totalHorasRodamiento;

}
