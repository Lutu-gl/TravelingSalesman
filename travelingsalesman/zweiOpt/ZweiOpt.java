package travelingsalesman.zweiOpt;


import travelingsalesman.exakterAlgo.Ort;

import java.util.ArrayList;

public class ZweiOpt {

    public double start(Matrix matrix){

        int orteAnzahl = (int) matrix.getMatrixSize();
        double distance=0;
        double shortestDistance=Integer.MAX_VALUE;

        //array mit den ganzen Orten (da wird die route gespeichert)
        //ArrayList<travelingsalesman.exakterAlgo.Ort> route = new ArrayList<travelingsalesman.exakterAlgo.Ort>((int) matrix.getMatrixSize());
        Ort[] route = new Ort[orteAnzahl];

        for (int i = 0; i < route.length; i++) {
            //route.add(new Ort(i));
            route[i] = new Ort(i);
        }

        while(true) {

            //swap
            //tausche die Punkte
        }

        //return 77.44;
    }

    private void swap(Ort[] route, int i1, int i2){
        Ort temp = route[i1];
        route[i1] = route[i2];
        route[i2] = temp;
    }


}
