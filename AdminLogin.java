package complaint.system;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String user = req.getParameter("username");
        String pass = req.getParameter("password");

        try {
            Connection con = DBConnect.getConnection();
            String sql = "SELECT * FROM admin WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                res.sendRedirect("viewComplaints.jsp");
            } else {
                res.getWriter().println("Invalid login!");
            }

            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            res.getWriter().println("Error: " + e.getMessage());
        }
    }
}
