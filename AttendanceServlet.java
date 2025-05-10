import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class AttendanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = request.getParameter("sid");
        String subject = request.getParameter("subject");
        String date = request.getParameter("date");
        String status = request.getParameter("status");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/college", "root", "password");

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO attendance (student_id, subject, date, status) VALUES (?, ?, ?, ?)");
            ps.setString(1, sid);
            ps.setString(2, subject);
            ps.setString(3, date);
            ps.setString(4, status);
            int i = ps.executeUpdate();

            if (i > 0)
                out.println("<h3>Attendance Recorded Successfully!</h3>");
            else
                out.println("<h3>Failed to record attendance.</h3>");

            con.close();
        } catch(Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}
