import com.hankcs.hanlp.seg.common.Term;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class removeStopWords extends removePunctuation {

    public ArrayList<String> readStopWords(){
        ArrayList<String> stopWordAL  = new ArrayList();
        try{
            FileInputStream stopWordFile = new FileInputStream("E:\\code\\idea\\VSMWeb1\\ChineseStopWord.txt");//停用词
            InputStreamReader input=new InputStreamReader(stopWordFile,"UTF-8");
            BufferedReader stopWordBR = new BufferedReader(input);//构造一个BufferedReader类来读取ChineseStopWord文件
            String stopWord;
            while ((stopWord = stopWordBR.readLine()) != null) {//使用readLine方法，一次读一行 读取停用词
                stopWordAL.add(stopWord);
            }
            stopWordBR.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }//读取停用词表
        return stopWordAL;
    }

    public ArrayList removeStopWords()//去停用词
    {
        ArrayList<String> stopWordAL  = readStopWords();
        ArrayList<String> TermList = new ArrayList();
        List<Term> termList =nonPunctuation();
        for (int i = 0; i < termList.size(); i++) {
            TermList.add(termList.get(i).toString());
        }
        TermList.removeAll(stopWordAL);
        return TermList;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ArrayList termList = removeStopWords();
        out.write(termList.toString());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        doGet(request,response);
    }

}
