package com.gco;

import java.sql.*;

public class SignMeUp {

private Connection connect;
private ResultSet rest;
private Statement stmt, insStmt;




public void InsertStmt(String uname, String pname,  String fname, String lname, String street,
			String rcity, String rstate, String rzip) {
try
{
	insStmt = connect.createStatement();
	insStmt.executeUpdate("INSERT INTO getusers ("
		+ "user_name, pass_word, fname, lname, usraddr, usrcity, "
		+ "usrstate, usrzip) VALUES ('" + uname + "', '" + pname + "', '" + fname
		+ "', '" + lname + "', '" 
		+ street + "', '" + rcity
		+ "', '" + rstate
		+ "', '" + rzip + "')");
} catch (SQLException ens)
	{
	System.out.println(ens.getMessage());
	}
}

public void connectDB()
{
	try 
	{
		
		Class.forName("com.mysql.jdbc.Driver");
	}catch (ClassNotFoundException e)
	{ 
		System.out.println("Database cannot open");
	}
	try 
	{
		DriverManager.registerDriver(new  com.mysql.jdbc.Driver());
		connect = DriverManager.getConnection ("jdbc:mysql://localhost/login?" 
		+ "user=root&password=");
		System.out.println("Connection established");
	} catch (SQLException e)
	{
		System.out.println(e.getMessage());
	}
}
				
public int loginListener(String usn, String pwd)
{
	String sName=null, sPass=null;
	try 
	{
		String uName = usn;
		String uPass = pwd;
		stmt = connect.createStatement();
		rest = stmt.executeQuery("SELECT * FROM getusers "
		+ "WHERE user_name='" + uName + "'"); 
		if (rest.absolute(1))
		{
		   //rest.next();
		   sName = rest.getString("user_name");
		   sPass = rest.getString("pass_word");
		   System.out.println(sName + "&" + sPass);
		   if (uName.equals(sName)&& uPass.equals(sPass))
		   {
			return 1;
		   }
		   else
		   {
			return 2;
		   }
		   
		}
		else
		{
			return 3;
			
		}
		
	} catch (SQLException e)
	    
	  {
		System.out.println(e.getMessage());
	    return 3;
	  } 
}
}
	