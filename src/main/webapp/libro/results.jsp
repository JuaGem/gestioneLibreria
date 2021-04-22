<%@page import="it.gestionelibreria.model.Libro"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

	<meta charset="ISO-8859-1">
	<jsp:include page="../header.jsp" />
	
	<link href="./assets/css/global.css" rel="stylesheet">
	<title>Pagina dei risultati</title>

</head>

<body>

	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
		<div class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none': ''}" role="alert">
		  ${successMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<!---------------------------------->
		<!-- ESEMPI DI ALERT DI BOOTSTRAP -->
		<div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
		  Esempio di operazione fallita!
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		<div class="alert alert-info alert-dismissible fade show d-none" role="alert">
		  Aggiungere d-none nelle class per non far apparire
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		<!--------   end  ------------------>
		<!---------------------------------->
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Lista dei risultati</h5> 
		    </div>
		    <div class='card-body'>
		    	<a class="btn btn-primary " href="PrepareInsertLibroServlet">Add New</a>
		    
		        <div class='table-responsive'>
		            <table class='table table-striped'>
		                <thead>
		                    <tr>
		                        <th>Id</th>
		                        <th>Titolo</th>
		                        <th>Genere</th>
		                        <th>Pagine</th>
		                        <th>Data Pubblicazione</th>
		                        <th>Azioni</th>
		                    </tr>
		                </thead>
		                <tbody>
		                	<% List<Libro> listaLibri = (List<Libro>)request.getAttribute("listaLibriAttribute");
		                		for(Libro item:listaLibri){ %>
		                    <tr>
		                        <td><%=item.getId() %></td>
		                        <td><%=item.getTitolo() %></td>
		                        <td><%=item.getGenere() %></td>
		                        <td><%=item.getPagine() %></td>
		                        <td><%=item.getDataPubblicazione()!=null? new SimpleDateFormat("dd/MM/yyyy").format(item.getDataPubblicazione()):"N.D."%></td>
		                        <td>
									<a class="btn  btn-sm btn-outline-secondary" href="ExecuteVisualizzaLibroServlet?idLibro=<%=item.getId() %>">Visualizza</a>
									<a class="btn  btn-sm btn-outline-primary ml-2 mr-2" href="PrepareEditLibroServlet?idLibro=<%= item.getId() %>">Edit</a>
									<a class="btn btn-outline-danger btn-sm" href="PrepareDeleteLibroServlet?idLibro=<%= item.getId() %>">Delete</a>
								</td>
		                    </tr>
		                    <% } %>
		                </tbody>
		            </table>
		        </div>
		   
			<!-- end card-body -->			   
		    </div>
		</div>	 
	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />

</body>

</html>