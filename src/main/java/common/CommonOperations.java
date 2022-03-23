package common;

import com.github.sh0nk.matplotlib4j.NumpyUtils;

import java.util.List;

public class CommonOperations {
    public double[][] returnMappedZValueInPrimitiveDoubleTable(List<Double> yy, Double additionalParameter){
        return f1(mapListObjectDoubleToArrayPrimitiveDouble(yy), mapListObjectDoubleToArrayPrimitiveDouble(yy), additionalParameter);
    }

    public List<List<Double>> returnMappedZValueInDoubleList(List<Double> yy, Double additionalParameter){
        NumpyUtils.Grid<Double> grid = NumpyUtils.meshgrid(yy, yy);
        return grid.calcZ((xi, yj) -> (xi + yj + additionalParameter));
    }

    public double[] mapListObjectDoubleToArrayPrimitiveDouble(List<Double> valToMap){
        double[] y = new double[valToMap.size()];
        for(int i = 0; i < valToMap.size(); i++) y[i] = valToMap.get(i);
        return y;
    }

    private double f1(double x, double y, double additionalParameter) {
        return x + y + additionalParameter;
    }

    private double[][] f1(double[] x, double[] y, double additionalParameter) {
        double[][] z = new double[y.length][x.length];
        for (int i = 0; i < x.length; i++)
            for (int j = 0; j < y.length; j++)
                z[j][i] = f1(x[i], y[j], additionalParameter);
        return z;
    }
}
