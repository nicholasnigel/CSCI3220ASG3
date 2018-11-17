/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
            pi[i] = reader.nextDouble(); // get pi table
        }
        
        
        double[][] transition_table = new double[1000][1000];
        for(int i=0; i<num_states; i++){
            for(int j=0; j<num_states; j++){
                transition_table[i][j] = reader.nextDouble();
            }
        }       // get transition_table
        
        
        int num_symbols;
        num_symbols = reader.nextInt();
        String[] symbols = new String[4];
        
       
        
        for(int i=0; i<num_symbols; i++){
           symbols[i] = reader.next();          
              }
        
        // combine all symbol into one
        StringBuilder builder = new StringBuilder();
        for(String s: symbols){
            builder.append(s);
        }
        String str = builder.toString();
        
        
        
        double[][] emission_table = new double[1000][4];
        
        for(int i=0; i<num_states; i++){
            for(int j=0 ;j<num_symbols; j++){
                emission_table[i][j] = reader.nextDouble();
            }
        }
       
        
        String sequence = new String();
        sequence = reader.next();
        
        
        double[][] alpha = new double [sequence.length()][num_states]; // initialize alpha table, 
        
        // End of reading input from user console
        
        
        // Initializing first row:
        for( int i=0; i< num_states; i++){
            int index = str.indexOf(sequence.charAt(0));
            alpha[0][i] = pi[i] * emission_table[i][index];
        }// error here
        
       // filling in the entry from 2nd to last row
       for(int i =1; i<sequence.length(); i++){
           for(int j=0; j<num_states; j++){
               alpha[i][j] = 0.0;
               int index = str.indexOf(sequence.charAt(i));
               double emission_prob = emission_table[j][index];
               
               for(int k=0; k<num_states; k++){
                   alpha[i][j]+= alpha[i-1][k] * emission_prob* transition_table[k][j];
               }
               
           }
       }
       
       //get final answer by adding last row of alpha
       double final_answer= 0.0;
       
       for (int i=0; i<num_states ; i++) {
           final_answer+=alpha[sequence.length()-1][i];
       }
       
       System.out.println(final_answer);
       
        
    } // end of main fucntion
    
} // end of class
