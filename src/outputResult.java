import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

public class outputResult extends sortSimilarities{


    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        List<Map.Entry<String,Double>> list=sortSimilarities();
        Map<String, String> fileName_value= new HashMap<>();

        File file = new File("E:\\code\\idea\\VSMWeb1\\text");
        if (file.exists())
        {
            File[] files = file.listFiles();
            for (File file2 : files) {
                for (Map.Entry<String,Double> mapping:list) {
                    if(mapping.getKey().equals(file2.getName())){
                        fileName_value.put(file2.getName(),readFile(file2));
                    }
            }
            }
        out.write(fileName_value.toString());
    }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        doGet(request,response);
    }

    public String readFile(File file){
        String encoding = "UTF-8";
        File File   = new File(file.getAbsolutePath());
        Long fileLength = File.length();
        byte[] fileContent = new byte[fileLength.intValue()];
        try {
            FileInputStream in = new FileInputStream(File);
            in.read(fileContent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(fileContent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;

    }

}}


