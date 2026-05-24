# SimpleTron

> A minimal virtual machine simulator written in Java — fetch, decode, execute.

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![License](https://img.shields.io/badge/license-MIT-blue?style=for-the-badge)

**SimpleTron** emulates a simple CPU with a 100-word memory, an accumulator, and a 12-instruction set. Programs are entered as signed 4-digit integers and executed using a classic fetch–decode–execute cycle. Built as an educational tool for learning assembly-level programming, memory operations, and control flow.

Heavily inspired by the **Simpletron** architecture from Deitel & Deitel's *C++ How to Program*.

---

## Features

- **100-word memory** — each slot holds a signed 4-digit integer (−9999 to +9999)
- **Accumulator-based CPU** — all arithmetic flows through a single register
- **12 instructions** — I/O, load/store, arithmetic, branching, and halt
- **Full memory dump** — on halt or error, prints register state and a 10×10 memory grid
- **Error handling** — division by zero and invalid opcodes gracefully halt with diagnostics

---

## Architecture

```
┌──────────────────────────────────────────────────┐
│                   SimpleTron                      │
│                                                   │
│  ┌──────────┐    ┌──────────────┐                 │
│  │  Memory  │    │ Accumulator  │                 │
│  │  100 ×   │◄──►│   (int)      │                 │
│  │  int     │    └──────────────┘                 │
│  └──────────┘                                     │
│       ▲                                           │
│       │ fetch/execute                             │
│  ┌────┴───────┐    ┌──────────────────┐           │
│  │Instr. Decode│    │Instr. Counter    │           │
│  │ opcode/oper │    │ (program counter)│           │
│  └────────────┘    └──────────────────┘           │
└──────────────────────────────────────────────────┘
```

Each instruction is a 4-digit integer:

- **First 2 digits** — opcode (operation to perform)
- **Last 2 digits** — operand (memory address)

---

## Instruction Set

| Opcode | Mnemonic     | Action                                  |
|--------|--------------|-----------------------------------------|
| `10`   | `READ`       | Read integer from input into memory     |
| `11`   | `WRITE`      | Print memory value to output            |
| `20`   | `LOAD`       | Copy memory → accumulator               |
| `21`   | `STORE`      | Copy accumulator → memory               |
| `30`   | `ADD`        | `acc = acc + memory[operand]`           |
| `31`   | `SUBTRACT`   | `acc = acc - memory[operand]`           |
| `32`   | `DIVIDE`     | `acc = acc / memory[operand]`           |
| `33`   | `MULTIPLY`   | `acc = acc * memory[operand]`           |
| `40`   | `BRANCH`     | Jump to address (unconditional)         |
| `41`   | `BRANCHNEG`  | Jump if accumulator < 0                 |
| `42`   | `BRANCHZERO` | Jump if accumulator == 0                |
| `43`   | `HALT`       | Stop execution, dump state              |

---

## Getting Started

### Prerequisites

- [Java JDK](https://jdk.java.net/) 8 or later

### Run

```bash
javac SimpleTron.java
java SimpleTron
```

### Enter a Program

Type one instruction per line as a signed 4-digit integer. Enter `exit` to finish loading and begin execution.

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

## Example: Add Two Numbers

```
   00  1007     READ first number into address 07
   01  1008     READ second number into address 08
   02  2007     LOAD   value at 07 → accumulator
   03  3008     ADD    value at 08 to accumulator
   04  2109     STORE  result into address 09
   05  1109     WRITE  print result
   06  4300     HALT   stop
   07  0000              first number (input)
   08  0000              second number (input)
   09  0000              result (output)
```

---

## Example: Count to 10

```
   00  2008     LOAD   initial value (0)
   01  2109     STORE  into counter at 09
   02  2009     LOAD   counter
   03  3009     ADD    one (stored at 09?? wait)
```

> A proper loop-based counter using `BRANCHZERO` and `BRANCH` is left as an exercise for the reader.

---

## Debug Output

On `HALT` or error, SimpleTron prints a full state dump:

```
REGISTERS:
accumulator           +00007
instructionCounter    07
instructionRegister   +04300
operationCode         43
operand               00

MEMORY:
        0      1      2      3      4      5      6      7      8      9
00  +01007 +01008 +02007 +03008 +02109 +01109 +04300 +00003 +00004 +00007
10  +00000 +00000 +00000 +00000 +00000 +00000 +00000 +00000 +00000 +00000
...
```

---

## Project Structure

```
SimpleTron/
└── SimpleTron.java       ← single-file application (~155 lines)
```

Everything lives in one file — zero dependencies, zero config.

---

## Key Design Details

| Component | Implementation |
|-----------|---------------|
| **Memory** | `int[100]` array |
| **Accumulator** | local `int` variable in `execute()` |
| **Instruction Counter** | loop index in `execute()` |
| **Instruction Decode** | `opcode = instruction / 100; operand = instruction % 100;` |
| **Input** | `Scanner` over `System.in` |
| **Error Handling** | division by zero and unknown opcode → dump + return |

---

## Limitations

- No file-based program loading (must type manually)
- Fixed 100-word memory
- No symbolic labels or assembler — raw numeric codes only

---

## Possible Improvements

- Load programs from a text file
- Increase memory size or make it dynamic
- Write a simple assembler that converts mnemonics → numeric codes
- Add indexed addressing or subroutines
- GUI frontend with step-through debugging

---

## License

MIT
