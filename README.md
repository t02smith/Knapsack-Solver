
# Knapsack Problem

The Knapsack Problem is about finding a subset of items such that your selection has
the maximum value possible whilst being under or equal to the given weight limit. I 
have solved it by modelling it as a boolean satisfiability problem and using a genetic
algorithm.

## Genetic Algorithm

### Chromosomes

Each chromosome is formulated as a binary number where each bit represents whether an item is
in the knapsack (1) or not (0). For example, if we had four items then the chromosome *1011* 
would mean that the *first*, *third* and *fourth* items are included in the knapsack 
but the *second* is omitted. 
\
\
Each chromosome will be part of a generation and is given a **fitness**, will undergo a **mutation** and will be part of a 
**crossover** with another gene.

### Fitness

Each gene is given a fitness based upon how good of a solution it is. \
For all given fitness algorithms, if the total weight of the items included is larger than
the max capacity of the knapsack then its fitness will be 0.

### Mutation

Mutations occur once per generation and are used to alter each gene in some way, with the 
hopes of creating a more optimal solution. In this repo, I have provided several different
mutations to try out:
- **Random**
    > Each gene in a chromosome will have a random chance to be flipped.
- **Fill Capacity**
    > Loops through the chromosome and if an item can be added to the knapsack without going
    > over the weight threshld then it is added.
- **Remove Least Valuable**
    > Chooses the item of least value per weight that is included in the knapsack and removes it.
- **Double Mutation**
    > Allows for multiple mutations to be performed per generation.

### Crossover

Crossover's will be performed after the mutations occur and will take two chromosomes and
swap some selection of genes. There are several different crossovers you can test:
- **Single Point**
    > Chooses a single point in the chromosome and swaps all genes betweens the two from that
    > gene to the end of the chromosome.
- **Double Point**
    > Chooses a start and end point and swaps all genes between the two chromosomes between 
    > the start and end index.
- **Uniform**
    > Each gene will have a random chance to be swapped with its counterpart in the other chromosome.

## Config

The config file will allow you to alter the genetic algorithm to your liking including:
- How many chromosomes make up each generation
- A maximum number of generations to complete
- The capacity of the knapsack
- A target value, which when reached will halt the program
- The list of items (which can be read from a file)
- The fitness, mutation and crossover algorithm used
