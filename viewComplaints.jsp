<%@ page import="java.sql.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="complaint.system.DBConnect" %>

<html>
<head>
  <title>View Complaints</title>
  <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f4f9;
            padding: 20px;
        }
        table {
            width: 80%;
            border-collapse: collapse;
            margin: auto;
            background: #fff;
            box-shadow: 0px 2px 8px rgba(0,0,0,0.1);
        }
        th, td {
            padding: 10px;
            text-align: center;
            border: 1px solid #ddd;
        }
        th {
            background: #007BFF;
            color: white;
        }
    </style>
</head>
<body>

<div class="text-container">
  <h2>All Complaints</h2>
</div>

<div class="container">
  <table>
    <tr>
      <th>ID</th>
      <th>Name</th>
      <th>Email</th>
      <th>Complaint</th>
      <th>Status</th>
      <th>Date Submitted</th>
      <th>Action</th>
    </tr>

    <%
      try {
          Connection con = DBConnect.getConnection();
          Statement st = con.createStatement();
          ResultSet rs = st.executeQuery("SELECT * FROM complaints ORDER BY created_at DESC");
          SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

          while(rs.next()) {
    %>
    <tr>
      <td><%= rs.getInt("id") %></td>
      <td><%= rs.getString("name") %></td>
      <td><%= rs.getString("email") %></td>
      <td><%= rs.getString("complaint") %></td>
      <td><%= rs.getString("status") %></td>
      <td>
        <%
          java.sql.Timestamp ts = rs.getTimestamp("created_at");
          if (ts != null) { out.print(sdf.format(ts)); }
        %>
      </td>
      <td>
        <form action="UpdateComplaint" method="post">
          <input type="hidden" name="id" value="<%= rs.getInt("id") %>">
          <select name="status">
            <option>Pending</option>
            <option>In Progress</option>
            <option>Resolved</option>
          </select>
          <input type="submit" value="Update">
        </form>
      </td>
    </tr>
    <%
          }
          rs.close();
          st.close();
          con.close();
      } catch(Exception e) {
          out.println("Error: " + e.getMessage());
      }
    %>
  </table>
</div>

</body>
</html>
