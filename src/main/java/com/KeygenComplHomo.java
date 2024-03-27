package com;

import java.util.Random;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class KeygenComplHomo {

    Keygen keys = new Keygen();
    private BigInteger Xp = (BigInteger.valueOf(2).pow(Parameters.KEPPA)).divide(keys.getPrivateKey());
    private int[] setS = new int[Parameters.theta];

    public int[] privateKeyGen() {   
        // Initialisation du vecteur avec tous les bits à 0
        int[] vectS = new int[Parameters.Theta];
        
        // Génération aléatoire des indices où placer les bits à 1
        Random random = new Random();
        for (int i = 0; i < Parameters.theta; i++) {
            int index = random.nextInt(Parameters.Theta);
            // S'assurer que l'index n'a pas déjà été choisi
            while (vectS[index] == 1) {
                index = random.nextInt(Parameters.Theta);
            }
            vectS[index] = 1;
            this.setS[i] = index;
        }

        return vectS;
    }

    public void displayPrivateKey() {
        // Affichage du vecteur généré
        int[] vectS = this.privateKeyGen();
        System.out.print("Vecteur s = {");
        for (int i = 0; i < Parameters.Theta; i++) {
            System.out.print(vectS[i]);
            if (i < Parameters.Theta - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("}");
    }

    public int[] getSetS() {
        return this.setS;
    }

    public BigInteger[] publicKeyGen() {
        BigInteger[] u = new BigInteger[Parameters.Theta];
        Random random = new Random();

        BigInteger lowerBound = BigInteger.ZERO;
        BigInteger upperBound = BigInteger.valueOf(2).pow(Parameters.KEPPA + 1); 
     
        BigInteger sumUi;

        do {
            sumUi = BigInteger.ZERO;
            for(int i = 0; i < Parameters.Theta; i++ ) {
                do {
                    u[i] = new BigInteger(upperBound.subtract(lowerBound).bitLength(), random);
                } while (u[i].compareTo(lowerBound) < 0 || u[i].compareTo(upperBound) >= 0);
            }
    
            for (int j : this.setS) {
                sumUi = sumUi.add(u[j]);
            }
            System.out.println("pas encore");
        } while (!sumUi.equals((this.Xp).mod(upperBound)));

        BigInteger[] vectY = new BigInteger[Parameters.Theta];

        for(int i = 0; i < Parameters.Theta; i++ ) {
            vectY[i] = u[i].divide(BigInteger.valueOf(2).pow(Parameters.KEPPA));  
        }

        return u;
    }

    
    
}    