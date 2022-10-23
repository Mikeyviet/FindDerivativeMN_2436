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
            public void setCoEff(int coEff) {
                this.coEff = coEff;
            }

            // Method to set the power of the term
            public void setPower(int power) {
                this.power = power;
            }

            // Method to get the coefficient of the term
            public int getCoEff() {
                return coEff;
            }

            // Method to get the power of the term
            public int getPower() {
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
         * @info This method will insert a term into the linked list. If the
         * list is empty, the new node will become the head and the tail will
         * point to the head. If the list is not empty, the new node will be
         * inserted at the end of the list. The method will return true if the
         * term was successfully
         *
         * @param coEff - the coefficient of the term
         * @param power - the power of the term
         * @return Boolean - true if the term was successfully inserted, false
         * if not
         */
        public Boolean insertTerm(int coEff, int power) {
            if (head == null)
            {
                head = new Term(coEff, power);
                tail = head;
            } else
            {
                tail.next = new Term(coEff, power);
                tail = tail.next;
            }
            // increase the total of terms in the list
            termCount++;
            return true;
        }

        // Method to print the contents of the linked list
        public void printList() {
            Term temp = this.head;
            while (temp != null)
            {
                System.out.print(temp.toString());
                if(temp != tail){
                    System.out.print(" + ");
                }
                temp = temp.next;
            }
        }

                
    }

    public static void main(String[] args) {

        // Create a new object of type PolynomialLL to store the polynomial expression
        PolynomialLL myPolynomial = new PolynomialLL();

        // Get the polynomial expression from the user
        getPolynomial(myPolynomial);
     
        
        // Calculate derivate of polynomial
        // differentiatePolynomial(myPolynomial);

        
        // Delete The lines below - Used for Reference
        // Display the polynomial expression
        System.out.print("The polynomial expression is: ");
        myPolynomial.printList();
        System.out.println();

    }

    /**
     * @name getTerm()
     * @info This method will provide a brief explanation on polynomials followed 
     *          by a prompt for the user to enter a polynomial expression based on 
     *          the format provided in the explanation.
     * @param expression Linked List structure to store the polynomial expression 
     *          that was entered by user.
     */
    public static void getPolynomial(PolynomialLL expression) {

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

        simplifyPolynomial(expression, Polynomial);

        
        // Delete This - Only for reference
        System.out.println("You entered: " + Polynomial);
        expression.printList();
        System.out.println();
    }

    /**
     * @name splitPolynomial
     * @info This method will split the string containing the polynomial
     * expression into individual terms and store them in a linked list
     * @param expressionLL Linked List structure to store the polynomial
     * expression
     * @param tempPolynomial string containing the polynomial expression
     */
    public static void simplifyPolynomial(PolynomialLL expressionLL, String tempPolynomial) {

        // Array to hold the terms of the polynomial for use with the simplification
        // - methods
        String[] numbers;

        // Split the polynomial into individual terms
        numbers = splitTerms(tempPolynomial);
                
        // Extract all the numbers from the terms 
        extractNumbers(expressionLL, numbers);
        
        // Sort the terms in the polynomial from largerst to smallest power
        sortTerms(expressionLL);    
        
        // Add all the "like" terms (terms containing variables with the same 
        // - degree) together in preparation of sorting
        addLikeTerms(expressionLL);
        
        
        System.out.println();
    }

    /**
     * @name splitTerms()
     * @info Method to split the polynomial into individual terms and store the 
     *          new terms in an array of strings
     * @param polynomial The string containing the polynomial expression received from
     *          input.
     * @return numbers The string array containing the individual terms retrieved
     *          from the polynomial.
     */
    public static String[] splitTerms(String polynomial){
        
        // Search and remove power symbol ('^') using the string replace() and
        // - split the polynomial using the string split() with look lookahead and lookbehind Regex
        // - as delimiters
        String[] parts = polynomial.replace("^", "").split("((?=\\+)|(?=\\-)|x)");
        
        // Create array to store the the terms after all the white spaces and 
        // - power symbols ('^') have been removed from them.
        String[] numbers = new String[parts.length];

        // Remove all blank spaces in each term with string.replaceAll().
        for (int i = 0; i < parts.length; i++)
        {
            numbers[i] = parts[i].replaceAll("\\s+", "");
        }        
        
        return numbers;
    }
    
    /**
     * @name extractNumbers()
     * @info Method extracts all the numerical values from the array of strings.
     *          The values are then inserted in the linked list as the 
     *          coefficient and power attributes.
     * @param expression The reference to the polynomial linked list
     * @param extractedNum The string array containing polynomial without 
     *          whitespaces and '^' symbols
     * @throws IllegalArgumentException() Exception thrown for data that is not
     *          a numerical value (integer)
     */
    public static void extractNumbers(PolynomialLL expression, String[] extractedNum) {

        // Extract all the numbers from the terms 
        // extractNumbers(expressionLL);
        // variable to hold the coefficient of the term
        int coefficient = 0;
        int exponent = 0;

        // Loop through the array and extract the coefficient and exponent of each term and 
        // - store them in the linked list
        for (int i = 0; i < extractedNum.length; i += 2)
        {
            try
            {
                // Extract the coefficient and exponent of the term
                coefficient = Integer.parseInt(extractedNum[i]);
                exponent = Integer.parseInt(extractedNum[i + 1]);

                // Insert the term into the linked list
                expression.insertTerm(coefficient, exponent);

            } catch (NumberFormatException e)
            {
                throw new IllegalArgumentException();
            }
        }

    }    /**
     * @name addLikeTerms()
     * @info Method will compare all the degrees of term variables for matching
     *      degrees and combine those terms by adding the term values together.
     * @param likePoly The linked list structure containing the polynomial 
     *      expression to be simplified.
     */
    public static void addLikeTerms(PolynomialLL likePoly){
        
        int tempTotalCoEff = 0;
        
        // A reference to the current term in the polynomial (linked list)
        PolynomialLL.Term currentTerm;
        
        PolynomialLL.Term prevTerm;
        
        PolynomialLL.Term nextTerm;
        
        // Point the currentTerm to the beginning of the linked list
        currentTerm = likePoly.head;
        nextTerm = currentTerm.next;

             
        prevTerm = null;
           
        while(currentTerm != null)
        {
            
            
            if(currentTerm.power == nextTerm.power)
            {
                tempTotalCoEff = currentTerm.coEff + nextTerm.coEff;
                likePoly.head.setCoEff(tempTotalCoEff);
                
                // Replace the head of the list
                likePoly.head = currentTerm.next;
                likePoly.termCount--;                         
            }
            
            prevTerm = currentTerm;
            currentTerm = currentTerm.next;     
        }
        
        if(currentTerm == null)
        {
            return;
        } 

            prevTerm.next = currentTerm.next;
            
            tempTotalCoEff = 0;
    }
    


    
    /**
     * @name sortTerms()
     * @info Method will sort the terms in the polynomial by the degrees of the
     *      variables from largest to smallest
     * @param sortedPoly 
     */
    public static void sortTerms(PolynomialLL sortedPoly){
        
        int tempPower = 0;
        int tempCoEff = 0;
        
        // A reference to the current term in the polynomial (linked list)
        PolynomialLL.Term currentTerm;
        
        PolynomialLL.Term nextTerm;
        
        // Point the currentTerm to the beginning of the linked list
        currentTerm = sortedPoly.head;

        
        while(currentTerm != null){
            // Move the reference to the next term in order to compare it with the
            // - first term.            
            nextTerm = currentTerm.next;
            
            while(nextTerm != null)
            {
                if(currentTerm.power < nextTerm.power)
                {
                    tempPower = currentTerm.power;
                    tempCoEff = currentTerm.coEff;
                    currentTerm.power = nextTerm.power;
                    currentTerm.coEff = nextTerm.coEff;
                    nextTerm.power = tempPower;
                    nextTerm.coEff = tempCoEff;
                }
                nextTerm = nextTerm.next;
            }
            currentTerm = currentTerm.next;
        }        
    }
    
    /**
     * @name differentiatePolynomial()
     * @info Method will calculate the derivative of the give polynomial
     * @param derivative 
     */
    public static void differentiatePolynomial(PolynomialLL derivative){
        
    } 

}
