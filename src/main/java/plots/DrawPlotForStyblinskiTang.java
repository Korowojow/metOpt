package plots;

import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PythonExecutionException;
import com.github.sh0nk.matplotlib4j.builder.ContourBuilder;

import java.io.IOException;
import java.util.List;

import common.CommonOperations;
import org.math.plot.*;

import javax.swing.*;


public class DrawPlotForStyblinskiTang {

    public void drawPlots(List<Double> x, List<Double> y) throws IOException, PythonExecutionException {
        drawScatterPlot(x, y);
        drawContourPlot(x, y);
        drawSurfacePlot(x, y);
    }

    private void drawScatterPlot(List<Double> x, List<Double> y) throws IOException, PythonExecutionException {
        Plot plt = Plot.create();
        plt.plot().add(x, y, "o").label("StyblinskiTang");
        plt.legend().loc("upper right");
        plt.title("scatter");
        plt.show();
    }

    private void drawContourPlot(List<Double> x, List<Double> y) throws IOException, PythonExecutionException {
        CommonOperations commonOperations = new CommonOperations();
        List<List<Double>> zCalced = commonOperations.returnMappedZValueInDoubleList(y, 0D);

        Plot plt = Plot.create();
        ContourBuilder contour = plt.contour().add(x, x, zCalced);
        plt.clabel(contour)
                .inline(true)
                .fontsize(10);
        plt.title("contour - StyblinskiTang");
        plt.show();
    }

    private void drawSurfacePlot(List<Double> xx, List<Double> yy){
        CommonOperations commonOperations = new CommonOperations();

        double[][] z1 = commonOperations.returnMappedZValueInPrimitiveDoubleTable(yy, 0D);
        double[] x1 = commonOperations.mapListObjectDoubleToArrayPrimitiveDouble(xx);

        Plot3DPanel plot = new Plot3DPanel("SOUTH");

        plot.addGridPlot("StyblinskiTang", x1, x1, z1);

        JFrame frame = new JFrame("a plot panel");
        frame.setSize(600, 600);
        frame.setContentPane(plot);
        frame.setVisible(true);

    }

}
