import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/logindb", "root", "root");
            PreparedStatement ps = con.prepareStatement("select * from registeruser where userName=? and userPass=?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String loggedInUsername = rs.getString("username");
                out.print("<div style='text-align: center;background-color: #6fc1ff; width: 100%; height:100vh;'>");
                out.print("<p style='color: red; font-size: 30px;'>Welcome, " + loggedInUsername + "!</p>");
                out.print("</div>");
            } else {
                // Username and password didn't match, show error message
                out.print("<div style='text-align: center; margin-top: 25px;'>");
                out.print("<p style='color: red; font-size: 18px;'>Sorry, username or password error!</p>");
                out.print("</div>");
                request.getRequestDispatcher("login.html").include(request, response);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        out.close();
    }
}
