// ACSL Contest 3 problem
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.*;
import java.lang.Math; 

public class C {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter input: ");
        String[] ori = input.nextLine().split(" "); // r c s n (n numbers)
        boolean left; 

        int r, c, s, n;
        try {
        	r = Integer.parseInt(ori[0]);
        	c = Integer.parseInt(ori[1]);
        	s = Integer.parseInt(ori[2]);
        	n = Integer.parseInt(ori[3]);
        }
        catch (NumberFormatException e) {
        	r = 0;
        	c = 0;
        	s = 0;
        	n = 0;
        }
        
        int [][] grid = new int[r][c]; // create grid. 
        // fill grid with zeros
        int squareNum = 1;
        for ( int i = 0; i < r; i++) {
        	for (int j = 0; j < c; j++) {
        		grid[i][j] = squareNum;
        		squareNum++;
        	}
        }
        
        int [] blocked = new int[n];
        for (int i = 0; i < blocked.length; i++) {
        	blocked[i] = Integer.parseInt(ori[i+4]);
        	System.out.println("blocked[" + i + "] is: " + blocked[i]);
        }
        
        // determine if it's left to right or right to left.
        if (s % c == 0) {
        	// it's right to left. so flip values.
        	left = true;
        	s = r * c - s + 1;
        }
        
        //System.out.println(doesAWork(s, blocked, c));
        // get arrayList and keep adding when you "add" A/B/C. then print out arraylist at the end.
        ArrayList<String> list = new ArrayList<String>();
        boolean keepGoing = true;
        while (keepGoing) {
        	// check if A works
        	System.out.println("in keep going");
        		
        	if (doesAWork(s, blocked, r, c)) { // r and c are the dimensions of the square grid
        		// use A. add A to arraylist. 
        		System.out.println("A does work. s is: " + s);
        		list.add("A");
        		s = s + 3; // A has length 3. s is the new starting position for B. 
        		System.out.println("new s is: " + s);
        		//get new starting square for B.
        		// check that the previous square was not already at the final column
				double temp = s;
				if ((temp-1) % c == 0.0) {
					break;
				}
				System.out.println("leaving A if loop");
        	}
                	
        	if (doesBWork(s, blocked, r, c)) {
				// use B. add B to arraylist.
				System.out.println("B does work. s is: " + s);
				list.add("B");
				// get new starting square for C.
				s = s + c + 2;
				
				double temp = s;
				if ((temp-1) % c == 0.0) {
					break;
				}
        	}
        	System.out.println("Just left B if loop");
        	if (doesCWork(s, blocked, r, c)) {
        		System.out.println("C does work. s is: " + s);
				// use C. add C to arraylist. 
				list.add("C");
				// get new starting square for A.
				
				s = s + 2 * c + 2;
				
				double temp = s;
				if ((temp-1) % c == 0.0) {
					break;
				}
        	}
        	System.out.println("Just left C if loop");
        	if (!doesAWork(s, blocked, r, c) && !doesBWork(s, blocked, r, c) && !doesCWork(s, blocked, r, c)) {
        		System.out.println("in last if loop");
        		keepGoing = false;
        		// reached final column
        	}
        	System.out.println("right here.");
        }
        System.out.println("left while loop");
        // print out arraylist
        System.out.println("printing array list");
        for (String x : list)
        	System.out.print(x);
        	
        System.out.println();
        
	}
	
	public static boolean doesAWork (int s, int [] blocked, double numRows, double numCols) { // assume left to right
		// assumes the previous square has a circle
		System.out.println("here1");
		// check if starting squares are blocked
		for (int i : blocked) {
			if (i == s || i == s + 1 || i == s + 2)
				return false;
		}
		System.out.println("here2");
		// check that starting square is within the grid
		if (s < 1 || s > (numRows * numCols)) {
			return false;
		}
		System.out.println("here3");
		// check there is enough space for A in that row
		double rowNum = Math.ceil(s / numCols);
		System.out.println("rowNum is: " + rowNum);
		double spaceLeft = (rowNum * numCols) - s;
		if (spaceLeft < 2.0) {
			// not enough space
			System.out.println("not enough space left");
			return false;
		}	
		System.out.println("here4");
		return true;
	}
	
	public static boolean doesBWork (int s, int [] blocked, double numRows, double numCols) { // assume left to right
		// assumes the previous square has a circle
			
		// check if squares are blocked
		for (int i : blocked) {
			if (i == s || i == s + numCols || i == s + numCols + 1)
				return false;
		}
		// check that starting square is within the grid
		if (s < 1 || s > (numRows * numCols)) {
			return false;
		}
		
		// check there is enough space for B to the right (in that row)
		double rowNum = Math.ceil(s / numCols);
		System.out.println("rowNum is: " + rowNum);
		double spaceLeft = (rowNum * numCols) - s;
		if (spaceLeft < 1.0) {
			// not enough space
			return false;
		}
		// check there is enough space for B below that row
		if (rowNum + 1.0 > numRows) {
			return false;
		}
		return true;
	}
	
	
	
	public static boolean doesCWork (int s, int [] blocked, double numRows, double numCols) { // assume left to right
		// assumes the previous square has a circle
		
		// check if squares are blocked
		for (int i : blocked) {
			if (i == s || i == s + 1 || i == s + numCols + 1 || i == s + (2 * numCols) + 1)
				return false;
		}
		// check that starting square is within the grid
		if (s < 1 || s > (numRows * numCols)) {
			return false;
		}
		
		// check there is enough space for C to the right (in that row)
		double rowNum = Math.ceil(s / numCols);
		System.out.println("rowNum is: " + rowNum);
		double spaceLeft = (rowNum * numCols) - s;
		if (spaceLeft < 1.0) {
			// not enough space
			return false;
		}
		// check there is enough space for B below that row
		if (rowNum + 2.0 > numRows) {
			return false;
		}
		return true;
	}

}