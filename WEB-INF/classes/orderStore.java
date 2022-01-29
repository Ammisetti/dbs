import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class orderStore extends GenericServlet {
    public void service (ServletRequest request, ServletResponse response) throws ServletException, IOException {
        String stocks = request.getParameter("stocks");
        String type = request.getParameter("type");
        int price = Integer.parseInt(request.getParameter("price"));
        int qty = Integer.parseInt(request.getParameter("qty"));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Connection con = null;
        Statement stm = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbs", "root", "");
            stm = con.createStatement();
            String sql = String.format("INSERT INTO orders VALUES ('%s', '%s', %d, %d)", stocks, type, price, qty);
            int i = stm.executeUpdate(sql);
            if(i>0) {
                out.println("You order is successfully placed");
            }
        } catch (Exception e) {
            out.println(e);
        }
    }
}
