package travelingsalesman.heuristischerAlgo;


public class Main {
    public static void main(String[] args) {
        System.out.println("Traveling Salesman: ");

        //Matrix Sachen
        Matrix matrix = new Matrix();
        //matrix.readMatrixFromFile("C:\\Users\\Megaport\\IdeaProjects\\TravelingSalesman\\travelingsalesman\\exakterAlgo\\MatrixFile5Orte.csv");
        matrix.leseMatrixvonDatei("F:\\Oberschule 4\\Technologie und Planung\\TravelingSalesman\\src\\travelingsalesman\\MatrixFile17Orte.csv"); //read the matrix
        //matrix.printMatrix();

        //Zeit parsen
        long maxZeitMilli = Long.parseLong(args[0]);
        System.out.println("angegebene Zeit in millis="+maxZeitMilli);


        //Algorithmus aufrufen
        ZweiOpt zweiOpt = new ZweiOpt();
        DreiOpt dreiOpt = new DreiOpt();

        NearestNeighbour nearestNeighbour = new NearestNeighbour();

        //NearestInsertion nearestInsertion = new NearestInsertion(matrix);
        //System.out.println("nearest Insertion fertig!");
        //double distance = zweiOpt.start(matrix, maxZeitMilli);
        double distance = dreiOpt.start(matrix, maxZeitMilli);
        System.out.println(distance);


        /*
        double distance = zweiOpt.start(matrix, maxZeitMilli);
        System.out.println("ausgerechnete Distanz: " + distance);


        double distance2 = dreiOpt.start(matrix, maxZeitMilli);
        System.out.println("ausgerechnete Distanz: " + distance2);
         */
    }
}
