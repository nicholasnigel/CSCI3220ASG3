import sys
sys.setrecursionlimit(15000)
# Discontinue ones that have emission_prob | transition_prob = 0
def get_entry(i,j):
        if alpha_table[i][j] != -1:    # if there is entry found, then return it

                return alpha_table[i][j]
        elif i == 0 :                    # if  i is 0 this is base case
                alpha_table[i][j] = pi[j] * emission_table[j][alphabet_to_index[sequence[i]]]
                return alpha_table[i][j]
        else :                  # else if not base case,
                # Gettting entries from the i-1 row
                sum = 0 

                if emission_table[j][alphabet_to_index[sequence[i]]] == 0 :
                        alpha_table[i][j] = 0
                        return alpha_table[i][j]
                
                for entry in range(num_states): # entry : 0 -> num_states -1
                        if transition_table[entry][j] == 0:
                                sum += 0
                                continue
                        sum += get_entry(i-1, entry) * emission_table[j][alphabet_to_index[sequence[i]]] * transition_table[entry][j]
                alpha_table[i][j] = sum 
                return alpha_table[i][j]

                


# Main 

# Get Input: - - - - - - - - - - - - - - - - - - - - - - - - - -
num_states = eval(input())
pi = []
for i in range(num_states):
    num = eval(input())
    pi.append(num)


# initialize the transition table first :
# Read: transition_table[i][j] = prob. of moving from state i -> j 
transition_table = [[] for x in range(num_states)]
for i in range(num_states):
    for j in range(num_states):
        prob = eval(input())
        transition_table[i].append(prob)

alphabet_to_index = {}
num_symbols = int(input())
symbols = []
for i in range(num_symbols):
    sym = input()
    alphabet_to_index[sym] = i
    symbols.append(sym)

# size of emission_table is num_states x num_symbols 
emission_table = [[] for x in range(num_states)]

# emission_table[i][j] show the emission probability of ei(bj) probability state i produces output j with indexing starting at 0
for i in range(num_states):
        for j in range(num_symbols):
                prob = eval(input())
                emission_table[i].append(prob)


sequence = input()
# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
# Now constructing alpha_table, has dimension sequence.length x num_states
alpha_table = [[-1 for y in range(num_states)] for x in range(len(sequence))] # set up -1 as placeholder to know whether the entry is filled or not

# sum is addding all entry in the last row
sum = 0
for i in range(num_states):
        sum += get_entry(len(sequence)-1, i)


print(sum)
