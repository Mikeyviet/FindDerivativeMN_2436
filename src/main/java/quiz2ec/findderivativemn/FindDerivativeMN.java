
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

import java.util.*;

/**
 * @Name FindDerivativeMN
 * @author Michael Nguyen
 * @info This class will encapsulate the linked list and nodes that will make up
 * the linked list containing all the terms of the polynomial expression.
 */
public class FindDerivativeMN {



    private class PolynomialLL {
        private Term head;  // First term in the list (polynomial)
        private Term tail;  // Last term in the list (polynomial)
        
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

        }
        // Default constructor for PolynomialLL
        PolynomialLL() {
            head = null;
            tail = head;
        }
        // Parameterized constructor for PolynomialLL
        PolynomialLL(int coEff, int power) {
            head = new Term(coEff, power);
            tail = head;
        }
    }

    public static void main(String[] args) {

        PolynomialLL myPolynomial = new PolynomialLL<>();
        Term<Term> Term = new Term<>();

        myPolynomial = getTerm(myPolynomial);

    }

    /**
     * @info This mΩethod will prompt user to enter a polynomial expression
     * @param expression structure to store the polynomial expression
     */
    public PolynomialLL getTerm(PolynomialLL expression) {

        PolynomialLL temp = expression;
        
        // Give brief explanation of definition and structure of a polynomial expression to give
        // - users a reference for how to input their expression.
        System.out.println("A polynomial is an expression that is the sum of two or more algebraic terms containing different powers of the same variable.\n");
        System.out.println("The format of the polynomial terms can be represented as: \n'AX^N + AX^M + AX^L + ...' where 'A' is the Coefficient and 'N', 'M', 'L' are the exponents (powers) of variable 'X'.\n");

        System.out.println("According to the given polynomial format of 'AX^N + AX^M + AX^L, an example of a polynomial expression would be '5x^2 + 12x^3 + 8x^4 + 15x^8'.\r");
        System.out.println("---------------------------------------------------------------");
        System.out.println("Please enter a polynomial expression in the format presented above. \n \tEach term and operation should be separated by a whitespace. \n \tAll values must be positive integers.");
        System.out.println("\nEnter Here ------------>");

        Scanner input = new Scanner(System.in);


        return temp;
    }

}
