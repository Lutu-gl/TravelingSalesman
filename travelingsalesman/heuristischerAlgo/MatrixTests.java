package travelingsalesman.heuristischerAlgo;

import org.junit.BeforeClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixTests {
    static Matrix m;

    @BeforeClass
    public static void createMatrix(){
        m = new Matrix();
    }

    @DisplayName("Testet die Matrix Methoden auf Richtigkeit")
    @Test
    public void leseTest(){
        assertFalse(m.leseMatrixvonDatei("ABC"));
        assertFalse(m.leseMatrixvonDatei(""));
        assertTrue(m.leseMatrixvonDatei("C:\\Users\\Megaport\\IdeaProjects\\TravelingSalesman\\travelingsalesman\\MatrixFile5Orte.csv"));
    }

}
