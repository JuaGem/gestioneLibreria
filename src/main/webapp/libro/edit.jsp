<%@page import="it.gestionelibreria.model.Libro"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

	<meta charset="ISO-8859-1">
	
	<jsp:include page="../header.jsp" />
	<link href="./assets/css/global.css" rel="stylesheet">

	<title>Modifica Libro</title>

</head>

<body>

	<jsp:include page="../navbar.jsp" />
	
	<% Libro libroInEdit = (Libro) request.getAttribute("edit_attribute"); %>
	
	<main role="main" class="container">
	
			<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
			  ${errorMessage}
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
			</div>
			
			<div class='card'>
			    <div class='card-header'>
			        <h5>Inserisci elemento da modificare</h5> 
			    </div>
			    <div class='card-body'>
	
					<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
	
						<form method="post" action="ExecuteEditLibroServlet" novalidate="novalidate">
						
							<div class="form-row">
								<div class="form-group col-md-6">
								
									<label>Titolo<span class="text-danger">*</span></label>
									<input type="text" name="titolo" id="titolo" class="form-control" 
									value="<%= libroInEdit.getTitolo()!=null?libroInEdit.getTitolo():"" %>" placeholder="Inserire il titolo da modificare" required>
								
								</div>
								
								<div class="form-group col-md-6">
								
									<label>Genere<span class="text-danger">*</span></label>
									<input type="text" name="genere" id="genere" class="form-control" 
									value="<%= libroInEdit.getGenere()!=null?libroInEdit.getGenere():"" %>" placeholder="Inserire il genere da modificare" required>
								
								</div>
							</div>
							
							<div class="form-row">	
								<div class="form-group col-md-6">
								
									<label>Pagine<span class="text-danger">*</span></label>
									<input type="number" class="form-control" name="pagine" id="pagine" 
									value="<%= libroInEdit.getPagine()!=null?libroInEdit.getPagine():"" %>" placeholder="Inserire le pagine da modificare"  required>
									
								</div>
								<div class="form-group col-md-3">
								
									<label>Data di Pubblicazione<span class="text-danger">*</span></label>
		                     		<input class="form-control" id="dataPubblicazione" type="date" placeholder="dd/MM/yy"
		                         	title="formato : gg/mm/aaaa" value="<%= libroInEdit.getDataPubblicazione()!=null?new SimpleDateFormat("yyyy-MM-dd").format(libroInEdit.getDataPubblicazione()):"" %>" name="dataPubblicazione" required>
		                         	
								</div>
								
							</div>
								
							<a href="ListLibriServlet" class='btn btn-outline-secondary' style='width:7em;'>
			           			<i class='fa fa-chevron-left'></i> Indietro
			      			</a>
							<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary" style='width:7em;'>Modifica</button>
			      			<input type="hidden" name="idEditInput" value="<%=libroInEdit.getId() %>">
						
						</form>
			    
				<!-- end card-body -->			   
			    </div>
			</div>	
	
	
	<!-- end container -->	
	</main>
				
	<jsp:include page="../footer.jsp" />	
	
</body>

</html>