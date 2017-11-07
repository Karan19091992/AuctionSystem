package com.auction.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.auction.model.User;


public class UserDBUtil {

	private DataSource dataSource;

	public UserDBUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<User> getUsers() throws Exception {
		
		List<User> users = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from user order by lastname";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				
				
				// retrieve data from result set row
				int id = myRs.getInt("user_id");
				String firstName = myRs.getString("firstname");
				String lastName = myRs.getString("lastname");
				String email = myRs.getString("email");
				int age = myRs.getInt("age");
				String username = myRs.getString("username");
				String password = myRs.getString("password");
				
				
				// create new user object
				User tempUser = new User(id, firstName, lastName, email, age, username, password);
				
				// add it to the list of users
				users.add(tempUser);				
			}
			
			return users;		
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}		
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();   // doesn't really close it ... just puts back in connection pool
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public void addUser(User theUser) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "insert into user "
					   + "(firstname, lastname, email, age, username, password) "
					   + "values (?, ?, ?, ?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the user
			myStmt.setString(1, theUser.getFirstName());
			myStmt.setString(2, theUser.getLastName());
			myStmt.setString(3, theUser.getEmail());
			myStmt.setInt(4, theUser.getAge());
			myStmt.setString(5, theUser.getUserName());
			myStmt.setString(6, theUser.getPassword());
			
			// execute sql insert
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public User getUser(String theUserId) throws Exception {

		User theUser = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int userId;
		
		try {
			// convert user id to int
			userId = Integer.parseInt(theUserId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected user
			String sql = "select * from user where user_id=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, userId);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				String firstName = myRs.getString("firstname");
				String lastName = myRs.getString("lastname");
				String email = myRs.getString("email");
				int age = myRs.getInt("age");
				String username = myRs.getString("username");
				String password = myRs.getString("password");
				
				// use the userId during construction
				theUser = new User(userId, firstName, lastName, email, age, username, password);
			}
			else {
				throw new Exception("Could not find user id: " + userId);
			}				
			
			return theUser;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void updateUser(User theUser) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create SQL update statement
			String sql = "update user "
						+ "set firstname=?, lastname=?, email=?, age=?, username=?, password=? "
						+ "where user_id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setString(1, theUser.getFirstName());
			myStmt.setString(2, theUser.getLastName());
			myStmt.setString(3, theUser.getEmail());
			myStmt.setInt(4, theUser.getAge());
			myStmt.setString(5, theUser.getUserName());
			myStmt.setString(6, theUser.getPassword());
			myStmt.setInt(7, theUser.getUserId());
			
			// execute SQL statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public void deleteUser(String theUserId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// convert user id to int
			int userId = Integer.parseInt(theUserId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete user
			String sql = "delete from user where user_id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, userId);
			
			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}	
	}
	
	
}
