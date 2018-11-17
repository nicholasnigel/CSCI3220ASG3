# Main 

# Get Input: - - - - - - - - - - - - - - - - - - - - - - - - - -
num_states = input()
pi = []
for i in range(num_states):
    num = input()
    pi.append(num)


# initialize the transition table first :
transition_table = [[] for x in range(num_states)]
for i in range(num_states):
    for j in range(num_states):
        prob = input()
        transition_table[i].append(prob)

alphabet_to_index = {}
num_symbols = input()
symbols = []
for i in range(num_symbols):
    sym = raw_input()
    alphabet_to_index[sym] = i
    symbols.append(sym)

# size of emission_table is num_states x num_symbols 
emission_table = [[] for x in range(num_states)]

# emission_table[i][j] show the emission probability of ei(bj) probability state i produces output j with indexing starting at 0
for i in range(num_states):
        for j in range(num_symbols):
                prob = input()
                emission_table[i].append(prob)


sequence = raw_input()
# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
# Now constructing alpha_table, has dimension sequence.length x num_states
alpha_table = [[] for x in range(len(sequence))]

# First initialize the first row of the table 
for i in range(num_states):     # for first row, multiply inital prob * emission prob from that state
        result =  pi[i] * emission_table[i][alphabet_to_index[sequence[0]]]
        alpha_table[0].append(result)



# From here on find from row 2-> last row( which is length of string)
# entry = alpha(t-1,j) x ej(oi) x transition j->i
for i in range(1,len(sequence)):        # i correspond to row 
        for j in range(num_states):     # j correspond to each column
                sum = 0 
                emission_prob = emission_table[j][alphabet_to_index[sequence[i]]]
                # From all entries in i-1 find the stuff
                for k in range(num_states):
                        sum += alpha_table[i-1][k] * emission_prob * transition_table[k][j]
                alpha_table[i].append(sum)


# finally add the probabilty on last row
sum = 0 
for prob in alpha_table[len(sequence)-1]:
        sum += prob
print(sum)

 