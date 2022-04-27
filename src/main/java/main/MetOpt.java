package main;

import com.github.sh0nk.matplotlib4j.NumpyUtils;
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
        okresowa.implementation(xForOkresowa);

        //ALGORYTM BEZGRADIENTOWY

//        //STYBLINSKI TANG
//        Double x1 = -5D;
//        Double x2 = -5D;
//        Double poprzedniaWartosc = styblinskiTang.calculateValueInSpecificPoint(List.of(-5D,-5D), 2);
//        Double minValue
//                = styblinskiTang.calculateValueInSpecificPoint(List.of(-5D,-5D), 2);
//        Double krokProbny = 0.3;
//        Double krokRoboczy = 0.1;
//        Double tolerancja = 0.001;
        //OKRESOWA
        Double x1 = 0D;
        Double x2 = 0D;
        Double poprzedniaWartosc = okresowa.calculateValueInSpecificPoint(List.of(-10D,-10D), 2);
        Double minValue
                = okresowa.calculateValueInSpecificPoint(List.of(-10D,-10D), 2);
        Double krokProbny = 0.1;
        Double krokRoboczy = 0.025;
        Double tolerancja = 0.0001;


        List<Double> wektor1 = new ArrayList<>();
        wektor1.add(1D);
        wektor1.add(0D);
        List<Double> wektor2 = new ArrayList<>();
        wektor2.add(0D);
        wektor2.add(1D);
        List<Double> wektor3 = new ArrayList<>();
        wektor3.add(-1D);
        wektor3.add(0D);
        List<Double> wektor4 = new ArrayList<>();
        wektor4.add(0D);
        wektor4.add(-1D);

        //Krok PROBNY
        while(sqrt((minValue - poprzedniaWartosc)*(minValue - poprzedniaWartosc)) > tolerancja) {
            poprzedniaWartosc = minValue;
            List<Double> calculatedValue = findMinValue(minValue, wektor1, wektor2, wektor3, wektor4, krokProbny, x1, x2);
            if (calculatedValue.get(0).equals(minValue)) {
                    krokProbny = krokProbny / 2;
                    krokRoboczy = krokRoboczy / 2;
            } else {
                //KROK ROBOCZY
                Double valueInKrokRoboczy = 10000D;
                if (calculatedValue.get(1) == 1D) {
                    x1 = x1 + krokProbny * wektor1.get(0);
                    x2 = x2 + krokProbny * wektor1.get(1);
                    valueInKrokRoboczy = valueInWektor(minValue, wektor1, krokRoboczy, x1, x2);
                    x1 = x1 + krokRoboczy * wektor1.get(0);
                    x2 = x2 + krokRoboczy * wektor1.get(1);
                }
                if (calculatedValue.get(1) == 2D) {
                    x1 = x1 + krokProbny * wektor2.get(0);
                    x2 = x2 + krokProbny * wektor2.get(1);
                    valueInKrokRoboczy = valueInWektor(minValue, wektor2, krokRoboczy, x1, x2);
                    x1 = x1 + krokRoboczy * wektor2.get(0);
                    x2 = x2 + krokRoboczy * wektor2.get(1);
                }
                if (calculatedValue.get(1) == 3D) {
                    x1 = x1 + krokProbny * wektor3.get(0);
                    x2 = x2 + krokProbny * wektor3.get(1);
                    valueInKrokRoboczy = valueInWektor(minValue, wektor3, krokRoboczy, x1, x2);
                    x1 = x1 + krokRoboczy * wektor3.get(0);
                    x2 = x2 + krokRoboczy * wektor3.get(1);
                }
                if (calculatedValue.get(1) == 4D) {
                    x1 = x1 + krokProbny * wektor4.get(0);
                    x2 = x2 + krokProbny * wektor4.get(1);
                    valueInKrokRoboczy = valueInWektor(minValue, wektor4, krokRoboczy, x1, x2);
                    x1 = x1 + krokRoboczy * wektor4.get(0);
                    x2 = x2 + krokRoboczy * wektor4.get(1);
                }
                    minValue = valueInKrokRoboczy;
            }
        }
        System.out.println("Minimalna wartosc to: "+minValue);



        // Value in specific point in any dimension can be calculated with following functions
        // okresowa.calculateValueInSpecificPoint(List<Double> listOfXValuesInSpecificPoint, int dimensions);
        // styblinskiTang.calculateValueInSpecificPoint(List<Double> listOfXValuesInSpecificPoint, int dimensions);
        }


    private static Double valueInWektor(Double minValue, List<Double> wektor, Double krokProbny, Double x1, Double x2) throws Exception {
            StyblinskiTang styblinskiTang = new StyblinskiTang();
            Okresowa okresowa = new Okresowa();

      //  Double wartoscKroku = styblinskiTang.calculateValueInSpecificPoint(List.of(x1 + wektor.get(0) * krokProbny, x2 + wektor.get(1) * krokProbny), 2);
        Double wartoscKroku = okresowa.calculateValueInSpecificPoint(List.of(x1 + wektor.get(0) * krokProbny, x2 + wektor.get(1) * krokProbny), 2);
        return wartoscKroku;
    }

    private static List<Double> findMinValue(Double minValue, List<Double> wektor1,List<Double> wektor2,List<Double> wektor3,List<Double> wektor4, Double krokProbny, Double x1, Double x2) throws Exception {

        Double value = valueInWektor(minValue, wektor1, krokProbny, x1, x2);
        if(value<minValue) {
            return List.of(value, 1D);
        }

        value = valueInWektor(minValue, wektor2, krokProbny, x1, x2);
        if(value<minValue) {
            return List.of(value, 2D);
        }

        value = valueInWektor(minValue, wektor3, krokProbny, x1, x2);
        if(value<minValue) {
            return List.of(value, 3D);
        }

        value = valueInWektor(minValue, wektor4, krokProbny, x1, x2);
        if(value<minValue) {
            return List.of(value, 4D);
        }

        else return List.of(minValue, 0D);
    }
}
