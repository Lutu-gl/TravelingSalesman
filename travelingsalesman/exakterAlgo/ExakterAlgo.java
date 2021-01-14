package travelingsalesman.exakterAlgo;


import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

//Wahl eines exakten Algorithmus und beispielhafte Anwendung an einem kleinen Beispiel (Implementation in Java)
public class ExakterAlgo {

    public static void main(String[] args) {
        Matrix matrix = new Matrix();
        //matrix.readMatrixFromFile("C:\\Users\\Megaport\\IdeaProjects\\TravelingSalesman\\travelingsalesman\\exakterAlgo\\MatrixFile.csv");
        matrix.readMatrixFromFile("E:\\Oberschule 4\\Technologie und Planung\\TravelingSalesman\\src\\travelingsalesman\\exakterAlgo\\MatrixFile.csv");
        matrix.printMatrix();

        int orteAnzahl = 4;
        double distance=0;
        double shortestDistance=Integer.MAX_VALUE;

        //Array mit Allen Orten erstellen:
        ArrayList<Ort> orte = new ArrayList<Ort>((int) matrix.getMatrixSize());
        for (int i = 0; i < matrix.getMatrixSize(); i++) {
            orte.add(new Ort());
        }

        for (int i = 0; i < matrix.getMatrixSize(); i++) {
            orte.get(i).setIndex(i);
        }

        ArrayList<Integer> arrayList = new ArrayList<Integer>( Arrays.asList(0,1,2,3));


        for (int i = 0; i < 6; i++) {       //(orteAnzahl-1)!     //Für alle routen
            distance=0;
            for(int j=0; j < orteAnzahl-1; j++){        //Für alle Orte
                distance += matrix.getDistance( orte.get(arrayList.get(j)).getIndex(), orte.get(arrayList.get(j+1)).getIndex()  );
                //System.out.println( matrix.getDistance( orte.get(arrayList.get(j)).getIndex(), orte.get(arrayList.get(j+1)).getIndex()  ) + " " + j);
            }
            distance += matrix.getDistance( orte.get(arrayList.get(orteAnzahl-1)).getIndex(), orte.get(arrayList.get(0)).getIndex());


            shortestDistance = Math.min(distance, shortestDistance);
            System.out.println(arrayList);
            System.out.println("Ausgerechnete distance="+distance + " KrüzesteDistance="+shortestDistance );

            findNextPermutation(arrayList);
        }
    }







    public static boolean findNextPermutation(ArrayList<Integer> data) {
// If the given dataset is empty
// or contains only one element
// next_permutation is not possible
        if (data.size() <= 1) return false;
        int last = data.size() - 2;

// find the longest non-increasing
// suffix and find the pivot
        while (last >= 0) {
            if (data.get(last) < data.get(last + 1)) {
                break;
            }
            last--;
        }

// If there is no increasing pair
// there is no higher order permutation
        if (last < 0) return false;

        int nextGreater = data.size() - 1;

// Find the rightmost successor
// to the pivot
        for (int i = data.size() - 1; i > last; i--) {
            if (data.get(i) > data.get(last))
            {
                nextGreater = i;
                break;
            }
        }

// Swap the successor and
// the pivot
        data = swap(data, nextGreater, last);

// Reverse the suffix
        data = reverse(data, last + 1, data.size() - 1);

// Return true as the
// next_permutation is done
        return true;
    }

    public static ArrayList<Integer> swap(ArrayList<Integer> data, int left, int right) {
// Swap the data
        int temp = data.get(left);
        data.set(left, data.get(right));
        data.set(right, temp);

// Return the updated array
        return data;
    }

    // Function to reverse the sub-array
// starting from left to the right
// both inclusive
    public static ArrayList<Integer> reverse(ArrayList<Integer> data, int left, int right) {
// Reverse the sub-array
        while (left < right) {
            int temp = data.get(left);
            data.set(left++,
                    data.get(right));
            data.set(right--, temp);
        }

// Return the updated array
        return data;
    }







    /*
    //https://www.quora.com/How-would-you-explain-an-algorithm-that-generates-permutations-using-lexicographic-ordering + https://www.youtube.com/watch?v=goUlyp4rwiU&t=4s&ab_channel=TheCodingTrain
    public static void lexicographic(int[] array){
        int largestI = -1;

        //Step 1
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] < array[i+1]){
                largestI = i;
            }
        }
        if (largestI == -1){
            //System.out.println("Stopp largestI = -1");
            for (int i : array) {
                System.out.print(i);
            }
            System.out.println();
            return;
        }

        //STEP 2:
        int largestJ = -1;

        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] < array[largestI]){
                largestJ = i;
            }
        }

        //STEP 3:
        int temp = array[largestI];
        array[largestI] = array[largestJ];
        array[largestJ] = temp;

        //STEP 4:
        int[] splitarray = new int[4];

        int count=0;
        for (int i = array.length-1; i > largestI+1; i--) {
            splitarray[count++] = array[i];
        }
        count=0;
        for (int i =  largestI+1; i < array.length; i++) {
            array[i] = splitarray[count++];
        }

        for (int i : array) {
            System.out.print(i);
        }
        System.out.println();
        //return array;
    }

     */
}