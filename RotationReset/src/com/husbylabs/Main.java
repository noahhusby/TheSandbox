package com.husbylabs;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        double angle = Double.parseDouble(JOptionPane.showInputDialog("Enter an angle"));
        double calculation = angle/360;
        int finalInt = (int)Math.floor(calculation);
        double remove = 360 * finalInt;
        double finalCalc = angle - remove;
        System.out.println("Primed angle:" + finalCalc);
    }
}
