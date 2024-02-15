package com;

import java.util.ArrayList;

public class Evaluate {

    ArrayList<Integer> addition;
    
    public Evaluate(Integer[] publicKey, ArrayList<Instruction> circuit, ArrayList<Integer> cipherBinary) {
        System.out.println("Evaluate");
        for (Integer component: cipherBinary) {
            for (int i = Integer.SIZE; i >= 0 ; i--) {
                int bit = (component >> i) & 1;
                System.out.print(bit);
            }
        }
    }

    public Evaluate(ArrayList<Integer> c1, ArrayList<Integer> c2) {
        addition = test(c1, c2);
    }

    public ArrayList<Integer> test(ArrayList<Integer> c1, ArrayList<Integer> c2) {
        System.out.println("Evaluate");
        ArrayList<Integer> encryptedAdditionBuilder = new ArrayList<>();

        for (int i = 0; i < c2.size(); i++) {
            if (c1.size() == i) {
                break;
            }
            //System.out.print(c2.get(i) + " + ");
            //System.out.print(c2.get(i) + " || ");
            encryptedAdditionBuilder.add(c2.get(i) + c1.get(i));
        }
        return encryptedAdditionBuilder;
    }

    public void display() {
        for (Integer bit: addition) {
            System.out.print(bit + " ");
        }
        System.out.println();
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