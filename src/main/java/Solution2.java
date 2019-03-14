// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
import java.util.ArrayList;
import java.util.List;
// CLASS BEGINS, THIS CLASS IS REQUIRED
public class Solution2
{


    public static void main(String[] args) {
        List<List<Integer>> allLocations = new ArrayList<List<Integer>>();
        List<Integer> cord1= new ArrayList<Integer>();
        List<Integer> cord2= new ArrayList<Integer>();
        List<Integer> cord3= new ArrayList<Integer>();


        cord1.add(1);         cord1.add(0); cord1.add(0);
        cord2.add(1);         cord2.add(0); cord2.add(0);
        cord3.add(1);         cord3.add(9); cord3.add(1);


        allLocations.add(cord1);
        allLocations.add(cord2);
        allLocations.add(cord3);

        System.out.println(minimumDistance(3, 3,allLocations));
    }




    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    static int minimumDistance(int numRows, int numColumns, List<List<Integer>> area)
    {
        // WRITE YOUR CODE HERE


        int [][] pathmatrix  = new int[numRows][numColumns];
        for(int i=0;i<numRows;i++){
            for(int j=0;j<numColumns;j++){
                pathmatrix[i][j]=area.get(i).get(j);
            }
        }



        int visited[][] = new int[numRows][numColumns];
        return traverse(0,0,0,pathmatrix, numRows, numColumns, visited);



    }


    static public int  traverse(int i, int j , int distance, int [][] pathmatrix  , int rows, int columns, int[][] visited){
        int result=0;

        visited[i][j]=1;
        if(pathmatrix[i][j]==9)
            return distance;



        if (i+1<rows && pathmatrix[i+1][j]!=0 && visited[i+1][j] !=1)
        {
            int d1 =  traverse(i+1,j,distance+1, pathmatrix, rows, columns, visited);
            System.out.println(distance+" d1 --->"+d1);
            result  = Math.max(result,d1);

        }
        if(j+1<columns && pathmatrix[i][j+1]!=0 && visited[i][j+1] !=1)
        {
            int d2 =   traverse(i,j+1,distance+1, pathmatrix, rows, columns, visited);
            result = Math.max(result,d2);
        }
        if (i-1>=0 && pathmatrix[i-1][j]!=0 && visited[i-1][j] !=1)
        {
            int d3 = traverse(i-1,j,distance+1, pathmatrix, rows, columns, visited);
            result = Math.max(result,d3);
        }
        if (j-1>=0 && pathmatrix[i][j-1]!=0 && visited[i][j-1] !=1)
        {
            int d4 = traverse(i,j-1,distance+1, pathmatrix, rows, columns, visited );
            result = Math.max(result,d4);
        }

        return result;
    }



    // METHOD SIGNATURE ENDS
}