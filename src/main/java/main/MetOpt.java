package main;

import com.github.sh0nk.matplotlib4j.NumpyUtils;
import com.github.sh0nk.matplotlib4j.PythonExecutionException;
import functions.Okresowa;
import functions.StyblinskiTang;

import java.io.IOException;

import java.util.List;

public class MetOpt {
    public static void main(String[] args) throws IOException, PythonExecutionException {
        List<Double> xForStyblinskiTang = NumpyUtils.arange(-5, 5, 0.15);
        List<Double> xForOkresowa = NumpyUtils.arange(-10, 10, 0.5);

        StyblinskiTang styblinskiTang = new StyblinskiTang();
        Okresowa okresowa = new Okresowa();

        styblinskiTang.implementation(xForStyblinskiTang);
        okresowa.implementation(xForOkresowa);

        // Value in specific point in any dimension can be calculated with following functions
        // okresowa.calculateValueInSpecificPoint(List<Double> listOfXValuesInSpecificPoint, int dimensions);
        // styblinskiTang.calculateValueInSpecificPoint(List<Double> listOfXValuesInSpecificPoint, int dimensions);
        }
}
