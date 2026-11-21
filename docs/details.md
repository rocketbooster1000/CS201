# Details for P6 Huffman Coding in Fall 2026

You should have read through the [README](../README.md) document to learn about P6 and Huffman coding before proceeding with code and analysis.


## Outline 

- [Starter Code and Using Git](#starter-code-and-using-git)
- [Details on Git with a Partner](#details-on-git-with-a-partner)
- [Part 0: Understanding and Running Starter Code](#part-0-understanding-and-running-starter-code)
- [Details on diff via the command line interface (CLI)](#details-on-diff-via-the-command-line-interface-cli)
- [`BitInputStream` and `BitOutputStream` Classes](#bitinputstream-and-bitoutputstream-classes)
- [Part 1: Implementing `HuffProcessor.decompress`](#part-1-implementing-huffprocessordecompress)
- [Part 2: Implementing `HuffProcessor.compress`](#part-2-implementing-huffprocessorcompress)
- [Analysis and Submission](#analysis--submission)
- [Appendix: How the Tree in `decompress` was generated](#appendix-how-the-tree-in-decompress-was-generated)


## Starter Code and Using Git

All coding aspects of the assignment, including workflow and the classes you use and write, are part of this document.

You should have already read the [README](../README.md) file to get a high-level overview of the project and early engagement points. The details here are needed to explain the classes you're given, the code you'll write, and the implementation details.

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



# Part 0: Understanding and Running Starter Code

Once you understand the Huffman coding algorithm, you should review this section to understand the organization of the starter code. For details on the `BitInputStream` and `BitOutputStream` classes, see the section below on these classes.


## Running Starter Code (with incomplete `HuffProcessor.decompress`)

**Do this after first cloning your forked repository.**

**Run `HuffMainDecompress`**. This prompts for a file to decompress, then calls `HuffProcessor.decompress`. You're given a stub version of that method; it initially ***simply copies the first file to another file***, it doesn’t actually decompress it. To make sure you know how to use this program, we recommend you run the program as you fork/cloned it and as described below.

Choose `mystery.jpg.hf` from the data folder to decompress (the `.hf` suffix indicates this has been compressed by a working `HuffProcessor.compress`). When prompted with a name for the file to save, use the default name the program supplies with a  `UHF prefix`, i.e., save with the name `UFH-mystery.jpg (that prefix is the default).  

Then run `diff` on the command line/terminal [see details below](#details-on-diff-via-the-command-line-interface-cli) for information on using `diff`). Use diff to compare two files: the original, `mystery.jpg.hf` and the uncompressed version: `UHF-mystery.jpg`. The `diff` program should _indicate_ these files are the same. This is because the code you first get from git simply copies the first file to another file, it doesn't actually decompress it. **You will use `diff` to check whether your implementation is working correctly locally, there are no JUnit tests for this project.**

The main takeaways here in running before implementing `HuffProcess.decompress` are to 
- Understand what to run when decompressing.
- Understand how to use `diff` on the command line to compare files.

## Details on diff via the command line interface (CLI)

There is a mac/unix command `diff` you can run in a terminal/bash shell on Mac/Windows (including the built-in terminal in VS Code). This command-line (CLI) `diff`  compares two files and indicates if they are the same bit-for-bit or not. First you must navigate to the directory/folder containing the files you would like to compare. You can use the following commands to navigate directories in your terminal:

- `pwd` stands for "print working directory." Entering it will print the path to your current directory (the folder you are in).
- `ls` stands for "list subdirectories." Entering it will print all files and subfolders in your current folder. 
- `cd` stands for "change direcotory. Entering it, followed by a subfolder name, will navigate to that subfolder. For example, `cd Documents`. Entering `cd` with nothing after will navigate to your home directory. Entering `cd ..` will navigate to the enclosing directory (that is, will "back up one level" in the folder hierarchy).

Once you are in the same folder as the files you would like to compare, you can type (terminal/shell): `diff foo.txt bar.txt` to compare `foo.txt` and `bar.txt`.

If the files are the same _nothing is printed_. If the files are different there's an indication of where they are different for text files, and just `different` if the files are binary/compressed/image files. For your purposes in P5 it isn't especially crucial that you understand the output printed by `diff` - generally you will just want to check if a decompressed file is exactly same as the original file before any compression/decompression.

## `BitInputStream` and `BitOutputStream` Classes

These two classes are provided to help in reading/writing bits in (un)compressed files. They extend the Java [InputStream](https://docs.oracle.com/javase/9/docs/api/java/io/InputStream.html) and [OutputStream](https://docs.oracle.com/javase/9/docs/api/java/io/OutputStream.html) classes, respectively. They function just like Scanner, except instead of reading in / writing out arbitrarily delimited “tokens”, they read/write a specified number of bits. Note that two consecutive calls to the `readBits` method will likely return different results since InputStream classes maintain an internal "cursor" or "pointer" to a spot in the stream from which to read -- and once read the bits won't be read again (unless the stream is reset).

The only methods you will need to interact with are the following:
1. *`int BitInputStream.readBits(int numBits)`*: This method reads from the source the specified number of bits and returns an integer. Since integers are 32-bit in Java, the parameter `numBits` must be between 1 and 32, inclusive. **It will return -1 if there are no more bits to read.**
2. `void BitInputStream.reset()`: This method repositions the “cursor” to the beginning of the input file.
3. `void BitOutputStream.writeBits(int numBits, int value)`: This method writes the least-significant `numBits` bits of the  parameter `value` to the output file.



<br><br><br>
 

## Debugging
It's not easy to debug when reading/writing bits at a time. You may choose to write _shadow_ print statements each time you read/write bits to echo to the screen what you're writing to a file. It will likely be helpful to print at different points where your code is failing or where an exception is thrown. Use the Ed discussion board and helper hours as well.
**See the [appendix section](#appendix-how-the-tree-in-decompress-was-generated) for important information on understanding this compression algorithm.


# Part 1: Implementing `HuffProcessor.decompress`

You should begin programming by implementing `decompress` first before moving on to `compress`. **You'll remove the code you're given intially** in `HuffProcessor.decompress` and implement code to actually **decompress** as described in this section. You **must remember to close the output file** before `decompress` returns. The call `out.close()` is in the code you're given, be sure it's in the code you write as well.

There are four conceptual steps in decompressing, outlined in text and code below. These are a useful source as you write code to decompress.

1. Read the 32-bit "magic" number as a check on whether the file is Huffman-coded (see lines 150-153 below)
2. Read the tree used to decompress, this is the same tree that was used to compress, i.e., was written during compression (helper method call on line 154 below).
3. Read the bits from the compressed file and use them to traverse root-to-leaf paths, writing leaf values to the output file. Stop when finding `PSEUDO_EOF` (hidden while loop on lines 156-174 below).
4. Close the output file (line 175 below).

We recommend using the code found below as a base/basis for your decompress method because it illustrates how to use the bit-stream classes and how to throw appropriate exceptions. However, you will not be penalized if you use an alternative method to code decompression.


<div align="center">
  <img width="837" height="254" src="../p6-figures/decompress.png">
</div>

To understand this in more detail, please review the explanation in the [Huffman coding writeup](https://www2.cs.duke.edu/csed/poop/huff/info/) -- in particular you'll need to know how the tree was written to write code that reads the tree back.


As you can see in the example above, a `HuffException` is thrown if the file of compressed bits does not start with the 32-bit value `HUFF_TREE`. Your code should also throw a `HuffException` if reading bits ever fails, i.e., the `readBits` method returns -1. That could happen in the helper methods when reading the tree and when reading the compressed bits.

## Reading the Tree (private HuffNode readTree)

Reading the tree using a helper method is essentially required since reading the tree, stored using a pre-order traversal, is much simpler with recursion. You don't have to use the names or parameters described above, though you can.
In the _201 Huffman Compression Protocol_, interior tree nodes are indicated by the single bit 0 and leaf nodes are indicated by the single bit 1. No bits/values are written after the `0` for internal nodes and a 9-bit value is written after the `1` for a leaf node. 

This leads to the following pseudocode to read the tree:

``` java
private HuffNode readTree(BitInputStream in) {
        bit = in.readBits(1);
        if (bit == -1) throw exception
        if (bit == 0) {
                left = readTree(...)
                right = readTree(...)
                 return new HuffNode(0,0,left,right);
        }
        else {
            value = read BITS_PER_WORD+1 bits from input
            return new HuffNode(value,0,null,null);
        }
  }
``` 

For example, the tree below corresponds to the bit sequence `0001X1Y01Z1W01A1B`, with each letter representing a 9-bit sequence to be stored in a leaf as shown in the tree diagrammed. You'll read these 9-bit chunks with an appropriate call of `readBits`. Rather than use 9, you should use `BITS_PER_WORD + 1`, the +1 is needed since one leaf stores `PSEUDO_EOF` so all leaf nodes store a 9-bit value. See the [appendix](#appendix-how-the-tree-in-decompress-was-generated) for a detailed explanation on how this tree was constructed.

### Example Tree

<div align="center">
  <img width="291" height="213" src="../p6-figures/huffheadtreeNODES.png">
</div>


### Reading Compressed Bits (while (true))

Once you've read the bit sequence representing the tree, you'll read the remaining bits from the `BitInputStream` representing the compressed file one bit at a time, traversing the tree from the root and going left or right depending on whether you read a zero or a one. This is represented in the pseudocode for `decompress` by the hidden while loop.

The pseudocode from the [Huffman coding writeup](https://www.cs.duke.edu/csed/poop/huff/info/)  is reproduced in the section below, this is the same code shown in that document -- it's not perfect Java, hence pseudocode. (Note: you break when reaching `PSEUDO_EOF`, and then no bits are written to the output file. Otherwise, when writing a value stored in a leaf to the output stream, you write 8, or `BITS_PER_WORD` bits).

### pseudocode example to decompress with tree

``` java
  HuffNode current = root; 
  while (true) {
      int bits = input.readBits(1);
      if (bits == -1) {
          throw new HuffException("bad input, no PSEUDO_EOF");
      }
      else { 
          if (bits == 0) current = current.left;
          else current = current.right;

          if (current is a leaf node) {
              if (current.value == PSEUDO_EOF) 
                  break;   // out of loop
              else {
                  write BITS_PER_WORD bits to output for current.value;
                  current = root; // start back after leaf
              }
          }
      }
  }
  close output file
```

## Testing the decompress method

To test whether your decompression code works we suggest uncompressing three files with the `.hf` suffix:
- `hidden1.txt.hf`, when decompressed to `UHF-hidden1.txt` it should be the same as the file `h1.txt`, verify with diff or by reading the file UHF-hidden1.txt, e.g., you can open it with a text editor or type `cat UHF-hidden1.txt` on the command line
- `hidden2.txt.hf`, when decompressed should be readable and the same as `h2.txt`.
- `mystery.jpg.hf`, when decompressed to `UHF-mystery.jpg` you should be able to open this .jpg file and hopefully smile in recognition.


<br><br><br>

# Part 2: Implementing `HuffProcessor.compress`

**Do not start compress until decompress works.**

There are five basic steps to compress a file that can then be decompressed using your already written decompress code.

1. Determine the frequency of every eight-bit character/chunk in the file being compressed (see line 78 below).
2. From the frequencies, create the Huffman trie/tree used to create encodings greedily (see line 79 below).
3. From the trie/tree, create the encodings for each eight-bit character chunk (see lines 83-84 below).
4. Write the magic number and the tree to the beginning/header of the compressed file (see lines 81-82 below).
5. Read the file again and write the encoding for each eight-bit chunk, followed by the encoding for PSEUDO_EOF, then close the file being written (not shown).

You won't need to throw exceptions for the steps outlined. A brief description of each step follows. More details can be found in the explanation of the Huffman algorithm in the [Huffman coding writeup](https://www.cs.duke.edu/csed/poop/huff/info/).

<div align="center">
  <img width="600" height="180" src="../p6-figures/compresscode.png">
</div>

## Determining Frequencies (private int[] getCounts)

Create an integer array that can store 256 values (use `ALPH_SIZE`). You'll read 8-bit characters/chunks, (using `BITS_PER_WORD` rather than 8), and use the read/8-bit value as an index into the array, incrementing the frequency of that 8-bit chunk. Conceptually this is a map from 8-bit chunk to an `int` frequency for that chunk, it's easy to use an array for this, mapping the index to the number of times the index occurs, e.g., `counts['a']` is the number of times 'a' occurs in the input file being compressed. The code you start with in compress (and decompress) illustrates how to read until the sentinel -1 is read to indicate there are no more bits in the input stream. 

## Making Huffman Trie/Tree (private HuffNode makeTree)

You'll use a greedy algorithm and a `PriorityQueue` of `HuffNode` objects to create the trie. Since `HuffNode` implements `Comparable` (using weight), the code you write will remove the minimal-weight nodes when `pq.remove()` is called as shown in the pseudocode included in the section below.

### makeTree pseudocode

``` java
PriorityQueue<HuffNode> pq = new PriorityQueue<>();
for(every index such that freq[index] > 0) {
    pq.add(new HuffNode(index,freq[index],null,null));
}
pq.add(new HuffNode(PSEUDO_EOF,1,null,null)); // account for PSEUDO_EOF having a single occurrence

while (pq.size() > 1) {
   HuffNode left = pq.remove();
   HuffNode right = pq.remove();
   // create new HuffNode t with weight from
   // left.weight+right.weight and left, right subtrees
   pq.add(t);
}
HuffNode root = pq.remove();
return root;
```

You'll need to ***be sure that `PSEUDO_EOF` is represented in the tree. *** As shown above, you should only add nodes to the priority queue for indexes/8-bit values that occur, i.e., that have non-zero frequencies/weights.


## Make Codings from Trie/Tree (private makeEncodings)

For this, you'll essentially implement a recursive helper method, similar to code for the [LeafTrails APT problem](https://www2.cs.duke.edu/csed/newapt/leaftrails.html). As shown in the example code for compress above, this method populates an array of Strings such that `encodings[val]` is the zero-one encoding of the 8-bit chunk val. The recursive helper method will have the array of encodings as one parameter, a node that's the root of a subtree as another parameter, and a string that's the path to that node as a string of zeros and ones. The first call of the helper method might be as shown, e.g., in the helper method `makeEncodings`.
``` java
    String[] encodings = new String[ALPH_SIZE + 1];
    makeEncodings(root,"",encodings);
```

In this method, if the `HuffNode` parameter is a leaf (recall that a leaf node has no left or right child), an encoding for the value stored in the leaf is added to the array, e.g.,
``` java
   if (isLeaf(root)) {
        encodings[root.value] = path;
        return;
   }
```
If the root is not a leaf, you'll need to make recursive calls adding "0" to the end of the path when making a recursive call on the left subtree and adding "1" to the end of the path when making a recursive call on the right subtree. Every node in a Huffman tree has two children. ***Be sure that you only add a single "0" for left-call and a single "1" for right-call. Each recursive call has a String path that's one more character than the parameter passed, e.g., path + "0" and path + "1".***

## Writing the Tree (private void writeTree)

Writing the tree is similar to the code you wrote to read the tree when decompressing. If a node is an internal node, i.e., not a leaf, write a single bit of zero and then make two recursive calls as part of the pre-order traversal. Otherwise, if the node is a leaf, write a single bit of one, followed by _nine bits_ of the value stored in the leaf.  This is a pre-order traversal: write one bit for the node, then make two recursive calls if the node is an internal node. **No recursion is used for leaf nodes.** You'll need to write 9 bits, or `BITS_PER_WORD + 1`, because there are 257 possible values including `PSEUDO_EOF`. To be safe, you should write nothing if the `HuffNode` parameter to `writeTree` is `null`, but this will not happen in practice since each internal node has two children.

## Writing Compressed Bits

After writing the tree, you'll need to read the file being compressed one more time. As shown above, the ***`BitInputStream` is reset, then all the 8-bit chunks are read again to compress it***. The first reading was to determine frequencies of every 8-bit chunk. The encoding for each 8-bit chunk read is stored in the array created when encodings were made from the tree. These encodings are stored as strings of zeros and ones, e..g., "010101110101". To convert such a string to a bit-sequence you can use `Integer.parseInt` specifying a radix, or base of two. For example, to write the encoding for the 8-bit chunk representing 'A', which has an ASCII value of 65, you'd use:
``` java
    String code = encoding['A']
    out.writeBits(code.length(), Integer.parseInt(code,2));
```
You'll use code like this for every 8-bit chunk read from the file being compressed. 
**You must also write the bits that encode `PSEUDO_EOF`, i.e.,**
``` java
    String code = encoding[PSEUDO_EOF]
    out.writeBits(code.length(), Integer.parseInt(code,2));
```
You'll write these bits _after_ writing the bits for every 8-bit chunk. The encoding for `PSEUDO_EOF` is used when decompressing, ***so you'll need to write the encoding bits before the output file is closed.***


# Analysis

You'll submit the analysis as a PDF separate from the code in Gradescope. If you are working with a partner, you and your partner should submit a single analysis document.

For the analysis questions, we will let $`N`$ be the number of total characters in a file to encode, and let $`M`$ be the total number of *unique* characters in the file. Note that both refer to the *non-compressed file*. Note that $`M \leq N`$. Define the *compression ratio* of a file to be the number of bits in the original file divided by the number of bits in the compressed file.

Use [this template for your analysis questions](https://courses.cs.duke.edu/compsci201/fall25/assign/p6-huffman-analysis.docx) when submitting to Gradescope.

Note that running the `HuffMainCompress` and `HuffMainDecompress` programs will print information to the terminal about the number of bits and the runtime of the compress and decompress algorithms.

**Question 1.** Suppose you want to compress two different files: `fileA` and `fileB`. Both have $`N`$ total characters and $`M`$ unique characters. The characters in `fileA` follow a uniform distribution, meaning each of the unique characters appears $`N/M`$ times. In `fileB`, the $`i`$'th unique character appears $`2^i`$ times (and the numbers add up to $`N`$), so some characters are much more common than others. Which file should achieve a higher compression ratio? Explain your answer. **The compression ratio is the size of the original file divided by the size of the compressed file.**

**Question 2.** Typically the number of total characters  $`N`$ is much greater than the number of unique characters, $`M`$ which is at most 256 when reading 8-bit chunks as with Huffman coding. Files are read twice when compressing: once to count/determine encodings, and once to actually write the encodings. Run your compression code on two images from the `data` folder: `oak-ridge.jpg` and `mtblanc.jpg`. In your analysis, include the time it takes to compress each of these files, what the compression ratio is: size of original/size of compressed. Do the same for the text file `hawthorne.txt` Can you make any conclusions about compression of images compared to text files? About the time to run compression on files? Discuss and justify your answers based on empirical/runtime data. 

**Question 3.** When running `decompress`, each character that is decompressed requires traversing nodes in the Huffman coding tree, and there are $`N`$ such characters. Run your `decompress` code on the images you compressed in the previous question. Make some conclusions about the runtimes of compressed images compared to compressed text files when decompressing? Justify your answers by runtimes as you can.

## Submitting and Grading

Push your code to Git. Do this often. To submit:

1. Submit your code on gradescope to the autograder. If you are working with a partner, refer to [this document](https://docs.google.com/document/d/e/2PACX-1vREK5ajnfEAk3FKjkoKR1wFtVAAEN3hGYwNipZbcbBCnWodkY2UI1lp856fz0ZFbxQ3yLPkotZ0U1U1/pub) for submitting to Gradescope with a partner. 
2. Submit a PDF to Gradescope in the separate Analysis assignment. Be sure to mark pages for the questions as explained in the [gradescope documentation here](https://help.gradescope.com/article/ccbpppziu9-student-submit-work#submitting_a_pdf). If you are working with a partner, you should submit a single document and [add your partner to your group on gradescope](https://help.gradescope.com/article/m5qz2xsnjy-student-add-group-members).

Points are awarded equally for compression and decompression. You'll get points for decompressing and compressing text and image files. These are 10 points each, for a total of 40 points possible on the code. The analysis is scored separately by TAs for a total of 12 points (4 per question).


# Appendix: How the Tree in `decompress` was generated

A 9-bit sequence represents a "character"/chunk stored in each leaf. This character/chunk, actually a value between 0-255, will be written to the output stream when uncompressing. One leaf stores PSEUDO_EOF, this won't be printed during uncompression, but will stop the process of reading bits.

<div align="center">
  <img width="291" height="213" src="../p6-figures/huffheadtreeNODES.png">
</div>

When you read the first 0, you know it's an internal node (it's a 0), you'll create an internall `HuffNode` node, and recursively read the left and right subtrees. The left subtree call will read the bits 001X1Y01Z1W as the left subtree of the root and the right subtree recursive call will read  01A1B as the right subtree. Note that in the bit-sequence representing the tree, a single bit of 0 and 1 differentiates INTERNAL nodes from LEAF nodes, not the left/right branch taken in uncompressing -- that comes later. The internal node that's the root of the left subtree of the overall root has its own left subtree of 01X1Y and a right subtree of 01Z1W. When you read the single 1-bit, your code will need to read 9-bits to obtain the value stored in the leaf.


