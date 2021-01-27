package travelingsalesman.heuristischerAlgo;


import java.util.ArrayList;

public class DreiOpt {
    public double start(Matrix matrix, long maxZeitMilli){
        long start = System.currentTimeMillis();        //Start zeit

        int orteAnzahl = (int) matrix.getMatrixSize();
        double distance=0;
        double shortestDistance=Integer.MAX_VALUE;

        //array mit den ganzen Orten (da wird die route gespeichert)

        Ort[] route = new Ort[orteAnzahl];

        for (int i = 0; i < route.length; i++) {
            route[i] = new Ort(i);
        }
        //int i=0;

        while(System.currentTimeMillis() - start < maxZeitMilli) {  //end Zeit
            //Distanz ausrechnen
            for(int j=0; j < orteAnzahl-1; j++){
                distance += matrix.getDistance( route[j].getIndex(), route[j+1].getIndex()  );
            }
            distance += matrix.getDistance( route[orteAnzahl-1].getIndex(), route[0].getIndex());

            shortestDistance = Math.min(distance, shortestDistance);

            //2 Orte tauschen   (Kleines Problem daran ist, dass Math.random und auch random.nextInt nicht hinterherkommt mit der geschwindikeit des Programms und öffters die selbe random zahl gibt.)
            swap(route, (int) (Math.random() * orteAnzahl), (int) (Math.random()*orteAnzahl));
            //Random random = new Random();
            //swap(route, random.nextInt(orteAnzahl), random.nextInt(orteAnzahl));

            //System.out.println(i +" = " + route[0].getIndex()+ " " + route[1].getIndex() + " " +route[2].getIndex() + " " +route[3].getIndex() +" " + route[4].getIndex());
            //System.out.println("Vergangene Zeit: " + (System.currentTimeMillis() - start));
            distance=0;
            //i++;
        }
        //System.out.println("switches="+i);
        return shortestDistance;
    }

    private void swap(Ort[] route, int i1, int i2){
        Ort temp = route[i1];
        route[i1] = route[i2];
        route[i2] = temp;
    }

    //Funktioniert in der Theorie :)
    public static void NaechstesLexicographicOrder(ArrayList<Ort> array){
        if (array.size() <= 1) return;
        int last = array.size() - 2;

        //Find the largest x such that P[x]<P[x+1].
        while (last >= 0) {
            if (array.get(last).getIndex() < array.get(last + 1).getIndex()) {
                break;
            }
            last--;
        }

        if (last < 0) return;

        int nextGreater = array.size() - 1;

        // Find the largest y such that P[x]<P[y].
        for (int i = array.size() - 1; i > last; i--) {
            if (array.get(i).getIndex() > array.get(last).getIndex()) {
                nextGreater = i;
                break;
            }
        }

        //Swap P[x] and P[y].
        array = swap(array, nextGreater, last);

        //Reverse P[x+1 .. n].
        array = reverse(array, last + 1, array.size() - 1);

        //Ende
        return;
    }

    //Function, that swaps a 2 indexes of a given array
    public static ArrayList<Ort> swap(ArrayList<Ort> array, int nextGreater, int last) {
        Ort temp = array.get(nextGreater);
        array.set(nextGreater, array.get(last) );
        array.set(last, temp);

        return array;
    }

    //Function that reverses a part of a given array
    public static ArrayList<Ort> reverse(ArrayList<Ort> array, int nextGreater, int last) {
        while (nextGreater < last) {
            Ort temp = array.get(nextGreater);
            array.set(nextGreater++, array.get(last));
            array.set(last--, temp);
        }

        return array;
    }
}
