package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;


public class User {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/egelectrio?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertUser(String UserName, String UserAddress, String UserMobile, String UserEmail, String UserAccNo)  
	{   
		String output = ""; 	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for inserting."; } 
	 
			// create a prepared statement 
			String query = " insert into user(`UID`,`UserName`,`UserAddress`,`UserMobile`,`UserEmail`,`UserAccNo`)" + " values (?, ?, ?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, UserName);
			 preparedStmt.setString(3, UserAddress);
			 preparedStmt.setString(4, UserMobile);
			 preparedStmt.setString(5, UserEmail);
			 preparedStmt.setString(6, UserAccNo);
			
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	   
			String newUser = readUser(); 
			output =  "{\"status\":\"success\", \"data\": \"" + newUser + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the User.\"}";  
			System.err.println(e.getMessage());   
		} 		
	  return output;  
	} 	
	
	public String readUser()  
	{   
		String output = ""; 
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
	 
			// Prepare the html table to be displayed    
			output = "<table border=\'1\'><tr><th>User Name</th><th>Address</th><th>Mobile</th><th>Email</th><th>Account Number</th><th>Update</th><th>Remove</th></tr>";
	 
			String query = "select * from user";    
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
	 
			// iterate through the rows in the result set    
			while (rs.next())    
			{     
				 String UID = Integer.toString(rs.getInt("UID"));
				 String UserName = rs.getString("UserName");
				 String UserAddress = rs.getString("UserAddress");
				 String UserMobile = rs.getString("UserMobile");
				 String UserEmail = rs.getString("UserEmail");
				 String UserAccNo = rs.getString("UserAccNo");
				 
				// Add into the html table 
				output += "<tr><td><input id=\'hidUserIDUpdate\' name=\'hidUserIDUpdate\' type=\'hidden\' value=\'" + UID + "'>" 
							+ UserName + "</td>"; 
				output += "<td>" + UserAddress + "</td>";
				output += "<td>" + UserMobile + "</td>";
				output += "<td>" + UserEmail + "</td>";
				output += "<td>" + UserAccNo + "</td>";
	 
				// buttons     
				output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-userid='" + UID + "'>" + "</td></tr>"; 
			
			}
			con.close(); 
	   
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the User.";    
			System.err.println(e.getMessage());   
		} 	 
		return output;  
	}
	
	public String updateUser(String UID, String UserName, String UserAddress, String UserMobile, String UserEmail, String UserAccNo)  
	{   
		String output = "";  
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for updating."; } 
	 
			// create a prepared statement    
			String query = "UPDATE user SET UserName=?,UserAddress=?,UserMobile=?,UserEmail=?,UserAccNo=?"  + "WHERE UID=?";  	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setString(1, UserName);
			 preparedStmt.setString(2, UserAddress);
			 preparedStmt.setString(3, UserMobile);
			 preparedStmt.setString(4, UserEmail);
			 preparedStmt.setString(5, UserAccNo);
			 preparedStmt.setInt(6, Integer.parseInt(UID)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close();  
			String newUser = readUser();    
			output = "{\"status\":\"success\", \"data\": \"" + newUser + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the User.\"}";   
			System.err.println(e.getMessage());   
		} 	 
	  return output;  
	} 
	
	public String deleteUser(String UID)   
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for deleting."; 			
			} 
	 
			// create a prepared statement    
			String query = "delete from user where UID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, Integer.parseInt(UID)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newUser = readUser();    
			output = "{\"status\":\"success\", \"data\": \"" +  newUser + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "Error while deleting the User.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
}
