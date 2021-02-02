package travelingsalesman.heuristischerAlgo;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("---------------------------");
        System.out.println("Traveling Salesman Problem");
        System.out.println("---------------------------");


        Matrix matrix = new Matrix();
        Scanner sc1 = new Scanner(System.in);


        System.out.println("Bitte Bitte den Pfad angeben:");
        String pfad = sc1.nextLine();
        if (!matrix.leseMatrixvonDatei(pfad)){
            System.exit(0);
        }


        ArrayList<Ort> route = new ArrayList<Ort>((int) matrix.getMatrixSize());
        for (int i = 0; i < matrix.getMatrixSize(); i++) {
            route.add(new Ort(i));
        }

        long maxZeitMilli = Long.parseLong(args[0]);
        System.out.println("angegebene Zeit in millis="+maxZeitMilli);

        ZweiOpt zweiOpt = new ZweiOpt();
        DreiOpt dreiOpt = new DreiOpt();
        NearestNeighbour nearestNeighbour = new NearestNeighbour();
        NearestInsertion nearestInsertion = new NearestInsertion();

        System.out.println("Wollen Sie die Matrix mit einem Pfad eingeben oder über die Konsole (1/2): ");
        int input = sc1.nextInt();

        if (input == 1){
            System.out.println("Was Möchten sie für einene Algorithmus verwenden");
            System.out.println("Nur DreiOpt (1)");
            System.out.println("Nur ZweiOpt (2)");
            System.out.println("DreiOpt mit NearestNeighbour (3)");
            System.out.println("ZweiOpt mit NearestNeighbour (4)");

            Scanner sc2 = new Scanner(System.in);
            input = sc2.nextInt();

            if (input == 1){
                System.out.println("DreiOpt:");
                System.out.println("Distanz: " + dreiOpt.start(matrix, 100, route));
            }
            else if (input == 2){
                System.out.println("ZweiOpt:");
                System.out.println("Distanz: " + zweiOpt.start(matrix, 100, route));
            }
            else if(input == 3){
                System.out.println("DreiOpt mit Nearest Neighbour:");
                System.out.println("Distanz: " + dreiOpt.start(matrix,1000, nearestNeighbour.start(matrix) ));
            }
            else if(input == 4){
                System.out.println("ZweiOpt mit Nearest Neighbour:");
                System.out.println("Distanz: " + zweiOpt.start(matrix,1000, nearestNeighbour.start(matrix) ));
            }
            else {
                System.out.println("Die eingabe ist falsch");
            }
        }
        else if (input == 2){

        }



        //DreiOpt dreiOpt = new DreiOpt();
        //System.out.println( "ausgerechnete Distanz Nur NN mit DreiOpt: " + dreiOpt.start(matrix,1000, nearestNeighbour.start(matrix) ));
        //System.out.println( "ausgerechnete Distanz Nur NN mit ZweiOpt: " + zweiOpt.start(matrix,1000, nearestNeighbour.start(matrix) ));
        //System.out.println("Distanz: " + zweiOpt.start(matrix,1000, nearestNeighbour.start(matrix) ));
        //zweiOpt.start(matrix, 100, route)

        /*
        double distance = zweiOpt.start(matrix, maxZeitMilli);
        System.out.println("ausgerechnete Distanz: " + distance);


        double distance2 = dreiOpt.start(matrix, maxZeitMilli);
        System.out.println("ausgerechnete Distanz: " + distance2);



        /*System.out.println("Traveling Salesman: ");

        //Matrix Sachen
        Matrix matrix = new Matrix();
        //matrix.readMatrixFromFile("C:\\Users\\Megaport\\IdeaProjects\\TravelingSalesman\\travelingsalesman\\exakterAlgo\\MatrixFile5Orte.csv");
        matrix.leseMatrixvonDatei("F:\\Oberschule 4\\Technologie und Planung\\TravelingSalesman\\src\\travelingsalesman\\MatrixFile17Orte.csv"); //read the matrix
        //matrix.printMatrix();

        //Zeit parsen
        long maxZeitMilli = Long.parseLong(args[0]);
        System.out.println("angegebene Zeit in millis="+maxZeitMilli);


        ZweiOpt zweiOpt = new ZweiOpt();
        NearestInsertion nearestInsertion = new NearestInsertion();

        System.out.println("ausgerechnete Distanz zweiOpt + Ni " + zweiOpt.start(matrix, 100, nearestInsertion.start(matrix)));

        ArrayList<Ort> route = new ArrayList<Ort>((int) matrix.getMatrixSize());
        for (int i = 0; i < matrix.getMatrixSize(); i++) {
            route.add(new Ort(i));
        }

        System.out.println("ausgerechnete Distanz ohne Ni: " + zweiOpt.start(matrix, 100, route));

        System.out.println( "ausgerechnete Distanz Nur ni: " + matrix.getDistance( nearestInsertion.start(matrix) ));


        DreiOpt dreiOpt = new DreiOpt();
        System.out.println("ausgerechnete Distanz DreiOpt mit Ni: " + dreiOpt.start(matrix, 100, nearestInsertion.start(matrix)));
        System.out.println("ausgerechnete Distanz DreiOpt ohne Ni: " + dreiOpt.start(matrix, 100, route));

        NearestNeighbour nearestNeighbour = new NearestNeighbour();
        System.out.println( "ausgerechnete Distanz Nur NN: " + matrix.getDistance( nearestNeighbour.start(matrix) ));
        System.out.println( "ausgerechnete Distanz Nur NN mit DreiOpt: " + dreiOpt.start(matrix,1000, nearestNeighbour.start(matrix) ));
        System.out.println( "ausgerechnete Distanz Nur NN mit ZweiOpt: " + zweiOpt.start(matrix,1000, nearestNeighbour.start(matrix) ));



        /*
        double distance = zweiOpt.start(matrix, maxZeitMilli);
        System.out.println("ausgerechnete Distanz: " + distance);


        double distance2 = dreiOpt.start(matrix, maxZeitMilli);
        System.out.println("ausgerechnete Distanz: " + distance2);
         */

    }
}
