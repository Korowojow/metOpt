package functions;

import com.github.sh0nk.matplotlib4j.PythonExecutionException;
import plots.DrawPlotForOkresowa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Okresowa {

    Double result = 0D;
    Double sum = 0D;

    public void implementation(List<Double> x) throws IOException, PythonExecutionException {
        DrawPlotForOkresowa drawPlotForOkresowa = new DrawPlotForOkresowa();
        drawPlotForOkresowa.drawPlots(x, getResultY(x), getResultYInTwoDimensions(x));
    }

    //Value in a specific point with given number of dimensions can be calculated
    //number of x must match witch  number of dimensions
    public Double calculateValueInSpecificPoint(List<Double> x, int dimensions) throws Exception {
        if(x.size()==dimensions){
            x.forEach(x1->{
                result += (updateSumInAnyDimensions(x1, x));
            });
            return result + 1;
        }
        else throw new Exception("Invalid arguments");
    }

    private List<Double> getResultYInTwoDimensions(List<Double> x) {
        List<Double> result = new ArrayList<>();
        x.forEach(x1 -> {
            result.add(updateSumInTWoDimensions(x1));
        });
        return result;
    }

    private List<Double> getResultY(List<Double> x) {
        List<Double> result = new ArrayList<>();
        x.forEach(x1 -> {
            result.add(updateSum(x1)+1D);
        });
        return result;
    }

    private Double updateSum(Double x) {
        return Math.sin(x) * Math.sin(x) - 0.1*(Math.exp(-x*x));
    }

    private Double updateSumInTWoDimensions(Double x) {
        return Math.sin(x) * Math.sin(x) - 0.1*(Math.exp(-x*x+x*x));
    }

    private Double updateSumInAnyDimensions(Double x, List<Double> doubles) {
        return Math.sin(x) * Math.sin(x) - 0.1*(Math.exp(-calculateSumOfXSquare(doubles)));
    }

    private Double calculateSumOfXSquare(List<Double> doubles){
        doubles.forEach(x1 ->{
            sum += x1*x1;
        });
        return sum;
    }
}

