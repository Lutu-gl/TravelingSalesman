package travelingsalesman.heuristischerAlgo;

/**
 * @author Lutu
 * Das ist die Klasse ort, die ein index speichert.
 * Dieser Index gibt an welchem Block/Zeile in der Matrix dieser Ort belegt
 */
public class Ort {
    private int index;

    public Ort(){}

    /**
     * @param index
     * ein weiterer Konstruktor.
     * */
    public Ort(int index) {
        this.index = index;
    }

    /**
     * @return index an welchem Block/Zeile in der Matrix dieser Ort belegt
     *  Getter
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index an welchem
     * Setter
     */
    public void setIndex(int index) {
        this.index = index;
    }
}
