<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/head.jsp"%>

<article class="row">
	<h2>Hola Mundo</h2>
	<section id="libros" class="p-lg-4 col-lg-6 col-xl-12">
		<h3>Coleccion</h3>
		<table
			class="table table-striped table-bordered table-hover table-sm table-responsive-xl">
			<thead class="thead-dark">
				<tr>
					<th>Id</th>
					<th>Nombre</th>

					<th>Opciones</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="{var}" var="libro">
					<tr>
						<td>{objeto.id}</td>

						<td>{objeto.nombre}</td>

					</tr>
				</c:forEach>
			</tbody>
			<tfoot class="thead-dark">
				<tr>
					<th>Id</th>
					<th>Nombre</th>
					<th><a href="libro" class="btn btn-primary btn-sm">Añadir</a>
					</th>
				</tr>
			</tfoot>
		</table>
	</section>
</article>

<%@ include file="/WEB-INF/views/includes/footer.jsp"%>
