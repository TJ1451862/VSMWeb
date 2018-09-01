import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class document extends countTf{

    private String docpath;
    private int docID;

    public document(String docpath, int docID) {
        this.docpath = docpath;
        this.docID = docID;
    }

    public int getDocID(){
        return docID;
    }

    public List<Term> segment(){

        String docs=readDocument(docpath);
        HanLP.Config.ShowTermNature = false;//停用词性显示
        List<Term> termList = HanLP.segment(docs);
        return termList;

    }//标准分词

    public List<Term> nonPunctuation()//去标点
    {
        List<Term> termList = this.segment();
        String Query=termList.toString();
        Query=Query.replaceAll("[\\pP\\n\\t\\s]", "");
        Query=Query.replaceAll("　　", "");
        HanLP.Config.ShowTermNature = false;//停用词性显示
        termList = HanLP.segment(Query);
        return termList;
    }

    public ArrayList removeStopWords()//去停用词
    {
        ArrayList<String> stopWordAL  = readStopWords();
        ArrayList<String> TermList = new ArrayList();
        List<Term> termList =this.nonPunctuation();
        for (int i = 0; i < termList.size(); i++) {
            TermList.add(termList.get(i).toString());
        }
        TermList.removeAll(stopWordAL);
        return TermList;
    }

    public Map<String, Double> countTf() {
        ArrayList<String> termList = this.removeStopWords();
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

    public double[] setVector(Map<String, Double> idfmap)  {
        Map<String, Double> tfmap = this.countTf();
        int idflength = idfmap.size();
        double[] vector = new double[idflength + 1];
        int num = 0;
        for (String in : idfmap.keySet()) {
            if (tfmap.containsKey(in)) {
                double tf = tfmap.get(in);
                double idf = idfmap.get(in);
                vector[num] = tf * idf;
            } else {
                num++;
            }
        }
        double sum = 0;
        for (double j : vector) {
            sum += Math.pow(j, 2);
        }
        sum = Math.pow(sum, 0.5);
        for (int i = 0; i < vector.length; i++) {
            vector[i] = vector[i] / sum;
        }
        return vector;
    }
}
