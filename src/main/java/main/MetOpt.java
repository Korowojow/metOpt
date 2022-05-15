package main;

import com.github.sh0nk.matplotlib4j.NumpyUtils;
import common.Bezgradientowa;
import functions.Okresowa;
import functions.StyblinskiTang;

import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.*;

public class MetOpt {
    public static void main(String[] args) throws Exception {
        List<Double> xForStyblinskiTang = NumpyUtils.arange(-5, 5, 0.15);
        List<Double> xForOkresowa = NumpyUtils.arange(-10, 10, 0.5);

        StyblinskiTang styblinskiTang = new StyblinskiTang();
        Okresowa okresowa = new Okresowa();

        // styblinskiTang.implementation(xForStyblinskiTang);
        // okresowa.implementation(xForOkresowa);
        Bezgradientowa bezgradientowa = new Bezgradientowa();
        bezgradientowa.metBezgrad();
    }
}
