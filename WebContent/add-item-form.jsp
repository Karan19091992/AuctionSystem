<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Add Item</title>

	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-student-style.css">	
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Auction System</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Add Item</h3>
		
		<form action="ItemControllerServlet" method="GET" enctype="multipart/form-data">
		
			<input type="hidden" name="command" value="ADD" />
			
			<table>
				<tbody>
					<tr>
						<td><label>Item name:</label></td>
						<td><input type="text" name="itemName" /></td>
					</tr>

					<tr>
						<td><label>Description:</label></td>
						<td><input type="text" name="description" /></td>
					</tr>

					<tr>
						<td><label>BidPrice:</label></td>
						<td><input type="text" name="bidPrice" /></td>
					</tr>
					
					<tr>
						<td><label>Item Image:</label></td>
						<td><input type="file" name="itemImage" size="50" /></td>
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
			<a href="ItemControllerServlet">Back to List</a>
		</p>
	</div>
</body>

</html>
