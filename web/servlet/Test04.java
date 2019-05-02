import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


@WebServlet("/Test04")
public class Test04 extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement psmt = null;

        String data = req.getParameter("data");
        PrintWriter out = resp.getWriter();
        out.print("Hello Servlet");
        int index = 1;
        Block block = new Block(index, "100", null);
        //block.getInformation();
        index++;

        String db_url = "jdbc:oracle:thin:@localhost:1521:xe";
        String db_id = "hr";
        String db_pw = "hr";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            conn = DriverManager.getConnection(db_url, db_id, db_pw);

            // todo: select
            String sql = "select * from blocker order by blockid ";
            psmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            //psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();

            if(rs.last()){}
            String prehash = rs.getString(3);

            Block block2 = new Block(index, data, prehash);

            String sql2 = "insert into blocker values (no_seq.nextval, ?, ?)";


            psmt = conn.prepareStatement(sql2);
            psmt.setString(1, data);
            //이전해쉬값을 받아와야하는데..
            psmt.setString(2, block2.getBlockHash());
            int cnt = psmt.executeUpdate();

            if(cnt>0){

                req.setAttribute("data", data);
                req.setAttribute("hash", block2.getBlockHash());
                resp.sendRedirect("index2.jsp");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

                try {
                    if(psmt!=null) {
                        psmt.close();
                    }
                    if(conn!=null){
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

    }
}
