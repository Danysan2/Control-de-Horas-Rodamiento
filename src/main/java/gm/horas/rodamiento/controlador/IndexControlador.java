package gm.horas.rodamiento.controlador;

import gm.horas.rodamiento.modelo.Empleado;
import gm.horas.rodamiento.servicio.EmpleadoServicio;
import gm.horas.rodamiento.servicio.ExportarExcelService;
import gm.horas.rodamiento.servicio.ExportarPdfService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@Controller
public class IndexControlador {

    private static final Logger logger =
            LoggerFactory.getLogger(IndexControlador.class);

    @Autowired
    EmpleadoServicio empleadoServicio;

    @Autowired
    ExportarExcelService exportarExcelService;

    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public String iniciar(ModelMap modelo){
        List<Empleado> empleados = empleadoServicio.listarRegistros();
        empleados.forEach(empleado -> logger.info(empleado.toString()));
        //Compartimos el modelo con la lista
        modelo.put("empleados", empleados);
        return "index";
    }

    @RequestMapping(value = "/agregar", method = RequestMethod.GET)
    public String mostrarAgregar(ModelMap modelo){
        modelo.put("empleadoForma", new Empleado()); // Para que no haya null en el formulario
        return "agregar"; //agregar jsp
    }

    @RequestMapping(value = "/agregar", method = RequestMethod.POST)
    public String agregar(@ModelAttribute("empleadoForma") Empleado empleado) {
        logger.info("Empleado original: " + empleado);

        int salidaEmpresa = convertirHoraAMinutos(empleado.getHoraSalidaEmpresa());
        int llegadaCliente = convertirHoraAMinutos(empleado.getHoraLlegadaCliente());
        int salidaCliente = convertirHoraAMinutos(empleado.getHoraSalidaCliente());
        int llegadaEmpresa = convertirHoraAMinutos(empleado.getHoraLlegadaEmpresa());

        // Tiempo con cliente real
        int tiempoConCliente = salidaCliente - llegadaCliente;

        // Viaje ida y regreso
        int ida = llegadaCliente - salidaEmpresa;
        int regreso = llegadaEmpresa - salidaCliente;

        // Pago bien calculado
        int pago = (ida + regreso) * 9850 / 60;

        // Total horas rodamiento
        int totalHorasRodamiento = ida + regreso;

        empleado.setTiempoConCliente(tiempoConCliente);
        empleado.setPago(pago);
        empleado.setTotalHorasRodamiento(totalHorasRodamiento);

        logger.info("Tiempo con cliente (min): " + tiempoConCliente);
        logger.info("Pago calculado: " + pago);

        empleadoServicio.guardarRegistro(empleado);

        return "redirect:/";
    }

    @RequestMapping(value = "/editar", method = RequestMethod.GET)
    public String mostrarEditar(@RequestParam int idEmpleado, ModelMap modelo){
        Empleado empleado = empleadoServicio.buscarRegistroPorId(idEmpleado);
        logger.info("Empleado a editar" + empleado);
        modelo.put("empleado" ,empleado);
        return "editar"; // mostrar editar.jsp
    }


    @RequestMapping(value = "/editar", method = RequestMethod.POST)
    public String editar(@ModelAttribute("empleadoForma") Empleado empleado) {
        logger.info("Empleado original: " + empleado);

        int salidaEmpresa = convertirHoraAMinutos(empleado.getHoraSalidaEmpresa());
        int llegadaCliente = convertirHoraAMinutos(empleado.getHoraLlegadaCliente());
        int salidaCliente = convertirHoraAMinutos(empleado.getHoraSalidaCliente());
        int llegadaEmpresa = convertirHoraAMinutos(empleado.getHoraLlegadaEmpresa());

        // Tiempo con cliente real
        int tiempoConCliente = salidaCliente - llegadaCliente;

        // Viaje ida y regreso
        int ida = llegadaCliente - salidaEmpresa;
        int regreso = llegadaEmpresa - salidaCliente;


        int pago = (ida + regreso) * 9850;

        // Total horas rodamiento
        int totalHorasRodamiento = ida + regreso;


        empleado.setTiempoConCliente(tiempoConCliente);
        empleado.setPago(pago);
        empleado.setTotalHorasRodamiento(totalHorasRodamiento);

        logger.info("Tiempo con cliente (min): " + tiempoConCliente);
        logger.info("Pago calculado: " + pago);

        empleadoServicio.guardarRegistro(empleado);

        return "redirect:/";
    }

    @RequestMapping(value = "/eliminar" , method = RequestMethod.GET)
    public String eliminar(@RequestParam int idEmpleado){
        Empleado empleado = new Empleado();
        empleado.setIdEmpleado(idEmpleado);
        empleadoServicio.eliminarRegistro(empleado);
        return "redirect:/";
    }

    private int convertirHoraAMinutos(String hora) {
        String[] partes = hora.split(":");
        int horas = Integer.parseInt(partes[0]);
        int minutos = Integer.parseInt(partes[1]);
        return horas * 60 + minutos;
    }

    @RequestMapping(value = "/ordenarFecha", method = RequestMethod.GET)
    public String ordenarPorFecha(ModelMap modelo) {
        List<Empleado> empleadosOrdenados = empleadoServicio.listarRegistrosOrdenadosPorFecha();
        modelo.put("empleados", empleadosOrdenados);
        return "index";
    }

    // Muestra el formulario
    @RequestMapping(value = "/buscarPorFechaForm", method = RequestMethod.GET)
    public String mostrarBuscarPorFecha() {
        return "buscarPorFecha"; // JSP con el formulario vacío
    }

    // Procesa el POST
    @RequestMapping(value = "/buscarPorFecha", method = RequestMethod.POST)
    public String buscarPorFecha(@RequestParam("fecha") Date fecha, ModelMap modelo) {
        List<Empleado> empleados = empleadoServicio.buscarPorFecha(fecha);
        modelo.put("empleados", empleados);
        return "buscarPorFecha"; // Muestra los resultados en el mismo JSP
    }

    @Autowired
    ExportarPdfService exportarPdfService;

    @RequestMapping("/exportarPDF")
    public void exportarPDF(HttpServletResponse response) throws IOException {
        List<Empleado> empleados = empleadoServicio.listarRegistros();

        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=cuenta_cobro_total.pdf";
        response.setHeader(headerKey, headerValue);

        exportarPdfService.exportarCuentaCobro(response, empleados);
    }

    @RequestMapping("/exportarExcel")
    public void exportarExcel(HttpServletResponse response) throws Exception {
        List<Empleado> empleados = empleadoServicio.listarRegistros();
        exportarExcelService.exportarRodamiento(response, empleados);
    }

    @RequestMapping(value = "/eliminarTodo", method = RequestMethod.GET)
    public String eliminarTodo() {
        empleadoServicio.eliminarTodoYResetearId();
        return "redirect:/";
    }
}
