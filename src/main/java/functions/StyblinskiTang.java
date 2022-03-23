package functions;

import com.github.sh0nk.matplotlib4j.PythonExecutionException;
import plots.DrawPlotForStyblinskiTang;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StyblinskiTang {

    public void implementation(List<Double> x) throws IOException, PythonExecutionException {
        DrawPlotForStyblinskiTang drawPlotForStyblinskiTang = new DrawPlotForStyblinskiTang();
        drawPlotForStyblinskiTang.drawPlots(x, getResultY(x));
    }

    private List<Double> getResultY(List<Double> x) {
        List<Double> result = new ArrayList<>();
        x.forEach(x1 -> {
            result.add(updateSum(x1)/2);
        });
        return result;
    }

    private Double updateSum(Double x) {
        return (x*x*x*x)-(16*x*x)+(5*x);//x^4 -16*x^2 + 5*x;
    }
}
