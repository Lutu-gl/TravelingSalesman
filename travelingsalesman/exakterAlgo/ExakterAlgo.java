package travelingsalesman.exakterAlgo;


//Wahl eines exakten Algorithmus und beispielhafte Anwendung an einem kleinen Beispiel (Implementation in Java)
public class ExakterAlgo {

    public static void main(String[] args) {
        Matrix matrix = new Matrix();
        matrix.readMatrixFromFile("C:\\Users\\Megaport\\IdeaProjects\\TravelingSalesman\\travelingsalesman\\exakterAlgo\\MatrixFile.csv");
        matrix.printMatrix();

    }

}
