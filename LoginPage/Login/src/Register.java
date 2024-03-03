import java.io.*;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Register extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String n = request.getParameter("userName");
        String p = request.getParameter("userPass");
        String e = request.getParameter("userEmail");
        String c = request.getParameter("userCountry");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/logindb", "root", "root");
            PreparedStatement ps = con.prepareStatement("insert into registeruser values(?,?,?,?)");

            ps.setString(1, n);
            ps.setString(2, p);
            ps.setString(3, e);
            ps.setString(4, c);

            int i = ps.executeUpdate();
            if (i > 0) {
                out.print("<!DOCTYPE html>");
                out.print("<html>");
                out.print("<head>");
                out.print("<title>Registration Success</title>");
                out.print("<style>");
                out.print("body {");
                out.print("    background-color: #6fc1ff;");
                out.print("    margin: 0;");
                out.print("    padding: 0;");
                out.print("    height: 100vh;");
                out.print("    display: flex;");
                out.print("    justify-content: center;");
                out.print("    align-items: center;");
                out.print("}");
                out.print("div {");
                out.print("    text-align: center;");
                out.print("    padding: 20px;");
                out.print("    background-color: #2496ff;");
                out.print("    border: 1px solid #ccc;");
                out.print("    border-radius: 5px;");
                out.print("    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);");
                out.print("}");
                out.print("</style>");
                out.print("</head>");
                out.print("<body>");
                out.print("<div>");
                out.print("<h1>You are successfully registered!</h1>");
                out.print("<a href='/Login/login.html'>Login Here</a>");
                out.print("</div>");
                out.print("</body>");
                out.print("</html>");
            }
        } catch (SQLIntegrityConstraintViolationException e1) {
            out.print("<!DOCTYPE html>");
            out.print("<html>");
            out.print("<head>");
            out.print("<title>Registration Error</title>");
            out.print("<style>");
            out.print("body {");
            out.print("    background-color: #6fc1ff;");
            out.print("    margin: 0;");
            out.print("    padding: 0;");
            out.print("    height: 100vh;");
            out.print("    display: flex;");
            out.print("    justify-content: center;");
            out.print("    align-items: center;");
            out.print("}");
            out.print("div {");
            out.print("    text-align: center;");
            out.print("    padding: 20px;");
            out.print("    background-color: #2496ff;");
            out.print("    border: 1px solid #ccc;");
            out.print("    border-radius: 5px;");
            out.print("    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);");
            out.print("}");
            out.print("</style>");
            out.print("</head>");
            out.print("<body>");
            out.print("<div>");
            out.print("<h1>Registration Error</h1>");
            out.print("<p>User already exists. Please choose a different username.</p>");
            out.print("</div>");
            out.print("</body>");
            out.print("</html>");
        } catch (Exception e2) {
            System.out.println(e2);
        }

        out.close();
    }
}
