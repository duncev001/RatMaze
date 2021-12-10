import java.util.*;
public class RatMaze {
    //4x4 matrix
    static int MATRIXSIZE = 4;
    static String SOLUTION = "";
    public static void printMatrix(int sol[][])
    {
        for (int i = 0; i < MATRIXSIZE; i++) {
            for (int j = 0; j < MATRIXSIZE; j++)
                System.out.print(" " + sol[i][j] + " ");
            System.out.println();
        }
    }

    public static boolean Check(int matrix[][], int x, int y)
    {
        if(x>=0 && x<MATRIXSIZE && y>= 0 && y<MATRIXSIZE && matrix[x][y]==1){
            return true;
        }
        return false;
    }

    //\#1
    public static boolean iterativeMatrix(int maze[][])
    {
        int maxBackings = MATRIXSIZE - 1;
        int backings = 0;
        boolean backing = false;
        int x = 0;
        int y = 0;
        String pathing = "{(0,0)";
        if(!Check(maze, x, y)){
            System.out.print("No Solution");
            return false;
        }
        while(backings<maxBackings){
            if(x==MATRIXSIZE-1 && y==MATRIXSIZE-1){
                pathing+="}";
                System.out.println(pathing);
                return true;
            } else if(Check(maze,x-1,y) && Check(maze,x-1,y+1) && backing){
                x--;
                y++; 
                pathing+= ",(" + x + "," + y + ")";
                backing = false;
            } else if(backing){
                if(Check(maze,x,y-1)){
                    y--;
                }
                else{
                    x--;
                }
            }  else if(Check(maze,x+1,y)){
                x++;
                pathing+= ",(" + x + "," + y + ")";
            } else if(Check(maze,x,y+1)){
                y++;
                pathing+= ",(" + x + "," + y + ")";
            } 
            //backtracking
            else {
                backing = true;
                backings++;
            }
        }
        System.out.print("No Solution");
        return false;
    }

    //\#2
    public static boolean recursive(int maze[][], int x, int y)
    {
        if((x==MATRIXSIZE-1) && (y==MATRIXSIZE-1))
        {
            SOLUTION = "(" + x + "," + y + ")," + SOLUTION;
            return true;
        }
        
        if(Check(maze,x,y))
        {
            if(Check(maze,x+1,y)){
                if(recursive(maze, x+1, y)){
                    SOLUTION = "(" + x + "," + y + ")," + SOLUTION;
                    return true;
                }
            }
            if(Check(maze,x,y+1)){
                if(recursive(maze, x, y+1)){
                    SOLUTION = "(" + x + "," + y + ")," + SOLUTION;
                    return true;
                }
            }
        }
        return false;
    }

    //\#2 cont.
    public static boolean recursiveMaze(int maze[][]){;
        if(recursive(maze, 0, 0)){
            System.out.println(SOLUTION);
            return true;
        }
        System.out.print("No Solution");
        return false;
    }

    public static void main(String args[])
    {
        int maze[][] = { { 1, 1, 1, 1 },
                         { 1, 1, 0, 1 },
                         { 1, 0, 0, 0 },
                         { 1, 1, 1, 1 } };
        printMatrix(maze);
        iterativeMatrix(maze);
        System.out.println("\n_____________");
        recursiveMaze(maze);
    }
}