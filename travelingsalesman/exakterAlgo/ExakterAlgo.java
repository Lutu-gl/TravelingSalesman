package travelingsalesman.exakterAlgo;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

//Wahl eines exakten Algorithmus und beispielhafte Anwendung an einem kleinen Beispiel (Implementation in Java)
public class ExakterAlgo {

    public static void main(String[] args) {
        Matrix matrix = new Matrix();
        matrix.readMatrixFromFile("E:\\Oberschule 4\\Technologie und Planung\\TravelingSalesman\\src\\travelingsalesman\\exakterAlgo\\MatrixFile.csv");
        matrix.printMatrix();


        double distance=0;
        double shortestDistance=0;

        //Array mit Allen Orten erstellen:
        ArrayList<Ort> orte = new ArrayList<Ort>((int) matrix.getMatrixSize());
        for (int i = 0; i < matrix.getMatrixSize(); i++) {
            orte.add(new Ort());
        }


        //Ort[] orte = new Ort[5];

        for (int i = 0; i < matrix.getMatrixSize(); i++) {
            orte.get(i).setIndex(i);
        }

        /*
         !punkte
         0123
         
         0132
         0213
         0231
         0312
         0321
         */
        int[][] route = new int[4][6];

        System.out.println("orte " + orte.size());
        for (int i = 0; i < orte.size(); i++) {
            distance += matrix.getDistance( orte.get(route[i]).getIndex() , orte.get( (route[i]+1) % orte.size() ).getIndex()   );
        }
        System.out.println(distance);







        int[] array = new int[4];
        array[0] = 0;
        array[1] = 1;
        array[2] = 2;
        array[3] = 3;
    }



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


}