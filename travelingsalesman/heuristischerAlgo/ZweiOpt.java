package travelingsalesman.heuristischerAlgo;


import java.util.ArrayList;
import java.util.Random;
import java.util.SplittableRandom;

public class ZweiOpt {

    public double start(Matrix matrix, long maxZeitMilli, ArrayList<Ort> route){
        long start = System.currentTimeMillis();        //Start zeit

        int orteAnzahl = (int) matrix.getMatrixSize();
        double distance=0;
        double shortestDistance=Double.MAX_VALUE;
        //route = null;

        //array mit den ganzen Orten (da wird die route gespeichert)
        // route = new ArrayList<Ort>(orteAnzahl);

        for (int i = 0; i < route.size(); i++) {
            //route.set(i, new Ort(i));


        }
        //int i=0;
        SplittableRandom random = new SplittableRandom();

        while(System.currentTimeMillis() - start < maxZeitMilli) {  //end Zeit
            //Distanz ausrechnen
            for(int j=0; j < orteAnzahl-1; j++){
                distance += matrix.getDistance( route.get(j).getIndex(), route.get(j + 1).getIndex()  );
            }
            distance += matrix.getDistance( route.get(orteAnzahl - 1).getIndex(), route.get(0).getIndex());

            shortestDistance = Math.min(distance, shortestDistance);

            //2 Orte tauschen   (Kleines Problem daran ist, dass Math.random und auch random.nextInt nicht hinterherkommt mit der geschwindikeit des Programms und öffters die selbe random zahl gibt.)
            //swap(route, (int) (Math.random() * orteAnzahl), (int) (Math.random()*orteAnzahl));
            //swap(route, (int) (Math.random() * orteAnzahl), random.nextInt(orteAnzahl));

            swap(route, random.nextInt(orteAnzahl), random.nextInt(orteAnzahl));

            //System.out.println(i +" = " + route[0].getIndex()+ " " + route[1].getIndex() + " " +route[2].getIndex() + " " +route[3].getIndex() +" " + route[4].getIndex());
            //System.out.println("Vergangene Zeit: " + (System.currentTimeMillis() - start));
            distance=0;
            //i++;
        }
        //System.out.println("switches="+i);
        return shortestDistance;
    }

    private void swap(ArrayList<Ort> route, int i1, int i2){
        Ort temp = route.get(i1);
        route.set(i1, route.get(i2));
        route.set(i2, temp);
    }


}
