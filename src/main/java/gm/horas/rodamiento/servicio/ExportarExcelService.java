package gm.horas.rodamiento.servicio;

import gm.horas.rodamiento.modelo.Empleado;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class ExportarExcelService {

    public void exportarRodamiento(HttpServletResponse response, List<Empleado> empleados) throws Exception {
        // 1. Cargar plantilla
        InputStream plantilla = getClass().getResourceAsStream("/plantillas/controlHorasExcel.xlsx");

        Workbook workbook = new XSSFWorkbook(plantilla);

        // 2. Acceder hoja
        Sheet sheet = workbook.getSheetAt(0);

        int filaInicio = 7;

        for (Empleado empleado : empleados) {
            var fila = sheet.createRow(filaInicio++);
            fila.createCell(0).setCellValue(empleado.getIdEmpleado());
            fila.createCell(1).setCellValue(empleado.getFecha().toString());
            fila.createCell(2).setCellValue(empleado.getHoraSalidaEmpresa());
            fila.createCell(3).setCellValue(empleado.getHoraLlegadaCliente());
            fila.createCell(4).setCellValue(empleado.getHoraSalidaCliente());
            fila.createCell(5).setCellValue(empleado.getHoraLlegadaEmpresa());
            fila.createCell(8).setCellValue(empleado.getActividad());
            fila.createCell(6).setCellValue(empleado.getTiempoConCliente());

            Integer totalRodamiento = empleado.getTotalHorasRodamiento();
            fila.createCell(7).setCellValue(totalRodamiento != null ? totalRodamiento : 0);
        }

        // 3. Configurar respuesta
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=rodamiento.xlsx");

        // 4. Escribir
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
