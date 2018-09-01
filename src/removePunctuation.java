import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class removePunctuation extends segmentQuery{

    public List<Term> nonPunctuation()//去标点
    {
        List<Term> termList = segment();
        String Query=termList.toString();
        Query=Query.replaceAll("[\\pP\\n\\t\\s]", "");
        Query=Query.replaceAll("　　", "");
        HanLP.Config.ShowTermNature = false;//停用词性显示
        termList = HanLP.segment(Query);
        return termList;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        List<Term> termList = nonPunctuation();
        out.write(termList.toString());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        doGet(request,response);
    }
}
