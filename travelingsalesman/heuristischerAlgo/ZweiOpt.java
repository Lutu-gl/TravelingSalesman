package travelingsalesman.heuristischerAlgo;

import java.util.ArrayList;
import java.util.SplittableRandom;

/**
 * @author Lutu
 * In der Klasse ZweiOpt ist der 2-Opt Algorithmus programmiert.
 * Der Algorithmus wird so lange ausgeführt bis die angegebene Zeit erreicht ist und gibt die gerechnete Distanz zurück.
 */

public class ZweiOpt {
    /**
     * @param matrix Ist ein Matrix Object (Matrix)
     * @param maxZeitMilli: Ist die Zeit wielange ZweiOpt Arbeiten soll (long)
     * @param route: Ist die route die zum Beispiel von NearestNeighbour schon generiert wurde (ArrayList<Ort>)
     * @return die ausgerechnete Distanz. (double)
     *
     * startet den 2Opt Algorithmus auf eine gegebene matrix und gibt die kleinste ausgerechnete Distanz zurück.
     * Das Programm ist fertig, wenn die maximale übergebene Distanz erreicht wurde.
     */
    public double start(Matrix matrix, long maxZeitMilli, ArrayList<Ort> route){
        long start = System.currentTimeMillis();        //Start zeit

        int orteAnzahl = (int) matrix.getMatrixSize();
        double distance=0;
        double shortestDistance=Double.MAX_VALUE;

        //array mit den ganzen Orten (da wird die route gespeichert)
        SplittableRandom random = new SplittableRandom();       //Ein schnelleres Random Generator als new Random

        while(System.currentTimeMillis() - start < maxZeitMilli) {  //end Zeit
            //Distanz ausrechnen
            for(int j=0; j < orteAnzahl-1; j++){
                distance += matrix.getDistance( route.get(j).getIndex(), route.get(j + 1).getIndex()  );
            }
            distance += matrix.getDistance( route.get(orteAnzahl - 1).getIndex(), route.get(0).getIndex());

            //Kürzeste Distanz speichern
            shortestDistance = Math.min(distance, shortestDistance);

            //2 Orte tauschen
            swap(route, random.nextInt(orteAnzahl), random.nextInt(orteAnzahl));

            //Distanz resetten
            distance=0;
        }

        //Kürzeste Distanz zurückgeben
        return shortestDistance;
    }

    /**
     * @param route: Route ist das Orte array das verwendet wird.
     * @param i1: Der Index den man tauschen möchte.
     * @param i2: Der 2. Index den man tauschen möchte.
     */
    private void swap(ArrayList<Ort> route, int i1, int i2){
        Ort temp = route.get(i1);
        route.set(i1, route.get(i2));
        route.set(i2, temp);
    }
}
