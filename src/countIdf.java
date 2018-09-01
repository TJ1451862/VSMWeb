import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class countIdf extends createIndex{

    public static Map<String, Double> countIdf(){
        File file = new File("E:\\code\\idea\\VSMWeb1\\text");
        Map<String, Double> idf_map= new HashMap<>();

        if (file.exists())
        {
            File[] files = file.listFiles();
            int ID=10;
            for (File file2 : files) {
                String FilePath=file2.getAbsolutePath();
                document doc=new document(FilePath,ID);
                ArrayList<String> sw=doc.removeStopWords();
                for (String string : sw) {
                    double count;
                    if (!idf_map.containsKey(string)) {
                        idf_map.put(string, 1.0);
                    }else {
                        count=idf_map.get(string)+1;
                        idf_map.put(string, count);
                    }
                }
                ID++;
            }
        }
        int N=idf_map.size();
        double idf;
        for (String key:idf_map.keySet()) {
            idf=Math.log(N/(idf_map.get(key)));
            idf_map.put(key,idf);
        }

        try{
            FileWriter inIn = new FileWriter(new File("E:\\code\\idea\\VSMWeb1\\idfIndex.txt"));
            inIn.write(idf_map.toString());
            inIn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return idf_map;
    }//求Idf,idf=log(N/df)

    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Map<String, Double> idf_map=countIdf();
        out.write(idf_map.toString());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        doGet(request,response);
    }


}
