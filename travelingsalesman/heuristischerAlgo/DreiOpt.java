package travelingsalesman.heuristischerAlgo;


import java.util.ArrayList;
import java.util.Comparator;

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

        ArrayList<Ort> list3P = new ArrayList<Ort>();
        list3P.add(null);
        list3P.add(null);
        list3P.add(null);
        while(System.currentTimeMillis() - start < maxZeitMilli) {  //end Zeit

            Ort[] rCopy = route.clone();

            int p1=0,p2=0,p3=0;
            while(p1 == p2 || p2 == p3 || p3 == p1)
            {
                p1 = (int) (Math.random() * orteAnzahl);
                p2 = (int) (Math.random() * orteAnzahl);
                p3 = (int) (Math.random() * orteAnzahl);
            }
            System.out.println("indexes to change: " + p1 + ","+ p2 +","+ p3);
            list3P.set(0,route[p1]);
            list3P.set(1,route[p2]);
            list3P.set(2,route[p3]);

            //Intellij felxt
            list3P.sort(Comparator.comparingInt(Ort::getIndex));


            for(int h = 0; h != 6; h++)
            {
                for(int j=0; j < orteAnzahl-1; j++){
                    distance += matrix.getDistance( rCopy[j].getIndex(), rCopy[j+1].getIndex()  );
                }
                distance += matrix.getDistance( rCopy[orteAnzahl-1].getIndex(), rCopy[0].getIndex());
                if(distance < shortestDistance)
                {
                    route = rCopy.clone();
                    shortestDistance = distance;
                    System.out.println("array wird jetzt verändert");
                    for (Ort e : route)
                    {
                        System.out.print(e.getIndex() + ",");
                    }

                }
                else
                    rCopy = route.clone();
                System.out.println(distance);
                distance = 0;
                swap3P(rCopy, p1, p2 ,p3, list3P);
            }

            System.out.println(shortestDistance);
        }
        return shortestDistance;
    }

    private void swap3P(Ort[] route, int p1, int p2, int p3, ArrayList<Ort> list3P){
        naechstesLexicographicOrder(list3P);

        for (Ort e :
                list3P)
        {
            System.out.print(e.getIndex() + ",");
        }
        route[p1] = list3P.get(0);
        route[p2] = list3P.get(1);
        route[p3] = list3P.get(2);

    }

    //Funktioniert in der Theorie :)
    public static void naechstesLexicographicOrder(ArrayList<Ort> array){
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
