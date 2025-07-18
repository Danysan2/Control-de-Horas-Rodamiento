<%@ include file="comunes/cabecero.jsp"%>
<%@ include file="comunes/navegacion.jsp"%>

<div class="container">
    <div class="text-center" style="margin: 30px">
        <h3>Editar Registro</h3>
    </div>

    <div>
        <form action="${urlEditar}" modelAttribute="empleadoForma" method="post">
            <input type="hidden" name="idEmpleado" value="${empleado.idEmpleado}" />

            <!-- Campo de fecha -->
            <div class="mb-3">
                <label for="fecha" class="form-label">Seleccione la fecha</label>
                <input type="date" class="form-control" id="fecha" name="fecha"
                       value="${empleado.fecha}" required />
            </div>

            <!-- Campo de hora salida empresa -->
            <div class="mb-3">
                <label for="horaSalidaEmpresa" class="form-label">Hora de salida de la empresa</label>
                <input type="time" class="form-control" id="horaSalidaEmpresa" name="horaSalidaEmpresa"
                       value="${empleado.horaSalidaEmpresa}" required />
            </div>

            <!-- Campo de hora llegada cliente -->
            <div class="mb-3">
                <label for="horaLlegadaCliente" class="form-label">Hora de llegada al cliente</label>
                <input type="time" class="form-control" id="horaLlegadaCliente" name="horaLlegadaCliente"
                       value="${empleado.horaLlegadaCliente}" required />
            </div>

            <!-- Campo de hora salida cliente -->
            <div class="mb-3">
                <label for="horaSalidaCliente" class="form-label">Hora de salida del cliente</label>
                <input type="time" class="form-control" id="horaSalidaCliente" name="horaSalidaCliente"
                       value="${empleado.horaSalidaCliente}" required />
            </div>

            <!-- Campo de hora llegada empresa -->
            <div class="mb-3">
                <label for="horaLlegadaEmpresa" class="form-label">Hora de llegada a la empresa</label>
                <input type="time" class="form-control" id="horaLlegadaEmpresa" name="horaLlegadaEmpresa"
                       value="${empleado.horaLlegadaEmpresa}" required />
            </div>

            <!-- Campo de actividad -->
            <div class="mb-3">
                <label for="actividad" class="form-label">Actividad</label>
                <input type="text" class="form-control" id="actividad" name="actividad"
                       value="${empleado.actividad}" required />
            </div>

            <div class="text-center">
                <button type="submit" class="btn btn-warning btn-sm me-3">Editar</button>
                <a href="${urlInicio}" class="btn btn-danger btn-sm">Regresar</a>
            </div>
        </form>
    </div>
</div>

<%@ include file="comunes/pie-pagina.jsp"%>