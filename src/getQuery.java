import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class getQuery extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        // 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String query=request.getParameter("query");

        FileOutputStream writerStream = new FileOutputStream("E:\\code\\idea\\VSMWeb1\\Query.txt");
        BufferedWriter Query = new BufferedWriter(new OutputStreamWriter(writerStream, "UTF-8"));
        Query.write(query);
        Query.close();

        out.write("查询问题："+query);
    }
}