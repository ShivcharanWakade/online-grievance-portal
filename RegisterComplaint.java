package complaint.system;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/RegisterComplaint")
public class RegisterComplaint extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String complaint = req.getParameter("complaint");

        try {
            Connection con = DBConnect.getConnection();
            String sql = "INSERT INTO complaints(name, email, complaint, status) VALUES (?, ?, ?, 'Pending')";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, complaint);
            ps.executeUpdate();

            ps.close();
            con.close();
            res.sendRedirect("register_success.html");
        } catch (Exception e) {
            res.getWriter().println("Error: " + e.getMessage());
        }
    }
}
