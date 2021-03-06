<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Update Student</title>

	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-student-style.css">	
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>AUCTION SYSTEM</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Update User</h3>
		
		<form action="UserControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="UPDATE" />

			<input type="hidden" name="userId" value="${THE_USER.userId}" />
			
			<table>
				<tbody>
					<tr>
						<td><label>First name:</label></td>
						<td><input type="text" name="firstName" 
								   value="${THE_USER.firstName}" /></td>
					</tr>

					<tr>
						<td><label>Last name:</label></td>
						<td><input type="text" name="lastName" 
								   value="${THE_USER.lastName}" /></td>
					</tr>

					<tr>
						<td><label>Email:</label></td>
						<td><input type="text" name="email" 
								   value="${THE_USER.email}" /></td>
					</tr>
					
					<tr>
						<td><label>Age:</label></td>
						<td><input type="text" name="age" 
								   value="${THE_USER.age}" /></td>
					</tr>

					<tr>
						<td><label>Username:</label></td>
						<td><input type="text" name="userName" 
								   value="${THE_USER.userName}" /></td>
					</tr>
					
					<tr>
						<td><label>Password:</label></td>
						<td><input type="text" name="password" 
								   value="${THE_USER.password}" /></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
					
				</tbody>
			</table>
		</form>
		
		<div style="clear: both;"></div>
		
		<p>
			<a href="UserControllerServlet">Back to List</a>
		</p>
	</div>
</body>

</html>