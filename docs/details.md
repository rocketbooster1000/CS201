# Details for P5 Percolation in Fall 2026

All coding aspects of the assignment, including workflow and the classes you use and write, are part of this document.

You should have already read the [README](../README.md) file to get a high-level overview of the project and early engagement points. The details here are needed to explain the classes you're given, the code you'll write, and the implementation details.

## Starter Code and Using Git

**_You should have installed all software (Java, Git, VS Code) before completing this project._** You can find 
the [directions for installation here](https://coursework.cs.duke.edu/201fall26/resources-201/-/blob/main/installingSoftware.md) (including workarounds for submitting without Git if needed).

We'll be using Git and the installation of GitLab at [coursework.cs.duke.edu](https://coursework.cs.duke.edu). All code for classwork will be kept here. Git is software used for version control, and GitLab is an online repository to store code in the cloud using Git.

**[This document details the workflow](https://coursework.cs.duke.edu/201fall26/resources-201/-/blob/main/projectWorkflow.md) for downloading the starter code for the project, updating your code on coursework using Git, and ultimately submitting to Gradescope for autograding.** We recommend that you read and follow the directions carefully this first time working on a project! While coding, we recommend that you periodically (perhaps when completing a method or small section) push your changes.


## Details on Git with a Partner

You may find it helpful to begin by reading the Working Together section of the [Git tutorial](https://gitlab.oit.duke.edu/academic-technology/cct/-/tree/master/git) from the Duke Colab. For more, see the [Git tutoraial by Gitlab](https://docs.gitlab.com/ee/tutorials/make_your_first_git_commit.html) including the link to an [extensive video tutorial](https://www.youtube.com/watch?v=4lxvVj7wlZw) if you prefer that.

One person should fork the starter code and then add their partner as a collaborator on the project. Choose Settings>Members>Invite Members. Then use the autocomplete feature to invite your partner to the project as a *maintainer*. Both of you can now clone and push to this project. See the [gitlab documentation here](https://docs.gitlab.com/ee/user/project/members/).

Now you should be ready to clone the code to your local machines.

1. Both students should clone the same repository and import it into VS Code just like previous projects.  
2. After both students have cloned and imported, one person should make a change (you could just write a comment in the code, for example). Commit and push this change. 
3. The other partner will then issue a git pull request. Simply use the command-line (in the same project directory where you cloned the starter code for the project) and type:
```bash
git pull
```
4. If the other partner now opens the project in VS Code again, they should see the modified code with the edit created by the first partner. 
5. You can continue this workflow: Whenever one person finishes work on the project, they commit and push. Whenever anyone starts work on the project, they begin by downloading the current version from the shared online repository using a git pull command.

This process works as long as only one person is editing at a time, and **you always pull before editing** and remember to **commit/push when finished**. If you forget to pull before editing your local code, you might end up working from an old version of the code different than what is in the shared online gitlab repository. If that happens, you may experience an error when you attempt to push your code back to the shared online repository. 

There are many ways to resolve these conflicts, essentially you just need to pick which of the different versions of the code you want to go with. See the [working together Git tutorial](https://gitlab.oit.duke.edu/academic-technology/cct/-/blob/master/git/working_together.md) and the [branching and merging Git tutorial](https://gitlab.oit.duke.edu/academic-technology/cct/-/blob/master/git/branching_merging.md) from the Duke Colab for more information. You can also refer to our [Git troubleshooting document](https://coursework.cs.duke.edu/201-public-documentation/resources-201/-/blob/main/troubleshooting.md#git-faq). 

If you run into a merge conflict, one thing that might be confusing is that the editor that opens where you can resolve them may, by default, be [VIM](https://www.vim.org), which can be very unintuitive if you have not used it before. You can either look up the basics there, or if you prefer you can set a different text editor as the default that git uses for editing commit messages, merge conflicts, etc. For example, to make Visual Studio Code the default editor (this was an optional step we suggested during installation, so you may have already done this):
1. Open the command palette on visual studio code (`shift` + `command` + `p` on Mac, or `shift` + `ctrl` + `p` on Windows).
2. Write `Shell Command: Install 'Code' command in path` - it should autocomplete to this, press enter. It may ask for your permission.
3. In a terminal, enter the command `git config --global core.editor "code --wait"`. Now VS Code should be the default editor for git.
4. You can confirm the change by trying `git config --global -e` in a terminal. This should open a VS Code window showing your `git config` file (you don't need to edit, this is just to confirm it worked).

The `--wait` command means that whenever `git` opens something in VS Code for you to edit, it will wait until you close that window/tab before proceeding.

**Important note: The `search` method will mark an open cell as full and then proceed to mark the open cells connected to that cell as full. As a result, you should terminate the method early (return ;) if the cell is full or blocked before creating the Queue.**

For this class, you'll @Override the `search` method to use
an explicit stack as shown in the [IterativeDFSBlobModel class](https://coursework.cs.duke.edu/201fall25/backtrack/-/blob/main/src/IterativeDFSBlobModel.java) with a stack of `int[]` objects just as in that code.

# Percolation Vocabulary and the Classes You Complete

In a system of rectangular/square NxN grid cells, each cell is sometimes referred to as a _site_ in the assignment and in supporting material, including this background.
- All sites start as **blocked**
- When a site is chosen at random it is **open**
- Open sites connected to the top row are **full**.
- A system in which there's a full cell in the bottom row (and thus a path of full cells from top to bottom) **percolates**.

Given a porous landscape with water on the surface (or oil below), under what conditions will the water be able to drain through to the bottom (or the oil to gush through to the surface)? Given a composite system composed of randomly distributed insulating and metallic materials, what fraction of the materials need to be metallic so that the composite system is an electrical conductor? Scientists have defined an abstract process known as percolation to model such situations.

We model a percolation system using an N-by-N grid of sites. **Each site is either open or blocked. A full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites.** In diagrams we color full sites blue to model water flowing from the top through the system. We say the **system percolates if there is at least one full site in the bottom row.** In other words, a system percolates if there is a path of open sites from the top row to the bottom row. For the porous substance example, the open sites correspond to empty space through which water might flow, so that a system that percolates lets water fill open sites, flowing from top to bottom.

For more on percolation see the [Princeton Case Study](http://introcs.cs.princeton.edu/java/24percolation/).

![An example of a percolating vs non-percolating system](./p6-figures/P6-percolates.png)

## Percolation Threshold

**(Rather than include this in the [the details document](docs/details.md) we include this information here. This project is a simulation).**

The percolation threshold problem is: if sites are independently set to be open with probability p (and therefore blocked with probability (1 − p), what is the probability that the system percolates? In other words, in a N-by-N grid, would the system percolate if N<sup>2</sup> randomly chosen cells are opened?  When p equals 0, the system does not percolate; when p equals 1, the system percolates. The plots below show the site vacancy probability p versus the percolation probability for 20-by-20 random grid (left) and 100-by-100 random grid (right).

![Threshold of percolating vs non-percolating system](./p6-figures/P6-threshold.png)

When N is sufficiently large, there is a threshold value `p*` (read this as p-star) such that when `p < p*` a random N-by-N grid almost never percolates, and when `p > p*`, a random N-by-N grid almost always percolates. No mathematical solution for determining the percolation threshold `p*` has yet been derived. Your task is to write a suite of computer programs to visualize the percolation process and estimate `p*` using Monte Carlo techniques. As you can see above, the percolation threshold in an NxN grid is about 0.593. The size of the grid doesn't matter as your simulations will show. 

### Helpful Videos 
The video [linked here](https://youtu.be/l4IyjqgYAT0) helps explain the techniques you'll read about (recorded for fall 2024). This video may be helpful after reading the assignment, or to get grounded before reading. This video may help situate the classes you write: `PercolationDFS`, `PercolationBFS`, and `PercolationUF`. 


### MazeDemo
The program `MazeDemo.java` within the project you get (`src/MazeDemo.java`) is a model for DFS, BFS, and Union-Find.  You should run the program to reinforce your understanding of these concepts. Two videos of solving a maze using DFS and BFS, respectively, can be found links below, looking at them and the source code for `MazeDemo.java` may help in understanding how the two searches work. 

- [dfs maze](https://www.youtube.com/watch?v=95igA_fWQtc)
- [bfs maze](https://www.youtube.com/watch?v=dffMgIjfczI)

# Overview: What to Do to Complete P5 Percolation

Here's a high-level view of the assignment. This is enough information to know what to do, but not necessarily how to do it. Each part is further explained below.

You'll create three new implementations of the `IPercolate` interface that you'll test, time, and answer questions about in analyzing tradeoffs (one of these is an optional challenge, and not required). 

1. Create class `PercolationDFS` that extends `PercolationDefault`. Implement one constructor, it simply calls `super` appropriately. You will implement one method, `@Override` the method `search` to use a Stack rather than recursion.
2. Create class `PercolationBFS` that extends `PercolationDefault`. Implement one constructor, it simply calls `super` appropriately. You will implement one method, `@Override` the method `search` to use BFS instead of DFS. 
3. **Challenge** (_12 engagement points_)Create class `PercolationUF` that implements the `IPercolate` interface. Implement a constructor and every method from the interface. Instance variables and methods are described in the [the details document](docs/details.md)

For all three classes, test the implementation using the JUnit test class `TestPercolate` by changing which of the three implementations is used. You **must also test using the `InteractivePercolationVisualizer` simulating the percolation using your own click-tests.**  Do not depend only on the JUnit tests.

For the analysis you will run `PercolationStats` and answer the analysis questions. 


## PercolationDFS

Your new class `PercolationDFS` will extend `PercolationDefault` and thus inherit state and methods from that class. That class has protected state, so can be extended.

1. You'll need to create a constructor with an int/size parameter whose only code is a call to `super(..)` to initialize the state in the parent class. 
2. You'll need to `@Override` `search` to use an explicit Stack for DFS rather than recursion.


You'll override the `search` method inherited from `PercolationDefault` to use a `Stack` rather than recursion in implementing a DFS approach. Use the ideas from the [BlobCounting code](https://coursework.cs.duke.edu/201fall26/backtrack/-/blob/main/src/BlobModel.java) we've seen in class, especially the [IterativeDFSBlobModel](https://coursework.cs.duke.edu/201fall26/backtrack/-/blob/main/src/IterativeDFSBlobModel.java). The `search` method you write **will be called from the inherited `updateOnOpen` from `PercolationDFSDefault.`** 

As an invariant, consider that **every cell on the stack should be `FULL`,** and you'll likely need to mark each `myGrid` cell as `FULL` before
pushing onto the stack.

See below on testing using the `InteractivePercolationVisualizer` and the JUnit testing class `TestPercolation` to test.

**Important note: The `search` method will mark an open cell as `FULL` and then proceed to mark the open cells connected to that cell as full. As a result, you should terminate the method early (return ;) if the cell is `FULL` or blocked before creating the Stack.**

## PercolationBFS

This class extends the `PercolationDFSDefault` class just as `PercolationDFS` does. 

1. You'll need to create a `PercolationBFS` constructor with an int/size parameter that calls super to initialize the state in the parent class.
2.  You'll need to `@Override` `search` to use a `Queue` just as you used a `Stack` for `PercolationDFS`.

You'll override the `search` method inherited from `PercolationDefault` to use a `Queue` and a breadth-first-search (BFS) approach. Use the ideas from the [BlobCounting code](https://coursework.cs.duke.edu/201fall26/backtrack/-/blob/main/src/BlobModel.java) we've seen in class, especially the [BFSBlobModel](https://coursework.cs.duke.edu/201fall26/backtrack/-/blob/main/src/BFSBlobModel.java). The `search` method you write **will be called from the inherited `updateOnOpen` from `PercolationDefault.`** 

See below on testing using the `InteractivePercolationVisualizer` and the JUnit testing class `TestPercolation` to test.

**Important note: The `search` method will mark an open cell as `FULL` and then proceed to mark the open cells connected to that cell as full. As a result, you should terminate the method early (return ;) if the cell is `FULL` or blocked before creating the Queue.**

As an invariant, consider that **every cell on the Queue should be `FULL`,** and you'll likely need to mark each `myGrid` cell as `FULL` before
adding to the queue.

## Testing implementations

For all three classes you write that extend `PercolationDefault` or implement the `IPercolate` interface.

3. Use  `InteractivePercolationVisualizer`,but you will need to change the line in `main` between lines 22-25 to create a `perc` object of type `PercolationDFS`  and then run this simulator to make sure it works. Similarly for `PercolationBFS` and `PercolationUF`. 
4. Modify and run `TestPercolation`. You will need to change the method `getPercolator` to return a `PercolationDFS` object.


## PercolationUF (Challenge, optional)

This class `implements` the `IPercolate` interface and will use an `IUnionFind` object to keep track of open and full cells. See the video at the beginning of the assignment for general ideas. You can also look at the `MazeDemo` class in the Percolate assignment to see how union-find can be used.

Each of the NxN cells is mapped to a number, and these numbers represent a cell's set for the disjoint-set/union-find algorithm. Initially every cell is a single number in its own set. You'll need two additional numbers, `VTOP` and `VBOTTOM`, for a total of N<sup>2</sup>+2 values. For information on these two extra numbers/variables, see [From DFS to Union-Find (Fall 2023): two approaches compared/contrasted](https://youtu.be/a_YMnW4hzmI).

### Instance variables for PercolationUF
1. A two-dimensional array of boolean values, `myGrid`, that represents whether a cell is open. Initially `myGrid[r][c]` should be `false` which is the default value when you create the grid. Each time a cell (r,c) is open, `myGrid[r][c]` will be set to true.
2. An integer `myOpenCount` which is the number of open cells, i.e., the number of true values in `myGrid`.
3. An `IUnionFind` object `myFinder` to store/reference the `IUnionFind` object passed to the constructor (which should be a `QuickUWPC` object in this assignment, you will see this later when you test it).
4. Two final values for `VTOP` and `VBOTTOM`, set to `size * size` and `size * size + 1`, for example, in the constructor. All instance variables should be private as shown.

<img src="p6-figures/P6-PercolationUF.png" width="400">

### Constructor for `PercolationUF`
Create a constructor `PercolationUF(IUnionFind finder, int size)` that will construct and initialize the NxN grid stored in the instance variable `myGrid` (where N is given by the `size` parameter). The constructor should initialize the `IUnionFind` object **of size NxN + 2** by calling `finder.initialize` appropriately and then storing this object in the appropriate instance variable which is `myFinder`.  Also initialize the remaining instance variables. This will be a total of five or six lines to initialize each of the five instance variables.

### Methods for `PercolationUF`
You must `@Override` each method from the `IPercolate` interface. As with methods you can see in `PercolationDFS`, these methods you write with (row,col) parameters should throw exceptions when the (row,col) are not in bounds. You may find it convenient to create your own **private or protected** helper method `inBounds` to make these checks as in `PercolationDFS`.

The `IUnionFind` classes use a single integer value to represent each set used in all methods like `find, union, connected`, and so on. You'll need to convert a (row,col) pair to a single integer. The simplest way to do this is to use `row*myGrid.length + col` -- you can see that this maps (0,0) to 0, and in a 10x10 grid will map (0,9) to 9, and (1,9) to 19.  

Be sure to deal with `VTOP` and `VBOTTOM` as well.

1. Method `isOpen` throws an `IndexOutOfBoundsException` (see `PercolationDFS` for example) when needed and otherwise simply returns the appropriate `myGrid` value.
2. Method `isFull` throws an `IndexOutOfBoundsException` (see `PercolationDFS` for example) when needed and otherwise checks if the (row,col) cell is connected to `VTOP` (convert (row,col) to an integer to call `myFinder.connected`).
3. Method `percolates` checks to see if `VTOP` is connected to `VBOTTOM` using `myFinder`.
4. Method `numberOfOpenSites` simply returns the value of the appropriate instance variable.
5. Method `open` throws an Exception when needed, checks to be sure the cell is not already open, and then marks the cell as open and connects with open neighbors as described below.

When a cell is marked as open, you'll write code to check each of the cell's four neighbors. If any of these cells is `OPEN`, the newly marked cell and the open neighbor should be connected by a call to `myFinder.union`. If the newly marked cell is in the top row it should be connected to `VTOP` by a call to `myFinder.union`. If the newly marked cell is in the bottom row it should be connected to `VBOTTOM` by a call to `myFinder.union`.

### Testing `PercolationUF`

See above for testing all implementations using `InteractivePercolationVisualizer` and `TestPercolation'

## Benchmarking and Analysis

Copy/Paste the runs described below and answer the questions that follow. You will answer these questions in a separate PDF document you submit to gradescope.

You're given `PercolationStats.java` which performs benchmarks for an `IPercolate` object using grid sizes of 100, 200, 400, 800, 1600, and 3200. If you don't change the value of the public `RANDOM_SEED` variable you should _**see the same mean values**_ of the Percolation threshold shown below. Your timing values may vary, but for all implementations using 10 trials you should see the same mean and standard deviation values. A sample run is provided below from running `PercolationDFS` on ola's laptop computer. **You will eventually reach a StackOverflowError because you’re running an infinite benchmark on a computer with finite memory. This is normal!**

### Expand for example simulation data

```
simulation data for 10 trials
grid	mean	stddev	total time
100	0.593	0.019	0.026
200	0.596	0.006	0.033
400	0.592	0.006	0.174
800	0.592	0.003	1.475

Exception in thread "main" java.lang.StackOverflowError
    at PercolationDFS.dfs(PercolationDFS.java:109)
    at PercolationDFS.dfs(PercolationDFS.java:109)
    at PercolationDFS.dfs(PercolationDFS.java:109)
    at PercolationDFS.dfs(PercolationDFS.java:110)
    at PercolationDFS.dfs(PercolationDFS.java:109)
    [... rest truncated]
```

## Analysis Template

Use this template for submitting your analysis on Gradescope [word template here](https://courses.cs.duke.edu/compsci201/spring26/assign/p5-analysis.docx)

Copy/paste the results for running `PercolationStats.java` for 10 trials on each `IPercolate` object (`PercolationDFS`, `PercolationBFS`, optionally `PercolationUF`) in your analysis document. 

So, first copy/paste data for the grid sizes shown above for all `IPercolate` classes you implement for this project. Then answer these questions using data from running `PercolationBFS` (and optionally `PercolationUF` with `QuickUWPC`).
1. How does doubling the grid size affect running time (keeping # trials fixed)? Specifically, by roughly what factor does the runtime increase each time we double the grid size?
2. How does doubling the number of trials, e.g., to 20 (and more) affect running time? Specifically, by roughly what factor does the runtime increase each time we double the number of trials?
3. Based on your observations of the timings with 10 trials, estimate the largest grid size you can run completely within at most 24 hours with 10 trials. Explain your reasoning.
4. Read this brief opinion article: [Cho and Cain, _Human-centered redistricting automation in the age of AI+, Science, September 2020](https://courses.cs.duke.edu/compsci201/spring26/assign/science-abd1879.pdf) about using statistical and computational techniques similar to those used in this assignment for socio-technical problems such as congressional districting. Write a paragraph about your thoughts after reading the article regarding the relationship between computational techniques and societal applications. **Do not use an LLM in writing.**

**After completing the analysis questions you should submit your answers as a PDF to the P5-analysis project on Gradescope.**

## Submitting and Grading

Push your code to Git. Do this often. Once you have run and tested your completed program locally:

1. Submit your code on gradescope to the autograder.
2. Submit a PDF to Gradescope in the separate Analysis assignment. Be sure to mark pages for the questions as explained in the [gradescope documentation here](https://help.gradescope.com/article/ccbpppziu9-student-submit-work#submitting_a_pdf).


