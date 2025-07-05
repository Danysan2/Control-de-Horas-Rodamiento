<%@ include file="comunes/cabecero.jsp"%>
<%@ include file="comunes/navegacion.jsp"%>

<div class="container mt-5">
    <h3 class="text-center mb-4">Buscar Registro por Fecha</h3>
    <form action="${pageContext.request.contextPath}/buscarPorFecha" method="post" class="text-center">
        <div class="mb-3">
            <label for="fecha">Selecciona la Fecha:</label>
            <input type="date" name="fecha" id="fecha" required class="form-control w-25 mx-auto">
        </div>
        <button type="submit" class="btn btn-primary">Buscar</button>
    </form>

    <c:if test="${not empty empleados}">
        <h4 class="mt-4">Resultados:</h4>
        <table class="table table-striped table-hover table-bordered align-middle">
            <thead class="table-dark text-center">
            <tr>
                <th>ID</th>
                <th>Fecha</th>
                <th>Hora Salida Empresa</th>
                <th>Hora Llegada Cliente</th>
                <th>Hora Salida Cliente</th>
                <th>Hora Llegada Empresa</th>
                <th>Actividad</th>
                <th>Tiempo con Cliente</th>
                <th>Pago</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="empleado" items="${empleados}">
                <tr>
                    <td>${empleado.idEmpleado}</td>
                    <td>${empleado.fecha}</td>
                    <td>${empleado.horaSalidaEmpresa}</td>
                    <td>${empleado.horaLlegadaCliente}</td>
                    <td>${empleado.horaSalidaCliente}</td>
                    <td>${empleado.horaLlegadaEmpresa}</td>
                    <td>${empleado.actividad}</td>
                    <td>${empleado.tiempoConCliente}</td>
                    <td>$<fmt:formatNumber value="${empleado.pago}" type="number" groupingUsed="true" minFractionDigits="2" maxFractionDigits="2"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>

<%@ include file="comunes/pie-pagina.jsp"%>