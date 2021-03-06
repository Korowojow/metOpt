package functions;

import com.github.sh0nk.matplotlib4j.PythonExecutionException;
import plots.DrawPlotForOkresowa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Okresowa {

    public void implementation(List<Double> x) throws IOException, PythonExecutionException {
        DrawPlotForOkresowa drawPlotForOkresowa = new DrawPlotForOkresowa();
        drawPlotForOkresowa.drawPlots(x, getResultY(x), getResultYInTwoDimensions(x));
    }

    //Value in a specific point with given number of dimensions can be calculated
    //number of x must match witch  number of dimensions
    public Double calculateValueInSpecificPoint(List<Double> x, int dimensions) throws Exception {
        if(x.size()==dimensions){
            double result = x.stream().mapToDouble(this::updateSumInAnyDimensions).sum();

            return result + 1 + calculateE(x);
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
        return (2 * Math.sin(x) * Math.sin(x)) - 0.1*(Math.exp(-(x*x+x*x)));
    }

    private Double updateSumInAnyDimensions(Double x) {
        return (Math.sin(x) * Math.sin(x));
    }
    private Double calculateE(List<Double> doubles) {
        return  -0.1*(Math.exp(-calculateSumOfXSquare(doubles)));
    }

    private Double calculateSumOfXSquare(List<Double> doubles){
        return doubles.stream().mapToDouble(x1 -> x1 * x1).sum();
    }
}

