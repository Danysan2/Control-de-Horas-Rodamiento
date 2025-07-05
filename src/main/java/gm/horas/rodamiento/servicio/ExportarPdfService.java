package gm.horas.rodamiento.servicio;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import gm.horas.rodamiento.modelo.Empleado;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ExportarPdfService {

    public void exportarCuentaCobro(HttpServletResponse response, List<Empleado> empleados) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());


        document.open();

        // Fuentes
        Font bold = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        Font normal = FontFactory.getFont(FontFactory.HELVETICA, 12);

        // Fecha actual
        String fechaHoy = java.time.LocalDate.now().toString();

        // Calcular suma total de pagos
        double sumaPagos = empleados.stream()
                .mapToDouble(Empleado::getPago)
                .sum();

        // Encabezado
        int nCuenta = 1;
        nCuenta++;
        Paragraph encabezado = new Paragraph("\nCUENTA DE COBRO Nº"+ nCuenta, bold);
        encabezado.setAlignment(Element.ALIGN_RIGHT);
        document.add((new Paragraph("Bogotá, " + fechaHoy , bold)));
        document.add(encabezado);
        document.add(Chunk.NEWLINE);

        // Empresa
        document.add(new Paragraph("\nESTRUPISOS SAS\n", bold));
        document.add(new Paragraph("NIT: 900500465\n"));

        // Debe a:
        document.add(new Paragraph("\nDEBE A:"));
        document.add(Chunk.NEWLINE);
        document.add(new Paragraph("MARTIN ALEXIS CRISTANCHO MORENO\nC.C. No. 987654321 de Bogotá", bold));
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);

        // Concepto
        document.add(new Paragraph("\nPor concepto: Mantenimiento preventivo y correctivo de equipos de cómputo cantidad (" + empleados.size() + ").", normal));

        // Suma total
        document.add(new Paragraph("\nLa suma de:", normal));
        document.add(new Paragraph(String.format("$ %, .2f", sumaPagos), bold));
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);

        // Cuenta bancaria
        document.add(new Paragraph("\nCUENTA DE AHORROS BANCOLOMBIA N° 69987344261", bold));

        // Firma
        document.add(new Paragraph("\nCordialmente,\n", normal));
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(new Paragraph("MARTIN ALEXIS CRISTANCHO MORENO\n", bold));
        document.add(new Paragraph("C.C. No.  80.238.735 de Bogotá" ));
        document.add(new Paragraph("Celular 3104793399"));

        document.close();
    }

}
