<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>CMS</title>

<base href="${pageContext.request.contextPath}/" />

	<!-- MDB icon -->
	<link rel="icon" href="img/favicon.ico" type="image/x-icon">
	<!-- Font Awesome -->
	<link rel="stylesheet"
		href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
	<!-- Google Fonts Roboto -->
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap">
	<!-- Bootstrap core CSS -->
	<link rel="stylesheet" href="css/lib/bootstrap.min.css">
	<!-- Material Design Bootstrap -->
	<link rel="stylesheet" href="css/lib/mdb.min.css">
	<!-- MDBootstrap Datatables  -->
	<link href="css/lib/addons/datatables.min.css" rel="stylesheet">
	<!-- Custom Styles -->
	<link rel="stylesheet" href="css/style.css" />

</head>
<body>

    <header class="container-fluid text-white text-center py-3" id="home">
        <!-- Navegacion -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary static-top">

                <a class="navbar-brand logo_link" href="index">
                    <img class="logo_image img-fluid" src="img/logo-ipsum.svg" style="height:80px;" alt="logo-ipsum">
                </a>

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ml-auto">
                
	                <li class="nav-item active">
	                    <a class="nav-link" href="index"><i class="fas fa-store mr-1"></i>Inico</a>
	                </li>
	                <li class="nav-item">
	                    <a class="nav-link" href="admin/index"><i class="fas fa-clipboard mr-1"></i>Admin</a>
	                </li>

	            <%-- Gestionar acceso restringido --%>
		
				<c:choose>
					<c:when test="${sessionScope.email != null}">
						
						<li class="login-user-nav nav-item dropdown">
				        	<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="far fa-user-circle"></i></a>

					        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
					       		<span class="dropdown-item"><i class="fas fa-envelope-alt mr-1"></i>${sessionScope.email}</span>
					          	<a class="dropdown-item" href="logout"><i class="fas fa-unlock-alt mr-1"></i>Logout</a>
					        </div>

				      	</li>
				      	
					</c:when>
					
					<c:otherwise>
						
						<li class="nav-item">
							<a class="nav-link" href="login"><i class="fas fa-lock mr-1"></i>Login</a>
						</li>
	
					</c:otherwise>
				</c:choose>
			
                </ul>
            </div>
        </nav>
        
		<%-- Gestionar mensajes de error --%>
		
		<c:if test="${alertatexto != null}">
			<div class="alert alert-${alertanivel} alert-dismissible fade show rounded-0"
				role="alert">
				${alertatexto}
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
	
			<%
				session.removeAttribute("alertatexto");
					session.removeAttribute("alertanivel");
			%>
		</c:if>
	
	</header>

	<main class="container-fluid">