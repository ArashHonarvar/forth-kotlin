# Forth exercise in Kotlin
The project involves implementing an evaluator for a subset of the Forth programming language. Forth is a stack-based language, which means that the program's state is maintained by a stack data structure. The evaluator will support basic integer arithmetic operations, including addition (+), subtraction (-), multiplication (*), and division (/). The evaluator will also support stack manipulation operations, including DUP, DROP, SWAP, and OVER.

In addition to the built-in words, the evaluator will support the creation of new words using the customary syntax of Forth. To define a new word, the user will use the syntax: : word-name definition ;.

The data type supported by the evaluator is signed integers of at least 16 bits in size. The syntax rules for the evaluator state that a number is a sequence of one or more ASCII digits, and a word is a sequence of one or more letters, digits, symbols, or punctuation that is not a number. Words are case-insensitive.

Overall, the goal of the project is to provide a simple implementation of a Forth-like language evaluator that supports basic arithmetic and stack manipulation operations and can define new words.
