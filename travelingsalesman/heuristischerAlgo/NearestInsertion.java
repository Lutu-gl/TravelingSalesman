package travelingsalesman.heuristischerAlgo;
import java.util.ArrayList;

class NearestInsertion {

    public ArrayList<Ort> NearestInsertion(Matrix matrix){

        double distance = 0;
        double shortestDistance = Integer.MAX_VALUE;
        int orteAnzahl = (int) matrix.getMatrixSize();

        //array in dem die Orte der Matrix gespeichert sind
        ArrayList<Ort> route = new ArrayList<Ort>((int) matrix.getMatrixSize());
        //array in das das ergebnis kommt
        ArrayList<Ort> ergOrte = new ArrayList<Ort>();

        for (int i = 0; i < matrix.getMatrixSize(); i++) {
            route.add(new Ort(i));
        }
        //ergebnis in ergOrte schreiben
        ergOrte = insertNearest(route, ergOrte, matrix);

        return ergOrte;
        //return array anstad arraylist wenn man das will dann muss man oben auch noch Rückgabetyp ändern
        /*String[] erg = ergOrte.toArray(new String[0]);
        return erg;*/
    }

    //Algorithmus durchführen
    private ArrayList<Ort> insertNearest(ArrayList<Ort> route, ArrayList<Ort> erg, Matrix matrix){
        int orteAnzahl = route.size();
        double distance = 0;
        double shortesDistance = Integer.MAX_VALUE;
        int point = 0;

        //erstes element hinzufügen
        erg.add(route.get(0));

        //Ort finden der am nächsten zum ersten liegt.
        for (int i = 1; i < orteAnzahl; i++){
            distance = matrix.getDistance(route.get(i).getIndex(), erg.get(0).getIndex());
            if (distance < shortesDistance){
                shortesDistance = distance;
                point = i;
            }
        }
        erg.add(route.get(point));

        //restliche Punkte suchen und mit nearest insert einfuegen
        while (erg.size() < route.size()){
            shortesDistance = Integer.MAX_VALUE;
            int[] points = new int[orteAnzahl- erg.size()];
            //punkte die man noch nicht in der Route sind in points schreiben
            points = getUnusedPoints(route, erg, points);
            int atPossition =  0;
            //points durch gehen und schauen welcher Ungenutzte Punkt zu einem bereits in die Route integrieten Punkt die kützeste Strecke hat
            for (int i = 0; i < points.length; i++){
                //die Punkte die bereits in der Route sind durchgehen
                for (int i2 = 0; i2 < erg.size(); i2++){
                    //Distanz ausrechenen
                    distance = matrix.getDistance(route.get(points[i]).getIndex(), erg.get(i2).getIndex());
                    //niedrigste Distance speichern
                    if (distance < shortesDistance){
                        shortesDistance = distance;
                        point = points[i];
                        atPossition = i2;
                    }
                }
            }
            //Punkt mit der Kürzesten strecke in erg hinzufuegen
            erg.add(atPossition, new Ort(point));
        }
        return erg;
    }

    //alle Punkte die nicht in der Route sind zurückgeben
    private int[] getUnusedPoints(ArrayList<Ort> orte, ArrayList<Ort> erg, int[] points) {
        int point = 0;
        int count = 0;
        for(int i = 0; i < orte.size()-1; i++){
            point = 0;
            for (int j = 0; j < erg.size(); j++){
                if (erg.get(j).getIndex() == orte.get(i).getIndex()) point++;
            }
            if(point == 0){
                points[count] = i;
                count++;
            }
        }
        return points;
    }
}
