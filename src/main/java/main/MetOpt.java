package main;

import com.github.sh0nk.matplotlib4j.NumpyUtils;
import com.github.sh0nk.matplotlib4j.PythonExecutionException;
import functions.Okresowa;
import functions.StyblinskiTang;

import java.io.IOException;

import java.util.List;

public class MetOpt {
    public static void main(String[] args) throws IOException, PythonExecutionException {
        List<Double> x = NumpyUtils.arange(-5, 5, 0.15);
            StyblinskiTang styblinskiTang = new StyblinskiTang();
        styblinskiTang.implementation(x);
//        Okresowa okresowa = new Okresowa();
//        okresowa.implementation(x);
        }
}
