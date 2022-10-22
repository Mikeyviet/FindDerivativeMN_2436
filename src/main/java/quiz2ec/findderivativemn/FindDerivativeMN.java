/**
 * @Name    Michael Nguyen
 * @Course COSC 2436
 * @Assign. Quiz 2 Extra Credit (Problem 1)
 * @Description Write a program to help find the derivative of polynomial
 * expressions with simple powers (no negative or fractional exponents). The
 * rules of finding a derivative are the following:
 *
 * • If the expression is a constant value, the derivative is zero. • If the
 * expression is any power in the form AX^N, where is A is a positive or
 * negative integer, and N is any positive integer, the derivative is equal to A
 * times N, times X raised to the power of N-1, or ANX^(N-1). • The derivative
 * of a sum of multiple operands is the sum of the derivatives of each operand.
 *
 * The program must implement the linked list data structure to store the
 * polynomial expression.
 */

package quiz2ec.findderivativemn;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;


/**
 * @Name FindDerivativeMN
 * @author Michael Nguyen
 * @info This class will encapsulate the linked list and nodes that will make up
 * the linked list containing all the terms of the polynomial expression.
 */
public class FindDerivativeMN {



    private static class PolynomialLL {
        private Term head;  // First term in the list (polynomial)
        private Term tail;  // Last term in the list (polynomial)
        private int termCount;  // Number of terms in the list (polynomial)
        
        // The object that will be used to store the two numbers (coefficient and exponent). This,
        // - along with the pointer to the next node, will be used to create the nodes (terms) of
        // - the linked list. I will use the term "term" to refer to the node and the object that
        // - it contains.

        private static class Term {
            int coEff;  // coefficient
            int power;  // power
            Term next;  // pointer to the next term in the polynomial list

            // Default constructor for Term
            Term() {
                coEff = 0;
                power = 0;
            }

            // Parameterized constructor for Term
            Term(int coEff, int power) {
                this.coEff = coEff;
                this.power = power;
            }

            // Method to set the coefficient of the term
            public void setCoEff(int coEff){
                this.coEff = coEff;
            }

            // Method to set the power of the term
            public void setPower(int power){
                this.power = power;
            }

            // Method to get the coefficient of the term
            public int getCoEff(){
                return coEff;
            }

            // Method to get the power of the term
            public int getPower(){
                return power;
            }

            // Method to overrides the toString() function to print contents of the term
            
            public String toString() {
                return " " + this.getCoEff() + "x^" + this.getPower();
            }
            
        }
        // Default constructor for PolynomialLL
        PolynomialLL() {
            head = null;
            tail = head;
            termCount = 0;
        }
        // Parameterized constructor for PolynomialLL
        PolynomialLL(int coEff, int power) {
            head = new Term(coEff, power);
            tail = head;
            termCount++;
        }

        /**
         * @Name insertTerm
         * @info This method will insert a term into the linked list. If the list is empty,
         *          the new node will become the head and the tail will point to the head. 
         *          If the list is not empty, the new node will be inserted at the end of 
         *          the list. The method will return true if the term was successfully
         * 
         * @param coEff - the coefficient of the term
         * @param power - the power of the term
         * @return boolean - true if the term was successfully inserted, false if not
         */
        public Boolean insertTerm(int coEff, int power){
            if(head == null){
                head = new Term(coEff, power);
                tail = head;
            }
            else{
                tail.next = new Term(coEff, power);
                tail = tail.next;
            }
            // increase the total of terms in the list
            termCount++;    
            return true;
        }

        // Method to print the contents of the linked list
        public void printList(PolynomialLL list){
            Term temp = head;
            while(temp != null){
                System.out.print(temp.toString());
                temp = temp.next;
            }
        }
    }
    
    private static class Parts{
        private String terms;
        
        // Constructor
        Parts(String terms){
            this.terms = terms;
        }
        
        // Method to set terms
        public void setTerms(String terms){
            this.terms = terms;
        }
        
        // Method to get terms
        public String getTerms(){
            return terms;
        }
        
        // Override constructor to print the data in object
        public String toString(){
            return this.terms;
        }
    }

    public static void main(String[] args) {

        // Create a new object of type PolynomialLL to store the polynomial expression
        PolynomialLL myPolynomial = new PolynomialLL();

        // Get the polynomial expression from the user
        getTerm(myPolynomial);

        // Display the polynomial expression
        System.out.print("The polynomial expression is: ");
        myPolynomial.printList(myPolynomial);


        System.out.println();
        
    }

    /**
     * @info This method will prompt user to enter a polynomial expression
     * @param expression structure to store the polynomial expression
     */
    public static void getTerm(PolynomialLL expression) {

//        PolynomialLL temp = expression;

        Scanner input = new Scanner(System.in);
        // Variable to hold the user's input
        String Polynomial = "";

        // Give brief explanation of definition and structure of a polynomial expression to give
        // - users a reference for how to input their expression.
        System.out.println("A polynomial is an expression that is the sum of two or more algebraic terms containing different powers of the same variable.\n");
        System.out.println("The format of the polynomial terms can be represented as: \n'AX^N + AX^M + AX^L + ...' where 'A' is the Coefficient and 'N', 'M', 'L' are the exponents (powers) of variable 'X'.\n");
        System.out.println("According to the given polynomial format of 'AX^N + AX^M + AX^L, an example of a polynomial expression would be '5x^2 + 12x^3 + 8x^4 + 15x^8'.\r");
        System.out.println("---------------------------------------------------------------");
        System.out.println("Please enter a polynomial expression in the format presented above. \n \tEach term and operation should be separated by a whitespace. \n \tAll values must be positive integers.");
        System.out.println("\nEnter Here ------------>");

        Polynomial = input.nextLine();

        System.out.println("You entered: " + Polynomial);
        
        // Split the input string into individual terms to store into a linked list
        splitPolynomial(expression, Polynomial);
        
        

        // print the linked list

    }

    /**
     * @name splitPolynomial
     * @info This method will split the string containing the polynomial expression into individual terms and store them in a linked list
     * @param expressionLL Linked List structure to store the polynomial expression
     * @param tempPolynomial string containing the polynomial expression
     */
    public static void splitPolynomial(PolynomialLL expressionLL, String tempPolynomial)
    {
        // variable to hold the coefficient of the term
        int coefficient = 0;
        int exponent = 0;
        
        // Create an array list of terms objects
//        ArrayList<Parts> TermsList = new ArrayList<Parts>();

        // Search and remove power symbol ('^') using the string replace() and
        // - split the polynomial using the string split() with look lookahead and lookbehind Regex
        // - as delimiters
        String[] parts = tempPolynomial.replace("^", "").split("((?=\\+)|(?=\\-)|x)");
        
        String[] numbers = new String[parts.length];
        
        // Remove all blank spaces in each term with string.replaceAll().
        for(int i = 0; i < parts.length; i++){
            numbers[i] = parts[i].replaceAll("\\s+", "");
        }
        
        // The polynomial string will be split up into individual terms using a "whitespace" 
        // - as the delimiter and will be stored in an array of strings called "parts"
        
//        
        // test code
        String testString = "12x^5 + 9x^4 + 26x^3 - 18x^2 + 10x^1 - 5x^0"; 
        System.out.println(java.util.Arrays.toString(testString.split("(?=[+-])")));

        

        // Loop through the array and extract the coefficient and exponent of each term and 
        // - store them in the linked list
        for (int i = 0; i < numbers.length; i += 2) {
            try {
                // Extract the coefficient and exponent of the term
                coefficient = Integer.parseInt(numbers[i]);
                exponent = Integer.parseInt(numbers[i + 1]);

                // Insert the term into the linked list
                expressionLL.insertTerm(coefficient, exponent);

            } catch (NumberFormatException e) {
                throw new IllegalArgumentException();
            }
        }



    }

}
