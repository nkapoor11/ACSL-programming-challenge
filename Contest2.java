/**
 * This program solves the ACSL Contest 2 Programming Problem.
 * @author Neil Kapoor
*/

import java.util.Scanner;

public class Contest2 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a line of input, and press enter to see the output: ");
		String line1 = input.nextLine(); 
		boolean missingIsOpening = false; // tracks whether missing parenthesis/bracket is the opening or closing oone. 
		int bracketCount = 0;
		int [] bracketIndexes = new int[2]; // bracket index
		int [] parenthesisIndexes = new int[2]; // parenthesis index
		int [] outputLocations = new int[20]; // stores output locations. 
		
		int parenthesisCount = 0;
		
		// find if bracket or parenthesis is missing. 
		for (int i = 0, j = 0, k = 0; i < line1.length(); i++) {
			if ( (line1.substring(i, i+1)).equals("[") || (line1.substring(i, i+1)).equals("]") ) {
				bracketCount++;
				bracketIndexes[j] = i; 
				j++;
			}
			if ( (line1.substring(i, i+1)).equals("(") || (line1.substring(i, i+1)).equals(")") ) {
				parenthesisCount++;
				parenthesisIndexes[k] = i; 
				k++;
			}
		}
		if (bracketCount > parenthesisCount) {
			// determine whether missing parenthesis is "(" or ")".
			// use parenthesisIndexes array!
			for (int index : parenthesisIndexes) {
				if ( (line1.substring(index, index+1)).equals("(")) {
					// missing parenthesis is ")"
					missingIsOpening = false;
					//System.out.println("Here");
					break;
				} 
				else { 
					// missing parenthesis is "("
					missingIsOpening = true; 
				}
			}
			if (missingIsOpening) {
				// valid locations are between opening bracket and closing parenthesis. 
				for (int i = bracketIndexes[0], idx = 0; i < parenthesisIndexes[0]-1; i++) {
					if ( !(line1.substring(i, i+1)).equals("+") & !(line1.substring(i, i+1)).equals("*") 
						& !(line1.substring(i, i+1)).equals("-") & !(line1.substring(i, i+1)).equals("/") 
						& !(line1.substring(i, i+1)).equals("[") )   {			
						// index i values are valid for the missing opening parenthesis. 
						outputLocations[idx] = i;
						idx++;
					}
				}
				// print all output values except the last. 
				String out = "";
				for (int o = 0; o < outputLocations.length; o++) {			
					if (outputLocations[o] != 0 && outputLocations[o+1] == 0 )
						System.out.print(outputLocations[o] + 1);
					else if (outputLocations[o] != 0) {
						System.out.print((outputLocations[o] + 1) + ", ");
					}
				}
				System.out.println();
			}
			else {
				// valid locations are between opening parenthesis and closing bracket. 
				for (int i = parenthesisIndexes[0], idx = 0; i < bracketIndexes[1]+1; i++) {
					switch (line1.charAt(i)) {  
						case '+': 
							outputLocations[idx] = i; 
							idx++; break;
						case '-': 
							outputLocations[idx] = i; 
							idx++; break; 
						case '*': 
							outputLocations[idx] = i; 
							idx++; break;
						case '/': 
							outputLocations[idx] = i; 
							idx++; break;
						case ']': 
							outputLocations[idx] = i; 
							idx++; break;
					}					
				}
				// print all output values except the first. 
				String out = "";
				for (int o = 1; o < outputLocations.length; o++) {			
					if (outputLocations[o] != 0 && outputLocations[o+1] == 0 )
						System.out.print(outputLocations[o] + 1);
					else if (outputLocations[o] != 0) {
						System.out.print((outputLocations[o]+1) + ", ");
					}
				}
				System.out.println();			
			} // end of else block.
		} // end of if block.
		else {
			// determine whether missing bracket is "[" or "]".
			// use bracketIndexes array!
			for (int index : bracketIndexes) {
				if ( (line1.substring(index, index+1)).equals("[")) {
					// missing bracket is "]"
					missingIsOpening = false;
					break;
				} 
				else { 
					// missing bracket is "["
					missingIsOpening = true; 
				}
			}
			if (missingIsOpening) {
				// valid locations are beginning of line to opening parenthesis.
				// e.g. "3-(1+2)-31/5]*4": indexes allowed are 0, 2.  
				for (int i = 0, idx = 1; i < bracketIndexes[0]; i++) {
					if (i < parenthesisIndexes[0]+1) {
						switch (line1.charAt(i)) {  
							case '+': 
								outputLocations[idx] = i + 1; 
								idx++; break;
							case '-': 
								outputLocations[idx] = i + 1; 
								idx++; break; 
							case '*': 
								outputLocations[idx] = i + 1; 
								idx++; break;
							case '/': 
								outputLocations[idx] = i + 1; 
								idx++; break;	
						}	
					}
				}
				// add beginning-of-line location to outputLocations array. 
				outputLocations[0] = 0;
				// print all output values except the last. 
				String out = "";
				for (int o = 0; o < outputLocations.length; o++) {			
					if (outputLocations[o] != 0 && outputLocations[o+1] == 0 )
						System.out.print(outputLocations[o] + 1);
					else if (o == 0 | outputLocations[o] != 0) {
						System.out.print((outputLocations[o] + 1) + ", ");
					}
				}
					System.out.println();
			} // end of missingIsOpening block.			
			else {
				// valid locations are between closing parenthesis and end of line. 
				// 13-[1*(6+18)/32*22
				for (int i = parenthesisIndexes[1]+1, idx = 0; i < line1.length(); i++) {
					switch (line1.charAt(i)) {  
						case '+': 
							outputLocations[idx] = i; 
							idx++; break;
						case '-': 
							outputLocations[idx] = i; 
							idx++; break; 
						case '*': 
							outputLocations[idx] = i; 
							idx++; break;
						case '/': 
							outputLocations[idx] = i; 
							idx++; break;
						case ')': 
							outputLocations[idx] = i; 
							idx++; break;
					}						
				}
				// add end-of-line location to outputLocations array. 
				outputLocations[outputLocations.length-1] = line1.length();
				// print all output values except the first. 
				String out = "";
				for (int o = 0; o < outputLocations.length; o++) {			
					if (outputLocations[o] != 0 && (o == outputLocations.length-1)) 
						System.out.print(outputLocations[o] + 1);
					else if (outputLocations[o] != 0) {
						System.out.print((outputLocations[o]+1) + ", ");
					}
				}
				System.out.println();
			}
		}
	}
}