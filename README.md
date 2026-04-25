SimpleTron (Java) — Minimal Virtual Machine

A compact educational virtual machine written in Java that simulates a simple computer architecture. Programs are entered as signed 4-digit integers and executed using a fetch–decode–execute cycle. This project is useful for understanding low-level concepts like memory, instruction sets, branching, and accumulators.

How It Works

SimpleTron emulates a basic CPU with:

Memory: 100 integer slots (-9999 to +9999)
Accumulator: Stores intermediate results
Instruction Counter: Points to the current instruction
Instruction Format:
First 2 digits → opcode
Last 2 digits → operand (memory address)

Example instruction:

2007
20 → LOAD
07 → Load value from memory location 07 into the accumulator
Instruction Set
Opcode	Instruction	Description
10	READ	Input a value into memory
11	WRITE	Output a value from memory
20	LOAD	Load memory value into accumulator
21	STORE	Store accumulator into memory
30	ADD	Add memory value to accumulator
31	SUBTRACT	Subtract memory value from accumulator
32	DIVIDE	Divide accumulator by memory value
33	MULTIPLY	Multiply accumulator by memory value
40	BRANCH	Jump to location
41	BRANCHNEG	Jump if accumulator < 0
42	BRANCHZERO	Jump if accumulator == 0
43	HALT	End program
How to Use
1. Run the Program

Compile and execute:

javac SimpleTron.java
java SimpleTron
2. Enter a Program
Input instructions one per line
Each must be a signed 4-digit integer
Type exit when finished

Example:

00 ? 1007
01 ? 1008
02 ? 2007
03 ? 3008
04 ? 2109
05 ? 1109
06 ? 4300
07 ? 0000
08 ? 0000
09 ? 0000
exit
3. Execution
Program runs immediately after loading
Input/output happens during execution
Ends when HALT (43) is reached
Example Program: Add Two Numbers

Goal: Read two numbers and print their sum

00 1007   // READ into address 07
01 1008   // READ into address 08
02 2007   // LOAD value at 07
03 3008   // ADD value at 08
04 2109   // STORE result at 09
05 1109   // WRITE result
06 4300   // HALT
07 0000   // Data storage
08 0000
09 0000
Debugging & Output

On completion (or error), SimpleTron prints:

Register values (accumulator, instruction counter, etc.)
Full memory dump (10×10 grid)

This helps trace execution and debug programs.

Key Rules
All instructions must be 4-digit signed integers
Memory is limited to 100 locations
Division by zero halts execution with an error
Invalid opcodes trigger a dump for debugging
Purpose

Designed for learning:

Assembly-style programming
CPU execution cycles
Memory manipulation
Control flow (branching)
Possible Improvements
Add file-based program loading
Expand memory size
Implement labels instead of raw addresses
Add assembler for human-readable code
