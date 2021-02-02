package travelingsalesman.exakterAlgo;


import java.util.ArrayList;
import java.util.Arrays;

//Wahl eines exakten Algorithmus und beispielhafte Anwendung an einem kleinen Beispiel (Implementation in Java)
public class ExakterAlgo {

    public static void main(String[] args) {
        Matrix matrix = new Matrix();
        //matrix.readMatrixFromFile("C:\\Users\\Megaport\\IdeaProjects\\TravelingSalesman\\travelingsalesman\\exakterAlgo\\MatrixFile5Orte.csv");
        matrix.readMatrixFromFile("F:\\Oberschule 4\\Technologie und Planung\\TravelingSalesman\\src\\travelingsalesman\\MatrixFile17Orte.csv"); //read the matrix
        matrix.printMatrix();
        System.out.println(matrix.getMatrixSize());

        int orteAnzahl = (int) matrix.getMatrixSize();
        double distance=0;
        double shortestDistance=Double.MAX_VALUE;

        //array with Orte
        ArrayList<Ort> orte = new ArrayList<Ort>((int) matrix.getMatrixSize());

        for (int i = 0; i < matrix.getMatrixSize(); i++) {
            orte.add(new Ort(i));
        }


        //routen liste restellen
        ArrayList<Integer> route = new ArrayList<Integer>( orteAnzahl );
        for (int i = 0; i < orteAnzahl; i++) {
            route.add(i);
        }

        System.out.println(orteAnzahl + " " + factorial(orteAnzahl-1));

        for (long i = 0; i < factorial(orteAnzahl-1); i++) {       //(orteAnzahl-1)!     //For all possible routes
            distance=0;
            for(int j=0; j < orteAnzahl-1; j++){        //for all orte
                distance += matrix.getDistance( orte.get(route.get(j)).getIndex(), orte.get(route.get(j+1)).getIndex()  );  //calc distance
            }
            distance += matrix.getDistance( orte.get(route.get(orteAnzahl-1)).getIndex(), orte.get(route.get(0)).getIndex());   //distance from last to beginning

            shortestDistance = Math.min(distance, shortestDistance);
            //System.out.println(route);
            //System.out.println("Ausgerechnete distance="+distance + " KrüzesteDistance="+shortestDistance );

            NextLexicographicOrder(route);      //get nextlexiographicOrder
        }

        System.out.println("\nKürzeste Distanz: " + shortestDistance);
    }

    //calcolates the factorial of a given number
    public static long factorial(int number) {
        long result = 1;

        for (long factor = 2; factor <= number; factor++) {
            result *= factor;
        }

        return result;
    }

    //Die Funktion gibt die nächste Logische reiheinfolge von einem array hinaus. Besser am Beispiel erklärt:
    //0 1 2 3 -> 0 1 3 2 -> 0 2 1 3 -> 0 2 3 1...
    //https://www.quora.com/How-would-you-explain-an-algorithm-that-generates-permutations-using-lexicographic-ordering + https://www.youtube.com/watch?v=goUlyp4rwiU&t=4s&ab_channel=TheCodingTrain
    public static void NextLexicographicOrder(ArrayList<Integer> array){
        if (array.size() <= 1) return;
        int last = array.size() - 2;

        //Find the largest x such that P[x]<P[x+1].
        while (last >= 0) {
            if (array.get(last) < array.get(last + 1)) {
                break;
            }
            last--;
        }

        if (last < 0) return;

        int nextGreater = array.size() - 1;

        // Find the largest y such that P[x]<P[y].
        for (int i = array.size() - 1; i > last; i--) {
            if (array.get(i) > array.get(last)) {
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
    public static ArrayList<Integer> swap(ArrayList<Integer> array, int nextGreater, int last) {
        int temp = array.get(nextGreater);
        array.set(nextGreater, array.get(last) );
        array.set(last, temp);

        return array;
    }

    //Function that reverses a part of a given array
    public static ArrayList<Integer> reverse(ArrayList<Integer> array, int nextGreater, int last) {
        while (nextGreater < last) {
            int temp = array.get(nextGreater);
            array.set(nextGreater++, array.get(last));
            array.set(last--, temp);
        }

        return array;
    }
}