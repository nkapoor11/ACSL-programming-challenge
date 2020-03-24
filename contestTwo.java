import java.util.Scanner;

public class contestTwo {

    public static void main(String[] args) {

        Scanner stdin = new Scanner(System.in);

        System.out.println("**************************************************************");
        System.out.println("* Welcome to the Symbol of Enclosure Finder for Expressions! *");
        System.out.println("*     Created by Reid Sox-Harris for the ACSL Contest #2     *");
        System.out.println("*       Version 0.1 Prealpha, still prone to explosions      *");
        System.out.println("**************************************************************");

        do {
            int pBegin = -1;
            int pEnd = -1;
            int bBegin = -1;
            int bEnd = -1;

            int result[] = {-1, -1, -1, -1, -1, -1};
            String input = null;
            int index = 0;

            if (args == null) {     //Accept input from arguments, if none exist use the Scanner
                input = args[0];
            } else {
                System.out.println("Enter expression:");
                input = stdin.nextLine().replaceAll("\\s+","");
            }

            if(!input.matches("^[0-9()*/+\\–\\-\\[\\]\\s]+$")) {       //Checks for non-correct characters
                System.out.println("Expression contains invalid characters. Please retype your equation only using 0-9, [, ], (, ), *, /, -, or +. All numbers must have two or fewer digits.");
                continue;
              }

            if(input.matches(".*[0-9]{3}.*")){      //Ensures there are at most two digits per number
                System.out.println("All numbers must have a maximum of two digits. Please retype your equation only using numbers with two or fewer digits.");
                continue;
            }

            for (short i = 0; i < input.length(); i++) {    //Determines the index of all encasing characters, defaults to -1
                if (input.charAt(i) == '[') {
                    bBegin = i;
                }
                if (input.charAt(i) == ']') {
                    bEnd = i;
                }
                if (input.charAt(i) == '(') {
                    pBegin = i;
                }
                if (input.charAt(i) == ')') {
                    pEnd = i;
                }
            }
            //TODO: Make all this less ugly!
            if (bBegin != -1 && bEnd != -1) {       //Start of cases where a parenthesis is needed
                if (pBegin != -1) { //Case for adding the ')'
                    for (int i = pBegin + 2; i < bEnd; i++) {
                        if (Character.isDigit(input.charAt(i)) && !Character.isDigit(input.charAt(i + 1)) && input.charAt(i - 1) != '(' && input.charAt(i - 2) != '(' && input.charAt(i - 1) != ']' ) {
                            result[index] = (i + 2);
                            index++;
                        }
                    }
                }
                if (pEnd != -1) { //Case for adding the '('
                    for (int i = bBegin; i < pEnd; i++) {
                        if (Character.isDigit(input.charAt(i)) && !Character.isDigit(input.charAt(i - 1)) && input.charAt(i + 1) != ')') {
                            result[index] = (i + 1);
                            index++;
                        }
                    }
                }
            }
            //TODO(REVISED): GIVE ME MORE THAN 12 HOURS FOR THIS FOR PRETTYNESS
            if (pBegin != -1 && pEnd != -1) {       //Start of cases where a bracket is needed
                if (bBegin != -1) { //Case for adding the ']'
                    for (int i = pEnd; i < input.length(); i++) {
                        char c = (i == input.length() - 1 ? 1 : input.charAt(i + 1));
                        if ((Character.isDigit(input.charAt(i)) || input.charAt(i) == ')') && !Character.isDigit(c) && input.charAt(i - 1) != '[') {
                            result[index] = (i + 2);
                            index++;
                        }
                    }
                }
                if (bEnd != -1) { //Case for adding the '['
                    for (int i = 0; i < pBegin; i++) {
                        if (Character.isDigit(input.charAt(i)) && !Character.isDigit(input.charAt(i - 1)) && input.charAt(i + 1) != ']') {
                            result[index] = (i + 2);
                            index++;
                        }
                    }
                }
            }
            //TODO(REV. 2): It works, just doesn't look nice. I'd call that a success.

            System.out.println("Values found:");       //Prints values
            for (int i = 0; i < index; i++) {
                System.out.print(result[i]);
                if(i != index - 1){System.out.print(", ");}
            }

            System.out.println();
            System.out.println("Would you like to try another expression? (y/n)");
            if(!stdin.nextLine().equalsIgnoreCase("y")){System.out.print("Exiting."); break;}
        } while(true);
    }
}
