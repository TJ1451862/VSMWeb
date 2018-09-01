import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class Test {
    public static void main(String[] args)throws ServletException, IOException,Exception {
        /*segmentQuery segm=new segmentQuery();
        System.out.println(segm.segment());
        removePunctuation remo=new removePunctuation();
        System.out.println(remo.nonPunctuation());
        removeStopWords remos=new removeStopWords();
        System.out.println(remos.removeStopWords());
        countTf tf=new countTf();
        System.out.println(tf.countTf());
        createIndex cr=new createIndex();
        cr.createIndex();
        countIdf co=new countIdf();
        co.countIdf();
        setVector se=new setVector();
        String vectorToString="[";
        double[] vector=se.setVector();
        for (int i = 0; i <vector.length ; i++) {
            if(vector[i]!=0.0){
                if (i<vector.length-1)
                    vectorToString=vectorToString+vector[i]+",";
                else vectorToString=vectorToString+vector[i]+"]";
            }
            }
        System.out.println(vectorToString);*/

        File file = new File("E:\\code\\idea\\VSMWeb1\\text");
        Map<Integer,List> map=new HashMap<>();
        Map<String, Double> idfmap=countIdf.countIdf();
        if (file.exists())
        {
            File[] files = file.listFiles();
            int ID=10;

            for (File file2 : files) {
                String FileName=file2.getName();
                System.out.println(FileName);
                }
                ID++;
            }

        }
}

