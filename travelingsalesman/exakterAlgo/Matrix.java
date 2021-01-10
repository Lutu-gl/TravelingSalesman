package travelingsalesman.exakterAlgo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public final class Matrix {
    private double[][] matrix = new double[1000][1000];


    public Matrix(){

    }

    public boolean readMatrixFromFile(String name){
        String[] spitData;
        int row = 0;        //Zeile
        int column = 0;     //Spalte

        try {
            File file = new File(name);
            Scanner reader = new Scanner(file);

            while(reader.hasNextLine()){
                String data = reader.nextLine();
                spitData = data.split(",");

                for(String ausgabe : spitData){
                    System.out.print(Double.parseDouble(ausgabe) + " ");
                    matrix[row][column] = Double.parseDouble(ausgabe);
                    column++;
                }
                System.out.println();
                row++;
                column=0;
            }

            reader.close();
        } catch(FileNotFoundException e){
            e.printStackTrace();
            return false;
        }


        return true;
    }



}
