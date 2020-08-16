<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Records</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

<!-- JS, Popper.js, and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</head>
<body>
	<table class="table table-dark">
	<thead>
		<tr>
			<th>TaxRef</th>
			<th>Account Number</th>
			<th>Date and time</th>
			<th>Description</th>
			<th>Withdrawls</th>
			<th>Debit</th>
			<th>Running Balance</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				
				<c:forEach var="accounts" items="${bank}"> 
            	<th><c:out value="${accounts.taxref}"/></th> 
            	<th><c:out value="${accounts.accno}"/></th> 
            	<th><c:out value="${accounts.datetime}"/></th> 
            	<th><c:out value="${accounts.description}"/></th> 
            	<th><c:out value="${accounts.withdrawls}"/></th> 
            	<th><c:out value="${accounts.debit}"/></th> 
            	<th><c:out value="${accounts.runningbalance}"/></th> 
       			 </c:forEach>
			</tbody>
	
	</table>
</body>
</html>