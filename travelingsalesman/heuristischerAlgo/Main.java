package travelingsalesman.heuristischerAlgo;

public class Main {
    public static void main(String[] args) {
        System.out.println("Traveling Salesman: 3-Opt");

        //Matrix Sachen
        Matrix matrix = new Matrix();
        matrix.leseMatrixvonDatei("C:\\Users\\Megaport\\IdeaProjects\\TravelingSalesman\\travelingsalesman\\MatrixFile5Orte.csv");
        //matrix.leseMatrixvonDatei("E:\\Oberschule 4\\Technologie und Planung\\TravelingSalesman\\src\\travelingsalesman\\MatrixFile5Orte.csv"); //read the matrix
        //matrix.printMatrix();

        //Zeit parsen
        long maxZeitMilli = Long.parseLong("500");
        System.out.println("angegebene Zeit in millis="+maxZeitMilli);

        /*
        //Algorithmus aufrufen
        ZweiOpt zweiOpt = new ZweiOpt();
        double distance = zweiOpt.start(matrix, maxZeitMilli);
        System.out.println("ausgerechnete Distanz: " + distance);

         */
        DreiOpt dreiOpt = new DreiOpt();
        double distance2 = dreiOpt.start(matrix, maxZeitMilli);
        System.out.println("ausgerechnete Distanz: " + distance2);
    }
}
