package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.DB.DBConnect;
import com.entity.User;

public class AddUserDao {
	public int registerAdduser(User adduser) throws ClassNotFoundException {
        String INSERT_USERS_SQL = "INSERT INTO user" +
            "  (id, name, email, password, phno) VALUES " +
            " (?, ?, ?, ?, ? );";

        int result = 0;

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
        		.getConnection("jdbc:mysql://localhost:3306/ebook-app","root","123456");

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setInt(1, adduser.getId() );
            preparedStatement.setString(2, adduser.getName());
            preparedStatement.setString(3, adduser.getEmail());
            preparedStatement.setString(4, adduser.getPassword());
            preparedStatement.setString(5, adduser.getPhno());
          
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return result;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    private Connection con;
   	private String query;
   	private PreparedStatement pst;
   	private ResultSet rs;
   	public AddUserDao(Connection con) {
   		this.con = con;
   	}
   	
   	public User  CheckUsername(String username)
    {
   		PreparedStatement stm = null;
  	  ResultSet rs = null;
  	Connection con = null;
  	 String query = "SELECT * FROM cnnck.user where name = ? ";
  	 try {
  		con = DBConnect.getConn();
  		stm = con.prepareStatement(query);
  		stm.setString(1, username);
  		rs = stm.executeQuery();
  		while(rs.next())
  		{
  			return new User();
  		}
} catch (Exception e) {
  		// TODO: handle exception
  	}
  	 
  	return null;
  	  
    }
   	public List<User> searchUsers(String query) {
   	    List<User> results = new ArrayList<>();

   	    try {
   	        // Prepare the SQL query with placeholders for the search term
   	        String sql = "SELECT * FROM user WHERE name LIKE ?";
   	        PreparedStatement statement = con.prepareStatement(sql);

   	        // Set the search term as a parameter in the SQL query
   	        String searchTerm = "%" + query + "%";
   	     statement.setString(1, searchTerm);

	        // Execute the query and loop through the results
	        ResultSet resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	        	User user = new User();
	            user.setId(resultSet.getInt("id"));
	            user.setName(resultSet.getString("name"));
	            user.setEmail(resultSet.getString("email"));
	            user.setPassword(resultSet.getString("password"));
	          
	            results.add(user);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return results;
	}



	
	public List<User> getAllUser1() {
     List<User> user= new ArrayList<>();
     try {

         query = "select * from user";
         pst = this.con.prepareStatement(query);
         rs = pst.executeQuery();

         while (rs.next()) {
         	User row = new User();
             row.setId(rs.getInt("id"));
             row.setName(rs.getString("name"));
             row.setEmail(rs.getString("email"));
             row.setPassword(rs.getString("password"));
             row.setPhno(rs.getString("phno"));    
             user.add(row);
         }

     } catch (SQLException e) {
         e.printStackTrace();
         System.out.println(e.getMessage());
     }
     return user;
	
}
	
	


	

}