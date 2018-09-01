import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class outPutEveryDoc_Tf extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        File file = new File("E:\\code\\idea\\VSMWeb1\\text");
        Map<Integer,String> map=new HashMap<>();

        if (file.exists())
        {
            File[] files = file.listFiles();
            int ID=10;
            for (File file2 : files) {
                String FilePath=file2.getAbsolutePath();
                document doc=new document(FilePath,ID);
                Map<String, Double> tf_map=doc.countTf();
                map.put(ID,tf_map.toString());
                ID++;
            }
        }
        //JSONObject jsonObject=JSONObject.parseObject(JSON.toJSONString(map));
        //out.print(jsonObject);
        out.write(map.toString());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        doGet(request,response);
    }

}
