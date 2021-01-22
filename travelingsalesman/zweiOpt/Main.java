package travelingsalesman.zweiOpt;


public class Main {
    public static void main(String[] args) {
        System.out.println("Traveling Salesman: 2-Opt");

        Matrix matrix = new Matrix();
        //matrix.readMatrixFromFile("C:\\Users\\Megaport\\IdeaProjects\\TravelingSalesman\\travelingsalesman\\exakterAlgo\\MatrixFile5Orte.csv");
        matrix.leseMatrixvonDatei("Z:\\Oberschule 4\\Technologie und Planung\\TravelingSalesman\\src\\travelingsalesman\\MatrixFile5Orte.csv"); //read the matrix
        //matrix.printMatrix();

        ZweiOpt zweiOpt = new ZweiOpt();
        double distance = zweiOpt.start(matrix);
        System.out.println("ausgerechnete Distanz: " + distance);

    }
}
