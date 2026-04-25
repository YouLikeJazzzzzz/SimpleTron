import java.util.Scanner;

public class SimpleTron {

    private static final int READ = 10;
    private static final int WRITE = 11;
    private static final int LOAD = 20;
    private static final int STORE = 21;
    private static final int ADD = 30;
    private static final int SUBTRACT = 31;
    private static final int DIVIDE = 32;
    private static final int MULTIPLY = 33;
    private static final int BRANCH = 40;
    private static final int BRANCHNEG = 41;
    private static final int BRANCHZERO = 42;
    private static final int HALT = 43;

    private static final int MEMORY_SIZE = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] memory = new int[MEMORY_SIZE];

        System.out.println("*** SimpleTron Ready ***");
        System.out.println("Enter instructions (type 'exit' to finish):");

        int location = 0;

        while (location < MEMORY_SIZE) {
            System.out.printf("%02d ? ", location);
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) break;

            try {
                int value = Integer.parseInt(input);
                if (value < -9999 || value > 9999) {
                    System.out.println("Value must be a 4-digit signed number.");
                    continue;
                }
                memory[location++] = value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a signed 4-digit integer.");
            }
        }

        System.out.println("*** Program Loaded ***");
        execute(memory, scanner);
    }

    private static void dump(int[] memory, int accumulator, int instructionCounter, int opcode, int operand) {
        System.out.println();
        System.out.println("REGISTERS:");
        System.out.printf("accumulator           %+05d%n", accumulator);
        System.out.printf("instructionCounter    %02d%n", instructionCounter);
        System.out.printf("instructionRegister   %+05d%n", opcode * 100 + operand);
        System.out.printf("operationCode         %02d%n", opcode);
        System.out.printf("operand               %02d%n", operand);

        System.out.println();
        System.out.println("MEMORY:");
        System.out.println("        0      1      2      3      4      5      6      7      8      9");

        for (int row = 0; row < 100; row += 10) {
            System.out.printf(
                    "%02d  %+05d %+05d %+05d %+05d %+05d %+05d %+05d %+05d %+05d %+05d%n",
                    row,
                    memory[row], memory[row+1], memory[row+2], memory[row+3], memory[row+4],
                    memory[row+5], memory[row+6], memory[row+7], memory[row+8], memory[row+9]
            );
        }
        System.out.println();
    }

    private static void execute(int[] memory, Scanner scanner) {
        int accumulator = 0;
        int instructionCounter = 0;

        while (instructionCounter < MEMORY_SIZE) {
            int instruction = memory[instructionCounter];
            int opcode = instruction / 100;
            int operand = instruction % 100;

            switch (opcode) {
                case READ:
                    System.out.print("Input: ");
                    memory[operand] = scanner.nextInt();
                    break;

                case WRITE:
                    System.out.println("Output: " + memory[operand]);
                    break;

                case LOAD:
                    accumulator = memory[operand];
                    break;

                case STORE:
                    memory[operand] = accumulator;
                    break;

                case ADD:
                    accumulator += memory[operand];
                    break;

                case SUBTRACT:
                    accumulator -= memory[operand];
                    break;

                case MULTIPLY:
                    accumulator *= memory[operand];
                    break;

                case DIVIDE:
                    if (memory[operand] == 0) {
                        System.out.println("Error: Divide by zero.");
                        dump(memory, accumulator, instructionCounter, opcode, operand);
                        return;
                    }
                    accumulator /= memory[operand];
                    break;

                case BRANCH:
                    instructionCounter = operand;
                    continue;

                case BRANCHNEG:
                    if (accumulator < 0) {
                        instructionCounter = operand;
                        continue;
                    }
                    break;

                case BRANCHZERO:
                    if (accumulator == 0) {
                        instructionCounter = operand;
                        continue;
                    }
                    break;

                case HALT:
                    System.out.println("*** Program Terminated Normally ***");
                    dump(memory, accumulator, instructionCounter, opcode, operand);
                    return;

                default:
                    System.out.println("Invalid opcode at " + instructionCounter);
                    dump(memory, accumulator, instructionCounter, opcode, operand);
                    return;
            }

            instructionCounter++;
        }
    }
}
