package functions;

import com.github.sh0nk.matplotlib4j.PythonExecutionException;
import common.CommonOperations;
import plots.DrawPlotForOkresowa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Okresowa {

    public void implementation(List<Double> x) throws IOException, PythonExecutionException {
        DrawPlotForOkresowa drawPlotForOkresowa = new DrawPlotForOkresowa();
        drawPlotForOkresowa.drawPlots(x, getResultY(x), getResultYInTwoDimensions(x));
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
            result.add(updateSum(x1));
        });
        return result;
    }

    private Double updateSum(Double x) {
        return Math.sin(x) * Math.sin(x) - 0.1*(Math.exp(-x*x));
    }
    private Double updateSumInTWoDimensions(Double x) {
        CommonOperations commonOperations = new CommonOperations();
        return Math.sin(x) * Math.sin(x) - 0.1*(Math.exp(-x*x+x*x));
    }
}

