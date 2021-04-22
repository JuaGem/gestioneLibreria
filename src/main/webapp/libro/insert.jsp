<%@page import="java.text.SimpleDateFormat"%>
<%@page import="it.gestionelibreria.model.Libro"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	
	<jsp:include page="../header.jsp" />
	<meta charset="ISO-8859-1">
	
	<link href="./assets/css/global.css" rel="stylesheet">
	<title>Inserisci Nuovo Libro</title>

</head>

<body>

	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  ${errorMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Inserisci nuovo libro</h5> 
		    </div>
		    <div class='card-body'>

				<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
				
				<% Libro libroDaInserire = (Libro) request.getAttribute("libroDaInserire"); %>

				<form method="post" action="ExecuteInsertLibroServlet" novalidate="novalidate">
				
					<div class="form-row">
						<div class="form-group col-md-6">
							<label>Titolo <span class="text-danger">*</span></label>
							<input type="text" name="titolo" id="titolo" value="<%=libroDaInserire.getTitolo()!=null?libroDaInserire.getTitolo():""%>" class="form-control" placeholder="Inserire il titolo" required>
						</div>
						
						<div class="form-group col-md-6">
							<label>Genere <span class="text-danger">*</span></label>
							<input type="text" name="genere" id="genere" value="<%=libroDaInserire.getGenere()!=null?libroDaInserire.getGenere():"" %>" class="form-control" placeholder="Inserire il genere" required>
						</div>
					</div>
					
					<div class="form-row">	
						<div class="form-group col-md-6">
							<label>Pagine <span class="text-danger">*</span></label>
							<input type="number" class="form-control" name="pagine" value="<%=libroDaInserire.getPagine()!=null?libroDaInserire.getPagine():"" %>" id="pagine" placeholder="Inserire pagine" required>
						</div>
						<div class="form-group col-md-3">
							<label>Data di Pubblicazione<span class="text-danger">*</span></label>
                       		<input class="form-control" id="dataPubblicazione" value="<%=libroDaInserire.getDataPubblicazione()!=null?new SimpleDateFormat("yyyy-MM-dd").format(libroDaInserire.getDataPubblicazione()):"" %>" type="date" placeholder="dd/MM/yy"
                           		title="formato : gg/mm/aaaa"  name="dataPubblicazione" required>
						</div>
						
					</div>
						
					<a href="ListLibriServlet" class='btn btn-outline-secondary' style='width:7em'>
	            		<i class='fa fa-chevron-left'></i> Indietro
	        		</a>
					<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
					

					</form>
		    
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />

</body>

</html>