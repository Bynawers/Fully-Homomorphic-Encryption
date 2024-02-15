package com;

import java.util.ArrayList;

public class Evaluate {
    
    public Evaluate(Integer[] publicKey, ArrayList<Instruction> circuit, ArrayList<Integer> cipherBinary) {
        System.out.println("Evaluate");
        for (Integer component: cipherBinary) {
            for (int i = Integer.SIZE; i >= 0 ; i--) {
                int bit = (component >> i) & 1;
                System.out.print(bit);
            }
            //for (Instruction instruction: circuit) {

            //}
        }
    }
}

class Instruction {
    OperationType operation;
    Integer bit;
    Integer operand;

    public Instruction(OperationType operation, Integer bit, Integer operand) {
        this.operation = operation;
        this.operand = operand;
        this.bit = bit;
        Operation(bit, operand, operation);
    }

    public Integer Operation(Integer bit, Integer value, OperationType operation) {
        switch (operation) {
            case ADD:
                return bit + value;
            case MULT:
                return bit * value;
            default:
                throw new IllegalArgumentException("Type d'opération non supporté: " + this);
        }
    }
}

enum OperationType {
    ADD,
    MULT
}