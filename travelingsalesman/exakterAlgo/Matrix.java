package travelingsalesman.exakterAlgo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public final class Matrix {
    //private double[][] matrix = new double[length][length];


    public Matrix(){

    }

    public boolean readMatrixFromFile(String name){
        String[] spitData;

        try {
            File file = new File(name);
            Scanner reader = new Scanner(file);

            while(reader.hasNextLine()){
                String data = reader.nextLine();
                spitData = data.split(",");
                for(String ausgabe : spitData){
                    System.out.print(ausgabe + " ");
                }
                System.out.println();
            }

        } catch(FileNotFoundException e){
            e.printStackTrace();
        }





        return true;
    }



}
