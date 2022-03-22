package functions;

import com.github.sh0nk.matplotlib4j.PythonExecutionException;
import plots.DrawPlot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StyblinskiTang {
    private Double sum = 0.0;

    public void implementation(List<Double> x) throws IOException, PythonExecutionException {
        DrawPlot.drawScatterPlot(x,getResultY(x));
        DrawPlot.drawContourPlot(x, getResultY(x));
        DrawPlot.drawSurfacePlot(x, getResultY(x));
    }

    private List<Double> getResultY(List<Double> x) {
        List<Double> result = new ArrayList<>();
        x.forEach(x1 -> {
            sum = updateSum(x1);
            result.add(sum/2);
        });
        return result;
    }

    private Double updateSum(Double x) {
        return (x*x*x*x)-(16*x*x)+(5*x);//x^4 -16*x^2 + 5*x;
    }
}
