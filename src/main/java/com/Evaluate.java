package com;

import java.util.ArrayList;

public class Evaluate {

    private ArrayList<Long> valueBinaryArray;
    
    public Evaluate(Integer[] publicKey, ArrayList<Instruction> circuit, ArrayList<Integer> cipherBinary) {

        for (Integer component: cipherBinary) {
            for (int i = Integer.SIZE; i >= 0 ; i--) {
                int bit = (component >> i) & 1;
            }
        }
    }

    public Evaluate(ArrayList<Long> c1, ArrayList<Long> c2) {
        valueBinaryArray = additionCrypted(c1, c2);
    }

    public ArrayList<Long> getValueBinaryArray() {
        return valueBinaryArray;
    }

    public ArrayList<Long> additionCrypted(ArrayList<Long> c1, ArrayList<Long> c2) {
        ArrayList<Long> ValueBinaryArrayBuilder = new ArrayList<>();
        long bitTmp = 0;

        System.out.println(c1.size());
        System.out.println(c2.size());
        for (int i = 0; i < c2.size(); i++) {
            if (i != c2.size() - 1) {
                bitTmp = c1.get(i) + c2.get(i) + deductionValue(i, c1, c2);
                System.out.println(c2.get(i) + " + " + c1.get(i) + " + (" + c2.get(i + 1)  + " * " + c1.get(i + 1) + ") = " + bitTmp);
                ValueBinaryArrayBuilder.add(bitTmp);
            }
            else {
                bitTmp = c2.get(i) + c1.get(i);
                ValueBinaryArrayBuilder.add(bitTmp);
            }
            //System.out.println(bitTmp);
        }

        return ValueBinaryArrayBuilder;
    }

    private Long deductionValue(Integer index, ArrayList<Long> c1, ArrayList<Long> c2) {
        long deduction = 0;
        for (int i = c2.size() - 1; i != 0 ; i--) {
            if (i > index) {
                System.out.print("X ");
                if (i < c2.size() - 1) {
                    deduction = deduction + c2.get(i + 1) * c1.get(i + 1);
                }
            }
        }
        return deduction;
    }

    public void display() {
        System.out.println("====== EVALUATE ======");
        System.out.print("> BINARY ARRAY : ");
        Utils.displayArray(valueBinaryArray);
        System.out.println("========================");
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