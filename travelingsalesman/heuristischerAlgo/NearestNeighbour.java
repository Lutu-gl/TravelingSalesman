package travelingsalesman.heuristischerAlgo;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author chris
 */
public class NearestNeighbour
{
    private final Stack<Integer> stack;

    public NearestNeighbour()
    {
        stack = new Stack<>();
    }

    /**
     *
     * @param matrix Ein Objekt Matrix das uebergeben wird, mit .getDistance kann man auf die einzelen Distanzen zugreifen
     * @return erg , double mit der Laenge des berechneten Weges.
     *
     */
    public ArrayList<Ort> start(Matrix matrix)
    {
        int numberOfNodes = (int) matrix.getMatrixSize();
        int[] visited = new int[numberOfNodes];//ob schun besucht
        int[] tour= new int[numberOfNodes];//berechnetet Route
        tour[0]=0;//Startpunkt ist Punkt 0
        visited[0] = 1; //Punkt 0 ist besucht
        stack.push(0);
        int element, dst = 0, i;
        double min = Double.MAX_VALUE;
        boolean minFlag;//wenn kleinste Distanz gefunden worden ist
        minFlag = false;
        double erg = 0.0;
        int c=1;//counter für array mit route

        while (!stack.isEmpty())//auf dem stack ist immer der vorhergehende Punkt wenn alle punkt besucht worden sind
        {
            element = stack.peek();//nehm das element vom Stack
            i = 0;
            min = Double.MAX_VALUE;
            while (i < numberOfNodes)
            {
                if (matrix.getDistance(element,i) > 1 && visited[i] == 0)
                {
                    if (min > matrix.getDistance(element,i))
                    {
                        min = matrix.getDistance(element,i);
                        dst = i;
                        minFlag = true;
                    }
                }
                i++;
            }
            if (minFlag)
            {
                visited[dst] = 1;
                stack.push(dst);//es wird der neue vorhergehnde Punkt bestimmt
                tour[c]=dst;
                c++;
                minFlag = false;
                continue;
            }
            stack.pop();
        }
        /**
         * Berechnet mit Hilfe von tour array die Länge
         */
        for(int o = 0; o< numberOfNodes; o++){
            //System.out.print(tour[o]+1+" ");
            if(o== numberOfNodes -1){
                erg += matrix.getDistance(tour[o], tour[0]);
            }
            else{
                erg += matrix.getDistance(tour[o], tour[o + 1]);
            }
        }
        ArrayList<Ort> route = new ArrayList<Ort>((int) matrix.getMatrixSize());

        for (int j = 0; j < tour.length; j++) {
               route.add(new Ort(tour[j]));
        }

        return route;

    }
}

