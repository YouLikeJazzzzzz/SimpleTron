# SimpleTron (Java) — Minimal Virtual Machine

A compact educational virtual machine written in Java that simulates a simple computer architecture. Programs are entered as signed 4-digit integers and executed using a fetch–decode–execute cycle. This project demonstrates low-level concepts such as memory management, instruction sets, branching, and accumulator-based computation.

---

## How It Works

SimpleTron emulates a basic CPU with:

* **Memory:** 100 integer slots (`-9999` to `+9999`)
* **Accumulator:** Stores intermediate results
* **Instruction Counter:** Tracks current execution step

### Instruction Format

Each instruction is a 4-digit integer:

* First 2 digits → opcode
* Last 2 digits → memory address (operand)

Example:

```
2007
```

* `20` → LOAD
* `07` → load value from memory address 07 into the accumulator

---

## Instruction Set

| Opcode | Instruction | Description                        |
| ------ | ----------- | ---------------------------------- |
| 10     | READ        | Input value into memory            |
| 11     | WRITE       | Output value from memory           |
| 20     | LOAD        | Load memory into accumulator       |
| 21     | STORE       | Store accumulator into memory      |
| 30     | ADD         | Add memory value to accumulator    |
| 31     | SUBTRACT    | Subtract memory value              |
| 32     | DIVIDE      | Divide accumulator by memory value |
| 33     | MULTIPLY    | Multiply accumulator               |
| 40     | BRANCH      | Unconditional jump                 |
| 41     | BRANCHNEG   | Jump if accumulator < 0            |
| 42     | BRANCHZERO  | Jump if accumulator == 0           |
| 43     | HALT        | Stop program                       |

---

## How to Use

### 1. Compile and Run

```bash
javac SimpleTron.java
java SimpleTron
```

---

### 2. Enter a Program

* Enter one instruction per line
* Each must be a signed 4-digit integer
* Type `exit` to finish loading

Example:

```
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
```

---

### 3. Execution

* Program runs immediately after loading
* I/O occurs during execution
* Stops at `HALT (43)`

---

## Example Program: Add Two Numbers

```
00 1007   // READ into 07
01 1008   // READ into 08
02 2007   // LOAD 07
03 3008   // ADD 08
04 2109   // STORE result in 09
05 1109   // WRITE result
06 4300   // HALT
07 0000
08 0000
09 0000
```

---

## Debugging & Output

On completion or error, SimpleTron prints:

* Register state (accumulator, instruction counter, etc.)
* Full memory dump (10×10 grid)

Used to trace execution step-by-step.

---

## Key Rules

* Only 4-digit signed integers allowed
* Memory size is fixed at 100 locations
* Division by zero stops execution
* Invalid opcode triggers memory dump

---

## Purpose

Designed for learning:

* Assembly-level programming
* CPU execution cycles
* Memory operations
* Control flow and branching

---

## Possible Improvements

* File-based program loading
* Larger memory model
* Label-based assembly support
* Simple assembler for human-readable code
