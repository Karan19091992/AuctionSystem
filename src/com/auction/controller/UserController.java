package com.auction.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.auction.dao.UserDBUtil;
import com.auction.model.User;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDBUtil userDbUtil;
	
	@Resource(name="jdbc/auctiondb")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our student db util ... and pass in the conn pool / datasource
		try {
			userDbUtil = new UserDBUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
			
			// if the command is missing, then default to listing students
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			// route to the appropriate method
			switch (theCommand) {
			
			case "LIST":
				listUsers(request, response);
				break;
				
			case "ADD":
				addUser(request, response);
				break;
				
			case "LOAD":
				loadUser(request, response);
				break;
				
			case "UPDATE":
				updateUser(request, response);
				break;
			
			case "DELETE":
				deleteUser(request, response);
				break;
				
			default:
				listUsers(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}

	/** Delete user **/
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// read student id from form data
		String theUserId = request.getParameter("userId");
		
		// delete student from database
		userDbUtil.deleteUser(theUserId);
		
		// send them back to "list students" page
		listUsers(request, response);
	}

	/** Update Users **/
	
	private void updateUser(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// read student info from form data
		int id = Integer.parseInt(request.getParameter("userId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		int age = Integer.parseInt(request.getParameter("age"));
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// create a new student object
		User theUser = new User(id, firstName, lastName, email, age, username, password);
		
		// perform update on database
		userDbUtil.updateUser(theUser);
		
		// send them back to the "list students" page
		listUsers(request, response);
		
	}

	/** Load users **/
	
	private void loadUser(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		// read student id from form data
		String theUserId = request.getParameter("userId");
		
		// get student from database (db util)
		User theUser = userDbUtil.getUser(theUserId);
		
		// place student in the request attribute
		request.setAttribute("THE_USER", theUser);
		
		// send to jsp page: update-student-form.jsp
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/update-user-form.jsp");
		dispatcher.forward(request, response);		
	}

	/** Add Users **/
	
	private void addUser(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read student info from form data
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		int age = Integer.parseInt(request.getParameter("age"));
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		// create a new student object
		User theUser = new User(firstName, lastName, email, age, userName, password );
		
		// add the student to the database
		userDbUtil.addUser(theUser);
				
		// send back to main page (the student list)
		listUsers(request, response);
	}

	/** List of users**/
	
	private void listUsers(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		// get users from db util
		List<User> users = userDbUtil.getUsers();
		
		// add students to the request
		request.setAttribute("USER_LIST", users);
				
		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-users.jsp"); 
		dispatcher.forward(request, response);
	}

}
