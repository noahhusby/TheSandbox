package com.husbylabs;

import javax.swing.*;

public class Main {

    static int inputArrayValue = 0;
    static boolean finishedArrayValue = false;
    static double[] arr;

    static int currentPercent = 0; //Current round of inclusion
    static double first = -1000000000; //First calculation = Low number for science
    static boolean firstFinished = true; //Confirms first round completed
    static double firPer;

    public static void main(String[] args) {
        arr = new double[10];
        while(!finishedArrayValue) {
            String input = JOptionPane.showInputDialog(null,"Enter Number, or 'done' to continue");
            if (input.equals("done") || inputArrayValue > 9) {
                finishedArrayValue = true;
            } else {
                arr[inputArrayValue] = Double.parseDouble(input);
                inputArrayValue++;
            }
        }
        findOutlierArrry();
    }

    public static void findOutlierArrry() {
        double org = 0;
        double fin; //Recalculated Average
        for (int i = 0; i < inputArrayValue; i++) {
            org+=arr[i];
        }
        org = org/inputArrayValue;
        double percentOf = (org*(currentPercent/100.0f));  //Finds [x] percent of the normal average, x increases by 1 until final calculation can be solved
        int dB = 0; //Amount of values included in final calculation
        double base = 0; //Total value of added numbers divided by [dB]

        for (int i = 0; i < inputArrayValue; i++) {
            if (arr[i] < org - percentOf || arr[i] > org+percentOf) {

            } else {
                dB++;
                base+=arr[i];
            }
        }

        fin=base/dB;
        if(Double.isNaN(fin)) {
            currentPercent++;
            findOutlierArrry();
        }
        else {
            if(firstFinished) {
                first = fin;
                firPer = currentPercent;
                firstFinished = false;
            }
            if(first == fin) {
                currentPercent++;
                if(currentPercent > 99) {
                    first = fin+1;
                }
                findOutlierArrry();
            } else {
                System.out.println("Outlier Detection Program - Husby Labs");
                System.out.println("--------------------------------------");
                System.out.println("Raw Output: " + org);
                System.out.println("Calculated Raw Percentile: "+percentOf);
                System.out.println("First Calculation: " + first+" | " + firPer+"%");
                System.out.println("\033[92mFinal Calculation: " + fin+" | "+currentPercent+"%");
                if(firPer > 84) {
                    System.out.println("\033[31mWarning! Outlier % is high, numbers may be unrelated.");
                }
            }
        }

    }
}

