package travelingsalesman.heuristischerAlgo;


import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Traveling Salesman: ");

        //Matrix Sachen
        Matrix matrix = new Matrix();
        //matrix.readMatrixFromFile("C:\\Users\\Megaport\\IdeaProjects\\TravelingSalesman\\travelingsalesman\\exakterAlgo\\MatrixFile5Orte.csv");
        //matrix.leseMatrixvonDatei("F:\\Oberschule 4\\Technologie und Planung\\TravelingSalesman\\src\\travelingsalesman\\MatrixFile17Orte.csv"); //read the matrix
        //matrix.printMatrix();
        matrix.leseMatrixvonCmd();
        matrix.printMatrix();

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
