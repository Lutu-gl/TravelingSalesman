package travelingsalesman.heuristischerAlgo;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * @author Lutu
 * Main Datei für das TSP Programm
 */
public class Main {
    public static void main(String[] args) {


        System.out.println("---------------------------");
        System.out.println("Traveling Salesman Problem");
        System.out.println("---------------------------");


        Matrix matrix = new Matrix();


        int konsole=0;
        try{
            konsole = readInt("Wollen Sie die Matrix mit einem Pfad eingeben oder über die Konsole (0/1): ");
        }catch(Exception e){
            System.err.println("Es gab einen Fehler beim Einlesen");
            System.exit(0);
        }


        if(konsole == 0){
            String pfad = readString("Bitte den Pfad angeben:");
            if (!matrix.leseMatrixvonDatei(pfad)){
                System.exit(0);
            }
        }

        if(konsole == 1){
            matrix.leseMatrixvonCmd();
        }

        ArrayList<Ort> route = new ArrayList<Ort>((int) matrix.getMatrixSize());
        for (int i = 0; i < matrix.getMatrixSize(); i++) {
            route.add(new Ort(i));
        }

        long maxZeitMilli = Long.parseLong(args[0]);
        System.out.println("angegebene Zeit: "+maxZeitMilli);

        ZweiOpt zweiOpt = new ZweiOpt();
        DreiOpt dreiOpt = new DreiOpt();
        NearestNeighbour nearestNeighbour = new NearestNeighbour();
        NearestInsertion nearestInsertion = new NearestInsertion();

        int input=0;
        try{
            input = readInt("Was Möchten sie für einene Algorithmus verwenden\nNur DreiOpt (1)\nNur ZweiOpt (2)\nDreiOpt mit NearestNeighbour (3)\nZweiOpt mit NearestNeighbour (4)");
        }catch(Exception e){
            System.err.println("Es gab einen Fehler beim Einlesen");
            System.exit(0);
        }

        if (input == 1){
            System.out.println("DreiOpt:");
            System.out.println("Distanz: " + dreiOpt.start(matrix, maxZeitMilli, route));
        }
        else if (input == 2){
            System.out.println("ZweiOpt:");
            System.out.println("Distanz: " + zweiOpt.start(matrix, maxZeitMilli, route));
        }
        else if(input == 3){
            System.out.println("DreiOpt mit Nearest Neighbour:");
            long startTime = System.nanoTime()/1000;
            ArrayList<Ort> routeNN = nearestNeighbour.start(matrix);
            long endTime = System.nanoTime()/1000;

            System.out.println("Distanz: " + dreiOpt.start(matrix,maxZeitMilli - (endTime-startTime), routeNN ));
        }
        else if(input == 4){
            System.out.println("ZweiOpt mit Nearest Neighbour:");
            long startTime = System.nanoTime()/1000;
            ArrayList<Ort> routeNN = nearestNeighbour.start(matrix);
            long endTime = System.nanoTime()/1000;


            System.out.println("Distanz: " + zweiOpt.start(matrix,maxZeitMilli - (endTime-startTime), routeNN ));
        }
        else {
            System.out.println("Die Eingabe ist falsch");
        }
    }

    private static String readString(String message){    //Einlesen String
        String s = JOptionPane.showInputDialog(message);
        return s;
    }

    public static int readInt(String message){    //Einlesen String
        String s = JOptionPane.showInputDialog(message);
        return Integer.parseInt(s);
    }
}
