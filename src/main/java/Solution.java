// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
import java.util.List;
import java.util.*;
// CLASS BEGINS, THIS CLASS IS REQUIRED
public class Solution
{




    public static void main(String args[]){

        List<List<Integer>> allLocations = new ArrayList<List<Integer>>();
        List<Integer> cord1= new ArrayList<Integer>();
        List<Integer> cord2= new ArrayList<Integer>();
        List<Integer> cord3= new ArrayList<Integer>();


        cord1.add(1);         cord1.add(-3);
        cord2.add(1);         cord2.add(2);
        cord3.add(3);         cord3.add(4);


        allLocations.add(cord1);
        allLocations.add(cord2);
        allLocations.add(cord3);

        System.out.println(ClosestXdestinations(3, allLocations, 1));
    }



    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    static List<List<Integer>> ClosestXdestinations(int numDestinations,
                                             List<List<Integer>> allLocations,
                                             int numDeliveries)
    {
        // WRITE YOUR CODE HERE
        List<Distance> distances = new ArrayList<Distance>();
        for(List<Integer> l : allLocations){
            int a = l.get(0);
            int b = l.get(1);
            Distance d = new Distance();
            d.distance = Math.sqrt(a*a+b*b);
            d.x = a;
            d.y=b;
            distances.add(d);
        }

        //Arrays.sort(new List[]{distances});
        Collections.sort(distances);

        List<List<Integer>> result = new ArrayList();

        for(int i=0;i<numDeliveries;i++){
            List<Integer> res = new ArrayList<Integer>();
            Distance d = distances.get(i);
            res.add(d.x);
            res.add(d.y);
            result.add(res);
        }

        return result;

    }
    // METHOD SIGNATURE ENDS
}

class Distance implements Comparable{
    int x, y;
            double distance;


    public int compareTo(Object o){
        Distance obj = (Distance)o;
        if(this.distance>obj.distance)
            return 1 ;
        else if (this.distance<obj.distance)
            return  -1;
        else
            return 0;
    }

}


