package com.auction.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.auction.model.Item;

public class ItemDBUtil {

	private DataSource dataSource;

	public ItemDBUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}

	public List<Item> getItems() throws Exception {

		List<Item> items = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create sql statement
			String sql = "select * from item order by itemname"; // "EDIT insertdate in database"

			myStmt = myConn.createStatement();

			// execute query
			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {

				/* NOTE: Add insertDate in Image */
				// retrieve data from result set row
				int id = myRs.getInt("item_id");
				String itemName = myRs.getString("itemname");
				String description = myRs.getString("description");
				int bidPrice = myRs.getInt("bidprice");
				String itemImage = myRs.getString("itemimage");

				// create new Item object
				Item tempItem = new Item(id, itemName, description, bidPrice, itemImage);

				// add it to the list of items
				items.add(tempItem);
			}

			return items;
		} finally {
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
				myConn.close(); // doesn't really close it ... just puts back in connection pool
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public void addItem(Item theItem) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create sql for insert
			String sql = "insert into item " + "(itemname, description, bidprice, itemimage) " + "values (?, ?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			// set the param values for the item
			myStmt.setString(1, theItem.getItemName());
			myStmt.setString(2, theItem.getDescription());
			myStmt.setInt(3, theItem.getBidPrice());
			myStmt.setString(4, theItem.getItemImage());

			// execute sql insert
			myStmt.execute();
		} finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public Item getItem(String theItemId) throws Exception {

		Item theItem = null;

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int itemId;

		try {
			// convert item id to int
			itemId = Integer.parseInt(theItemId);

			// get connection to database
			myConn = dataSource.getConnection();

			// create sql to get selected item
			String sql = "select * from item where item_id=?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, itemId);

			// execute statement
			myRs = myStmt.executeQuery();

			// retrieve data from result set row
			if (myRs.next()) {
				String itemName = myRs.getString("itemname");
				String description = myRs.getString("description");
				int bidPrice = myRs.getInt("bidprice");
				String itemImage = myRs.getString("itemimage");

				// use the itemId during construction
				theItem = new Item(itemId, itemName, description, bidPrice, itemImage);
			} else {
				throw new Exception("Could not find item id: " + itemId);
			}

			return theItem;
		} finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void updateItem(Item theItem) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {

			// get a connection to database
			myConn = dataSource.getConnection();

			// create sql to delete item
			String sql = "update item " + "set itemname=?, description=?, bidprice=?, itemimage=? " + "where item_id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, theItem.getItemName());
			myStmt.setString(2, theItem.getDescription());
			myStmt.setInt(3, theItem.getBidPrice());
			myStmt.setString(4, theItem.getItemImage());

			// execute SQL statement
			myStmt.execute();

		} finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public void deleteItem(String theItemId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// convert item id to int
			int itemId = Integer.parseInt(theItemId);

			// get connection to database
			myConn = dataSource.getConnection();

			// create sql to delete item
			String sql = "delete from item where item_id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, itemId);

			// execute sql statement
			myStmt.execute();
		} finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}
	}

}
