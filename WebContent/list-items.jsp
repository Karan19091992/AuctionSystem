  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Auction System</title>
	
	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>

	<div id="wrapper">
		<div id="header">
			<h2>Auction System</h2>
		</div>
	</div>

	<div id="container">
	
		<div id="content">
		
			<!-- put new button: Add User -->
			
			<input type="button" value="Add Item" 
				   onclick="window.location.href='add-user-form.jsp'; return false;"
				   class="add-student-button"
			/>
			
			<table>
			
				<tr>
					<th>Item Name</th>
					<th>Description</th>
					<th>BidPrice</th>
					<th>Item Image</th>
					
					<th>Action</th>
				</tr>
				
				<c:forEach var="tempItem" items="${ITEM_LIST}">
					
		 			 <!-- set up a link for each student -->
					<c:url var="tempLink" value="ItemControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="itemId" value="${tempItem.itemId}" />
					</c:url>

					<!--  set up a link to delete a student -->
					 <c:url var="deleteLink" value="ItemControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="itemId" value="${tempItem.itemId}" />
					</c:url>  
																	
					<tr>
						<td> ${tempItem.itemName} </td>
						<td> ${tempItem.description} </td>
						<td> ${tempItem.bidPrice} </td>
						<td> ${tempItem.itemImage} </td>
						<td> 
							<a href="${tempLink}">Update</a> 
							 | 
							 <a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this user?'))) return false">
							Delete</a>	 
						</td>
					</tr>
				
				</c:forEach>
				
			</table>
		
		</div>
	
	</div>
</body>


</html>

