# Project 5: Percolation

This is the directions document for Project 5 Percolation in CompSci 201 at Duke University, Spring 2026.


**See [the details document](docs/details.md) for information** on using Git, starting the project, and more details about the project including information about the classes and concepts that are outlined briefly below. You'll absolutely need to read the information in the [details document](docs/details.md) to understand how the classes in this project work independently and together. The _details_ document also contains project-specific details. This current document provides a high-level overview of the assignment.

**You are STRONGLY encouraged to work with a partner on P5!** (and on P6 and P7). See [the details document](docs/details.md) for information on using Git with a partner and how the workflow can proceed. If you'd like to be paired (somewhat randomly, but you can write about yourself or a partner) then fill out this form to request a pairing (coming soon).

*You can earn early engagement points as described below.*


## Project Introduction

In this assignment, you will write a program to estimate the value of the [percolation threshold](http://en.wikipedia.org/wiki/Percolation_thresholds) via [Monte Carlo](http://en.wikipedia.org/wiki/Monte_Carlo_method) simulation. In doing so, you will better understand depth-first-search, breadth-first-search, union-find structures, and the use of computer simulations for statistical inquiry. _**Your goal will be to explore trade-offs in several approaches to estimate the percolation threshold in an NxN system.**_ 

### Acknowledgements 
This assignment originated at Princeton; thanks to Kevin Wayne from whom staff at Duke first learned of it, and to Jeff Forbes when he was at Duke for ensuring we kept it as part of the assignment-stack at Duke. We've revised it at Duke to be about trade-offs, DFS, BFS, and tangentially about Union-Find.






## Early Engagement Points (6)

Fork and clone the program and then run the `InteractivePercolationVisualizer`. Click the minimum number of squares until the grid percolates and record that number, call it $N$. Then determine how many different ways you can make the system percolate with only clicking $N$ squares.

Next, determine if it's possible to click on O(N<sup>2</sup>) squares _without_ the system percolating. Write a few sentences on how it's possible to do that, or write an argument that it's not possible. Hint: $N^2/2$ is O(N<sup>2</sup>), think about a chess board.

You'll submit the answers to these questions on [a form linked here](rick roll). You must submit by **FILL THIS DATE IN**, for engagement points.




For this project, the grading will be:

| Points | Grading Criteria |
| --- | --- |
| 8 | PercolationDFS |
| 8 | PercolationBFS |
| 0 | PercolationUF (12 engagement/challenge) |
| 8 | Analysis  |

# Autograder API Checker
We use an API checker to check that you implement the appropriate instance variables and methods for each method. Attached below is a sample output response from a head UTA’s test submission. 

![Sample API Checker from Head UTA's Computer](./p6-figures/P6-API.png)

In order to resolve this issue, make sure that if you’ve added any instance variables or helper methods not specified in the writeup that you change them to private. 
