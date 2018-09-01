import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class setVector extends countTf{

    public  double[] setVector(){
        Map<String, Double> idf_map=countIdf.countIdf();
        Map<String, Double> tfmap = countTf();
        int idflength = idf_map.size();
        double[] vector = new double[idflength + 1];
        int num = 0;
        for (String in : idf_map.keySet()) {
            if(tfmap.containsKey(in)){
                double tf = tfmap.get(in);
                double idf = idf_map.get(in);
                if((tf!=0)&&(idf!=0))
                    vector[num] = tf * idf;
            }else {
                num++;
            }
        }
        double sum=0;
        for (double j:vector) {
            sum+=Math.pow(j,2);
        }
        sum=Math.pow(sum,0.5);
        double a=0;
        for (int i = 0; i <vector.length ; i++) {
            vector[i]=vector[i]/sum;
            a+=Math.pow(vector[i],2);
        }
        return vector;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String vectorToString="[";
        double[] vector=setVector();
        for (int i = 0; i <vector.length ; i++) {
            if (i<vector.length-1){
                vectorToString=vectorToString+vector[i]+",";
            }else{
                vectorToString=vectorToString+vector[i]+"]";
            }
        }
        out.write(vectorToString);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        doGet(request,response);
    }

}
