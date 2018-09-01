import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class outPutVector extends countTf{

    public  Map<String,Double> outPutVector(){

        Map<String, Double> idf_map=countIdf.countIdf();
        Map<String, Double> tfmap = countTf();
        Map<String,Double> word_vector=new HashMap<>();
        int idfLength = idf_map.size();
        double[] vector = new double[idfLength + 1];
        int num = 0;
        for (String in : idf_map.keySet()) {
            if(tfmap.containsKey(in)){
                double tf = tfmap.get(in);
                double idf = idf_map.get(in);
                if((tf!=0)&&(idf!=0))
                    vector[num] = tf * idf;
                word_vector.put(in,vector[num]);
            }else {
                num++;
            }
        }
        double sum=0;
        for (String in:word_vector.keySet()) {
            sum+=Math.pow(word_vector.get(in),2);
        }
        sum=Math.pow(sum,0.5);
        for (String in:word_vector.keySet()) {
            double value=word_vector.get(in)/sum;
            word_vector.put(in,value);
        }
        return word_vector;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Map<String,Double> word_vector=outPutVector();
        String output="[0.0,";
        for (String in:word_vector.keySet()) {
            output=output+"……,("+in+")"+word_vector.get(in)+",";
        }
        output=output+"……,0.0]";
        out.write(output);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        doGet(request,response);
    }

}
