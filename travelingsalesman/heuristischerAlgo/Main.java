package travelingsalesman.heuristischerAlgo;


public class Main {
    public static void main(String[] args) {
        System.out.println("Traveling Salesman: 2-Opt");

        //Matrix Sachen
        Matrix matrix = new Matrix();
        //matrix.readMatrixFromFile("C:\\Users\\Megaport\\IdeaProjects\\TravelingSalesman\\travelingsalesman\\exakterAlgo\\MatrixFile5Orte.csv");
        matrix.leseMatrixvonDatei("E:\\Oberschule 4\\Technologie und Planung\\TravelingSalesman\\src\\travelingsalesman\\MatrixFile5Orte.csv"); //read the matrix
        //matrix.printMatrix();

        //Zeit parsen
        long maxZeitMilli = Long.parseLong(args[0]);
        System.out.println("angegebene Zeit in millis="+maxZeitMilli);


        //Algorithmus aufrufen
        ZweiOpt zweiOpt = new ZweiOpt();
        double distance = zweiOpt.start(matrix, maxZeitMilli);
        System.out.println("ausgerechnete Distanz: " + distance);

    }
}
