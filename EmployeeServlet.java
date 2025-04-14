import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class EmployeeServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String empId = request.getParameter("empId");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/company", "root", "password");

            Statement stmt = con.createStatement();
            String query = "SELECT * FROM employees";
            if (empId != null && !empId.isEmpty()) {
                query += " WHERE id=" + empId;
            }
            ResultSet rs = stmt.executeQuery(query);

            out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Dept</th></tr>");
            while(rs.next()) {
                out.println("<tr><td>" + rs.getInt("id") + "</td><td>" +
                            rs.getString("name") + "</td><td>" +
                            rs.getString("dept") + "</td></tr>");
            }
            out.println("</table>");
            con.close();
        } catch(Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}
