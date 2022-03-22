package plots;

import com.github.sh0nk.matplotlib4j.NumpyUtils;
import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PythonExecutionException;
import com.github.sh0nk.matplotlib4j.builder.ContourBuilder;

import java.io.IOException;
import java.util.List;
import org.math.plot.*;

import javax.swing.*;


public class DrawPlot {

    public static void drawScatterPlot(List<Double> x, List<Double> y) throws IOException, PythonExecutionException {
        Plot plt = Plot.create();
        plt.plot().add(x, y, "o").label("SyblinskiTang");
        plt.legend().loc("upper right");
        plt.title("scatter");
        plt.show();
    }

    public static void drawContourPlot(List<Double> x, List<Double> y) throws IOException, PythonExecutionException {
        NumpyUtils.Grid<Double> grid = NumpyUtils.meshgrid(y, y);

        List<List<Double>> zCalced = grid.calcZ((xi, yj) -> (xi + yj));

        Plot plt = Plot.create();
        ContourBuilder contour = plt.contour().add(x, x, zCalced);
        plt.clabel(contour)
                .inline(true)
                .fontsize(10);
        plt.title("contour");
        plt.show();
    }

    public static void drawSurfacePlot(List<Double> xx, List<Double> yy){

        double[] x = new double[xx.size()];
        for(int i = 0; i < xx.size(); i++) x[i] = xx.get(i);
        double[] y = new double[yy.size()];
        for(int i = 0; i < yy.size(); i++) y[i] = yy.get(i);

        double[][] z1 = f1(y, y);

        Plot3DPanel plot = new Plot3DPanel("SOUTH");

        plot.addGridPlot("myplot", x, x, z1);

        JFrame frame = new JFrame("a plot panel");
        frame.setSize(600, 600);
        frame.setContentPane(plot);
        frame.setVisible(true);

    }

    public static double f1(double x, double y) {
        return x + y;
    }

    public static double[][] f1(double[] x, double[] y) {
        double[][] z = new double[y.length][x.length];
        for (int i = 0; i < x.length; i++)
            for (int j = 0; j < y.length; j++)
                z[j][i] = f1(x[i], y[j]);
        return z;
    }

}
