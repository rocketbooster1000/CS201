# Project 4: Autocomplete, Fall 2026

This is the directions document for Project 4 Autocomplete in CompSci 201 at Duke University, Fall 2026.


**See [the details document](docs/details.md) for information** on using Git, starting the project, and more details about the project including information about the classes and concepts that are outlined briefly below. You'll absolutely need to read the information in the [details document](docs/details.md) to understand how the classes in this project work independently and together. The _details_ document also contains project-specific details, this document provides a high-level overview of the assignment.


## Project Background and Concepts

Autocomplete is an algorithm used in many modern software applications. In all of these applications, the user types text and the application suggests possible completions for that text as shown in the example images below taken from google search.

The left/first was taken in March 2019, the right/second on October 9, 2020)
<div align="center">
  <img width="384" height="344 "src="p4-figures/googleSearch.png">
  <img width="384" height="345" src="p4-figures/googleSearch2.png">
</div>

The screen shot below/left is from October 2025, and  March 2026 on the right.
<div align="center">
  <img width="384" height="344 "src="p4-figures/googlesearch2025.png">
  <img width="384" height="344 "src="p4-figures/googlesearch2026.png">
</div>


Although finding terms that contain a query by searching through all possible results is possible, these applications need some way to select only the most useful terms to display (since users will likely not comb through thousands of terms, nor will obscure terms like "duke cookiemonster" be useful to most users). Thus, autocomplete algorithms not only need a way to find terms that start with or contain the prefix, but a way of determining how likely each one is to be useful to the user and displaying "good" terms first. This all needs to be done efficiently so that a user can see completions in real time.

In this project, you will leverage a `Comparator` in Java as well as the binary search algorithm on sorted data to implement an efficient autocompleter. You will create a second implementation based on a `HashMap`. You will then benchmark and analyze the tradeoffs of these implementations.  

According to one study, in order to be useful the algorithm must do all this in less than 100 milliseconds (see article linked below). If it takes any longer, the user will already be inputting the next keystroke (while humans do not on average input one keystroke every 50 milliseconds, additional time is required for server communication, input delay, and other processes). Furthermore, the server must be able to run this computation for every keystroke, for every user. In this assignment, you will be implementing autocomplete using three different algorithms and data structures. Your autocomplete will be different than the industrial examples described above in two ways:

1. Each term will have a predetermined, constant weight/likelihood, whereas actual autocomplete algorithms might change a term's likelihood based on previous searches.
2. We will only consider terms which start with the user query, whereas actual autocomplete algorithms (such as the web browser example above) might consider terms which contain but do not start with the query.

The article linked below describes one group's recent analysis of different data structures to implement autocomplete efficiently. You'll be implementing a version of what they call a prefix hash tree, though we'll use a prefix hash list which is more efficient when terms aren't updated dynamically.
- https://medium.com/@prefixyteam/how-we-built-prefixy-a-scalable-prefix-search-service-for-powering-autocomplete-c20f98e2eff1

### Acknowledgements
The assignment was originally developed by Kevin Wayne and Matthew Drabick at Princeton University for their Computer Science 226 class. Former head CompSci 201 UTAs, Arun Ganesh (Trinity '17) and Austin Lu (Trinity '15) adapted the assignment for Duke with help from Jeff Forbes. Josh Hug updated the assignment and provided more of the testing framework. Owen Astrachan added the prefix-hash-list (and removed the weighted trie).




## Analysis Questions and Benchmarking

For a template to use when submitting to Gradescope, [use this .docx](https://courses.cs.duke.edu/compsci201/spring26/assign/p4-analysis-template.docx). There's a PDF if you replace 
the `.docx` with `.pdf` in the URL.

You'll submit the analysis as a PDF separate from the code in Gradescope. 

**Question 1.** Copy/Paste the results of when you run benchmarking code in `BenchmarkForAutoComplete`. In that class code look at the instance variables for `BenchmarkForAutocomplete` and uncomment the two other implementation names so that `String[] myCompletorNames` has all three Strings: `"BruteAutocomplete"`, `"BinarySearchAutocomplete"`, and `"HashListAutocomplete"` (if you want to benchmark only a subset of these, perhaps because one isn't working, just leave it commented out).

Run `BenchmarkForAutocomplete` three times, once for each of the files you'll see in the Benchmark program: `threeletterwords.txt`, `fourletterwords.txt`, and `alexa.txt`. You can change which file is being used inside of the `doMark` method. **Copy and paste all three results into your analysis**. An example and detailed information about the output is described in the section below.

#### Benchmarking Details

On Professor Astachan's laptop, the first few lines are what's shown below for `data/threeletterwords.txt` (in addition, the `sizeInBytes` for the implementations are shown at the bottom). These numbers are for a file of every three letter word "aaa, "aab", … "zzy", "zzz", not actual words, but 3-character strings. All times are listed in seconds.

- The `init time` data shows how long it took to initialize the different implementations.
- The `search` column shows the prefix being used to search for autocompletions; unlabeled "search" is for an empty string `""` which matches on every term. 
- The `size` column shows how many terms have `search` as a prefix. This is described as `M` in the [details](docs/details.md) document.
- `#match` shows the number of highest weight results being returned by `topMatches`. This is described as `k` in the description of runtimes in the [details](docs/details.md) document.
- The next three columns give the running time in seconds for `topMatches` with the given parameters for the different implementations.


```
init time: 0.004612     for BruteAutocomplete
init time: 0.003348     for BinarySearchAutocomplete
init time: 0.03887      for HashListAutocomplete
search  size    #match  BruteAutoc      BinarySear      HashListAu
        17576   50      0.00191738      0.00306458      0.00001950
        17576   50      0.00039575      0.00198267      0.00000546
a       676     50      0.00034438      0.00014479      0.00000942
a       676     50      0.00035567      0.00015113      0.00000350
b       676     50      0.00016033      0.00011954      0.00000292
```

**Question 2.** Let `N` be the total number of terms, let `M` be the number of terms that prefix-match a given `search` term (the `size` column above), and let `k` be the number of highest weight terms returned by `topMatches` (the `#match` column above). The runtime complexity of `BruteAutocomplete` is `O(N + M log(k))`. The runtime complexity of `BinarySearchAutocomplete` is `O(log(N) + M log(k))`. Yet you should notice (as seen in the example timing above) that `BruteAutocomplete` is similarly efficient or even slightly more efficient than `BinarySearchAutocomplete` on the empty `search` String `""`. Answer the following:
- Explain why this observation (that `BruteAutocomplete` is similarly efficient or even slightly more efficient than `BinarySearchAutocomplete` on the empty `search` String `""`) makes sense given the values of `N` and `M` -- in other words, is this an anomaly based on small clocktimes or is there a reason for it given the values of N, M, and what will happen with BinarySearch when searching for the empty string? (Hint: how many terms match the empty prefix?)


**Question 3.** Run the `BenchmarkForAutocomplete` again using `alexa.txt` but doubling `matchSize` to `100` (`matchSize` is specified in the `runAM` method). Again copy and paste your results. Recall that `matchSize` determines `k`, the number of highest weight terms returned by `topMatches` (the `#match` column above). Do your data support the hypothesis that the dependence of the runtime on `k` is logarithmic for `BruteAutocomplete` and `BinarySearchAutocomplete`?

**Question 4.** Briefly explain why `HashListAutocomplete` is much more efficient in terms of the empirical runtime of `topMatches`, but uses more memory than the other `Autocomplete` implementations.


## Submitting and Grading 

Push your code to Git. Do this often. Once you have run and tested your completed program locally:

1. Submit your code on gradescope to the autograder.
2. Submit a PDF to Gradescope in the separate Analysis assignment. Be sure to mark pages for the questions as explained in the [gradescope documentation here](https://help.gradescope.com/article/ccbpppziu9-student-submit-work#submitting_a_pdf).

### Grading

| Points | Grading Criteria |
| ------ | ------ |
| 4 | Code Comparator|
| 8 |  Code for BinarySearchLibrary firstIndex and lastIndex |
| 6 | Code for BinarySearchAutocomplete.topMatches |
| 9 | Code for HashListAutocomplete |
| 1 | API |
| 8 | Analysis code and questions answered. UTAs will grade and comment on this |




