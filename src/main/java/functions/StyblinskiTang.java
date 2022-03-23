package functions;

import com.github.sh0nk.matplotlib4j.PythonExecutionException;
import plots.DrawPlotForStyblinskiTang;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StyblinskiTang {
    Double result = 0D;

    public void implementation(List<Double> x) throws IOException, PythonExecutionException {
        DrawPlotForStyblinskiTang drawPlotForStyblinskiTang = new DrawPlotForStyblinskiTang();
        drawPlotForStyblinskiTang.drawPlots(x, getResultY(x));
    }

    //Value in a specific point with given number of dimensions can be calculated
    //number of x must match witch  number of dimensions
    public Double calculateValueInSpecificPoint(List<Double> x, int dimensions) throws Exception {
        if(x.size()==dimensions){
        x.forEach(x1->{
            result += ((updateSum(x1))/2);
        });
        return result;
        }
        else throw new Exception("Invalid arguments");
    }

    private List<Double> getResultY(List<Double> x) {
        List<Double> result = new ArrayList<>();
        x.forEach(x1 -> {
            result.add(updateSum(x1)/2);
        });
        return result;
    }

    private Double updateSum(Double x) {
        return (x*x*x*x)-(16*x*x)+(5*x);
    }
}
