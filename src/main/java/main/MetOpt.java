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

//        styblinskiTang.implementation(xForStyblinskiTang);
//         okresowa.implementation(xForOkresowa);
        Bezgradientowa bezgradientowa = new Bezgradientowa();
        bezgradientowa.metBezgrad();
// ------------------------------------------------------
//                 METODA GRADIENTOWA
// -------------------------------------------------------
        //Punkt startowy
        Double x1 = 1.3D;
        Double x2 = 1.3D;
        //Dł kroku
        Double e = 0.001;
        // wsp redukcji
        Double a = 0.5;
        // dokladnosc wyznaczenia minimum
        Double epsilon = 0.01;
        // limit liczby redukcji
        Double k = 100D;
        List<Double> actualValues = List.of(x1, x2);
        int i = 0;

        //-------------------------------------
        //Algorytm - styblinski tang
        //-------------------------------------
//        while(sqrt((styblisnkiPochodna(x1)*styblisnkiPochodna(x1)+2*styblisnkiPochodna(x1)*styblisnkiPochodna(x2)+styblisnkiPochodna(x2)*styblisnkiPochodna(x2))
//                *(styblisnkiPochodna(x1)*styblisnkiPochodna(x1)+2*styblisnkiPochodna(x1)*styblisnkiPochodna(x2)+styblisnkiPochodna(x2)*styblisnkiPochodna(x2)))>epsilon) {
//            Double gradientX1Value = styblisnkiPochodna(x1);
//            Double gradientX2Value = styblisnkiPochodna(x2);
//            actualValues = calculateNewValue(x1, x2, e, gradientX1Value, gradientX2Value, k);
//            if(actualValues.get(0).equals(x1) && actualValues.get(1).equals(x2)) {
//                break;
//            }
//            x1 = actualValues.get(0);
//            x2 = actualValues.get(1);
//            i++;
//        }
        //-------------------------------------
        //Algorytm - okresowa
        //-------------------------------------
        while(sqrt((okresowaPochodna(x1)*okresowaPochodna(x1)+2*okresowaPochodna(x1)*okresowaPochodna(x2)+okresowaPochodna(x2)*okresowaPochodna(x2))
                *(okresowaPochodna(x1)*okresowaPochodna(x1)+2*okresowaPochodna(x1)*okresowaPochodna(x2)+okresowaPochodna(x2)*okresowaPochodna(x2)))>epsilon) {
            Double gradientX1Value = okresowaPochodna(x1);
            Double gradientX2Value = okresowaPochodna(x2);
            actualValues = calculateNewValue(x1, x2, e, gradientX1Value, gradientX2Value, k);
            if(actualValues.get(0).equals(x1) && actualValues.get(1).equals(x2)) {
                break;
            }
            x1 = actualValues.get(0);
            x2 = actualValues.get(1);
            i++;
        }
        // Double wynikKoncowy = styblinskiTang.calculateValueInSpecificPoint(List.of(x1,x2), 2);
        Double wynikKoncowy = okresowa.calculateValueInSpecificPoint(List.of(x1,x2), 2);
        System.out.println("METODA GRANDIETOWA OBLICZONO NASTEPUJACE MINIMUM " + wynikKoncowy + " w punkcie (" + x1 + ", " + x2 + ")");
        System.out.println("ILOSC PETLI " + i);
    }

    private static List<Double> calculateNewValue(Double x1, Double x2, Double e, Double gradientX1Value, Double gradientX2Value, Double limitRedukcjiKroku) throws Exception {
        StyblinskiTang styblinskiTang = new StyblinskiTang();
        Okresowa okresowa = new Okresowa();
        double i = 0D;
        while(i<limitRedukcjiKroku) {
            Double x1new = calculateNewValueOfX(x1, e, gradientX1Value);
            Double x2new = calculateNewValueOfX(x2, e, gradientX2Value);
            //Double newValue = styblinskiTang.calculateValueInSpecificPoint(List.of(x1new, x2new), 2);
            //Double oldValue = styblinskiTang.calculateValueInSpecificPoint(List.of(x1, x2), 2);
            Double newValue = okresowa.calculateValueInSpecificPoint(List.of(x1new, x2new), 2);
            Double oldValue = okresowa.calculateValueInSpecificPoint(List.of(x1, x2), 2);
            //
            if (newValue < oldValue) {
                return List.of(x1new, x2new);
            } else {
                e = e / 2;
                i++;
            }
        }
        return List.of(x1, x2);
    }

    private static Double calculateNewValueOfX(Double previousValue, Double e, Double gradientX2Value) {
        return previousValue - e * gradientX2Value;
    }

    private static Double okresowaPochodna(Double x) {
        return (2/10) * exp(-(x*x)) * x + 2 * sin(x) * cos(x);
    }

    private static Double styblisnkiPochodna(Double x) {
        return 2*x*x*x - 16 * x + (5/2);
    }
}
