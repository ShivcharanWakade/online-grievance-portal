package complaint.system;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/CheckStatus")
public class CheckStatus extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String email = req.getParameter("email");

        try {
            Connection con = DBConnect.getConnection();
            String sql = "SELECT id, complaint, status FROM complaints WHERE email=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            res.setContentType("text/html");
            res.getWriter().println("<html><body>");
            res.getWriter().println("<h2>Your Complaints</h2>");
            res.getWriter().println("<table border='1'><tr><th>ID</th><th>Complaint</th><th>Status</th></tr>");

            while (rs.next()) {
                res.getWriter().println("<tr>");
                res.getWriter().println("<td>" + rs.getInt("id") + "</td>");
                res.getWriter().println("<td>" + rs.getString("complaint") + "</td>");
                res.getWriter().println("<td>" + rs.getString("status") + "</td>");
                res.getWriter().println("</tr>");
            }

            res.getWriter().println("</table>");
            res.getWriter().println("<br><a href='index.html'>Go Home</a>");
            res.getWriter().println("</body></html>");

            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            res.getWriter().println("Error: " + e.getMessage());
        }
    }
}
