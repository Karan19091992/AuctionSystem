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
			
			<input type="button" value="Add User" 
				   onclick="window.location.href='add-user-form.jsp'; return false;"
				   class="add-student-button"
			/>
			<input type="button" value="Add Item" 
				   onclick="window.location.href='add-item-form.jsp'; return false;"
				   class="add-student-button"
			/>
			<table>
			
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Age</th>
					<th>User Name</th>
					<th>Password</th>
					
					<th>Action</th>
				</tr>
				
				<c:forEach var="tempUser" items="${USER_LIST}">
					
		 			 <!-- set up a link for each student -->
					<c:url var="tempLink" value="UserControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="userId" value="${tempUser.userId}" />
					</c:url>

					<!--  set up a link to delete a student -->
					 <c:url var="deleteLink" value="UserControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="userId" value="${tempUser.userId}" />
					</c:url>  
																	
					<tr>
						<td> ${tempUser.firstName} </td>
						<td> ${tempUser.lastName} </td>
						<td> ${tempUser.email} </td>
						<td> ${tempUser.age} </td>
						<td> ${tempUser.userName} </td>
						<td> ${tempUser.password} </td>
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

