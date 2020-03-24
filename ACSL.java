import java.util.Stack;

public class ACSL {

public static void main(String[] args) {
		
		ACSL ee = new ACSL();
		
		String expr = "[2+3*8-3)]+6";
		char missingSymbol = ee.findMissingSymbol(expr);
		ee.acsl(expr, missingSymbol);
		
		expr = "[(2-5)+6";
		missingSymbol = ee.findMissingSymbol(expr);
		ee.acsl(expr, missingSymbol);
		
		expr = "[(5+5-2]*5";
		missingSymbol = ee.findMissingSymbol(expr);
		ee.acsl(expr, missingSymbol);
		
		expr = "13-[(6+18)/3*22";
		missingSymbol = ee.findMissingSymbol(expr);
		ee.acsl(expr, missingSymbol);
		
		expr = "[4/(12-8/4*25]";
		missingSymbol = ee.findMissingSymbol(expr);
		ee.acsl(expr, missingSymbol);
				

	}

	private void acsl(String str, char missingSymbol) {
		Stack<Integer> s = new Stack<Integer>();
		if(missingSymbol == '(') {
			int indexOfLeftB = str.indexOf('[');			
			for(int i = indexOfLeftB+1; i < str.length() && str.charAt(i) != ')'; i++) {
				
				StringBuffer sbuf = new StringBuffer();
                while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                		if (str.charAt(i+1) == ')') 
                			break;
                    sbuf.append(str.charAt(i++));
                }
                s.push(i);
			}
		} else if(missingSymbol == ')') {
			int indexOfLeftP = str.indexOf('(');
			
			for(int i = indexOfLeftP+1; i < str.length() && str.charAt(i) != ']'; i++) {
				
				StringBuffer sbuf = new StringBuffer();
                while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                		if (str.charAt(i+1) == ']') 
						break;
                    sbuf.append(str.charAt(i++));
                    
                }
                s.push(i);
			}
			
		}
		else if(missingSymbol == ']') {
			int indexOfRightP = str.indexOf(')');
			
			for(int i = indexOfRightP+1; i < str.length(); i++) {			
				StringBuffer sbuf = new StringBuffer();
                while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                	    sbuf.append(str.charAt(i++));
                    
                }
                s.push(i);
			}
			
		}
		
		System.out.println("index are "  + s);
	}

	private char findMissingSymbol(String str) {
		int cntOfleftP = 0;
		int cntOfrightP = 0;
		int cntOfleftB = 0;
		int cntOfrightB = 0;

		char missingSymbol = ' ';
		
		for (int i = 0; i < str.length(); i++) {
			switch (str.charAt(i)) {
			case '[':
				cntOfleftB++;
				break;
			case ']':
				cntOfrightB++;
				break;
			case '(':
				cntOfleftP++;
				break;
			case ')':
				cntOfrightP++;
				break;
			}
		}
		
		if(cntOfleftP > cntOfrightP)
			missingSymbol = ')';
		else if(cntOfleftP < cntOfrightP)
			missingSymbol = '(';
		else if(cntOfleftB > cntOfrightB)
			missingSymbol = ']';
		else if(cntOfleftB < cntOfrightB)
			missingSymbol = '[';
		
		return missingSymbol;
	}
}
