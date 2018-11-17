
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// returning
int lookup(char x, char symbols[],int num_symbols){
    // get index of character x
    int num;
    for(int i=0; x<num_symbols; x++){
        if(strcmp(x,symbols[i])==0){
            num = i;
        }
    }
    return num;
}

int main() {
    // Handling Input:
    int num_states;
    scanf("%d", &num_states);
    
    double pi[num_states];
    for(int i=0 ; i<num_states; i++){
        scanf("%lf",&pi[i]);
    }
    
    double transition_table[num_states][num_states];
    
    for(int i =0; i< num_states; i++){
        for (int j =0 ; j<num_states ; j++ ){
            scanf("%lf", &transition_table[i][j]);
        }
    }
    
    int num_symbols; // number of symbols ex. A,C,G,T
    scanf("%d", &num_symbols);
    char symbol[num_symbols];
    
    for(int i=0 ; i<num_symbols; i++){
        scanf("%c",&symbol[i]);
    }
    
    double emission_table[num_states][num_symbols];
    
    for(int i =0; i<num_states; i++){
        for(int j=0 ; j<num_symbols; j++){
            scanf("%lf",&emission_table[i][j]);
        }
    }
    
    char sequence[100];
    scanf("%s", sequence);
    // END OF INPUT
    
    int seq_length;
    seq_length = strlen(sequence);
    
    double alpha_table[seq_length][num_states];
    
    for(int i=0; i<num_states; i++){
        alpha_table[0][i] = pi[i] * emission_table[i][lookup(sequence[i],symbol,num_symbols)];
    }
    
    for(int i=1; i<seq_length;i++){
        for( int j=0; j<num_states; j++){
            int sum = 0;
            for(int k =0; k<num_states;k++){
                sum += alpha_table[i-1][k]  * emission_table[j][lookup(sequence[i], symbol, num_symbols)] * transition_table[k][j];
            }
            alpha_table[i][j] = sum;
        }
    }
    
    double answer =0.0;
    for(int i=0 ;i<num_states; i++){
        answer+= alpha_table[seq_length][i];
    }
    
    printf("%lf",answer);
    return 0;
}
