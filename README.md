# Project 6: Huffman Coding/Compression, Fall 2026

Welcome to P6!

You'll read this intro doc first to understand the background info necessary to complete this assignment. Once you've read this doc, navigate to the [details document](docs/details.md) for more information on the technical aspects on the projects. It covers information on using Git (with a partner), starting the project, and more details about the project including information about the classes and concepts that are outlined briefly below.


You are STRONGLY encouraged to work with a partner on P6! (as you were on P5 and will be on P7). See the [details document](docs/details.md) for information on using Git with a partner and how the workflow can proceed.


## Early Engagement

Read this [ChatGPT dialog](https://chatgpt.com/share/69173fa7-21d8-800f-9ab2-f5979b780158) that ola had on November 14, 2025. ChatGPT responded to five prompts/questions. Create a document in which you include each of the five prompts/questions and in which you summarize ChatGPT's response in a few sentences (do not write too much, be brief but thorough). Than use the Internet or any LLM to ask some question about Huffman Coding. In your document include your question and the answer(s) you found.

Submit via Gradescope before 12/4 at 11:59 pm for 12 engagment points (with a multiplicative factor of at least 3, e.g., 36 points). Use [this template](https://courses.cs.duke.edu/compsci201/spring26/assign/P6-engage.docx) to submit to Gradescope.

## Project Introduction

There are many techniques used to compress digital data (that is, to represent it using less memory). This assignment covers Huffman Coding, which is used everywhere from zipping a folder to jpeg and mp3 encodings. See the [details document](docs/details.md) for background on how the compression algorith was developed. **You should definitely use that document for background before programming.**

When you've read the description of the algorithm and data structures used you'll be ready to implement both decompression (a.k.a. uncompressing) and compression using Huffman Coding. You'll be using input and output or I/O classes that read and write 1 to many bits at a time, i.e., a single zero or one to several zeros and ones. This will make debugging your program a challenge.

### Post-reading/pre-coding self-assessment questions

You should be thinking about these questions before, during, and especially after writing your code to check your understanding:


1. Why are two passes over the input file to be compressed required when creating a compressed version of the input file?
2. What aspects of creating the Huffman tree from counts account for that process being a greedy algorithm?
3. At a high-level, how is the tree used to create 8-bit char/chunk encodings?
4. What is written first, after the magic number, in the compressed file?
5. Why are the bits written at the end of the compressed file representing PSEUDO_EOF required?
6. After reading the magic number and tree, how are the bits representing compressed data read when decompressing, e.g., how many bits are read each time the compressed data is accessed?

## Huffman Coding Background

(this backround is optional, but strongly encouragec)

Huffman coding was invented by David Huffman while he was a graduate student at MIT in 1950 when given the option of a term paper or a final exam. For details, see [this 1991 Scientific American Article](https://www.huffmancoding.com/my-uncle/scientific-american). In an autobiography Huffman had this to say about the epiphany that led to his invention of the coding method that bears his name:

"*-- but a week before the end of the term I seemed to have nothing to show for weeks of effort. I knew I'd better get busy fast, do the standard final, and forget the research problem. I remember, after breakfast that morning, throwing my research notes in the wastebasket. And at that very moment, I had a sense of sudden release, so that I could see a simple pattern in what I had been doing, that I hadn't been able to see at all until then. The result is the thing for which I'm probably best known: the Huffman Coding Procedure. I've had many breakthroughs since then, but never again all at once, like that. It was very exciting.*"

[The Wikipedia reference](https://en.wikipedia.org/wiki/Huffman_coding) is extensive as is [this online material](https://www.cs.duke.edu/csed/poop/huff/info/) developed as one of the original [Nifty Assignments][Nifty Assignments]. Both jpeg and mp3 encodings use Huffman Coding as part of their compression algorithms. In this assignment you'll implement a complete program to compress and uncompress data using Huffman coding.

## Huffman Algorithm

**You should read about the Huffman algorithm, and work to understand it.** You won't be able to complete the project easily without the overview you'll get from [this online material](https://www.cs.duke.edu/csed/poop/huff/info/)

When you've read the description of the algorithm and data structures used you'll be ready to implement both decompression (aka uncompressing) and compression  using Huffman Coding. You'll be using input and output or I/O classes that read and write 1 to many bits at a time, i.e., a single zero or one to several zeros and ones. This will make debugging your program a challenge.

### Acknowledgments and History

We first gave a Huffman coding assignment at Duke in Spring of 1994. Over the years many people have worked on creating the infrastructure for the bit-reading and -writing code (as we changed from C to C++ to Java at Duke) and the GUI that drives the Huffman assignment. It was one of the original so-called "nifty assignments" (see http://nifty.stanford.edu) in 1999. In Fall of 2018 we moved away from the GUI and using a simple main and the command-line. This was done for pragmatic and philosophical reasons. This README and [the details document](docs/details.md) have been revised to be more streamlined in Spring 2026 as suggested and implemented by UTA Sebastian deSouza.

## Completing the Assignments

Please navigate to the [the details document](docs/details.md) to complete the assignment. All coding details, analysis questions, and grading information are in that document.


