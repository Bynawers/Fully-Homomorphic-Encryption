package com;

import java.util.Random;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class KeygenComplHomo {

    private BigInteger Xp;
    private List<Integer> setS;

    private BigDecimal[] publicKey;
    private int[] privateKey;
    private BigInteger privateKey_p;

    public KeygenComplHomo(BigInteger privatekey_p) {
        this.privateKey_p = privatekey_p;
        this.Xp = ((BigInteger.valueOf(2).pow(Parameters.KEPPA)).divide(privatekey_p)).add(BigInteger.ONE);
        this.setS = new ArrayList();   

        this.privateKey = privateKeyGen();
        this.publicKey = publicKeyGen();
    }

    public BigInteger getPrivateKey_p() {
        return this.privateKey_p;
    }

    public int[] getPrivateKey() {
        return this.privateKey;
    }

    public BigDecimal[] getPublickey() {
        return this.publicKey;
    }
    

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
            this.setS.add(index);
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

    public List<Integer> getSetS() {
        return this.setS;
    }

    public BigDecimal[] publicKeyGen() {
        BigInteger[] u = new BigInteger[Parameters.Theta];
        Random random = new Random();

        BigInteger lowerBound = BigInteger.ZERO;
        BigInteger upperBound = BigInteger.valueOf(2).pow(Parameters.KEPPA + 1); 
     
        BigInteger sumUi;

        sumUi = BigInteger.ZERO;
        for(int i = 0; i < Parameters.Theta; i++ ) {
            do {
                u[i] = new BigInteger(upperBound.subtract(lowerBound).bitLength(), random);
            } while (u[i].compareTo(lowerBound) < 0 || u[i].compareTo(upperBound) >= 0);
        } 
    
        BigInteger z = BigInteger.ZERO;
        for (int j : this.setS) {
            sumUi = sumUi.add(u[j]);
            z = u[j];
        }

        while(!(this.Xp).equals(sumUi.mod(upperBound))) {
            sumUi = sumUi.subtract(z); 
            BigInteger l = (sumUi.divide(upperBound)).add(BigInteger.ONE);
            z = (l.multiply(upperBound)).subtract(sumUi);
            z = z.add(this.Xp);
            sumUi = sumUi.add(z);
        }

        Integer precision = Parameters.KEPPA;
            
        BigDecimal[] vectY = new BigDecimal[Parameters.Theta];

        BigDecimal dum  = BigDecimal.ZERO;

        for(int i = 0; i < Parameters.Theta; i++ ) {
            
            vectY[i] = new BigDecimal(u[i]).divide(new BigDecimal(2).pow(Parameters.KEPPA));    
            vectY[i] = vectY[i].setScale(precision, BigDecimal.ROUND_DOWN);
            //System.out.println(vectY[i]);
        }

        return vectY;
    }

    
    
}     
