# Project 3: DNA

This is the directions document for Project 3 DNA in CompSci 201 at Duke University, Fall 2026.

**See [the details document](docs/details.md) for information** on using Git, starting the project, and more details about the project including information about the classes and concepts that are outlined briefly below. You'll absolutely need to read the information in the [details document](docs/details.md) to understand how the classes in this project work independently and together. The _details_ document also contains project-specific details, this document provides a high-level overview of the assignment.


## Project Background and Introduction

In this assignment you'll experiment with different implementations of a simulated [restriction enzyme cutting](https://en.wikipedia.org/wiki/Restriction_enzyme) (or cleaving) of a DNA molecule. In this context, you will study the application of the linked list data structure toward a real world problem in scientific computing.

Specifically, you will develop a `LinkStrand` class, an implementation of the `IDnaStrand` interface that uses an internal linked list to model recombinant DNA. Your implementation will be more efficient for modeling DNA splicing operations than using a String or StringBuilder (as in the provided `StringStrand` and `StringBuilderStrand` classes respectively, which provide correct but less efficient implementations of the `IDnaStrand` interface). You will benchmark and analyze the efficiency improvements of the `LinkStrand` implementation over those provided in the starter code when conducting simulations of gene splicing experiments.

The simulation coded here is a simplification of the chemical process, but provides an example of the utility of linked lists as a data structure for certain algorithmic processes.

### Engagement Points: Optional Historical Details (8 points)

This background is interesting, but not really needed to do the assignment. There are some good stories here, but if you want to get to the assignment, you can skip this section. However, *there are pre-project Engagement Points possible here.* 

[Three scientists shared the Nobel Prize](http://nobelprize.org/nobel_prizes/medicine/laureates/1978/press.html) in 1978 for the discovery of restriction enzymes. They're also an essential part of the process called [PCR polymerase chain reaction](http://en.wikipedia.org/wiki/Polymerase_chain_reaction) which is one of the most significant discoveries/inventions in chemistry and for which Kary Mullis won/shared the Nobel Prize in 1993.

Kary Mullis, the inventor of PCR, is an interesting character. To see more about him see this archived copy of a [1992 interview in Omni Magazine](https://web.archive.org/web/20010121194200/http://omnimag.com/archives/interviews/mullis.html) or his [personal website](http://karymullis.com/) which includes information about his autobiography Dancing Naked in the Mind Field, though you can read this free [Nobel autobiography](https://www.nobelprize.org/prizes/chemistry/1993/mullis/biographical/) as well. A copy of his autobiography may be accessible via [Dancing Naked in the Mind Field](https://pearl-hifi.com/11_Spirited_Growth/10_Health_Neg/05_Pandemics/AIDS/Mullis_1988__Dancing_Naked_in_the_Mind_Field_80pgs..pdf) without copyright permission.

The simulation is a simplification of the chemical process, but provides an example of the utility of linked lists in implementing a data structure. The linked list code you'll write and reason about is an example of a chunk list.

**For pre-coding engagement points (six): Read three (or more) of the articles/works above about Kary Mullis and PCR and then [complete this form](https://forms.cloud.microsoft/r/i4tQ7xwMph).** You can complete this anytime before the final deadline for the assignment.

### Early Engagement Benchmark Points (8 points)

You need to run the `DNABenchmark` (see below) code before you
write the `LinkStrand` implementation. If you copy/paste results
and answer two questions about them before Tuesday, March 3 midnight. Complete [the form here](https://forms.cloud.microsoft/r/JT479AcuhU).
 

### Grading


| Points | Grading Criteria |
| ------ | ------ |
| 16 | Code for LinkStrand. This includes 4 for correct and efficient implementation of .charAt method, as well as 2 for API consistency. |
| 15 | Analysis questions answered. UTAs will grade and comment on this.  |



