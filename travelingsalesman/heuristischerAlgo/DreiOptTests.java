package travelingsalesman.heuristischerAlgo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.Test;

public class DreiOptTests {

    @DisplayName("Testet den 3-Opt auf Richtigkeit")
    @RepeatedTest(5)
    @Test
    public void DreiOptTest(){
        Matrix m = new Matrix();
        m.leseMatrixvonDatei("C:\\Users\\Megaport\\IdeaProjects\\TravelingSalesman\\travelingsalesman\\MatrixFile5Orte.csv");
        assert(new DreiOpt().start(m, 500)!=0);
    }
}