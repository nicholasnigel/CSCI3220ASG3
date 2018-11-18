/**
CSCI3220 2018-19 First Term Assignment 3
I declare that the assignment here submitted is original except for source
material explicitly acknowledged, and that the same or closely related material
has not been previously submitted for another course. I also acknowledge that I
am aware of University policy and regulations on honesty in academic work, and
of the disciplinary guidelines and procedures applicable to breaches of such
policy and regulations, as contained in the following websites.
University Guideline on Academic Honesty:
http://www.cuhk.edu.hk/policy/academichonesty/
Student Name: Nigel Nicholas
Student ID : 1155088791
 */

import java.util.*;
/**
 *
 * @author nigelnicholas
 */
public class forward {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
        // Reading Inputs From Console
        Scanner reader = new Scanner(System.in);
        int num_states;
        double[] pi = new double[1000];   // minimum 1000 elements
        num_states = reader.nextInt();
        
        
        for(int i=0; i<num_states; i++){
            pi[i] = reader.nextDouble(); // fill in pi_table (where pi means the initial probability)
        }
        
        
        double[][] transition_table = new double[1000][1000];
        for(int i=0; i<num_states; i++){
            for(int j=0; j<num_states; j++){
                transition_table[i][j] = reader.nextDouble();
            }
        }       // fill in transition table
        
        
        int num_symbols;
        num_symbols = reader.nextInt(); // number of symbols
        String[] symbols = new String[4];
        
       
        
        for(int i=0; i<num_symbols; i++){
           symbols[i] = reader.next();          
              } // Example of symbol would be : A,C,G,T
        
        // combine all symbol into one
        StringBuilder builder = new StringBuilder();
        for(String s: symbols){
            builder.append(s);
        }
        String str = builder.toString(); // from all the characters, build a whole string -> "ACGT"
        
        
        
        double[][] emission_table = new double[1000][4];
        
        for(int i=0; i<num_states; i++){
            for(int j=0 ;j<num_symbols; j++){
                emission_table[i][j] = reader.nextDouble();
            }
        }
       
        
        String sequence = new String();
        sequence = reader.next();
        
        
        double[][] alpha = new double [sequence.length()][num_states]; // initialize alpha table, 
        
        // End of reading input from user console - - - - - - - - - - - - - - - - - - - -
        
        
        // Initializing first row:
        for( int i=0; i< num_states; i++){
            int index = str.indexOf(sequence.charAt(0));
            alpha[0][i] = pi[i] * emission_table[i][index];
        } // filling in the first row of the table with formula provided in lec note
        
       // filling in the entry from 2nd to last row
       for(int i =1; i<sequence.length(); i++){ // i to move the row number
           for(int j=0; j<num_states; j++){ // j to move the column number in a given row
               alpha[i][j] = 0.0;
               int index = str.indexOf(sequence.charAt(i));
               double emission_prob = emission_table[j][index];
               
               for(int k=0; k<num_states; k++){
                   // for all remaining table: fill in the table with formula in lec note that involves row-1 entries
                   alpha[i][j]+= alpha[i-1][k] * emission_prob* transition_table[k][j]; 
               }
               
           }
       }
       
       //get final answer by adding last row of alpha
       double final_answer= 0.0;    // initialize with 0
       
       for (int i=0; i<num_states ; i++) {
           final_answer+=alpha[sequence.length()-1][i];
       }
       
       System.out.println(final_answer); // final_answer will store the likelihood
       
        
    } // end of main fucntion
    
} // end of class
