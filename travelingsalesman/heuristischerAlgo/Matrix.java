package travelingsalesman.heuristischerAlgo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public final class Matrix {
    private double[][] matrix;

    public Matrix(){
    }

    /**
     *
     * @param name Pfad zu Datei
     * @return wenn die Datei gefunden wird True sonst False
     * Einlesen von der Matrix Datei mit einem Scanner welcher aus der Datei liest. bei eine Exception wird False zurückgegeben und der Stacktree ausgegeben in der Konsole
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
     * Einlesen der Matrix von der Konsole
     */
    public void leseMatrixvonCmd(){

        int number_of_nodes;
        Scanner scanner = null;
        try {
            System.out.println("Wieviele Punkte gibt es?");
            scanner = new Scanner(System.in);
            number_of_nodes = scanner.nextInt();
            matrix = new double[number_of_nodes][number_of_nodes];
            System.out.println("Gib jetzt die Matrix ein");
            for (int i = 0; i <= number_of_nodes-1; i++) {
                for (int j = 0; j <= number_of_nodes-1; j++) {
                    matrix[i][j] = scanner.nextDouble();
                }
            }
            for (int i = 0; i <= number_of_nodes-1; i++) {
                for (int j = 0; j <= number_of_nodes-1; j++) {
                    if (matrix[i][j] == 1 && matrix[j][i] == 0) {
                        matrix[j][i] = 1;
                    }
                }
            }
        } catch (InputMismatchException inputMismatch) {
            System.out.println("Falsches Eingabe Format");
        }
        scanner.close();
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

    /**
     *
     * @param route Die route mit den Indexen aus welchen es die Distanz berechnen soll
     * @return berechnete Distanz(Double)
     */
    public double getDistance(ArrayList<Ort> route){
        double distance=0;

        for(int j=0; j < getMatrixSize()-1; j++){
            distance += getDistance( route.get(j).getIndex(), route.get(j + 1).getIndex()  );
        }
        distance += getDistance( route.get((int) (getMatrixSize() - 1)).getIndex(), route.get(0).getIndex());

        return distance;
    }
}