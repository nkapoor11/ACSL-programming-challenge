import java.util.*;
public class ACSLContest3 {
    public static boolean checkA(int start, int col, int[] grid, boolean left) {
        int r = start/col;
        int c = start % col;
        if (left) {
            if(c + 2 < col && grid[start+1] != -1 && grid[start+2] != -1)
                return true;
        }
        else {
            if(c - 2 >= 0 && grid[start-1] != -1 && grid[start-2] != -1)
                return true;
        }

        return false;
    }
    public static boolean checkB(int start, int col, int row, int[]grid, boolean left) {
        int r = start/col;
        int c = start % col;
        if (left) {
            if (c + 1 < col && r + 1 < row && grid[start + col] != -1 && grid[start + col + 1] != -1)
                return true;
        }
        else {
            if (c - 1 >= 0 && r - 1 >= 0 && grid[start - 1] != -1 && grid[start - col - 1] != -1)
                return true;
        }
        return false;
    }
    public static boolean checkC(int start, int col, int row, int[]grid, boolean left) {
        int r = start/col;
        int c = start % col;
        if (left) {
            if(c+1 < col && r + 2 < row && grid[start+1] != -1 && grid[start+col+1] != -1 && grid[start+(2*col)+1] != -1)
                return true;
        }
        else
        {
            if(c-1 >= 0 && r - 2 >= 0 && grid[start-(2*col)-1] != -1 && grid[start-col] != -1 && grid[start-(2*col)] != -1)
                return true;
        }
        return false;
    }
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);

        //convert to col: x % c
        //convert to row: (((float) y - 0.1)/) + 1

        int r = scan.nextInt();
        int c = scan.nextInt();
        int start = scan.nextInt()-1;
        int n = scan.nextInt();
        boolean left = (start %c == 0);

        String output = "";
        int marker = start;
        int grid[] = new int[r*c];

        for(int i = 0; i < r*c; i++) {
            grid[i] = 0;
        }
        for(int i = 0; i < n; i++) {
            grid[scan.nextInt()-1] = -1;
        }

        while((start % c != 0 && left) || (!left && (start%c != c-1 || start == -1)) || start == marker) {
            if(checkA(start, c, grid, left)) {
                if (left)
                    start += 3;
                else
                    start -=3;
                output += "A";
                //System.out.println(start);
                //System.out.println(output);
                if ((start % c == 0 && left) || (!left && (start%c == c-1 || start==-1))) break;
            }

            if(checkB(start, c, r, grid, left)) {
                if (left)
                    start += (c+2);
                else
                    start -= (c+2);
                output += "B";
                //System.out.println(start);
                //System.out.println(output);
                if ((start % c == 0 && left) || (!left && (start%c == c-1 || start==-1))) break;
            }

            if(checkC(start, c, r, grid, left)) {
                if (left)
                    start += (2 * c + 2);
                else
                    start -= (2*c+2);
                output += "C";
                //System.out.println(start);
                //System.out.println(output);
            }
        }
        if (left)
            System.out.println(output);
        else {
            for (int i = output.length() - 1; i >= 0; i--) {
                System.out.print(output.charAt(i));
            }
            System.out.println();
        }
    }
}
