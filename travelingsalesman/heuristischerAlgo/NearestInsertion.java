package travelingsalesman.heuristischerAlgo;
import java.util.ArrayList;

class NearestInsertion {

    public NearestInsertion(Matrix matrix){

        double distance = 0;
        double shortestDistance = Integer.MAX_VALUE;
        int orteAnzahl = (int) matrix.getMatrixSize();

        //array mit Orte
        ArrayList<Ort> route = new ArrayList<Ort>((int) matrix.getMatrixSize());
        ArrayList<Ort> ergOrte = new ArrayList<Ort>();

        for (int i = 0; i < matrix.getMatrixSize(); i++) {
            route.add(new Ort(i));
        }
            ergOrte = insertNearest(route, ergOrte, matrix);


    }

    //Algorithmus durchfuehren
    private ArrayList<Ort> insertNearest(ArrayList<Ort> route, ArrayList<Ort> erg, Matrix matrix){
        int orteAnzahl = route.size();
        double distance = 0;
        double shortesDistance = Integer.MAX_VALUE;
        int point = 0;

        //erstes element
        erg.add(route.get(0));

        for (int i = 1; i < orteAnzahl; i++){
            distance = matrix.getDistance(route.get(i).getIndex(), erg.get(0).getIndex());
            if (distance < shortesDistance){
                shortesDistance = distance;
                point = i;
            }
        }
        //zweites element
        erg.add(route.get(point));

        //restliche Punkte suchen und mit nearest insert einfuegen
        while (erg.size() < route.size()){

            shortesDistance = Integer.MAX_VALUE;
            int[] points = new int[orteAnzahl- erg.size()];
            //punkte die man noch vergeben kann finden
            points = getUnusedPoints(route, erg, points);
            int atPossition =  0;
            //punkte durch gehen und schauen welcher Ungenutzte Punkt zu einem Bereits in die Route integrieten Punkt die kützeste Strecke hat
            for (int i = 0; i < points.length; i++){
                //die Punkte die bereits in der Route sind durchgehen
                for (int i2 = 0; i2 < erg.size(); i2++){
                    distance = matrix.getDistance(route.get(points[i]).getIndex(), erg.get(i2).getIndex());
                    //niedrigste Distance speichern
                    if (distance < shortesDistance){
                        shortesDistance = distance;
                        point = points[i];
                        atPossition = i2;
                    }
                }
            }
            erg.add(atPossition, new Ort(point));
            //for(int i = 0; i<erg.size(); i++ ) System.out.println("Erg " + erg.get(i).getIndex());

        }
        return erg;
    }

    /*private ArrayList<Ort> insertNearest(ArrayList<Ort> orte, ArrayList<Ort> erg, Matrix matrix, ArrayList<Integer> route){
        double distanceP1 = 0;
        double distanceP2 = 0;
        double shortestDistance = Integer.MAX_VALUE;
        int point = 0;

        System.out.println("Array length" + erg.size());
        //ersten 2 ort hinzufügen
        if (erg.isEmpty()){
            erg.add(new Ort(0));
            for (int i = 1; i < orte.size(); i++){
                distanceP1 = matrix.getDistance(orte.get(route.get(0)).getIndex(), orte.get(route.get(i)).getIndex());
                if (distanceP1 < shortestDistance){
                    shortestDistance = distanceP1;
                    point = i;
                }
            }
            erg.add(new Ort(point));
            return erg;
        }

        point = 0;
        //alle punkte durch gehen und dann die küzeste strecke von allen finden
        for (int i = 0; i < erg.size(); i++){
            int tmpPoint = 0;
            ArrayList<Ort> tmpOrte = new ArrayList<Ort>();
            tmpOrte.addAll(erg);

            //noch nicht verwendeten punkt finden und stecke damit vergleichen
            for (int j =0; j < orte.size(); j++){
                //for(int x = 0; x < tmpOrte.size(); x++) System.out.println("index tmport" + tmpOrte.get(x).getIndex() + 1);
                tmpPoint = getUnusedPoint(orte, tmpOrte);
                if (tmpPoint == -1) break;

                //vergleicht welche punkte von der strecke AB am nähesten sind
                if (i+1 < erg.size()){
                    distanceP1 = matrix.getDistance(erg.get(i).getIndex(), orte.get(tmpPoint).getIndex());
                    distanceP2 = matrix.getDistance(erg.get(i+1).getIndex(), orte.get(tmpPoint).getIndex());
                    distanceP1 += distanceP2;
                    tmpOrte.add(new Ort(tmpPoint));
                }

                if (distanceP1 < shortestDistance){
                    shortestDistance = distanceP1;
                    point = tmpPoint;
                }
                //System.out.println(point);
                distanceP1 = 0;
                distanceP2 = 0;
            }
            System.out.println("Point = "+ point);
            System.out.println("Distance = "+ shortestDistance);
            System.out.println("i" + i);
            System.out.println("erg size " + erg.size());
            for(int x = 0; x < erg.size(); x++) System.out.println(erg.get(x).getIndex() + 1);

            //punkt hinzufuegen
            erg.add(i+1, orte.get(point));
            //if (erg.size() > 5) erg.remove(5);
            System.out.println("orteget(point)= " + orte.get(point).getIndex());

            for(int x = 0; x < erg.size(); x++) System.out.println(erg.get(x).getIndex() + 1);
            System.out.println("_________");

        }


        return erg;
    }*/
    //returned ersten punkt der noch nicht verbunden ist und sonst -1
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
