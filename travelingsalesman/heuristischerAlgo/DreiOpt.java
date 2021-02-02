package travelingsalesman.heuristischerAlgo;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author Stefan Hasler
 */
public class DreiOpt {
    /**
     *
     * @param matrix Matrix welche die Distanzen enthält
     * @param maxZeitMilli Maximale Zeit welche der Alg brauchen darf in millisekunden
     * @param routeUebergeben Die Route welche der Alg verbesseren soll
     * @return Distanz welche der Alg berechnet
     *
     *3-Opt nimmt 3 punkte und macht jede Kombination dieser Punkte, setzt diese dann in die Route ein und vergleicht sie mit der alten Route
     * Die schlechter der beiden Routen wird verworfen
     */
    public double start(Matrix matrix, long maxZeitMilli, ArrayList<Ort> routeUebergeben){
        long start = System.currentTimeMillis();        //Start zeit

        int orteAnzahl = (int) matrix.getMatrixSize();
        double distance=0;
        double shortestDistance=Double.MAX_VALUE;

        //array mit den ganzen Orten (da wird die route gespeichert)
        Ort[] route = new Ort[orteAnzahl];

        route = routeUebergeben.toArray(new Ort[routeUebergeben.size()]);

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

                }
                else
                    rCopy = route.clone();
                distance = 0;
                swap3P(rCopy, p1, p2 ,p3, list3P);
            }
        }
        return shortestDistance;
    }
    /**
     *
     * @param route Die momenaten Route
     * @param p1 Erster Index
     * @param p2 Zweiter Index
     * @param p3 Dritter Index
     * @param list3P die Orte in einer Arraylist
     * Macht die nächste logische Lexographische Folge der 3 Punkte
     *
     *       
     */
    private void swap3P(Ort[] route, int p1, int p2, int p3, ArrayList<Ort> list3P){
        naechstesLexicographicOrder(list3P);

        route[p1] = list3P.get(0);
        route[p2] = list3P.get(1);
        route[p3] = list3P.get(2);

    }

    /**
     *
     * @param array Array welches verändert werden soll
     *              Es mach die Lexographische Reihnfolge
     *              des Übergebenen Arrays d.H.:
     *              123 -> 132
     *              132 -> 213
     *              213 -> 231
     *              231 -> 312
     *              312 -> 321
     *              Aber mit Orten
     */
    public static void naechstesLexicographicOrder(ArrayList<Ort> array){
        //https://www.quora.com/What-is-lexicographic-order?share=1 von der Seite
        if (array.size() <= 1) return;
        int last = array.size() - 2;

        //Finde das größte x damit P[x]<P[x+1].
        while (last >= 0) {
            if (array.get(last).getIndex() < array.get(last + 1).getIndex()) {
                break;
            }
            last--;
        }

        if (last < 0) return;

        int nextGreater = array.size() - 1;

        // Finde das größte y damit P[x]<P[y].
        for (int i = array.size() - 1; i > last; i--) {
            if (array.get(i).getIndex() > array.get(last).getIndex()) {
                nextGreater = i;
                break;
            }
        }

        //Tausche P[x] und P[y].
        array = swap(array, nextGreater, last);

        //Reverse P[x+1 .. n].
        array = reverse(array, last + 1, array.size() - 1);

        //Ende
        return;
    }

    //Function, was 2 indexe von array tauscht
    /**
     *
     * @param array arraylist mit Orten
     * @param nextGreater index1
     * @param last  index2
     * @return geswapped Arraylist
     *
     * Dreht 2 indexe um mit einen Simplen Dreieckstausch
     */
    public static ArrayList<Ort> swap(ArrayList<Ort> array, int nextGreater, int last) {
        Ort temp = array.get(nextGreater);
        array.set(nextGreater, array.get(last) );
        array.set(last, temp);

        return array;
    }

    //Funktion das einen bestimmten Teil vom Array reversed
    /**
     *
     * @param array arraylist mit Orten
     * @param nextGreater die Nächst höhere zahl
     * @param last  Die letze zahl
     * @return reverste Arraylist
     * Dreht die übergeben Arraylist simpel um
     */
    public static ArrayList<Ort> reverse(ArrayList<Ort> array, int nextGreater, int last) {
        while (nextGreater < last) {
            Ort temp = array.get(nextGreater);
            array.set(nextGreater++, array.get(last));
            array.set(last--, temp);
        }

        return array;
    }
}
