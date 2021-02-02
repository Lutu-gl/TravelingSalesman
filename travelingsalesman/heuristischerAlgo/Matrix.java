package travelingsalesman.heuristischerAlgo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public final class Matrix {
    private double[][] matrix;

    public Matrix(){
    }

    //Lest matrix von einer angegebene File ein und wandelt es in double matrix array um.
    public boolean leseMatrixvonDatei(String name){
        String[] zerteilterText;
        String text = "";

        try {
            File datei = new File(name);
            Scanner scanner = new Scanner(datei);
            while(scanner.hasNextLine()){
                text += scanner.nextLine();
            }
            zerteilterText = text.split(",");
            int groeseZeile = (int) Math.sqrt(zerteilterText.length);
            matrix = new double[groeseZeile][groeseZeile];

            for (int i = 0; i < groeseZeile; i++) {
                for (int n = 0; n < groeseZeile; n++) {
                    matrix[i][n] = Double.parseDouble(zerteilterText[ (groeseZeile-1)*i + n +i ]);
                }
            }

            scanner.close();
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

    public double getDistance(ArrayList<Ort> route){
        double distance=0;

        for(int j=0; j < getMatrixSize()-1; j++){
            distance += getDistance( route.get(j).getIndex(), route.get(j + 1).getIndex()  );
        }
        distance += getDistance( route.get((int) (getMatrixSize() - 1)).getIndex(), route.get(0).getIndex());

        return distance;
    }

    public double getMatrixSize(){
        return matrix.length;
    }
}