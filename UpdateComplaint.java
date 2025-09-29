package complaint.system;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/UpdateComplaint")
public class UpdateComplaint extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        String status = req.getParameter("status");

        try {
            Connection con = DBConnect.getConnection();
            String sql = "UPDATE complaints SET status=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();

            ps.close();
            con.close();
            res.sendRedirect("viewComplaints.jsp");
        } catch (Exception e) {
            res.getWriter().println("Error: " + e.getMessage());
        }
    }
}
