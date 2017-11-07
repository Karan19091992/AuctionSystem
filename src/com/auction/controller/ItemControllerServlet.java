package com.auction.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import com.auction.dao.ItemDBUtil;
import com.auction.model.Item;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/ItemControllerServlet")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class ItemControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ItemDBUtil itemDbUtil;
	
	@Resource(name="jdbc/auctiondb")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our item db util ... and pass in the conn pool / datasource
		try {
			itemDbUtil = new ItemDBUtil(dataSource);
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
			
			// if the command is missing, then default to listing items
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			// route to the appropriate method
			switch (theCommand) {
			
			case "LIST":
				listItems(request, response);
				break;
				
			case "ADD":
				addItem(request, response);
				break;
				
			case "LOAD":
				loadItem(request, response);
				break;
				
			case "UPDATE":
				updateItem(request, response);
				break;
			
			case "DELETE":
				deleteItem(request, response);
				break;
				
			default:
				listItems(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}

	/** Delete user **/
	
	private void deleteItem(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// read item id from form data
		String theItemId = request.getParameter("itemId");
		
		// delete item from database
		itemDbUtil.deleteItem(theItemId);
		
		// send them back to "list items" page
		listItems(request, response);
	}

	/** Update Users **/
	
	private void updateItem(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// read item info from form data
		int id = Integer.parseInt(request.getParameter("itemId"));
		String itemName = request.getParameter("itemName");
		String description = request.getParameter("description");
		int bidPrice = Integer.parseInt(request.getParameter("bidPrice"));
		String itemImage = request.getParameter("itemImage");
		
		// create a new item object
		Item theItem = new Item(id, itemName, description, bidPrice, itemImage);
		
		// perform update on database
		itemDbUtil.updateItem(theItem);;
		
		// send them back to the "list items" page
		listItems(request, response);
		
	}

	/** Load item **/
	
	private void loadItem(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		// read item id from form data
		String theItemId = request.getParameter("itemId");
		
		// get item from database (db util)
		Item theItem = itemDbUtil.getItem(theItemId);
		
		// place item in the request attribute
		request.setAttribute("THE_ITEM", theItem);
		
		// send to jsp page: update-item-form.jsp
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/update-item-form.jsp");
		dispatcher.forward(request, response);		
	}

	/** Add Users **/
	
	private void addItem(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read item info from form data
//		int id = Integer.parseInt(request.getParameter("itemId"));
		String itemName = request.getParameter("itemName");
		String description = request.getParameter("description");
		int bidPrice = Integer.parseInt(request.getParameter("bidPrice"));
		String itemImage = null;
		
		try {
			  
	        InputStream inputStream = null; // input stream of the upload file
	         
	        // obtains the upload file part in this multipart request
			 Part filePart = request.getPart("itemImage");
		        if (filePart != null) {
		            // prints out some information for debugging
		            System.out.println("filePart.getName()" +filePart.getName());
		            System.out.println("filePart.getSize()" +filePart.getSize());
		            System.out.println("filePart.getContentType()" +filePart.getContentType());
		             
		            // obtains input stream of the upload file
		            inputStream = filePart.getInputStream();
		           
		        }
		        
			 if (inputStream != null) {
	                // fetches input stream of the upload file for the blob column
	            //    statement.setBlob(3, inputStream);
	              itemImage = inputStream.toString();  
	            }
	         
	   
		// create a new item object
		Item theItem = new Item(itemName, description, bidPrice, itemImage);
		
		// add the item to the database
		itemDbUtil.addItem(theItem);
				
		// send back to main page (the item list)
		listItems(request, response);
		
		}catch (Exception e) {
			
		}
		
	}

	/** List of users**/
	
	private void listItems(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		// get users from db util
		List<Item> items = itemDbUtil.getItems();
		
		// add items to the request
		request.setAttribute("ITEM_LIST", items);
				
		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-items.jsp"); 
		dispatcher.forward(request, response);
	}
}
