import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class createIndex extends HttpServlet {

    public Map<String, ArrayList<Integer>> createIndex(){
        Map<String, ArrayList<Integer>> index_map= new HashMap<>();
        File file = new File("E:\\code\\idea\\VSMWeb1\\text");
        if (file.exists())
        {
            File[] files = file.listFiles();
            int ID=10;
            for (File file2 : files) {
                String FilePath=file2.getAbsolutePath();
                document doc=new document(FilePath,ID);
                //doc.countTf();
                ArrayList<String> sw=doc.removeStopWords();
                for (String string : sw) {
                    ArrayList<Integer> list=new ArrayList<>();
                    if (!index_map.containsKey(string)) {
                        list.add(ID);
                        index_map.put(string, list);
                    }else {
                        list=index_map.get(string);
                        if (!list.contains(ID)) {
                            list.add(ID);
                        }
                    }
                }
                ID++;
            }
        }
        try{
            FileWriter inIn = new FileWriter(new File("E:\\code\\idea\\VSMWeb1\\Index.txt"));
            inIn.write(index_map.toString());
            inIn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return index_map;
    }//建立倒排索引

    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Map<String, ArrayList<Integer>> index =createIndex();
        out.write(index.toString());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        doGet(request,response);
    }


}
