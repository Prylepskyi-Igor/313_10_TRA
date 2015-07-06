package Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databaseConfig.DatabaseConfig;
import databaseConfig.PersonModel;

/**
 * Servlet implementation class Family
 */
@WebServlet("/Family")
public class Family extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Family() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<PersonModel> parentList = new ArrayList<>();
		ArrayList<PersonModel> childList = new ArrayList<>();
        
        try {
            DatabaseConfig person_db = new DatabaseConfig("myjava");
            Connection conn = person_db.Config();
            
            // Execute SQL query
            String sql = "SELECT * FROM parent WHERE parent_id = " + request.getAttribute("person_id");

            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            // Extract data from result set
            ResultSet rs = pstmt.executeQuery();
            
            // Extract data from result set
            while(rs.next()){
                PersonModel person = new PersonModel();
                
                person.setFirstname(rs.getString("first_name"));
                person.setLastname(rs.getString("last_name"));
                person.setBirthday(rs.getString("birth_date"));
                
                parentList.add(person);
            }
            
         // Execute SQL query
            sql = "SELECT * FROM child WHERE child_id = " + request.getAttribute("person_id");

            pstmt = conn.prepareStatement(sql);
            
            // Extract data from result set
            rs = pstmt.executeQuery();
            
            // Extract data from result set
            while(rs.next()){
                PersonModel person = new PersonModel();
                
                person.setFirstname(rs.getString("first_name"));
                person.setLastname(rs.getString("last_name"));
                person.setBirthday(rs.getString("birth_date"));
                
                childList.add(person);
            }
            
            conn.close();   //close connection
        } catch (SQLException ex) {
            //Logger.getLogger(db.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("parentList", parentList);
        request.setAttribute("childList", childList);
        request.getRequestDispatcher("/personDetails.jsp").forward(request, response);
	}
}
