import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class countSimilarities extends setVector{

    public double countSimilarity( double[] qsVetor,document doc,Map<String, Double> idfMap){
        double[] docVector=doc.setVector(idfMap);
        double similarity=0;
        for (int i = 0; i <docVector.length ; i++) {
            if((docVector[i]!=0)&&(qsVetor[i]!=0))
                similarity+=docVector[i]*qsVetor[i];
        }
        return similarity;
    }//计算查询与单个文档的相似度

    public Map<String,Double>  Similarities() {
        File file = new File("E:\\code\\idea\\VSMWeb1\\text");
        Map<Integer,Double> id_Similarity=new HashMap<>();
        Map<String,Double> FileName_Similarity=new HashMap<>();
        Map<String, Double> idfMap=countIdf.countIdf();
        double[] qsVector=setVector();

        if (file.exists())
        {
            File[] files = file.listFiles();
            int ID=10;
            double similarity;
            for (File file2 : files) {
                String FilePath=file2.getAbsolutePath();
                String FileName=file2.getName();
                document doc=new document(FilePath,ID);
                similarity=countSimilarity(qsVector,doc,idfMap);
                if(similarity!=0.0){
                    id_Similarity.put(ID,similarity);
                    FileName_Similarity.put(FileName,similarity);
                }
                ID++;
            }
        }

        return FileName_Similarity;
    }//查询与所有文档的相似度

    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Map<String,Double> FileName_Similarity=Similarities();
        out.write(FileName_Similarity.toString());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        doGet(request,response);
    }

}
