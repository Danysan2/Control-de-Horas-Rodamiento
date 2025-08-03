<%@ include file="comunes/cabecero.jsp"%>
<%@ include file="comunes/navegacion.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!-- Titulo -->
<div class="container" xmlns:c="http://www.w3.org/1999/XSL/Transform" xmlns:c="http://www.w3.org/1999/xhtml">
    <div class="text-center" style="margin: 60px text-size:24px padding: 20px">
        <h3>Sistema de empleados</h3>
    </div>
</div>

<!-- Contenedor de la tabla -->
<div class="container">
    <table class="table table-striped table-hover table-bordered align-middle">
        <thead class="table-dark text-center">
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Fecha</th>
            <th scope="col">Hora Salida Empresa</th>
            <th scope="col">Hora Llegada Cliente</th>
            <th scope="col">Hora Salida Cliente</th>
            <th scope="col">Hora Llegada Empresa</th>
            <th scope="col">Actividad</th>
            <th scope="col">Tiempo con el Cliente</th>
            <th scope="col">Pago</th>
            <th scope="col"></th>

        </tr>
        </thead>
        <tbody>
        <c:forEach var="empleado" items="${empleados}">
            <tr>
                <th scope="row">${empleado.idEmpleado}</th>
                <td>${empleado.fecha}</td>
                <td>${empleado.horaSalidaEmpresa}</td>
                <td>${empleado.horaLlegadaCliente}</td>
                <td>${empleado.horaSalidaCliente}</td>
                <td>${empleado.horaLlegadaEmpresa}</td>
                <td>${empleado.actividad}</td>
                <td>${empleado.tiempoConCliente}</td>
                <td>$<fmt:formatNumber value="${empleado.pago}" type="number" groupingUsed="true" minFractionDigits="2" maxFractionDigits="2"/></td>
                <td class="text-center">
                    <c:url var="urlEditar" value="/editar">
                        <c:param name="idEmpleado" value="${empleado.idEmpleado}" />
                    </c:url>

                    <a href="${urlEditar}" class="btn btn-warning btn-sm me-3">Editar</a>

                    <c:url var="urlEliminar" value="/eliminar">
                        <c:param name="idEmpleado" value="${empleado.idEmpleado}" />
                    </c:url>
                    <a href="${urlEliminar}" class="btn btn-danger btn-sm">Eliminar</a>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="text-center mb-4" style="text-center">
    <a href="${pageContext.request.contextPath}/exportarExcel" class="btn btn-success btn-lg">Exportar a excel</a>
    <a href="${pageContext.request.contextPath}/eliminarTodo" class="btn btn-danger">
        Eliminar todo y resetear ID
    </a>
    <c:url var="urlExportarPDF" value="/exportarPDF">
        <c:param name="idEmpleado" value="${empleado.idEmpleado}" />
    </c:url>

    <a href="${pageContext.request.contextPath}/exportarPDF" class="btn btn-danger btn-lg">Exportar a PDF</a>
    <a href="${pageContext.request.contextPath}/buscarPorFechaForm" class="btn btn-primary btn-lg">Buscar Registro por fecha</a>
    <a href="${pageContext.request.contextPath}/ordenarFecha" class="btn btn-dark btn-lg">Organizar por Fecha</a>
</div>

<%@ include file="comunes/pie-pagina.jsp"%>