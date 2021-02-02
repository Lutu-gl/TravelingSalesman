package travelingsalesman.heuristischerAlgo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public final class Matrix {
    private double[][] matrix;

    public Matrix(){
    }

    /**
     *
     * @param name Pfad zu Datei
     * @return wenn die Datei gefunden wird True sonst False
     */
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

    /**
     * Gibt die eingelesene Matrix aus
     */
    public void printMatrix() {
        for (double[] doubles : matrix) {
            for (double aDouble : doubles) {
                System.out.print(aDouble + " ");
            }
            System.out.println();
        }
    }

    /**
     *
     * @param index1 erster index
     * @param index2 zweiter index
     * @return distanz zwischen index1 und index2
     */
    public double getDistance(int index1, int index2){
        return matrix[index1][index2];
    }

    /**
     *
     * @return größe der Matrix
     */
    public int getMatrixSize(){
        return matrix.length;
    }
}