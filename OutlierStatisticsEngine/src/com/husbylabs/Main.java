package com.husbylabs;

import javax.swing.*;

public class Main {

    static double outPer = 0;
    static double first = -1000000000;
    static boolean firstFinished = true;
    static double firPer;
    public static void main(String[] args) {
        double a = Double.parseDouble(JOptionPane.showInputDialog(null,"Enter A"));
        double b = Double.parseDouble(JOptionPane.showInputDialog(null,"Enter B"));
        double c = Double.parseDouble(JOptionPane.showInputDialog(null,"Enter C"));
        outlier(a,b,c);
    }


    public static void outlier(double a, double b, double c) {

            double org; //Normal Average
            double fin; //Recalculated Average
            boolean aDis = false; //Is [a] disabled
            boolean bDis = false; //Is [b] disabled
            boolean cDis = false; //Is [c] disabled

            org = (a+b+c)/3; //Finds normal average

            double percentOf = (org*(outPer/100.0f));  //Finds [x] percent of the normal average, x increases by 1 until final calculation can be solved

            int dB = 0; //Amount of values included in final calculation
            double base = 0; //Total value of added numbers divided by [dB]

        /*
         * Checks if given value falls in range calculated by current percent or [round]
         */
        if(a < org - percentOf || a > org + percentOf) {
                aDis = true;
            }
            else {
                dB++;
                base = base + a;
            }
            if(b < org - percentOf || b > org + percentOf) {
                bDis = true;
            }
            else {
                dB++;
                base = base + b;
            }
            if(c < org - percentOf || c > org + percentOf) {
                cDis = true;
            }
            else {
                dB++;
                base = base + c;
            }

            //Calculates final value
            fin = base/dB;

            /*
             * Checks if final value is valid. If not, outlier allowance percent is increased by one and repeated
             */
            if(Double.isNaN(fin)) {
                outPer++;
                outlier(a,b,c);
            }
            else {
                if(firstFinished) {
                    first = fin;
                    firPer = outPer;
                    firstFinished = false;
                }
                if(first == fin) {
                    outPer++;
                    if(outPer > 99) {
                        first = fin+1;
                    }
                    outlier(a,b,c);
                } else {
                    System.out.println("Outlier Detection Program - Husby Labs");
                    System.out.println("--------------------------------------");
                    System.out.println("Raw Output: " + org);
                    System.out.println("Calculated Raw Percentile: "+percentOf);
                    System.out.println("A Removed: "+aDis);
                    System.out.println("B Removed: " +bDis);
                    System.out.println("C Removed: " +cDis);
                    System.out.println("First Calculation: " + first+" | " + firPer+"%");
                    System.out.println("\033[92mFinal Calculation: " + fin+" | "+outPer+"%");
                    if(firPer > 84) {
                        System.out.println("\033[31mWarning! Outlier % is high, numbers may be unrelated.");
                    }
                }
            }
        }
    }

