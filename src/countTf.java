import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class countTf extends removeStopWords{

    public Map<String, Double> countTf() {
        ArrayList<String> termList = removeStopWords();
        Map<String, Double> tf_map = new HashMap<>();

        for (String string : termList) {
            if (!tf_map.containsKey(string)) {
                tf_map.put(string, 1.0);
            } else {
                double tf = 1 + Math.log(tf_map.get(string) + 1);
                tf_map.put(string, tf);
            }
        }

        return tf_map;
    }//计算tf,tf=1+log(tf)

    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Map<String, Double> tf_map =countTf();
        out.write(tf_map.toString());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        doGet(request,response);
    }

}
