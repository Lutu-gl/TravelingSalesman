package travelingsalesman.heuristischerAlgo;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NearestInsertionTest {
    @Test
    public void nearestInsertionMethodenTest(){
        Matrix m1 = new Matrix();
        m1.leseMatrixvonDatei("C:\\Users\\Megaport\\IdeaProjects\\TravelingSalesman\\travelingsalesman\\MatrixFile17Orte.csv");
        int points = (int)m1.getMatrixSize();
        NearestInsertion n1 = new NearestInsertion();
        assertEquals(n1.NearestInsertion(m1).size(), points+1);

    }
}
