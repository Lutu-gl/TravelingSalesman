package travelingsalesman.exakterAlgo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public final class Matrix {
    private double[][] matrix;

    public Matrix(){
    }

    //Lest matrix von einer angegebene File ein und wandelt es in double matrix array um.
    public boolean readMatrixFromFile(String name){
        String[] spitDataArray;
        String data = "";

        try {
            File file = new File(name);
            Scanner reader = new Scanner(file);
            while(reader.hasNextLine()){
                data += reader.nextLine();
            }
            spitDataArray = data.split(",");
            int rootNum = (int) Math.sqrt(spitDataArray.length);
            matrix = new double[rootNum][rootNum];

            for (int i = 0; i < rootNum; i++) {
                for (int n = 0; n < rootNum; n++) {
                    matrix[i][n] = Double.parseDouble(spitDataArray[ (rootNum-1)*i + n +i ]);
                }
            }
            
            reader.close();
        } catch(FileNotFoundException e){
            e.printStackTrace();
            return false;
        }


        return true;
    }

    //Printet die matrix
    public void printMatrix() {
        for (double[] doubles : matrix) {
            for (double aDouble : doubles) {
                System.out.print(aDouble + " ");
            }
            System.out.println();
        }
    }


    public double getDistance(int index1, int index2){
        return matrix[index1][index2];
    }

    public double getMatrixSize(){
        return matrix.length;
    }


}