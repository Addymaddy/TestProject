import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution3 {



    static int countConnections(List<List<Integer>> matrix) {
        int x = matrix.size();
        int y = matrix.get(0).size();
        int [][] arrMatrix  = new int[x][y];

        Set<Connection> resultSet = new HashSet<Connection>();

        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                arrMatrix[i][j]=matrix.get(i).get(j);
            }
        }

        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                if(arrMatrix[i][j]==1)
                    checkConnection(arrMatrix, i, j , resultSet,x,y);
            }
        }


        return resultSet.size();

    }

    public static void checkConnection(int[][] arr, int i, int j, Set<Connection> resulSet, int maxRow, int maxCol){


        if(i+1<maxRow && arr[i+1][j]==1){
            Connection co = new Connection();
            co.fromx=i;
            co.fromy=j;
            co.tox=i+1;
            co.toy=j;
            resulSet.add(co);
        }

        //
        if(i-1 >=0 && arr[i-1][j]==1){
            Connection co = new Connection();
            co.fromx=i;
            co.fromy=j;
            co.tox=i-1;
            co.toy=j;
            resulSet.add(co);
        }
        //
        if(j+1 < maxCol && arr[i][j+1]==1){
            Connection co = new Connection();
            co.fromx=i;
            co.fromy=j;
            co.tox=i;
            co.toy=j+1;
            resulSet.add(co);
        }
        //
        if(j-1 >=0 && arr[i][j-1]==1){
            Connection co = new Connection();
            co.fromx=i;
            co.fromy=j;
            co.tox=i;
            co.toy=j-1;
            resulSet.add(co);
        }
        //
        if(i-1>=0 && j-1>=0 && arr[i-1][j-1]==1){
            Connection co = new Connection();
            co.fromx=i;
            co.fromy=j;
            co.tox=i-1;
            co.toy=j-1;
            resulSet.add(co);
        }
        //
        if(i-1>=0 && j+1<maxCol && arr[i-1][j+1]==1){
            Connection co = new Connection();
            co.fromx=i;
            co.fromy=j;
            co.tox=i-1;
            co.toy=j+1;
            resulSet.add(co);
        }
        //
        if(i+1<maxRow && j+1<maxCol && arr[i+1][j+1]==1){
            Connection co = new Connection();
            co.fromx=i;
            co.fromy=j;
            co.tox=i+1;
            co.toy=j+1;
            resulSet.add(co);
        }
        //
        if(i-1>=0 && j-1>=0 && arr[i-1][j-1]==1){
            Connection co = new Connection();
            co.fromx=i;
            co.fromy=j;
            co.tox=i-1;
            co.toy=j-1;
            resulSet.add(co);
        }
    }

    static class Connection{
        int fromx; int fromy;
        int tox; int toy;

        public boolean equals(Object o){
            Connection co = (Connection)o;
            if(co.tox == this.fromx && co.toy == this.fromy && co.fromx == this.tox && co.fromy == this.toy)
                return true;
            else
                return false;
        }
    }


}
